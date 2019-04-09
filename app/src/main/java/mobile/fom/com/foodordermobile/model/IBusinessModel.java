package mobile.fom.com.foodordermobile.model;

import mobile.fom.com.foodordermobile.bean.Business;

public interface IBusinessModel {
    /**
     * 获取商家列表
     *
     * @param callBack
     */
    void findBusiness(ICallBack callBack);

    void register(Business business,ICallBack callBack);

    interface ICallBack {
        void onSuccess(String msg);

        void onFailure(String msg);
    }

}
