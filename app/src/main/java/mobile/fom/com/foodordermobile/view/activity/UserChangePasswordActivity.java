package mobile.fom.com.foodordermobile.view.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.presenter.UserPresenter;
import mobile.fom.com.foodordermobile.util.EditTextUtil;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IUserChangePasswordView;

public class UserChangePasswordActivity extends AppCompatActivity implements IUserChangePasswordView {

    private EditText et_account;
    private EditText et_name;
    private EditText et_password;
    private EditText et_repassword;
    private List<EditText> editTextList;
    private UserPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_change_password);
        presenter = new UserPresenter(this);
        initView();
    }

    private void initView() {
        et_account = findViewById(R.id.et_change_user_account);
        et_name = findViewById(R.id.et_change_user_name);
        et_password = findViewById(R.id.et_change_user_password);
        et_repassword = findViewById(R.id.et_change_user_repassword);
        editTextList = new ArrayList<>();
        editTextList.add(et_account);
        editTextList.add(et_name);
        editTextList.add(et_password);
        editTextList.add(et_repassword);
        progressDialog = new ProgressDialog(this);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, UserChangePasswordActivity.class);
        context.startActivity(intent);
    }

    public void changeClick(View view) {
        if (EditTextUtil.isEmpty(editTextList)) {
            ToastUtil.showToast(this, "有值为空哦");
            return;
        }
        if (!et_password.getText().toString().trim().equals(et_repassword.getText().toString().trim())) {
            ToastUtil.showToast(this, "两次输入密码不一致哦");
            return;
        }
        progressDialog.show();
        presenter.changePassword(et_account.getText().toString().trim()
                , et_name.getText().toString().trim()
                , et_password.getText().toString().trim());

    }

    //-----------------------presenter回调-----------------
    @Override
    public void changeSuccess() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(UserChangePasswordActivity.this, "修改成功，去登陆吧");
                progressDialog.dismiss();
                finish();
            }
        });
    }

    @Override
    public void changeFailed(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(UserChangePasswordActivity.this, msg);
                progressDialog.dismiss();
            }
        });
    }
}
