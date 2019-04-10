package mobile.fom.com.foodordermobile.model;

import mobile.fom.com.foodordermobile.bean.Business;

public interface IBusinessModel {
    /**
     * 获取商家列表
     *
     * @param callBack
     */
    void findBusiness(ICallBack callBack);

    /**
     * 注册
     * @param business 注册的business，封装在bean里
     * @param callBack
     */
    void register(Business business, ICallBack callBack);

    /**
     * 登录
     * @param b_id 账号
     * @param password 密码
     */
    void login(String b_id, String password,ICallBack callBack);

    interface ICallBack {
        void onSuccess(String msg);

        void onFailure(String msg);
    }

}
