package mobile.fom.com.foodordermobile.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Order;

public class BusinessOrderAdapter extends RecyclerView.Adapter<BusinessOrderAdapter.OrderHolder> {
    List<Order> orderList;

    public BusinessOrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_business_order, viewGroup, false);
        OrderHolder holder = new OrderHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHolder orderHolder, int i) {
        Order order = orderList.get(i);
        orderHolder.tv_u_id.setText(order.getU_id());
        orderHolder.tv_price.setText("￥" + order.getPrice());
        //判断接单按钮是否应该出现
        if (order.getState() == Order.STATE_NEW) {
            orderHolder.bt_receipt.setVisibility(View.VISIBLE);
        } else if (order.getState() == Order.STATE_OLD) {
            orderHolder.bt_receipt.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderHolder extends RecyclerView.ViewHolder {

        final TextView tv_u_id;
        final TextView tv_price;
        final Button bt_receipt;

        OrderHolder(@NonNull View itemView) {
            super(itemView);
            tv_u_id = itemView.findViewById(R.id.tv_business_order_u_id);
            tv_price = itemView.findViewById(R.id.tv_business_order_price);
            bt_receipt = itemView.findViewById(R.id.bt_receipt);
        }
    }

}
