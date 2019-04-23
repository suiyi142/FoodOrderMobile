package mobile.fom.com.foodordermobile.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.view.activity.BusinessUpdateGoodsActivity;

public class BusinessGoodsAdapter extends RecyclerView.Adapter<BusinessGoodsAdapter.ViewHolder> {

    private List<Goods> goodsList;

    public BusinessGoodsAdapter(List<Goods> goodsArrayList) {
        this.goodsList = goodsArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_business_goods, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final Goods goods = goodsList.get(i);
        viewHolder.tv_name.setText(goods.getName());
        viewHolder.tv_other.setText(goods.getOther());
        String price = "￥" + goods.getPrice();
        viewHolder.tv_price.setText(price);
        //点击条目跳转到修改商品界面
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessUpdateGoodsActivity.startActivity(viewHolder.itemView.getContext(), goods);
            }
        });

    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView iv_pic;
        private final TextView tv_name;
        private final TextView tv_other;
        private final TextView tv_price;
        private final View itemView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
            iv_pic = itemView.findViewById(R.id.iv_business_goods_pic);
            tv_name = itemView.findViewById(R.id.tv_item_business_goods_name);
            tv_other = itemView.findViewById(R.id.tv_item_business_goods_other);
            tv_price = itemView.findViewById(R.id.tv_item_business_goods_price);
        }
    }
}
