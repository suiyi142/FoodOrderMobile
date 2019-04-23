package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Goods;
import mobile.fom.com.foodordermobile.presenter.BusinessPresenter;
import mobile.fom.com.foodordermobile.util.EditTextUtil;
import mobile.fom.com.foodordermobile.util.ToastUtil;
import mobile.fom.com.foodordermobile.view.IBusinessGoodsView;

public class BusinessUpdateGoodsActivity extends AppCompatActivity implements View.OnClickListener, IBusinessGoodsView {

    private Goods goods;
    private Button bt_delete;
    private Button bt_update;
    private EditText et_other;
    private EditText et_price;
    private EditText et_name;
    private ArrayList<EditText> editTextArrayList = new ArrayList<>();
    private BusinessPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_updata_goods);
        presenter = new BusinessPresenter(this);
        initData();
        initView();
        setData2View();
    }

    private void initData() {
        Intent intent = getIntent();
        goods = (Goods) intent.getSerializableExtra("goods");
    }

    private void initView() {
        et_name = findViewById(R.id.et_business_update_name);
        et_price = findViewById(R.id.et_business_update_price);
        et_other = findViewById(R.id.et_business_update_other);
        editTextArrayList.add(et_name);
        editTextArrayList.add(et_price);
        editTextArrayList.add(et_other);
        bt_update = findViewById(R.id.bt_business_update_submit);
        bt_delete = findViewById(R.id.bt_business_add_delete);
        bt_update.setOnClickListener(this);
        bt_delete.setOnClickListener(this);

    }

    //给EditText设置数据
    private void setData2View() {
        et_name.setText(goods.getName());
        et_price.setText(goods.getPrice());
        et_other.setText(goods.getOther());
    }

    public static void startActivity(Context context, Goods goods) {
        Intent intent = new Intent(context, BusinessUpdateGoodsActivity.class);
        intent.putExtra("goods", goods);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_business_update_submit:
                if (EditTextUtil.isEmpty(editTextArrayList)) {
                    ToastUtil.showToast(this, "有值为空");
                    return;
                } else {
                    goods.setName(et_name.getText().toString().trim());
                    goods.setOther(et_other.getText().toString().trim());
                    goods.setPrice(et_price.getText().toString().trim());
                    presenter.updateGoods(goods);
                }

                break;
            case R.id.bt_business_add_delete:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        new AlertDialog.Builder(this)
                .setMessage("确定删除？")
                .setTitle("警告")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteGoods(goods.getG_id());
                    }
                }).create().show();
    }

    //----------------------------------------回调方法--------------------------
    @Override
    public void changeSuccess(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessUpdateGoodsActivity.this, msg);
                BusinessUpdateGoodsActivity.this.finish();
            }
        });
    }

    @Override
    public void changeFailed(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(BusinessUpdateGoodsActivity.this, msg);
            }
        });
    }
}
