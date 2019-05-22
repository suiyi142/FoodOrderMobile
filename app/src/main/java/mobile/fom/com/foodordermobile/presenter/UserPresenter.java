package mobile.fom.com.foodordermobile.presenter;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.bean.Order;
import mobile.fom.com.foodordermobile.bean.User;
import mobile.fom.com.foodordermobile.constant.FoodOrderConstant;
import mobile.fom.com.foodordermobile.model.IModelCallBack;
import mobile.fom.com.foodordermobile.model.IUserModel;
import mobile.fom.com.foodordermobile.model.model.UserModel;
import mobile.fom.com.foodordermobile.view.IUserChangePasswordView;
import mobile.fom.com.foodordermobile.view.IUserGoodsView;
import mobile.fom.com.foodordermobile.view.IUserLoginRegisterView;
import mobile.fom.com.foodordermobile.view.IUserOrderView;
import mobile.fom.com.foodordermobile.view.IUserView;

public class UserPresenter {

    private static final String TAG = "UserPresenter";

    private IUserModel mUserModel;
    private IUserLoginRegisterView mUserLoginRegisterView;
    private IUserView mUserView;
    private IUserGoodsView mUserGoodsView;
    private IUserChangePasswordView mUserChangePasswordView;
    private IUserOrderView mUserOrderView;

    public UserPresenter(IUserLoginRegisterView mUserLoginRegisterView) {
        this.mUserLoginRegisterView = mUserLoginRegisterView;
        getUserModel();
    }

    public UserPresenter(IUserView mUserView) {
        this.mUserView = mUserView;
        getUserModel();
    }

    public UserPresenter(IUserGoodsView mUserGoodsView) {
        this.mUserGoodsView = mUserGoodsView;
        getUserModel();
    }

    public UserPresenter(IUserChangePasswordView mUserChangePasswordView) {
        this.mUserChangePasswordView = mUserChangePasswordView;
        getUserModel();
    }

    public UserPresenter(IUserOrderView mUserOrderView) {
        this.mUserOrderView = mUserOrderView;
        getUserModel();
    }

    /**
     * 登录
     *
     * @param account  账号
     * @param password 密码
     */
    public void login(String account, String password) {
        mUserModel.login(account, password, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("0"))
                    mUserLoginRegisterView.loginFailed("登录错误");
                else {
                    Gson gson = new Gson();
                    User user = gson.fromJson(msg, User.class);
                    mUserLoginRegisterView.loginSuccess(user);
                }
            }

            @Override
            public void onFailure(String msg) {
                mUserLoginRegisterView.loginFailed(msg);
            }
        });
    }

    /**
     * 注册
     *
     * @param account  账号
     * @param name     姓名
     * @param password 密码
     */
    public void register(String account, String name, String password) {
        User user = new User(account, name, password);
        mUserModel.register(user, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1"))
                    mUserLoginRegisterView.registerSuccess();
                else
                    mUserLoginRegisterView.registerFailed(msg);
            }

            @Override
            public void onFailure(String msg) {
                mUserLoginRegisterView.registerFailed(msg);
            }
        });
    }

    /**
     * 保存登录状态
     *
     * @param remember  记住密码
     * @param autoLogin 自动登录
     * @param account   账号
     * @param password  密码
     */
    public void saveLoginState(boolean remember, boolean autoLogin, String account, String password) {
        mUserModel.saveLoginState(remember, autoLogin, account, password);
    }

    /**
     * 获取登录状态
     */
    public void getLoginState() {
        HashMap<String, Object> map = mUserModel.getLoginState();
        boolean remember = (boolean) map.get(FoodOrderConstant.REMEMBER_PASSWORD_STATE);
        boolean auto_login = (boolean) map.get(FoodOrderConstant.AUTO_LOGIN_STATE);
        String account = (String) map.get(FoodOrderConstant.REMEMBER_ACCOUNT);
        String password = (String) map.get(FoodOrderConstant.REMEMBER_PASSWORD);
        mUserLoginRegisterView.setCheckBoxState(remember, auto_login, account, password);
    }

    /**
     * 获取商家列表
     */
    public void getBusiness() {
        mUserModel.findBusiness(new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                ArrayList<Business> businessList = gson.fromJson(msg, new TypeToken<ArrayList<Business>>() {
                }.getType());
                mUserView.setBusinessList(businessList);
            }

            @Override
            public void onFailure(String msg) {
                mUserView.showGetBusinessFailed(msg);
            }
        });
    }

    /**
     * 获取商品列表
     */
    public void getGoods(String b_id) {
        mUserModel.findGoods(b_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                ArrayList<Goods> goodsList = gson.fromJson(msg, new TypeToken<ArrayList<Goods>>() {
                }.getType());
                mUserGoodsView.setGoodsList(goodsList);
            }

            @Override
            public void onFailure(String msg) {
                mUserGoodsView.showGetGoodsFailed(msg);
            }
        });
    }

    /*
    提交订单
     */
    public void commitOrder(String u_id, String b_id, Collection<Goods> list, String other) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        mUserModel.commitOrder(u_id, b_id, json, other, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1"))
                    mUserGoodsView.commitSuccess("提交成功");
                else
                    mUserGoodsView.commitFailed("提交失败");
            }

            @Override
            public void onFailure(String msg) {
                mUserGoodsView.commitFailed(msg);
            }
        });
    }

    /**
     * 修改密码
     *
     * @param account
     * @param name
     * @param newPassword
     */
    public void changePassword(String account, String name, String newPassword) {
        mUserModel.changePassword(account, name, newPassword, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                switch (msg) {
                    case "1":
                        mUserChangePasswordView.changeFailed("没有这个用户");
                        break;
                    case "2":
                        mUserChangePasswordView.changeFailed("用户昵称错误");
                        break;
                    case "3":
                        mUserChangePasswordView.changeSuccess();
                        break;
                }
            }

            @Override
            public void onFailure(String msg) {
                mUserChangePasswordView.changeFailed(msg);
            }
        });
    }

    public void getOrder(String account) {
        mUserModel.getOrder(account, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                List<Order> orderList = new Gson().fromJson(msg, new TypeToken<List<Order>>() {
                }.getType());
                mUserOrderView.getOrderSuccess(orderList);
            }

            @Override
            public void onFailure(String msg) {
                mUserOrderView.getOrderFiled(msg);
            }
        });
    }

    /*
    懒汉式创建model
     */
    private void getUserModel() {
        if (mUserModel == null)
            mUserModel = new UserModel();
    }
}
