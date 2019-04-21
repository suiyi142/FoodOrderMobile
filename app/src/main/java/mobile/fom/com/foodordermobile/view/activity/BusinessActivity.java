package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.RadioGroup;
import android.widget.TextView;

import mobile.fom.com.foodordermobile.App;
import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.fragment.BusinessOrderNowFragment;
import mobile.fom.com.foodordermobile.view.fragment.BusinessOrderOldFragment;

public class BusinessActivity extends App implements RadioGroup.OnCheckedChangeListener {

    private Business business;
    private RadioGroup rb_business_bottom;
    private BusinessOrderNowFragment nowFragment;
    private BusinessOrderOldFragment oldFragment;
    private FragmentManager manager;
    private TextView tv_business_order_state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        business = (Business) intent.getSerializableExtra("business");
    }

    private void initView() {
        nowFragment = BusinessOrderNowFragment.newInstance(business);
        oldFragment = BusinessOrderOldFragment.newInstance(business);
        rb_business_bottom = findViewById(R.id.rb_business_bottom);
        rb_business_bottom.setOnCheckedChangeListener(this);
        tv_business_order_state = findViewById(R.id.tv_business_order_state);
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_business, nowFragment);
        transaction.commit();
    }

    /*
    开启这个activity
     */
    public static void startActivity(Context context, Business business) {
        Intent intent = new Intent(context, BusinessActivity.class);
        intent.putExtra("business", business);
        context.startActivity(intent);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        FragmentTransaction transaction = manager.beginTransaction();
        switch (i) {
            case R.id.rb_business_order_now:
                transaction.replace(R.id.fl_business, nowFragment);
                tv_business_order_state.setText("实时订单");
                transaction.commit();
                break;
            case R.id.rb_business_order_old:
                transaction.replace(R.id.fl_business, oldFragment);
                tv_business_order_state.setText("历史订单");
                transaction.commit();
                break;
        }
    }
}
