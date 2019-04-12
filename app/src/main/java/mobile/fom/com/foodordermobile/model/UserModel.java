package mobile.fom.com.foodordermobile.model;

import android.content.SharedPreferences;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import mobile.fom.com.foodordermobile.App;
import mobile.fom.com.foodordermobile.bean.User;
import mobile.fom.com.foodordermobile.constant.FoodOrderConstant;
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
    public void findBusiness(IModelCallBack callBack) {
        //TODO
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
