package mobile.fom.com.foodordermobile.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessLoginView;

public class BusinessLoginActivity extends AppCompatActivity implements IBusinessLoginView, View.OnClickListener {

    private BusinessPresenter presenter;

    private ProgressDialog progressDialog;
    private ListView lv_login_business;
    private Button bt_to_business_register;
    private TextView tv_business_null;
    private ArrayList<Business> list;
    private ArrayList<String> businessNamesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);
        presenter = new BusinessPresenter(this);
        initView();
        initProgressDialog();
        presenter.findBusiness();

    }

    /*
    找到相关控件
     */
    private void initView() {
        lv_login_business = findViewById(R.id.lv_login_business);
        bt_to_business_register = findViewById(R.id.bt_to_business_register);
        tv_business_null = findViewById(R.id.tv_business_null);
        bt_to_business_register.setOnClickListener(this);
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
                progressDialog.dismiss();
                lv_login_business.setVisibility(View.VISIBLE);
                lv_login_business.setAdapter(adapter);

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
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
