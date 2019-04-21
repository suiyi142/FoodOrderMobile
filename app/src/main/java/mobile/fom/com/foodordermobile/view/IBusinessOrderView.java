package mobile.fom.com.foodordermobile.view;

import java.util.List;

import mobile.fom.com.foodordermobile.bean.Order;

public interface IBusinessOrderView {
    /**
     * 展示接收到的订单
     *
     * @param orderList
     */
    void showOrder(List<Order> orderList);

    /**
     * 展示错误信息
     *
     * @param msg
     */
    void showError(String msg);
}
