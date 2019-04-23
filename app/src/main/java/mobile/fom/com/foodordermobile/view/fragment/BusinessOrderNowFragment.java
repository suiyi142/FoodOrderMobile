package mobile.fom.com.foodordermobile.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.adapter.BusinessOrderAdapter;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Order;
import mobile.fom.com.foodordermobile.myview.CircleButton;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessOrderView;

public class BusinessOrderNowFragment extends Fragment implements IBusinessOrderView, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private View view;
    Business business;
    private CircleButton cb_research;
    private RecyclerView rcv_business_order_now;
    private SwipeRefreshLayout srl_business_order_now;
    private BusinessPresenter presenter;
    List<Order> orderList = new ArrayList<Order>();
    private BusinessOrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_business_order_now, null);
        business = (Business) getArguments().getSerializable("business");
        presenter = new BusinessPresenter(this);
        initView();
        return view;
    }

    private void initView() {
        srl_business_order_now = view.findViewById(R.id.srl_business_order_now);
        rcv_business_order_now = view.findViewById(R.id.rcv_business_order_now);
        srl_business_order_now.setRefreshing(true);
        srl_business_order_now.setOnRefreshListener(this);
        cb_research = view.findViewById(R.id.cb_research);
        cb_research.setOnClickListener(this);

        //设置列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv_business_order_now.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new BusinessOrderAdapter(orderList, this);
        rcv_business_order_now.setAdapter(adapter);

        //获取订单
        presenter.getNewOrder(business.getB_id());
    }

    //获取本fragment
    public static BusinessOrderNowFragment newInstance(Business business) {
        BusinessOrderNowFragment fragment = new BusinessOrderNowFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("business", business);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onRefresh() {
        presenter.getNewOrder(business.getB_id());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_research:
                srl_business_order_now.setRefreshing(true);
                presenter.getNewOrder(business.getB_id());
                break;
        }
    }
//-------------------------回调-----------------------------------

    /**
     * 展示接收到的订单
     *
     * @param newOrderList
     */
    @Override
    public void showOrder(final List<Order> newOrderList) {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (srl_business_order_now.isRefreshing())
                    srl_business_order_now.setRefreshing(false);
                orderList.clear();
                orderList.addAll(newOrderList);
                adapter.notifyDataSetChanged();

            }
        });

    }

    /**
     * 展示错误信息
     *
     * @param msg
     */
    @Override
    public void showError(final String msg) {
        Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessOrderNowFragment.this.getContext(), msg);
            }
        });
    }

}
