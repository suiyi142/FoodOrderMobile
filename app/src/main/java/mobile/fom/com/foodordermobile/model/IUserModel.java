package mobile.fom.com.foodordermobile.model;

import java.util.HashMap;

import mobile.fom.com.foodordermobile.bean.User;

public interface IUserModel {
    void login(String account, String password, IModelCallBack callBack);

    void register(User user, IModelCallBack callBack);

    void findBusiness(IModelCallBack callBack);

    void saveLoginState(boolean remember, boolean autoLogin, String account, String password);

    HashMap<String, Object> getLoginState();
}
