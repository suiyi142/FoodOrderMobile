package mobile.fom.com.foodordermobile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class App extends AppCompatActivity {

    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
    }

    public static Context getInstance() {
        return context;
    }
}

