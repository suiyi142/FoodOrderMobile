package mobile.fom.com.foodordermobile.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SpUtil {
	
	private static final String GREENHOUSE = "smarthouse";
	private static  SpUtil instance;
	
	static {
		instance = new SpUtil();
	}
	
	public static SpUtil getInstance(){
		if(instance==null){
			instance = new SpUtil();
		}
		return instance;
	}
	
	public static SharedPreferences getSharedPreperence(Context context){
		SharedPreferences sp = context.getSharedPreferences(GREENHOUSE, Context.MODE_PRIVATE);
		return sp;
	}
	
	public static void setStringSharedPreperence(SharedPreferences sp, String key, String value){
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}
	
}
