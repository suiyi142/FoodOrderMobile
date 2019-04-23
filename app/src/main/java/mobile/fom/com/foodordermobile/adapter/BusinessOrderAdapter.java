package mobile.fom.com.foodordermobile.adapter;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Order;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.activity.BusinessOrderActivity;
import mobile.fom.com.foodordermobile.view.fragment.BusinessOrderNowFragment;

public class BusinessOrderAdapter extends RecyclerView.Adapter<BusinessOrderAdapter.OrderHolder> {

    private final String TAG = "BusinessOrderAdapter";

    private List<Order> orderList;
    private BusinessOrderNowFragment fragment;

    public BusinessOrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    public BusinessOrderAdapter(List<Order> orderList, BusinessOrderNowFragment fragment) {
        this.orderList = orderList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_business_order, viewGroup, false);
        OrderHolder holder = new OrderHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderHolder orderHolder, @SuppressLint("RecyclerView") final int i) {
        Order order = orderList.get(i);
        orderHolder.tv_u_id.setText(order.getU_id());
        orderHolder.tv_price.setText("￥" + order.getPrice());
        //判断接单按钮是否应该出现
        if (order.getState() == Order.STATE_NEW) {
            orderHolder.bt_receipt.setVisibility(View.VISIBLE);
        } else {
            orderHolder.bt_receipt.setVisibility(View.INVISIBLE);
        }
        //按钮点击接单
        orderHolder.bt_receipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "点击了接单");
            }
        });
        //点击跳转订单详情
        orderHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessOrderActivity.startActivity(orderHolder.itemView.getContext(), orderList.get(i));
            }
        });

    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderHolder extends RecyclerView.ViewHolder {

        final TextView tv_u_id;
        final TextView tv_price;
        final Button bt_receipt;
        final View itemView;

        OrderHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            tv_u_id = itemView.findViewById(R.id.tv_business_order_u_id);
            tv_price = itemView.findViewById(R.id.tv_business_order_price);
            bt_receipt = itemView.findViewById(R.id.bt_receipt);
        }
    }

}
