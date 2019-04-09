package mobile.fom.com.foodordermobile.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import mobile.fom.com.foodordermobile.R;
import mobile.fom.com.foodordermobile.util.ToastUtil;

public class BusinessRegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_register_business_address;
    private EditText et_register_business_password;
    private EditText et_register_business_re_password;
    private EditText et_register_business_max_seats;
    private EditText et_register_business_other;
    private Button bt_register_business;
    private List<EditText> editTextList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_register);
        initView();

    }

    /*
        找到相关控件
    */
    private void initView() {
        et_register_business_address = findViewById(R.id.et_register_business_address);
        et_register_business_password = findViewById(R.id.et_register_business_password);
        et_register_business_re_password = findViewById(R.id.et_register_business_re_password);
        et_register_business_max_seats = findViewById(R.id.et_register_business_max_seats);
        et_register_business_other = findViewById(R.id.et_register_business_other);
        bt_register_business = findViewById(R.id.bt_register_business);
        //将editText添加到一个list里
        editTextList = new ArrayList<>();
        editTextList.add(et_register_business_address);
        editTextList.add(et_register_business_password);
        editTextList.add(et_register_business_re_password);
        editTextList.add(et_register_business_max_seats);
        editTextList.add(et_register_business_other);
        bt_register_business.setOnClickListener(this);

    }

    /*
    开启这个activity
     */
    protected static void startActivity(Context context) {
        context.startActivity(new Intent(context, BusinessRegisterActivity.class));
    }

    @Override
    public void onClick(View view) {
        isEmpty(editTextList);
    }

    private boolean isEmpty(List<EditText> editTextList) {
        boolean b = false;
        for (EditText editText : editTextList){
            b = TextUtils.isEmpty(editText.getText().toString().trim());
        }
        return b;
    }
}
