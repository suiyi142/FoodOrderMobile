package mobile.fom.com.foodordermobile.model;

import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Goods;

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

    /**
     * 修改商品
     *
     * @param goods
     * @param callBack
     */
    void updateGoods(Goods goods, final IModelCallBack callBack);

    /**
     * 删除商品
     *
     * @param g_id
     * @param callBack
     */
    void deleteGoods(String g_id, final IModelCallBack callBack);

    /**
     * 接单
     *
     * @param o_id
     * @param callBack
     */
    void receiptOrder(String o_id, final IModelCallBack callBack);

    /**
     * 拒单
     *
     * @param o_id
     * @param callBack
     */
    void refuseOrder(String o_id, final IModelCallBack callBack);

    /**
     * 核销
     *
     * @param o_id
     * @param callBack
     */
    void usedOrder(String o_id, final IModelCallBack callBack);

    /**
     * 获取用户姓名
     *
     * @param u_id
     * @param callBack
     */
    void getUserName(String u_id, final IModelCallBack callBack);

    /**
     * 获取消费列表
     *
     * @param o_id
     * @param callBack
     */
    void getOrderItem(String o_id, final IModelCallBack callBack);

}
