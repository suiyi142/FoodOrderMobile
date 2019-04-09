package mobile.fom.com.foodordermobile.constant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.R;

public class BaseActivity extends AppCompatActivity {

    private final static String TAG = "BaseActivity";
    private static List<Activity> list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        list.add(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        list.remove(this);
        super.onDestroy();
    }

    public static void removeAll() {
        for (Activity activity : list)
            activity.finish();
    }


}
