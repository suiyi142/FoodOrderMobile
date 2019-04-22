package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import mobile.fom.com.foodordermobile.App;
import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.view.fragment.BusinessOrderNowFragment;
import mobile.fom.com.foodordermobile.view.fragment.BusinessOrderOldFragment;

public class BusinessActivity extends App implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private Business business;
    private RadioGroup rb_business_bottom;
    private BusinessOrderNowFragment nowFragment;
    private BusinessOrderOldFragment oldFragment;
    private FragmentManager manager;
    private TextView tv_business_order_state;
    private TextView tv_business_seats;
    private TextView tv_add_goods;
    private TextView tv_business_exit;
    private TextView tv_show_goods;

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
        tv_business_seats = findViewById(R.id.tv_business_seats);
        tv_business_seats.setText("剩余座位" + business.getCurrent_seats());
        tv_add_goods = findViewById(R.id.tv_add_goods);
        tv_add_goods.setOnClickListener(this);
        tv_business_exit = findViewById(R.id.tv_business_exit);
        tv_business_exit.setOnClickListener(this);
        tv_show_goods = findViewById(R.id.tv_show_goods);
        tv_show_goods.setOnClickListener(this);
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

    /**
     * 设置座位
     *
     * @param business
     */
    public void setSeats(Business business) {
        this.business = business;
        tv_business_seats.setText("剩余座位" + business.getCurrent_seats());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_business_exit:
                this.finish();
                break;
            case R.id.tv_add_goods:
                BusinessAddGoodsActivity.startActivity(this, business);
                break;
            case R.id.tv_show_goods:
                BusinessAllGoodsActivity.startActivity(this,business);
                break;
        }

    }
}
