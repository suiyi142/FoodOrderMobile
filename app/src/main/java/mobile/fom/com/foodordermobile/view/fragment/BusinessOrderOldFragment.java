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

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.adapter.BusinessOrderAdapter;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.bean.Order;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessOrderView;

public class BusinessOrderOldFragment extends Fragment implements IBusinessOrderView, SwipeRefreshLayout.OnRefreshListener {
    private View view;
    Business business;
    private BusinessPresenter presenter;
    private SwipeRefreshLayout srl_business_order_old;
    private RecyclerView rcv_business_order_old;
    List<Order> orderList = new ArrayList<Order>();
    private BusinessOrderAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null)
            view = inflater.inflate(R.layout.fragment_business_order_old, null);
        business = (Business) getArguments().getSerializable("business");
        presenter = new BusinessPresenter(this);
        initView();
        return view;
    }

    private void initView() {
        srl_business_order_old = view.findViewById(R.id.srl_business_order_old);
        srl_business_order_old.setRefreshing(true);
        srl_business_order_old.setOnRefreshListener(this);
        rcv_business_order_old = view.findViewById(R.id.rcv_business_order_old);

        //设置列表
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rcv_business_order_old.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new BusinessOrderAdapter(orderList);
        rcv_business_order_old.setAdapter(adapter);

        //获取订单
        presenter.getOldOrder(business.getB_id());
    }

    //获取本fragment
    public static BusinessOrderOldFragment newInstance(Business business) {
        BusinessOrderOldFragment fragment = new BusinessOrderOldFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("business", business);
        fragment.setArguments(bundle);
        return fragment;

    }

    @Override
    public void onRefresh() {
        presenter.getOldOrder(business.getB_id());
    }

//-------------------------回调-----------------------------------

    /**
     * 展示接收到的订单
     *
     * @param newOrderList
     */
    @Override
    public void showOrder(final List<Order> newOrderList) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (srl_business_order_old.isRefreshing())
                    srl_business_order_old.setRefreshing(false);
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
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessOrderOldFragment.this.getContext(), msg);
            }
        });
    }

}
