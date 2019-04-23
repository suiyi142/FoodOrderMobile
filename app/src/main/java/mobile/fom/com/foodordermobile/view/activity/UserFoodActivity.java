package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;

import java.text.DecimalFormat;
import java.util.ArrayList;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.adapter.UserGoodsAdapter;
import mobile.fom.com.foodordermobile.adapter.ProductAdapter;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.bean.User;
import mobile.fom.com.foodordermobile.myview.MyListView;
import mobile.fom.com.foodordermobile.presenter.UserPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IUserGoodsView;

public class UserFoodActivity extends AppCompatActivity implements IUserGoodsView, View.OnClickListener {

    private static final String TAG = "UserFoodActivity";

    private User user;
    private Business business;
    private UserPresenter presenter;
    private ListView lv_user_food;
    private TextView tv_total_money;
    private TextView bv_unm;
    private LinearLayout ll_car;
    private View bottomSheet;
    private BottomSheetLayout bottomSheetLayout;
    private TextView tv_commit;

    private ViewGroup anim_mask_layout;//动画层
    private TextView tv_car;

    private ArrayMap<String, Goods> selectedList;
    Double totalMoney = 0.00;
    private static DecimalFormat df;
    private ArrayList<Goods> goodkList;
    private UserGoodsAdapter goodsAdapter;
    private ProductAdapter productAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_food);
        presenter = new UserPresenter(this);
        initValue();
        initView();
        presenter.getGoods(business.getB_id());
    }

    private void initValue() {
        Intent intent = getIntent();
        business = (Business) intent.getSerializableExtra("business");
        user = (User) intent.getSerializableExtra("user");
        df = new DecimalFormat("0.00");
    }

    private void initView() {
        lv_user_food = findViewById(R.id.lv_user_food);
        tv_car = findViewById(R.id.tv_car);
        tv_total_money = findViewById(R.id.tv_total_money);
        bv_unm = findViewById(R.id.bv_unm);
        ll_car = findViewById(R.id.ll_shopcar);
        bottomSheetLayout = findViewById(R.id.bottomSheetLayout);
        ll_car.setOnClickListener(this);
        tv_commit = findViewById(R.id.tv_commit);
        tv_commit.setOnClickListener(this);

    }

    public static void startActivity(Context context, Business business, User user1) {
        Intent intent = new Intent(context, UserFoodActivity.class);
        intent.putExtra("business", business);
        intent.putExtra("user", user1);
        context.startActivity(intent);
    }


    //根据商品id获取当前商品的采购数量
    public int getSelectedItemCountById(String g_id) {
        Goods temp = selectedList.get(g_id);
        if (temp == null) {
            return 0;
        }
        return temp.getNum();
    }

    /**
     * 点击事件
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_shopcar:
                showBottomSheet();
                break;
            case R.id.tv_commit:
                if (selectedList == null || selectedList.isEmpty()) {
                    ToastUtil.showToast(this, "购物车空着的哦");
                    break;
                } else {
                    showDialog();
                }
                break;
        }

    }

    //显示输入备注对话框
    private void showDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_commit_order, null);
        final EditText et_dialog_commit_other = dialogView.findViewById(R.id.et_dialog_commit_other);

        AlertDialog dialog = new AlertDialog.Builder(this).setView(dialogView).setTitle("有备注要写吗").create();
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "提交", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //点击dialog确认按钮
                String other = et_dialog_commit_other.getText().toString().trim();
                //提交订单
                presenter.commitOrder(user.getAccount(),business.getB_id(),selectedList.values(),other);
                //回退到商家界面
                //UserFoodActivity.this.finish();

            }
        });
        dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //点击dialog取消按钮
            }
        });

        dialog.show();

    }

    //创建购物车view
    private void showBottomSheet() {
        bottomSheet = createBottomSheetView();
        if (bottomSheetLayout.isSheetShowing()) {
            bottomSheetLayout.dismissSheet();
        } else {
            if (selectedList.size() != 0) {
                bottomSheetLayout.showWithSheetView(bottomSheet);
            }
        }
    }

    //查看购物车布局
    private View createBottomSheetView() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_user_bottom_sheet, (ViewGroup) getWindow().getDecorView(), false);
        MyListView lv_product = view.findViewById(R.id.lv_user_goods);
        TextView clear = view.findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearCart();
            }
        });
        productAdapter = new ProductAdapter(UserFoodActivity.this, goodsAdapter, selectedList);
        lv_product.setAdapter(productAdapter);
        return view;
    }

    //清空购物车
    public void clearCart() {
        selectedList.clear();
        if (goodkList.size() > 0) {
            for (Goods goods : goodkList) {
                goods.setNum(0);
            }
            goodsAdapter.notifyDataSetChanged();
        }
        update(true);
    }

    /**
     * adapter回调，添加购物车
     *
     * @param type            增加还是减少
     * @param goods           商品类型
     * @param refreshGoodList 是否修改
     */
    public void handlerCarNum(int type, Goods goods, boolean refreshGoodList) {
        if (type == 0) {
            Goods temp = selectedList.get(goods.getG_id());
            if (temp != null) {
                if (temp.getNum() < 2) {
                    goods.setNum(0);
                    selectedList.remove(goods.getG_id());

                } else {
                    int i = goods.getNum();
                    goods.setNum(--i);
                }
            }


        } else if (type == 1) {
            Goods temp = selectedList.get(goods.getG_id());
            if (temp == null) {
                goods.setNum(1);
                selectedList.put(goods.getG_id(), goods);
            } else {
                int i = goods.getNum();
                goods.setNum(++i);
            }
        }

        update(refreshGoodList);

    }

    //刷新布局 总价、购买数量等
    private void update(boolean refreshGoodList) {
        int size = selectedList.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            Goods item = selectedList.valueAt(i);
            count += item.getNum();
            totalMoney += item.getNum() * Double.parseDouble(item.getPrice());
        }
        tv_total_money.setText("￥" + String.valueOf(df.format(totalMoney)));
        totalMoney = 0.00;
        if (count < 1) {
            bv_unm.setVisibility(View.GONE);
        } else {
            bv_unm.setVisibility(View.VISIBLE);
        }

        bv_unm.setText(String.valueOf(count));


        if (goodsAdapter != null) {
            goodsAdapter.notifyDataSetChanged();
        }
        if (productAdapter != null) {
            productAdapter.notifyDataSetChanged();
        }

        if (bottomSheetLayout.isSheetShowing() && selectedList.size() < 1) {
            bottomSheetLayout.dismissSheet();
        }
    }

    /**
     * @param
     * @return void
     * @throws
     * @Description: 创建动画层
     */
    private ViewGroup createAnimLayout() {
        ViewGroup rootView = (ViewGroup) this.getWindow().getDecorView();
        LinearLayout animLayout = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        animLayout.setLayoutParams(lp);
        animLayout.setId(Integer.MAX_VALUE - 1);
        animLayout.setBackgroundResource(android.R.color.transparent);
        rootView.addView(animLayout);
        return animLayout;
    }

    private View addViewToAnimLayout(final ViewGroup parent, final View view,
                                     int[] location) {
        int x = location[0];
        int y = location[1];
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = x;
        lp.topMargin = y;
        view.setLayoutParams(lp);
        return view;
    }

    public void setAnim(final View v, int[] startLocation) {
        anim_mask_layout = null;
        anim_mask_layout = createAnimLayout();
        anim_mask_layout.addView(v);//把动画小球添加到动画层
        final View view = addViewToAnimLayout(anim_mask_layout, v, startLocation);
        int[] endLocation = new int[2];// 存储动画结束位置的X、Y坐标
        tv_car.getLocationInWindow(endLocation);
        // 计算位移
        int endX = 0 - startLocation[0] + 40;// 动画位移的X坐标
        int endY = endLocation[1] - startLocation[1];// 动画位移的y坐标

        TranslateAnimation translateAnimationX = new TranslateAnimation(0, endX, 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, 0, endY);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
        translateAnimationY.setRepeatCount(0);// 动画重复执行的次数
        translateAnimationY.setFillAfter(true);

        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(800);// 动画的执行时间
        view.startAnimation(set);
        // 动画监听事件
        set.setAnimationListener(new Animation.AnimationListener() {
            // 动画的开始
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            // 动画的结束
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.GONE);
            }
        });

    }

    //----------------------接口回调--------------------
    @Override
    public void setGoodsList(final ArrayList<Goods> goodsList) {
        goodkList = goodsList;
        if (goodsList.size() != 0)
            selectedList = new ArrayMap<>();
        for (Goods goods : goodsList) {
            Log.i(TAG, goods.toString());
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                goodsAdapter = new UserGoodsAdapter(UserFoodActivity.this, goodsList);
                lv_user_food.setAdapter(goodsAdapter);
            }
        });
    }

    @Override
    public void showGetGoodsFailed(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(UserFoodActivity.this, msg);
            }
        });
    }


}
