package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.bean.Business;

public class BusinessAddGoodsActivity extends AppCompatActivity {

    private Business business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_add_goods);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        business = (Business) intent.getSerializableExtra("business");
    }

    public static void startActivity(Context context, Business business) {
        Intent intent = new Intent(context, BusinessAddGoodsActivity.class);
        intent.putExtra("business", business);
        context.startActivity(intent);

    }
}
