package mobile.fom.com.foodordermobile.model.model;

import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashMap;

import mobile.fom.com.foodordermobile.App;
import mobile.fom.com.foodordermobile.bean.User;
import mobile.fom.com.foodordermobile.constant.FoodOrderConstant;
import mobile.fom.com.foodordermobile.model.IModelCallBack;
import mobile.fom.com.foodordermobile.model.IUserModel;
import mobile.fom.com.foodordermobile.util.HttpUtil;
import mobile.fom.com.foodordermobile.util.SpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class UserModel implements IUserModel {
    /**
     * 用户登录
     *
     * @param account  账号
     * @param password 密码
     * @param callBack 回调
     */
    @Override
    public void login(String account, String password, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.U_USER_LOGIN;
        HashMap<String, String> map = new HashMap<>();
        map.put("account", account);
        map.put("password", password);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("connection error");

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });

    }

    /**
     * @param user     要添加的用户
     * @param callBack 回调
     */
    @Override
    public void register(User user, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.U_USER_REGISTER;
        HashMap<String, String> map = new HashMap<>();
        map.put("account", user.getAccount());
        map.put("name", user.getName());
        map.put("password", user.getPassword());
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("connection error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    @Override
    public void findBusiness(final IModelCallBack callBack) {
        HttpUtil.sendHttpRequest(FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.U_FIND_BUSINESS, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("connection wrong");
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body() != null ? response.body().string() : null;
                callBack.onSuccess(body);

            }
        });
    }

    /**
     * 保存自动登录和记住密码状态
     *
     * @param remember  记住密码
     * @param autoLogin 自动登录
     * @param password  密码
     */
    @Override
    public void saveLoginState(boolean remember, boolean autoLogin, String account, String password) {
        SharedPreferences sp = SpUtil.getSharedPreperence(App.getInstance());
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(FoodOrderConstant.AUTO_LOGIN_STATE, autoLogin);
        editor.putBoolean(FoodOrderConstant.REMEMBER_PASSWORD_STATE, remember);
        editor.putString(FoodOrderConstant.REMEMBER_ACCOUNT, account);
        editor.putString(FoodOrderConstant.REMEMBER_PASSWORD, password);
        editor.apply();
    }

    /**
     * 获取商品列表
     *
     * @param b_id
     */
    @Override
    public void findGoods(String b_id, final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.U_FIND_GOODS;
        HashMap<String, String> map = new HashMap<>();
        map.put("b_id", b_id);
        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("connection error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    /**
     * 提交订单
     *
     * @param u_id     用户id
     * @param b_id     商家id
     * @param jsonList 商品列表
     * @param other    备注
     */
    @Override
    public void commitOrder(String u_id, String b_id, String jsonList, String other,final IModelCallBack callBack) {
        String url = FoodOrderConstant.SERVER_ADDRESS + FoodOrderConstant.U_COMMIT_ORDER;
        HashMap<String, String> map = new HashMap<>();
        map.put("u_id", u_id);
        map.put("b_id", b_id);
        map.put("goods_json", jsonList);
        map.put("other", other);

        HttpUtil.sendHttpRequest(url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailure("connection error");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.onSuccess(response.body().string());
            }
        });
    }

    /**
     * 获取本地登录状态
     *
     * @return 登录状态K, V
     */
    @Override
    public HashMap<String, Object> getLoginState() {
        HashMap<String, Object> map = new HashMap<>();
        SharedPreferences sp = SpUtil.getSharedPreperence(App.getInstance());
        map.put(FoodOrderConstant.AUTO_LOGIN_STATE, sp.getBoolean(FoodOrderConstant.AUTO_LOGIN_STATE, false));
        map.put(FoodOrderConstant.REMEMBER_PASSWORD_STATE, sp.getBoolean(FoodOrderConstant.REMEMBER_PASSWORD_STATE, false));
        map.put(FoodOrderConstant.REMEMBER_ACCOUNT, sp.getString(FoodOrderConstant.REMEMBER_ACCOUNT, ""));
        map.put(FoodOrderConstant.REMEMBER_PASSWORD, sp.getString(FoodOrderConstant.REMEMBER_PASSWORD, ""));
        return map;
    }
}
