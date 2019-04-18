package mobile.fom.com.foodordermobile.view;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.bean.Business;

public interface IUserView {
    /**
     * 设置user的商家列表
     *
     * @param businessList
     */
    void setBusinessList(ArrayList<Business> businessList);

    /**
     * 展示错误信息
     *
     * @param msg 错误信息
     */
    void showGetBusinessFailed(String msg);
}
