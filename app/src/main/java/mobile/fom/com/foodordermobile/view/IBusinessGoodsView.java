package mobile.fom.com.foodordermobile.view;

public interface IBusinessGoodsView {

    /**
     * 修改成功
     *
     * @param msg
     */
    void changeSuccess(final String msg);

    /**
     * 修改失败
     *
     * @param msg 失败信息
     */
    void changeFailed(final String msg);

}
