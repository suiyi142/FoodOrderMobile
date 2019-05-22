package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.adapter.UserBusinessAdapter;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.bean.User;
import mobile.fom.com.foodordermobile.presenter.UserPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IUserView;

public class UserActivity extends AppCompatActivity implements IUserView, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ListView lv_user_list;
    private ArrayList<Business> businessList;
    private UserPresenter presenter;
    private SwipeRefreshLayout srf_user;
    private User user;
    private TextView tv_user_order;
    private TextView tv_user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        presenter = new UserPresenter(this);
        initValue();
        initView();
        presenter.getBusiness();

    }

    /**
     * 初始化值
     */
    private void initValue() {
        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("user");
    }

    /**
     * 初始化view
     */
    private void initView() {
        lv_user_list = findViewById(R.id.lv_user_list);
        srf_user = findViewById(R.id.srf_user);
        lv_user_list.setOnItemClickListener(this);
        srf_user.setOnRefreshListener(this);
        tv_user_order = findViewById(R.id.tv_user_order);
        tv_user_name = findViewById(R.id.tv_user_name);
        tv_user_name.setText("欢迎您：" + user.getName());
        tv_user_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserOrderActivity.startActivity(UserActivity.this, user.getAccount());
            }
        });
    }

    /*
    列表点击事件
     */
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (businessList.get(i).getCurrent_seats() == 0)
            ToastUtil.showToast(this, "该餐厅没有座位了");
        else
            UserFoodActivity.startActivity(this, businessList.get(i), user);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        presenter.getBusiness();
    }

    public static void startActivity(Context context, User user) {
        Intent intent = new Intent(context, UserActivity.class);
        intent.putExtra("user", user);
        context.startActivity(intent);
    }

    //----------------------回调-----------------------

    /**
     * 刷新列表回调
     *
     * @param businessList
     */
    @Override
    public void setBusinessList(ArrayList<Business> businessList) {

        this.businessList = businessList;

        final UserBusinessAdapter adapter = new UserBusinessAdapter(this, R.layout.item_user_business, businessList);
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                lv_user_list.setAdapter(adapter);
                if (srf_user.isRefreshing()) {
                    srf_user.setRefreshing(false);
                    ToastUtil.showToast(UserActivity.this, "刷新成功");
                }
            }
        });
    }

    /**
     * 获取列表失败
     *
     * @param msg
     */
    @Override
    public void showGetBusinessFailed(final String msg) {
        runOnUiThread(new Thread() {
            @Override
            public void run() {
                ToastUtil.showToast(UserActivity.this, msg);
            }
        });
    }
}
