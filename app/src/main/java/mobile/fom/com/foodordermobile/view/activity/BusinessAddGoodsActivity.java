package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Business;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.EditTextUtil;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessGoodsView;

public class BusinessAddGoodsActivity extends AppCompatActivity implements View.OnClickListener, IBusinessGoodsView {

    private Business business;
    private ImageView iv_add;
    private EditText et_name;
    private EditText et_price;
    private EditText et_other;
    private Button bt_submit;
    private ArrayList<EditText> editTextArrayList = new ArrayList<>();
    private BusinessPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_add_goods);
        presenter = new BusinessPresenter(this);
        initData();
        initView();
    }

    private void initData() {
        Intent intent = getIntent();
        business = (Business) intent.getSerializableExtra("business");
    }

    private void initView() {
        iv_add = findViewById(R.id.iv_business_update);
        et_name = findViewById(R.id.et_business_update_name);
        et_price = findViewById(R.id.et_business_update_price);
        et_other = findViewById(R.id.et_business_update_other);
        editTextArrayList.add(et_name);
        editTextArrayList.add(et_price);
        editTextArrayList.add(et_other);
        bt_submit = findViewById(R.id.bt_business_update_submit);
        bt_submit.setOnClickListener(this);
    }

    public static void startActivity(Context context, Business business) {
        Intent intent = new Intent(context, BusinessAddGoodsActivity.class);
        intent.putExtra("business", business);
        context.startActivity(intent);

    }

    @Override
    public void onClick(View v) {
        if (EditTextUtil.isEmpty(editTextArrayList)){
            ToastUtil.showToast(this,"有值为空");
            return;
        }
        String name = et_name.getText().toString().trim();
        String price = et_price.getText().toString().trim();
        String other = et_other.getText().toString().trim();
        presenter.addGoods(business.getB_id(),name,price,other);


    }

    //------------------------------接口回调-----------------------
    @Override
    public void changeSuccess(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessAddGoodsActivity.this,msg);
                BusinessAddGoodsActivity.this.finish();
            }
        });
    }

    @Override
    public void changeFailed(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessAddGoodsActivity.this,msg);
            }
        });
    }
}
