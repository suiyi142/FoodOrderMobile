package mobile.fom.com.foodordermobile.model;

import mobile.fom.com.foodordermobile.bean.User;

public interface IUserModel {
    void login(String u_id,String password);
    void register(User user);
    void findBusiness();
}
