package mobile.fom.com.foodordermobile.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.App;
import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessLoginView;

public class BusinessLoginActivity extends App implements IBusinessLoginView, View.OnClickListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "BusinessLogin";
    private BusinessPresenter presenter;

    private ProgressDialog progressDialog;
    private ListView lv_login_business;
    private Button bt_to_business_register;
    private TextView tv_business_null;
    private ArrayList<Business> list;
    private ArrayList<String> businessNamesList;
    private AlertDialog passwordDialog;
    private EditText et_login_business_password;
    private Button bt_login_business;

    private int selectBusiness;
    private SwipeRefreshLayout srl_business_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);
        presenter = new BusinessPresenter(this);
        initView();
        initProgressDialog();
        initPasswordDialog();
        presenter.findBusiness();

    }

    /*
    初始化输入密码对话框
     */
    private void initPasswordDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_password, null);
        et_login_business_password = view.findViewById(R.id.et_login_business_password);
        bt_login_business = view.findViewById(R.id.bt_login_business);
        passwordDialog = new AlertDialog.Builder(this).setView(view).create();
        bt_login_business.setOnClickListener(this);
    }

    /*
    找到相关控件
     */
    private void initView() {
        lv_login_business = findViewById(R.id.lv_login_business);
        bt_to_business_register = findViewById(R.id.bt_to_business_register);
        tv_business_null = findViewById(R.id.tv_business_null);
        bt_to_business_register.setOnClickListener(this);
        srl_business_login = findViewById(R.id.srl_business_login);
        srl_business_login.setOnRefreshListener(this);
    }

    /*
    初始化progressDialog
     */
    private void initProgressDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("加载中");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    /*
    开启这个activity
     */
    protected static void startActivity(Context context) {
        context.startActivity(new Intent(context, BusinessLoginActivity.class));
    }


    @Override
    public void setBusinessList(ArrayList<Business> list) {
        this.list = list;
        businessNamesList = new ArrayList<>();
        for (Business business : list) {
            businessNamesList.add(business.getAddress());
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(BusinessLoginActivity.this, android.R.layout.simple_list_item_1, businessNamesList);
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                if (srl_business_login.isRefreshing())
                    srl_business_login.setRefreshing(false);
                progressDialog.dismiss();
                lv_login_business.setVisibility(View.VISIBLE);
                lv_login_business.setAdapter(adapter);
                lv_login_business.setOnItemClickListener(BusinessLoginActivity.this);

            }
        });
    }


    @Override
    public void setFailedMsg(final String msg) {
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                progressDialog.dismiss();
                tv_business_null.setText("点击重试");
                tv_business_null.setVisibility(View.VISIBLE);
                tv_business_null.setOnClickListener(BusinessLoginActivity.this);
                ToastUtil.showToast(BusinessLoginActivity.this, msg);
            }
        });
    }

    @Override
    public void BusinessZero() {
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                String text = "莫得商家,点击重试";
                SpannableString span = new SpannableString(text);
                span.setSpan(new UnderlineSpan(), 7, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                tv_business_null.setText(span);
                tv_business_null.setVisibility(View.VISIBLE);
                tv_business_null.setOnClickListener(BusinessLoginActivity.this);
            }
        });
    }

    @Override
    public void toBusiness(final Business business) {
        progressDialog.dismiss();
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                passwordDialog.dismiss();
                ToastUtil.showToast(BusinessLoginActivity.this, "登陆成功");
                Log.i(TAG, business.toString());
                BusinessActivity.startActivity(BusinessLoginActivity.this, business);
                BusinessLoginActivity.this.finish();
            }
        });

    }

    @Override
    public void showLoginErrorMsg(final String msg) {
        progressDialog.dismiss();
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessLoginActivity.this, msg);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_business_null:
                presenter.findBusiness();
                progressDialog.show();
                tv_business_null.setVisibility(View.GONE);
                break;
            case R.id.bt_to_business_register:
                BusinessRegisterActivity.startActivity(this);
                break;
            case R.id.bt_login_business:
                progressDialog.show();
                String b_id = list.get(selectBusiness).getB_id();
                String password = et_login_business_password.getText().toString().trim();
                if (TextUtils.isEmpty(b_id) || TextUtils.isEmpty(password)) {
                    ToastUtil.showToast(this, "密码或账号为空");
                    break;
                }
                presenter.businessLogin(b_id, password);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        selectBusiness = i;
        passwordDialog.show();
    }

    /*
    下拉刷新监听
     */
    @Override
    public void onRefresh() {
        presenter.findBusiness();
    }
}
