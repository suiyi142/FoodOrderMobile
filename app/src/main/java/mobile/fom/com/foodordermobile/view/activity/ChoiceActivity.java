package mobile.fom.com.foodordermobile.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import mobile.fom.com.foodordermobile.App;
import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.constant.FoodOrderConstant;
import mobile.fom.com.foodordermobile.util.SpUtil;

public class ChoiceActivity extends App {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choise);
        sp = SpUtil.getSharedPreperence(this);
        int state = sp.getInt(FoodOrderConstant.CHOICE_STATE, FoodOrderConstant.STATE_NULL);
        if (state == FoodOrderConstant.STATE_BUSINESS)
            BusinessLoginActivity.startActivity(this);
        else if (state == FoodOrderConstant.STATE_USER)
            UserLoginActivity.startActivity(this);

    }

    /*
    点击去Business
     */
    public void toBusiness(View view) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(FoodOrderConstant.CHOICE_STATE, FoodOrderConstant.STATE_BUSINESS);
        editor.apply();
        BusinessLoginActivity.startActivity(this);

    }

    /*
    点击去User
     */
    public void toUser(View view) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(FoodOrderConstant.CHOICE_STATE, FoodOrderConstant.STATE_USER);
        editor.apply();
        UserLoginActivity.startActivity(this);
    }
}
