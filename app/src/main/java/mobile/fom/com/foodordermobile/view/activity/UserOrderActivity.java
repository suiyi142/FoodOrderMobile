package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.adapter.UserOrderAdapter;
import mobile.fom.com.foodordermobile.bean.Order;
import mobile.fom.com.foodordermobile.presenter.UserPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IUserOrderView;

public class UserOrderActivity extends AppCompatActivity implements IUserOrderView, XRecyclerView.LoadingListener {

    private String account;
    private XRecyclerView xrv_order;
    private UserPresenter presenter;
    private List<Order> orderList = new ArrayList<>();
    private UserOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);
        presenter = new UserPresenter(this);
        initData();
        initView();
    }

    private void initView() {
        xrv_order = findViewById(R.id.xrv_order);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrv_order.setLayoutManager(layoutManager);
        adapter = new UserOrderAdapter(orderList);
        xrv_order.setAdapter(adapter);
        xrv_order.setLoadingListener(this);
        xrv_order.setLoadingMoreEnabled(false);
        presenter.getOrder(account);
    }

    private void initData() {
        account = getIntent().getStringExtra("account");
    }

    public static void startActivity(Context context, String account) {
        Intent intent = new Intent(context, UserOrderActivity.class);
        intent.putExtra("account", account);
        context.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        presenter.getOrder(account);
    }

    @Override
    public void onLoadMore() {

    }

    //----------------presenter回调----------------
    @Override
    public void getOrderSuccess(final List<Order> orders) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (orders.size()==0)
                    ToastUtil.showToast(UserOrderActivity.this,"没有订单哦·");
                orderList.clear();
                orderList.addAll(orders);
                xrv_order.refreshComplete();
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void getOrderFiled(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(UserOrderActivity.this,msg);
            }
        });
    }

}
