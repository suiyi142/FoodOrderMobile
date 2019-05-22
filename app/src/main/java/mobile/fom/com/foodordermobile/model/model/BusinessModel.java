package mobile.fom.com.foodordermobile.model.model;

import android.util.Log;

import java.io.IOException;
import java.util.HashMap;

import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.constant.FoodOrderConstant;
import mobile.fom.com.foodordermobile.model.IBusinessModel;
import mobile.fom.com.foodordermobile.model.IModelCallBack;
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
    public void findBusiness(final IModelCallBack callBack) {
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

    /*
    注册
     */
    @Override
    public void register(Business business, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_REGISTER;
        HashMap<String, String> map = new HashMap<>();
        map.put("address", business.getAddress());
        map.put("password", business.getPassword());
        map.put("max_seats", business.getMax_seats() + "");
        map.put("other", business.getOther());
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

    /*
    登录
     */
    @Override
    public void login(String b_id, String password, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_LOGIN;
        HashMap<String, String> map = new HashMap<>();
        map.put("b_id", b_id);
        map.put("password", password);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    /*
    获取历史订单
     */
    @Override
    public void getOldOrder(String b_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_GET_OLD_ORDER;
        HashMap<String, String> map = new HashMap<>();
        map.put("b_id", b_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    /*
    获取实时订单
     */
    @Override
    public void getNewOrder(String b_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_GET_NEW_ORDER;
        HashMap<String, String> map = new HashMap<>();
        map.put("b_id", b_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    /*
    添加商品
     */
    @Override
    public void addGoods(String b_id, String g_name, String g_price, String g_other, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_ADD_GOODS;
        HashMap<String, String> map = new HashMap<>();
        map.put("b_id", b_id);
        map.put("name", g_name);
        map.put("price", g_price);
        map.put("other", g_other);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });

    }

    /*
    获取所有商品
     */
    @Override
    public void getAllGoods(String b_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_GET_ALL_GOODS;
        HashMap<String, String> map = new HashMap<>();
        map.put("b_id", b_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                //Log.i(TAG,body);
                callBack.onSuccess(body);

            }
        });
    }

    /*
    修改商品
     */
    @Override
    public void updateGoods(Goods goods, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_UPDATE_GOODS;
        HashMap<String, String> map = new HashMap<>();
        map.put("g_id", goods.getG_id());
        map.put("name", goods.getName());
        map.put("price", goods.getPrice());
        map.put("other", goods.getOther());
        Log.i(TAG, goods.toString());
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    /*
    删除商品
     */
    @Override
    public void deleteGoods(String g_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_DELETE_GOODS;
        //g_id=9892F33650C34A99BF8F638A765C6841&name=大白&price=4&other=富含维生
        HashMap<String, String> map = new HashMap<>();
        map.put("g_id", g_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    /*
    接单
     */
    @Override
    public void receiptOrder(String o_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_RECEIPT_ORDER;
        //g_id=9892F33650C34A99BF8F638A765C6841&name=大白&price=4&other=富含维生
        HashMap<String, String> map = new HashMap<>();
        map.put("o_id", o_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    /*
    拒单
    */
    @Override
    public void refuseOrder(String o_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_REFUSE_ORDER;
        //g_id=9892F33650C34A99BF8F638A765C6841&name=大白&price=4&other=富含维生
        HashMap<String, String> map = new HashMap<>();
        map.put("o_id", o_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    /*
    核销
    */
    @Override
    public void usedOrder(String o_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_USED_ORDER;
        HashMap<String, String> map = new HashMap<>();
        map.put("o_id", o_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }


    @Override
    public void getUserName(String u_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.U_GET_USER_NAME;
        HashMap<String, String> map = new HashMap<>();
        map.put("account", u_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                callBack.onSuccess(responseString);
            }
        });
    }

    @Override
    public void getOrderItem(String o_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_GET_ORDER_ITEM;
        HashMap<String, String> map = new HashMap<>();
        map.put("o_id", o_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("login wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                callBack.onSuccess(responseString);
            }
        });
    }

    @Override
    public void changePassword(String b_id, String address, String newPassword, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_CHANGE_PASSWORD;
        HashMap<String, String> map = new HashMap<>();
        map.put("b_id", b_id);
        map.put("address", address);
        map.put("newPassword", newPassword);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("change password wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                callBack.onSuccess(responseString);
            }
        });

    }

    @Override
    public void deleteOrder(String o_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.B_BUSINESS_DELETE_ORDER;
        HashMap<String, String> map = new HashMap<>();
        map.put("o_id", o_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("connected wrong");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseString = response.body().string();
                callBack.onSuccess(responseString);
            }
        });
    }

}
