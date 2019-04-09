package mobile.fom.com.foodordermobile.model;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.constant.FoodOrderConstant;
import mobile.fom.com.foodordermobile.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BusinessModel implements IBusinessModel {

    private static final String TAG = "BusinessModel";

    /**
     * 获取商家列表
     */
    @Override
    public void findBusiness(final ICallBack callBack) {
        Log.i(TAG, FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_FIND_BUSINESS);
        HttpUtil.sendHttpRequest(FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_FIND_BUSINESS, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("findBusiness wrong");
                Log.i(TAG, "findBusiness wrong");
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body() != null ? response.body().string() : null;
                Log.i(TAG, body);
                callBack.onSuccess(body);

            }
        });
    }

    @Override
    public void register(Business business, final ICallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_REGISTER;
        HashMap<String,String> map = new HashMap<>();
        map.put("address",business.getAddress());
        map.put("password",business.getPassword());
        map.put("max_seats",business.getMax_seats()+"");
        map.put("other",business.getOther());
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("register failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });

    }

}
