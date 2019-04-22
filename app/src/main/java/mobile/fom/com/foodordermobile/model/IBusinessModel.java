package mobile.fom.com.foodordermobile.model;

import mobile.fom.com.foodordermobile.bean.Business;

public interface IBusinessModel {
    /**
     * 获取商家列表
     *
     * @param callBack
     */
    void findBusiness(final IModelCallBack callBack);

    /**
     * 注册
     *
     * @param business 注册的business，封装在bean里
     * @param callBack
     */
    void register(Business business, final IModelCallBack callBack);

    /**
     * 登录
     *
     * @param b_id     账号
     * @param password 密码
     */
    void login(String b_id, String password, final IModelCallBack callBack);

    /**
     * 获取历史订单
     *
     * @param b_id
     * @param callBack
     */
    void getOldOrder(String b_id, final IModelCallBack callBack);

    /**
     * 获取新订单
     *
     * @param b_id
     * @param callBack
     */
    void getNewOrder(String b_id, final IModelCallBack callBack);

    /**
     * 添加商品
     *
     * @param b_id
     * @param g_name
     * @param g_price
     * @param g_other
     * @param callBack
     */
    void addGoods(String b_id, String g_name, String g_price, String g_other, final IModelCallBack callBack);

    /**
     * 获取商家所有商品
     *
     * @param b_id
     * @param callBack
     */
    void getAllGoods(String b_id, final IModelCallBack callBack);

}
