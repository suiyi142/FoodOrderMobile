package mobile.fom.com.foodordermobile.view;

public interface IUserChangePasswordView {
    /**
     * 修改成功
     */
    void changeSuccess();

    /**
     * 修改失败
     *
     * @param msg 失败原因
     */
    void changeFailed(String msg);
}
