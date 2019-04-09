package mobile.fom.com.foodordermobile.presenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.model.BusinessModel;
import mobile.fom.com.foodordermobile.model.IBusinessModel;
import mobile.fom.com.foodordermobile.view.IBusinessView;

public class BusinessPresenter {
    private IBusinessModel mBusinessModel;
    private IBusinessView mBusinessView;

    public BusinessPresenter(IBusinessView mBusinessView) {
        this.mBusinessView = mBusinessView;
        mBusinessModel = new BusinessModel();
    }

    public void findBusiness(){
        mBusinessModel.findBusiness(new IBusinessModel.ICallBack(){
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
}
