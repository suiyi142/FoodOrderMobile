package mobile.fom.com.foodordermobile.util;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class HttpUtil {
    public static void sendHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).readTimeout(3, TimeUnit.SECONDS).build();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendHttpRequest(String address, Map<String, String> param, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(3, TimeUnit.SECONDS).readTimeout(3, TimeUnit.SECONDS).build();
        FormBody.Builder builder = new FormBody.Builder();
        for (String s : param.keySet()) {
            builder.add(s, param.get(s));
        }
        FormBody formBody = builder.build();
        Request request = new Request.Builder().url(address).post(formBody).build();
        client.newCall(request).enqueue(callback);
    }
}
