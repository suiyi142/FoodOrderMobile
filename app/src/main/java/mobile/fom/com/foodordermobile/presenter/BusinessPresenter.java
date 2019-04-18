package mobile.fom.com.foodordermobile.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.model.model.BusinessModel;
import mobile.fom.com.foodordermobile.model.IBusinessModel;
import mobile.fom.com.foodordermobile.model.IModelCallBack;
import mobile.fom.com.foodordermobile.view.IBusinessLoginView;
import mobile.fom.com.foodordermobile.view.IBusinessRegisterView;

public class BusinessPresenter {
    private IBusinessModel mBusinessModel;
    private IBusinessLoginView mBusinessLoginView;
    private IBusinessRegisterView mBusinessRegisterView;

    public BusinessPresenter(IBusinessLoginView mBusinessView) {
        this.mBusinessLoginView = mBusinessView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessRegisterView mBusinessRegisterView) {
        this.mBusinessRegisterView = mBusinessRegisterView;
        getBusinessModel();
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
    懒汉式创建model
     */
    private void getBusinessModel() {
        if (mBusinessModel == null)
            mBusinessModel = new BusinessModel();
    }
}
