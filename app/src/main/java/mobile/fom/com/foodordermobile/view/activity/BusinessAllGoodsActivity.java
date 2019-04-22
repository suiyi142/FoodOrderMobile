package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.adapter.BusinessGoodsAdapter;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessAllGoodsView;

public class BusinessAllGoodsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, IBusinessAllGoodsView {

    private Business business;
    private SwipeRefreshLayout srf_goods;
    private RecyclerView rcv_goods;
    private BusinessPresenter presenter;
    private List<Goods> goodsArrayList = new ArrayList<>();
    private BusinessGoodsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_all_goods);
        presenter = new BusinessPresenter(this);
        initData();
        initView();
        presenter.getAllGoods(business.getB_id());
    }

    private void initData() {
        Intent intent = getIntent();
        business = (Business) intent.getSerializableExtra("business");
    }

    private void initView() {
        srf_goods = findViewById(R.id.srf_business_goods);
        rcv_goods = findViewById(R.id.rcv_business_goods);
        srf_goods.setOnRefreshListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcv_goods.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new BusinessGoodsAdapter(goodsArrayList);
        rcv_goods.setAdapter(adapter);
    }


    public static void startActivity(Context context, Business business) {
        Intent intent = new Intent(context, BusinessAllGoodsActivity.class);
        intent.putExtra("business", business);
        context.startActivity(intent);
    }

    @Override
    public void onRefresh() {
        presenter.getAllGoods(business.getB_id());
    }

    //----------------------------------------------回调接口------------------------
    @Override
    public void setGoodsList(final List<Goods> goodsList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (srf_goods.isRefreshing())
                    srf_goods.setRefreshing(false);
                goodsArrayList.clear();
                goodsArrayList.addAll(goodsList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void showErrorMsg(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessAllGoodsActivity.this,msg);
            }
        });
    }
}
