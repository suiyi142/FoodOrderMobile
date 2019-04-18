package mobile.fom.com.foodordermobile.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Business;

/**
 * 用户显示商家列表的adapter
 */
public class UserBusinessAdapter extends ArrayAdapter<Business> {

    private int resource;

    public UserBusinessAdapter(Context context, int resource, List<Business> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Business business = getItem(position);
        ViewHolder holder;
        View view;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resource, parent, false);
            holder = new ViewHolder();
            holder.tv_user_item_business_name = view.findViewById(R.id.tv_user_item_business_name);
            holder.tv_user_item_current_seats = view.findViewById(R.id.tv_user_item_current_seats);
            view.setTag(holder);

        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();

        }
        holder.tv_user_item_business_name.setText(business.getAddress());
        if (business.getCurrent_seats() == 0)
            holder.tv_user_item_current_seats.setTextColor(Color.RED);
        holder.tv_user_item_current_seats.setText(String.valueOf(business.getCurrent_seats()));
        return view;
    }

    class ViewHolder {
        TextView tv_user_item_business_name;
        TextView tv_user_item_current_seats;
    }
}
