package mobile.fom.com.foodordermobile.view;

import java.util.List;

import mobile.fom.com.foodordermobile.bean.OrderItem;

public interface IOrderView {
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

    void setUserName(String name);

    void setOrderItem(List<OrderItem> list);
}
