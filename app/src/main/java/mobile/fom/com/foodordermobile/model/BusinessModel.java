package mobile.fom.com.foodordermobile.model;

import android.util.Log;

import java.io.IOException;

import mobile.fom.com.foodordermobile.constant.FoodOrderConstant;
import mobile.fom.com.foodordermobile.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BusinessModel implements IBusinessModel {

    private static final String TAG = "BusinessModel";

    /**
     * 获取商家列表
     * @return 商家列表
     */
    @Override
    public void findBusiness(final ICallBack callBack) {
        Log.i(TAG, FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_FIND_BUSINESS);
        HttpUtil.sendHttpRequest(FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_FIND_BUSINESS , new Callback() {
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

}
