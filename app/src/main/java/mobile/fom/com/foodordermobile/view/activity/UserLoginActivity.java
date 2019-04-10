package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.User;
import mobile.fom.com.foodordermobile.view.IUserLoginRegisterView;

public class UserLoginActivity extends AppCompatActivity implements IUserLoginRegisterView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_and_register);
        initView();
    }

    /*
    找到相关控件
     */
    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        TextView tv_toUserRegister = findViewById(R.id.tv_toUserRegister);
        EditText et_user_login_account = findViewById(R.id.et_user_login_account);
        EditText et_user_login_password = findViewById(R.id.et_user_login_password);
        Button bt_user_login = findViewById(R.id.bt_user_login);


    }



    protected static void startActivity(Context context){
        context.startActivity(new Intent(context,UserLoginActivity.class));
    }


    //------------------------响应业务逻辑------------------------------------
    @Override
    public void loginSuccess(User user) {

    }

    @Override
    public void loginFailed() {

    }

    @Override
    public void registerSuccess() {

    }

    @Override
    public void registerFailer(String msg) {

    }
}
