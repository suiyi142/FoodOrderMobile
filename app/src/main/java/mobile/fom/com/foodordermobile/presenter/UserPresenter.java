package mobile.fom.com.foodordermobile.presenter;

import mobile.fom.com.foodordermobile.model.IUserModel;
import mobile.fom.com.foodordermobile.model.UserModel;
import mobile.fom.com.foodordermobile.view.IUserLoginRegisterView;

public class UserPresenter {
    private IUserModel mUserModel;
    private IUserLoginRegisterView mUserLoginRegisterView;

    public UserPresenter(IUserLoginRegisterView mUserLoginRegisterView) {
        this.mUserLoginRegisterView = mUserLoginRegisterView;
        getUserModel();
    }

    /*
    懒汉式创建model
     */
    private void getUserModel() {
        if (mUserModel == null)
            mUserModel = new UserModel();
    }
}
