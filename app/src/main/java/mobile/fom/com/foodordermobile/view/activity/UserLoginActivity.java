package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.App;
import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.User;
import mobile.fom.com.foodordermobile.presenter.UserPresenter;
import mobile.fom.com.foodordermobile.util.EditTextUtil;
import mobile.fom.com.foodordermobile.util.SpUtil;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IUserLoginRegisterView;

public class UserLoginActivity extends App implements IUserLoginRegisterView, View.OnClickListener {

    private static final String TAG = "UserLoginActivity";
    private UserPresenter presenter;

    private final static int LOGIN = 0;
    private final static int REGISTER = 1;
    private static int PAGE_TAG = 0;
    private int BACK_TAG = 0;

    private List<EditText> registerList;
    private List<EditText> loginList;

    private ImageView iv_back;
    private TextView tv_toUserRegister;
    private EditText et_user_login_account;
    private EditText et_user_login_password;
    private Button bt_user_login;
    private CheckBox cb_user_remember;
    private CheckBox cb_user_autoLogin;
    private EditText et_user_register_account;
    private EditText et_user_register_name;
    private EditText et_user_register_password;
    private EditText et_user_register_re_password;
    private Button bt_user_register;
    private View v_user_login;
    private View v_user_register;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login_and_register);
        presenter = new UserPresenter(this);
        sp = SpUtil.getSharedPreperence(this);
        initView();
        presenter.getLoginState();


    }

    /*
    找到相关控件
     */
    private void initView() {
        loginList = new ArrayList<>();
        registerList = new ArrayList<>();
        //-----tittle的控件
        iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_toUserRegister = findViewById(R.id.tv_toUserRegister);
        tv_toUserRegister.setOnClickListener(this);
        //----login的控件
        v_user_login = findViewById(R.id.fragment_user_login);
        et_user_login_account = findViewById(R.id.et_user_login_account);
        loginList.add(et_user_login_account);
        et_user_login_password = findViewById(R.id.et_user_login_password);
        loginList.add(et_user_login_password);
        bt_user_login = findViewById(R.id.bt_user_login);
        bt_user_login.setOnClickListener(this);
        cb_user_remember = findViewById(R.id.cb_user_remember);
        cb_user_autoLogin = findViewById(R.id.cb_user_autoLogin);
        //设置checkedButton


        //----register的控件
        v_user_register = findViewById(R.id.fragment_user_register);
        et_user_register_account = findViewById(R.id.et_user_register_account);
        registerList.add(et_user_register_account);
        et_user_register_name = findViewById(R.id.et_user_register_name);
        registerList.add(et_user_register_name);
        et_user_register_password = findViewById(R.id.et_user_register_password);
        registerList.add(et_user_register_password);
        et_user_register_re_password = findViewById(R.id.et_user_register_re_password);
        registerList.add(et_user_register_re_password);
        bt_user_register = findViewById(R.id.bt_user_register);
        bt_user_register.setOnClickListener(this);


    }


    protected static void startActivity(Context context) {
        context.startActivity(new Intent(context, UserLoginActivity.class));
    }

    //各个按钮点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                //返回按钮点击
                toLogin();
                break;
            case R.id.tv_toUserRegister:
                //去注册按钮点击
                toRegister();
                break;
            case R.id.bt_user_login:
                //登录按钮点击
                if (EditTextUtil.isEmpty(loginList)) {
                    ToastUtil.showToast(this, "账号或密码为空");
                    break;
                }
                String login_account = et_user_login_account.getText().toString().trim();
                String login_password = et_user_login_password.getText().toString().trim();
                presenter.login(login_account, login_password);
                break;
            case R.id.bt_user_register:
                //注册按钮点击
                if (EditTextUtil.isEmpty(registerList)) {
                    ToastUtil.showToast(this, "有值为空哦");
                    break;
                }
                String register_password = et_user_register_password.getText().toString().trim();
                String register_re_password = et_user_register_re_password.getText().toString().trim();
                if (!register_password.equals(register_re_password)) {
                    ToastUtil.showToast(this, "两次输入密码不一致");
                    break;
                }

                String register_account = et_user_register_account.getText().toString().trim();
                String name = et_user_register_name.getText().toString().trim();
                presenter.register(register_account, name, register_password);
                break;
        }

    }

    //切换到register界面
    private void toRegister() {
        PAGE_TAG = REGISTER;
        Animation anim = AnimationUtils.loadAnimation(UserLoginActivity.this, R.anim.fromrightout);
        anim.setAnimationListener(new Animation.AnimationListener() {

            private Animation anim1;

            @Override
            public void onAnimationStart(Animation animation) {
                anim1 = AnimationUtils.loadAnimation(UserLoginActivity.this, R.anim.fromupin);
                iv_back.setVisibility(View.VISIBLE);
                v_user_login.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v_user_register.setVisibility(View.VISIBLE);
                v_user_register.startAnimation(anim1);
                iv_back.setVisibility(View.VISIBLE);
                tv_toUserRegister.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        v_user_login.startAnimation(anim);
    }

    //切换到登录页面
    private void toLogin() {
        iv_back.setVisibility(View.INVISIBLE);
        PAGE_TAG = LOGIN;
        Animation anim = AnimationUtils.loadAnimation(UserLoginActivity.this, R.anim.fromdownout);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                v_user_register.setVisibility(View.INVISIBLE);
                Animation anim1 = AnimationUtils.loadAnimation(UserLoginActivity.this, R.anim.fromleftin);
                v_user_login.setVisibility(View.VISIBLE);
                v_user_login.startAnimation(anim1);
                iv_back.setVisibility(View.GONE);
                tv_toUserRegister.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        v_user_register.startAnimation(anim);

    }

    //返回键按下时如果在注册页面就返回登录，否则执行系统返回
    @Override
    public void onBackPressed() {
        if (PAGE_TAG == REGISTER)
            toLogin();
        else if (BACK_TAG == 0) {
            ToastUtil.showToast(this, "再次点击退出");
            BACK_TAG++;
        } else
            super.onBackPressed();
    }

    //------------------------响应业务逻辑------------------------------------

    /**
     * 登录成功，保存login状态
     *
     * @param user 返回用户信息
     */
    @Override
    public void loginSuccess(final User user) {
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                ToastUtil.showToast(UserLoginActivity.this, user.toString());
                presenter.saveLoginState(cb_user_remember.isChecked()
                        , cb_user_autoLogin.isChecked()
                        , et_user_login_account.getText().toString().trim()
                        , et_user_login_password.getText().toString().trim());


            }
        });
    }

    @Override
    public void loginFailed(final String msg) {
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                ToastUtil.showToast(UserLoginActivity.this, msg);
            }
        });
    }

    @Override
    public void registerSuccess() {
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                toLogin();
                et_user_login_account.setText(et_user_register_account.getText().toString().trim());
                et_user_login_password.setText(et_user_register_password.getText().toString().trim());
                ToastUtil.showToast(UserLoginActivity.this, "注册成功，登录吧");
            }
        });
    }

    @Override
    public void registerFailed(final String msg) {
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                ToastUtil.showToast(UserLoginActivity.this, msg);
            }
        });
    }


    @Override
    public void setCheckBoxState(boolean remember, boolean auto_login, String account, String password) {
        cb_user_autoLogin.setChecked(auto_login);
        cb_user_remember.setChecked(remember);
        et_user_login_account.setText(account);
        if (remember)
            et_user_login_password.setText(password);
        if (auto_login){
            String login_account = et_user_login_account.getText().toString().trim();
            String login_password = et_user_login_password.getText().toString().trim();
            presenter.login(login_account, login_password);
        }
    }

}
