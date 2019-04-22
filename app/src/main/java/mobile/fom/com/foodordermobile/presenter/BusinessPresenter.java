package mobile.fom.com.foodordermobile.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.bean.Order;
import mobile.fom.com.foodordermobile.model.model.BusinessModel;
import mobile.fom.com.foodordermobile.model.IBusinessModel;
import mobile.fom.com.foodordermobile.model.IModelCallBack;
import mobile.fom.com.foodordermobile.view.IBusinessAllGoodsView;
import mobile.fom.com.foodordermobile.view.IBusinessLoginView;
import mobile.fom.com.foodordermobile.view.IBusinessOrderView;
import mobile.fom.com.foodordermobile.view.IBusinessRegisterView;
import mobile.fom.com.foodordermobile.view.IBusinessGoodsView;

public class BusinessPresenter {
    private IBusinessModel mBusinessModel;
    private IBusinessLoginView mBusinessLoginView;
    private IBusinessRegisterView mBusinessRegisterView;
    private IBusinessOrderView mBusinessOrderView;
    private IBusinessGoodsView mBusinessGoodsView;
    private IBusinessAllGoodsView mBusinessAllGoodsView;

    public BusinessPresenter(IBusinessLoginView mBusinessView) {
        this.mBusinessLoginView = mBusinessView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessOrderView mBusinessOrderView) {
        this.mBusinessOrderView = mBusinessOrderView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessRegisterView mBusinessRegisterView) {
        this.mBusinessRegisterView = mBusinessRegisterView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessGoodsView mBusinessGoodsView) {
        this.mBusinessGoodsView = mBusinessGoodsView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessAllGoodsView mBusinessAllGoodsView) {
        this.mBusinessAllGoodsView = mBusinessAllGoodsView;
        getBusinessModel();
    }

    /*
    懒汉式创建model
    */
    private void getBusinessModel() {
        if (mBusinessModel == null)
            mBusinessModel = new BusinessModel();
    }

    /**
     * 查询商家
     */
    public void findBusiness() {
        mBusinessModel.findBusiness(new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                ArrayList<Business> list = gson.fromJson(msg, new TypeToken<ArrayList<Business>>() {
                }.getType());
                if (list.size() == 0)
                    mBusinessLoginView.BusinessZero();
                else
                    mBusinessLoginView.setBusinessList(list);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessLoginView.setFailedMsg(msg);
            }
        });
    }

    /**
     * 商家注册
     *
     * @param business 商家bean
     */
    public void businessRegister(Business business) {
        mBusinessModel.register(business, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("1"))
                    mBusinessRegisterView.registerSuccess();
                else mBusinessRegisterView.registerFailed(msg);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessRegisterView.registerFailed(msg);
            }
        });
    }

    /**
     * 商家登录
     *
     * @param b_id     商家id
     * @param password 商家密码
     */
    public void businessLogin(String b_id, String password) {
        mBusinessModel.login(b_id, password, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("0"))
                    mBusinessLoginView.showLoginErrorMsg("登录失败");
                else {
                    Gson gson = new Gson();
                    Business business = gson.fromJson(msg, Business.class);
                    mBusinessLoginView.toBusiness(business);
                }
            }

            @Override
            public void onFailure(String msg) {
                mBusinessLoginView.showLoginErrorMsg(msg);
            }
        });
    }

    /*
    获取历史订单
     */
    public void getOldOrder(String b_id) {
        mBusinessModel.getOldOrder(b_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                List<Order> orderList = gson.fromJson(msg, new TypeToken<List<Order>>() {
                }.getType());
                mBusinessOrderView.showOrder(orderList);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessOrderView.showError(msg);
            }
        });
    }

    /*
    获取新订单
     */
    public void getNewOrder(String b_id) {
        mBusinessModel.getNewOrder(b_id, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                List<Order> orderList = gson.fromJson(msg, new TypeToken<List<Order>>() {
                }.getType());
                mBusinessOrderView.showOrder(orderList);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessOrderView.showError(msg);
            }
        });
    }

    /*
    增加商品
     */
    public void addGoods(String b_id, String g_name, String g_price, String g_other) {
        mBusinessModel.addGoods(b_id, g_name, g_price, g_other, new IModelCallBack() {
            @Override
            public void onSuccess(String msg) {
                if (msg.equals("0")) {
                    mBusinessGoodsView.changeFailed("添加失败");
                } else {
                    Goods goods = new Gson().fromJson(msg, Goods.class);
                    mBusinessGoodsView.changeSuccess(goods);
                }

            }

            @Override
            public void onFailure(String msg) {
                mBusinessGoodsView.changeFailed(msg);
            }
        });
    }

    public void getAllGoods(String b_id){

    }


}
