package mobile.fom.com.foodordermobile.view;

public interface IBusinessRegisterView {

    /**
     * 注册成功
     */
    void registerSuccess();

    /**
     * 注册失败
     * @param msg 失败原因
     */
    void registerFailed(String msg);
}
