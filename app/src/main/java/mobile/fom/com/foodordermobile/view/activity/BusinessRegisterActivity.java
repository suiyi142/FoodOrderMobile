package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessRegisterView;

public class BusinessRegisterActivity extends AppCompatActivity implements View.OnClickListener, IBusinessRegisterView {

    private static final String TAG = "BusinessRegister";

    private EditText et_register_business_address;
    private EditText et_register_business_password;
    private EditText et_register_business_re_password;
    private EditText et_register_business_max_seats;
    private EditText et_register_business_other;
    private Button bt_register_business;
    private List<EditText> editTextList;
    private BusinessPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_register);
        initView();
        presenter = new BusinessPresenter(this);

    }

    /*
        找到相关控件
    */
    private void initView() {
        et_register_business_address = findViewById(R.id.et_register_business_address);
        et_register_business_password = findViewById(R.id.et_register_business_password);
        et_register_business_re_password = findViewById(R.id.et_register_business_re_password);
        et_register_business_max_seats = findViewById(R.id.et_register_business_max_seats);
        et_register_business_other = findViewById(R.id.et_register_business_other);
        bt_register_business = findViewById(R.id.bt_register_business);
        //将editText添加到一个list里
        editTextList = new ArrayList<>();
        editTextList.add(et_register_business_address);
        editTextList.add(et_register_business_password);
        editTextList.add(et_register_business_re_password);
        editTextList.add(et_register_business_max_seats);
        editTextList.add(et_register_business_other);
        bt_register_business.setOnClickListener(this);

    }

    /*
    开启这个activity
     */
    protected static void startActivity(Context context) {
        context.startActivity(new Intent(context, BusinessRegisterActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (isEmpty(editTextList)) {
            ToastUtil.showToast(this, "有东西没有填哦");
            return;
        }
        String password = et_register_business_password.getText().toString().trim();
        String re_password = et_register_business_re_password.getText().toString().trim();
        Log.i(TAG,et_register_business_max_seats.getText().toString().trim());
        if (!password.equals(re_password)) {
            ToastUtil.showToast(this, "两次密码不一样哦");
            return;
        }
        Business business = new Business(null, et_register_business_address.getText().toString().trim(),
                password,
                0,
                Integer.parseInt(et_register_business_max_seats.getText().toString().trim()),
                et_register_business_other.getText().toString().trim());
        Log.i(TAG, business.toString());
        presenter.businessRegister(business);
    }

    /*
    判断有没有没输入的内容
     */
    private boolean isEmpty(List<EditText> editTextList) {
        boolean b;
        for (EditText editText : editTextList) {
            b = TextUtils.isEmpty(editText.getText().toString().trim());
            if (b)
                return true;
        }
        return false;
    }


    @Override
    public void registerSuccess() {
        runOnUiThread(new Thread(){
            @Override
            public void run() {
                ToastUtil.showToast(BusinessRegisterActivity.this,"注册成功，去登陆吧");
                BusinessRegisterActivity.this.finish();
            }
        });
    }

    @Override
    public void registerFailed(final String msg) {
        runOnUiThread(new Thread(){
            @Override
            public void run() {
                ToastUtil.showToast(BusinessRegisterActivity.this,msg);
                Log.i(TAG,msg);
            }
        });
    }
}
