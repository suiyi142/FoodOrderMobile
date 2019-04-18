package mobile.fom.com.foodordermobile;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.TreeMap;

import mobile.fom.com.foodordermobile.bean.Goods;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        HashMap<Goods,Integer> map = new HashMap<>();
        Goods goods = new Goods();
        map.put(goods, 1);
        System.out.print(map.get(goods));
        System.out.print(map.get(new Goods()));
    }
}
