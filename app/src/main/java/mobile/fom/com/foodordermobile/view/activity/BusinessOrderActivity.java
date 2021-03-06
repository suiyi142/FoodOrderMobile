package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.adapter.OrderGoodsAdapter;
import mobile.fom.com.foodordermobile.bean.Order;
import mobile.fom.com.foodordermobile.bean.OrderItem;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessGoodsView;
import mobile.fom.com.foodordermobile.view.IOrderView;

public class BusinessOrderActivity extends AppCompatActivity implements View.OnClickListener, IOrderView {

    private Order order;
    private TextView tv_u_name;
    private ListView lv_goods;
    private TextView tv_price;
    private TextView tv_state;
    private Button bt_oder_receipt;
    private Button bt_oder_refuse;
    private Button bt_oder_used;
    private BusinessPresenter presenter;
    private List<OrderItem> orderItems = new ArrayList<>();
    private OrderGoodsAdapter adapter;
    private TextView tv_order_other;
    private Button bt_oder_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_order);
        presenter = new BusinessPresenter(this);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra("order");
    }

    private void initView() {
        tv_u_name = findViewById(R.id.tv_order_u_name);
        lv_goods = findViewById(R.id.lv__order_goods);
        tv_price = findViewById(R.id.tv_order_price);
        tv_state = findViewById(R.id.tv_order_state);
        bt_oder_receipt = findViewById(R.id.bt_oder_receipt);
        bt_oder_refuse = findViewById(R.id.bt_oder_refuse);
        bt_oder_used = findViewById(R.id.bt_oder_used);
        tv_order_other = findViewById(R.id.tv_order_other);
        bt_oder_delete = findViewById(R.id.bt_oder_delete);
        bt_oder_delete.setOnClickListener(this);
        bt_oder_receipt.setOnClickListener(this);
        bt_oder_refuse.setOnClickListener(this);
        bt_oder_used.setOnClickListener(this);
        setViewState();
        presenter.getUserName(order.getU_id());
        presenter.getOrderItem(order.getO_id());
        adapter = new OrderGoodsAdapter(this, R.layout.item_order_goods, orderItems);
        lv_goods.setAdapter(adapter);
    }

    /*
    设置按钮状态
     */
    private void setViewState() {
        tv_order_other.setText("备注：" + order.getOther());
        tv_price.setText("金额：￥" + order.getPrice());
        switch (order.getState()) {
            case Order.STATE_NEW:
                bt_oder_receipt.setVisibility(View.VISIBLE);
                bt_oder_refuse.setVisibility(View.VISIBLE);
                bt_oder_used.setVisibility(View.GONE);
                tv_state.setText("待接单");
                break;
            case Order.STATE_RECEIPT:
                bt_oder_receipt.setVisibility(View.GONE);
                bt_oder_refuse.setVisibility(View.GONE);
                bt_oder_used.setVisibility(View.VISIBLE);
                tv_state.setText("已接单");
                break;
            case Order.STATE_USED:
                bt_oder_receipt.setVisibility(View.GONE);
                bt_oder_refuse.setVisibility(View.GONE);
                bt_oder_used.setVisibility(View.GONE);
                bt_oder_delete.setVisibility(View.VISIBLE);
                tv_state.setText("已核销");
                break;
            case Order.STATE_REFUSE:
                bt_oder_receipt.setVisibility(View.GONE);
                bt_oder_refuse.setVisibility(View.GONE);
                bt_oder_used.setVisibility(View.GONE);
                bt_oder_delete.setVisibility(View.VISIBLE);
                tv_state.setText("已拒接");
                break;
        }
    }

    public static void startActivity(Context context, Order order) {
        Intent intent = new Intent(context, BusinessOrderActivity.class);
        intent.putExtra("order", order);
        context.startActivity(intent);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_oder_receipt:
                presenter.receiptOrder(order.getO_id());
                break;
            case R.id.bt_oder_refuse:
                presenter.refuseOrder(order.getO_id());
                break;
            case R.id.bt_oder_used:
                presenter.usedOrder(order.getO_id());
                break;
            case R.id.bt_oder_delete:
                presenter.deleteOrder(order.getO_id());
                break;
        }
    }

    //--------------------------------------------------接口回调---------------------
    @Override
    public void changeSuccess(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessOrderActivity.this, msg);
                BusinessOrderActivity.this.finish();
            }
        });
    }

    @Override
    public void changeFailed(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessOrderActivity.this, msg);
            }
        });
    }

    @Override
    public void setUserName(final String name) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_u_name.setText("用户：" + name);
            }
        });
    }

    @Override
    public void setOrderItem(final List<OrderItem> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                orderItems.clear();
                orderItems.addAll(list);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
