package mobile.fom.com.foodordermobile.model;

public interface IBusinessModel {
    void findBusiness(ICallBack callBack);
    interface ICallBack {
        void onSuccess(String msg);
        void onFailure(String msg);
    }

}
