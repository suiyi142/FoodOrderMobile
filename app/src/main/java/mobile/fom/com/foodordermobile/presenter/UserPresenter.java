package mobile.fom.com.foodordermobile.presenter;

import com.google.gson.Gson;

import java.util.HashMap;

import mobile.fom.com.foodordermobile.bean.User;
import mobile.fom.com.foodordermobile.constant.FoodOrderConstant;
import mobile.fom.com.foodordermobile.model.IModelCallBack;
import mobile.fom.com.foodordermobile.model.IUserModel;
import mobile.fom.com.foodordermobile.model.UserModel;
import mobile.fom.com.foodordermobile.view.IUserLoginRegisterView;

public class UserPresenter {
    private IUserModel mUserModel;
    private IUserLoginRegisterView mUserLoginRegisterView;

    public UserPresenter(IUserLoginRegisterView mUserLoginRegisterView) {
        this.mUserLoginRegisterView = mUserLoginRegisterView;
        getUserModel();
    }

    /**
     * 登录
     *
     * @param account  账号
     * @param password 密码
     */
    public void login(String account, String password) {
        mUserModel.login(account, password, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("0"))
                    mUserLoginRegisterView.loginFailed("登录错误");
                else {
                    Gson gson = new Gson();
                    User user = gson.fromJson(msg, User.class);
                    mUserLoginRegisterView.loginSuccess(user);
                }
            }

            @Override
            public void onFailure(String msg) {
                mUserLoginRegisterView.loginFailed(msg);
            }
        });
    }

    /**
     * 注册
     *
     * @param account  账号
     * @param name     姓名
     * @param password 密码
     */
    public void register(String account, String name, String password) {
        User user = new User(account, name, password);
        mUserModel.register(user, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1"))
                    mUserLoginRegisterView.registerSuccess();
                else
                    mUserLoginRegisterView.registerFailed(msg);
            }

            @Override
            public void onFailure(String msg) {
                mUserLoginRegisterView.registerFailed(msg);
            }
        });
    }

    /**
     * 保存登录状态
     *
     * @param remember  记住密码
     * @param autoLogin 自动登录
     * @param account   账号
     * @param password  密码
     */
    public void saveLoginState(boolean remember, boolean autoLogin, String account, String password) {
        mUserModel.saveLoginState(remember, autoLogin, account, password);
    }

    /**
     * 获取登录状态
     */
    public void getLoginState() {
        HashMap<String, Object> map = mUserModel.getLoginState();
        boolean remember = (boolean) map.get(FoodOrderConstant.REMEMBER_PASSWORD_STATE);
        boolean auto_login = (boolean) map.get(FoodOrderConstant.AUTO_LOGIN_STATE);
        String account = (String) map.get(FoodOrderConstant.REMEMBER_ACCOUNT);
        String password = (String) map.get(FoodOrderConstant.REMEMBER_PASSWORD);
        mUserLoginRegisterView.setCheckBoxState(remember, auto_login, account, password);
    }

    /*
    懒汉式创建model
     */
    private void getUserModel() {
        if (mUserModel == null)
            mUserModel = new UserModel();
    }
}
