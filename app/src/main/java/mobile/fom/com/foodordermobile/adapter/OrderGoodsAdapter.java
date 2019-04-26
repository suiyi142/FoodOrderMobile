package mobile.fom.com.foodordermobile.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.OrderItem;

public class OrderGoodsAdapter extends ArrayAdapter<OrderItem> {

    private int resource;

    public OrderGoodsAdapter(@NonNull Context context, int resource, List<OrderItem> objects) {

        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderItem orderItem = getItem(position);
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        } else {
            view = convertView;
        }
        TextView tv_item_name = view.findViewById(R.id.tv_item_name);
        TextView tv_item_num = view.findViewById(R.id.tv_item_num);
        TextView tv_item_total = view.findViewById(R.id.tv_item_total);
        tv_item_name.setText(orderItem.getG_name());
        tv_item_num.setText(orderItem.getNum() + "");
        tv_item_total.setText(orderItem.getTotal() + "");
        return view;
    }
}
