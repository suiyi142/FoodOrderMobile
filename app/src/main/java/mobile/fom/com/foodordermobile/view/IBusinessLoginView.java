package mobile.fom.com.foodordermobile.view;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.bean.Business;

public interface IBusinessLoginView {

    /**
     * 显示列表,异步
     *
     * @param list 商家列表
     */
    void setBusinessList(ArrayList<Business> list);

    /**
     * 显示错误信息，异步
     *
     * @param msg 错误信息
     */
    void setFailedMsg(String msg);

    /**
     * 没有商家的情况
     */
    void BusinessZero();

    /**
     * 登录成功，去Business界面
     */
    void toBusiness(Business business);

    /**
     * 登录失败
     */
    void showLoginErrorMsg(String b_id, String msg);

}
