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
    void loginFailed();

    /**
     * 注册成功
     */
    void registerSuccess();

    /**
     * 注册失败
     *
     * @param msg 失败原因
     */
    void registerFailer(String msg);

}