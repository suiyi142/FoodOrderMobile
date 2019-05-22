package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.EditTextUtil;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessChangePasswordView;

public class BusinessChangePasswordActivity extends AppCompatActivity implements IBusinessChangePasswordView {

    private String b_id;
    private EditText et_address;
    private EditText et_password;
    private EditText et_repassword;
    private List<EditText> editTextList;
    private BusinessPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_change_password);
        presenter = new BusinessPresenter(this);
        initData();
        initView();
    }

    private void initView() {
        et_address = findViewById(R.id.et_change_business_address);
        et_password = findViewById(R.id.et_change_business_password);
        et_repassword = findViewById(R.id.et_change_business_repassword);
        editTextList = new ArrayList<>();
        editTextList.add(et_address);
        editTextList.add(et_password);
        editTextList.add(et_repassword);
    }

    private void initData() {
        b_id = getIntent().getStringExtra("b_id");
    }

    public static void startActivity(Context context, String b_id) {
        Intent intent = new Intent(context, BusinessChangePasswordActivity.class);
        intent.putExtra("b_id", b_id);
        context.startActivity(intent);
    }

    /**
     * 按钮点击事件
     */
    public void changeClick(View view) {
        if (EditTextUtil.isEmpty(editTextList)) {
            ToastUtil.showToast(this, "有值为空哦");
            return;
        }
        if (!et_password.getText().toString().trim().equals(et_repassword.getText().toString().trim())) {
            ToastUtil.showToast(this, "两次输入密码不一致哦");
            return;
        }
        presenter.changePassword(b_id, et_address.getText().toString().trim(), et_password.getText().toString().trim());


    }

    //---------------------------------presenter回调--------------
    @Override
    public void changeSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessChangePasswordActivity.this, "修改成功，去登陆吧");
                finish();
            }
        });
    }

    @Override
    public void changeFailed(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessChangePasswordActivity.this, msg);
                finish();
            }
        });
    }
}
