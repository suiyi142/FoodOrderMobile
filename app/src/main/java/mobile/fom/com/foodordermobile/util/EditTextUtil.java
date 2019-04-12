package mobile.fom.com.foodordermobile.util;

import android.text.TextUtils;
import android.widget.EditText;

import java.util.List;

public class EditTextUtil {
    /*
判断有没有没输入的内容
 */
    public static boolean isEmpty(List<EditText> editTextList) {
        boolean b;
        for (EditText editText : editTextList) {
            b = TextUtils.isEmpty(editText.getText().toString().trim());
            if (b)
                return true;
        }
        return false;
    }
}
