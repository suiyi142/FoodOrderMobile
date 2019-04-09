package mobile.fom.com.foodordermobile.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.model.BusinessModel;
import mobile.fom.com.foodordermobile.model.IBusinessModel;
import mobile.fom.com.foodordermobile.view.IBusinessLoginView;
import mobile.fom.com.foodordermobile.view.IBusinessRegisterView;

public class BusinessPresenter {
    private IBusinessModel mBusinessModel;
    private IBusinessLoginView mBusinessView;
    private IBusinessRegisterView mBusinessRegisterView;

    public BusinessPresenter(IBusinessLoginView mBusinessView) {
        this.mBusinessView = mBusinessView;
        getBusinessModel();
    }

    public BusinessPresenter(IBusinessRegisterView mBusinessRegisterView) {
        this.mBusinessRegisterView = mBusinessRegisterView;
        getBusinessModel();
    }

    public void findBusiness() {
        mBusinessModel.findBusiness(new IBusinessModel.ICallBack() {
            @Override
            public void onSuccess(String msg) {
                Gson gson = new Gson();
                ArrayList<Business> list = gson.fromJson(msg, new TypeToken<ArrayList<Business>>() {
                }.getType());
                if (list.size() == 0)
                    mBusinessView.BusinessZero();
                else
                    mBusinessView.setBusinessList(list);
            }

            @Override
            public void onFailure(String msg) {
                mBusinessView.setFailedMsg(msg);
            }
        });
    }

    public void businessRegister(Business business){
        mBusinessModel.register(business, new IBusinessModel.ICallBack() {
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

    /*
    懒汉式创建model
     */
    private void getBusinessModel() {
        if (mBusinessModel == null)
            mBusinessModel = new BusinessModel();
    }
}
