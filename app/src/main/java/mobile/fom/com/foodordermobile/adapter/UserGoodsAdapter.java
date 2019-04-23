package mobile.fom.com.foodordermobile.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.view.activity.UserFoodActivity;

public class UserGoodsAdapter extends BaseAdapter {

    private static final String TAG = "UserGoodsAdapter";

    private List<Goods> list;
    private Context context;

    public UserGoodsAdapter(Context context, List<Goods> list2) {
        this.context = context;
        this.list = list2;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_user_cart_1, null);
            viewholder = new ViewHolder();
            viewholder.tv_name = convertView.findViewById(R.id.tv_name);
            viewholder.tv_item_other = convertView.findViewById(R.id.tv_item_other);
            viewholder.tv_price = convertView.findViewById(R.id.tv_price);
            viewholder.iv_add = convertView.findViewById(R.id.iv_add);
            viewholder.iv_remove = convertView.findViewById(R.id.iv_remove);
            viewholder.tv_acount = convertView.findViewById(R.id.tv_item_num);
            viewholder.iv_pic = convertView.findViewById(R.id.iv_pic);
            viewholder.rl_item = convertView.findViewById(R.id.rl_item);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();

        }
        viewholder.tv_name.setText(list.get(position).getName());
        viewholder.tv_item_other.setText(list.get(position).getOther());
        viewholder.tv_price.setText("￥" + list.get(position).getPrice());

        if (list.get(position) != null) {
            //默认进来数量
            if (list.get(position).getNum() < 1) {
                viewholder.tv_acount.setVisibility(View.INVISIBLE);
                viewholder.iv_remove.setVisibility(View.INVISIBLE);
            } else {
                viewholder.tv_acount.setVisibility(View.VISIBLE);
                viewholder.iv_remove.setVisibility(View.VISIBLE);
                viewholder.tv_acount.setText(String.valueOf(list.get(position).getNum()));
            }
        } else {
            viewholder.tv_acount.setVisibility(View.INVISIBLE);
            viewholder.iv_remove.setVisibility(View.INVISIBLE);
        }

        viewholder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ((UserFoodActivity) context).getSelectedItemCountById(list.get(position).getG_id());
                Log.i(TAG, "iv_add" + String.valueOf(count));
                if (count < 1) {
                    viewholder.iv_remove.setAnimation(getShowAnimation());
                    viewholder.iv_remove.setVisibility(View.VISIBLE);
                    viewholder.tv_acount.setVisibility(View.VISIBLE);
                }

                ((UserFoodActivity) context).handlerCarNum(1, list.get(position), true);

                int[] loc = new int[2];
                viewholder.iv_add.getLocationInWindow(loc);
                for (int i = 0; i < loc.length; i++) {
                    Log.i("fyg", String.valueOf(loc[i]));
                }
                int[] startLocation = new int[2];// 一个整型数组，用来存储按钮的在屏幕的X、Y坐标
                v.getLocationInWindow(startLocation);// 这是获取购买按钮的在屏幕的X、Y坐标（这也是动画开始的坐标）
                ImageView ball = new ImageView(context);
                ball.setImageResource(R.drawable.number);
                ((UserFoodActivity) context).setAnim(ball, startLocation);// 开始执行动画

            }
        });

        viewholder.iv_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ((UserFoodActivity) context).getSelectedItemCountById(list.get(position).getG_id());
                Log.i("fyg", "iv_remove" + String.valueOf(count));
                if (count < 2) {
                    viewholder.iv_remove.setAnimation(getHiddenAnimation());
                    viewholder.iv_remove.setVisibility(View.GONE);
                    viewholder.tv_acount.setVisibility(View.GONE);
                }
                ((UserFoodActivity) context).handlerCarNum(0, list.get(position), true);

            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView tv_name, tv_item_other, tv_price;
        ImageView iv_add, iv_remove, iv_pic;
        TextView tv_acount;
        RelativeLayout rl_item;
    }


    //显示减号的动画
    private Animation getShowAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 2f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0, 1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    //隐藏减号的动画
    private Animation getHiddenAnimation() {
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0, 720, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 2f
                , TranslateAnimation.RELATIVE_TO_SELF, 0
                , TranslateAnimation.RELATIVE_TO_SELF, 0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1, 0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

}
