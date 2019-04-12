package mobile.fom.com.foodordermobile.view;

import mobile.fom.com.foodordermobile.bean.User;

public interface IUserLoginRegisterView {
    /**
     * 登录成功
     *
     * @param user 返回用户信息
     */
    void loginSuccess(User user);

    /**
     * 登录失败
     */
    void loginFailed(String msg);

    /**
     * 注册成功
     */
    void registerSuccess();

    /**
     * 注册失败
     *
     * @param msg 失败原因
     */
    void registerFailed(String msg);

    /**
     * 设置checkbox，账号密码，状态
     *
     * @param remember   记住密码
     * @param auto_login 自动登录
     * @param account    账号
     * @param password   密码
     */
    void setCheckBoxState(boolean remember, boolean auto_login, String account, String password);

}