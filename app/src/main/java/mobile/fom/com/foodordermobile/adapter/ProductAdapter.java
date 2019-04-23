package mobile.fom.com.foodordermobile.adapter;

import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.util.StringUtils;
import mobile.fom.com.foodordermobile.view.activity.UserFoodActivity;


/**
 * Created by fengyongge on 2016/5/24 0024.
 */

/***
 * 底部购物车
 */
public class ProductAdapter extends BaseAdapter {
    UserGoodsAdapter goodsAdapter;
    private UserFoodActivity activity;
    private ArrayMap<String,Goods> dataList;

    public ProductAdapter(UserFoodActivity activity, UserGoodsAdapter goodsAdapter, ArrayMap<String,Goods> dataList) {
        this.goodsAdapter = goodsAdapter;
        this.activity = activity;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.valueAt(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder viewholder;
        if (view == null) {
            view = LayoutInflater.from(activity).inflate(R.layout.item_product, null);
            viewholder = new ViewHolder();
            viewholder.tv_name = view.findViewById(R.id.tv_name);
            viewholder.tv_price = view.findViewById(R.id.tv_price);
            viewholder.iv_add = view.findViewById(R.id.iv_add);
            viewholder.iv_remove = view.findViewById(R.id.iv_remove);
            viewholder.tv_count = view.findViewById(R.id.tv_commit);

            view.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) view.getTag();
        }


        StringUtils.filtNull(viewholder.tv_name, dataList.valueAt(position).getName());//商品名称
        StringUtils.filtNull(viewholder.tv_price, "￥" + dataList.valueAt(position).getPrice());//商品价格
        viewholder.tv_count.setText(String.valueOf(dataList.valueAt(position).getNum()));//商品数量

        viewholder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.handlerCarNum(1, dataList.valueAt(position), true);
                goodsAdapter.notifyDataSetChanged();

            }
        });
        viewholder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.handlerCarNum(0, dataList.valueAt(position), true);
                goodsAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    class ViewHolder {
        TextView tv_price;
        TextView tv_name;
        ImageView iv_add, iv_remove;
        TextView tv_count;
    }

}