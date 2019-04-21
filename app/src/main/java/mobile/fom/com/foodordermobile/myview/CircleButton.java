package mobile.fom.com.foodordermobile.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import mobile.fom.com.foodordermobile.R;

public class CircleButton extends View {

    private static final String TAG = "CircleButton";

    Paint paint;
    private String text = "";
    private int color;
    private int textColor;

    public CircleButton(Context context) {
        super(context,null);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.CircleButton);
        color = array.getColor(R.styleable.CircleButton_color, Color.BLACK);
        textColor = array.getColor(R.styleable.CircleButton_mTextColor, Color.BLACK);
        text = array.getString(R.styleable.CircleButton_myText);
        array.recycle();

        Log.i(TAG, color+"");
        Log.i(TAG, textColor+"");
        Log.i(TAG, text);
        paint = new Paint();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        实例化画笔对象
//        给画笔设置颜色
        paint.setColor(color);
//        设置画笔属性
        paint.setStyle(Paint.Style.FILL);//画笔属性是实心圆
        //paint.setStyle(Paint.Style.STROKE);//画笔属性是空心圆
        paint.setStrokeWidth(8);//设置画笔粗细
        paint.setAlpha(230);
        paint.setAntiAlias(true);

        /*四个参数：
                参数一：圆心的x坐标
                参数二：圆心的y坐标
                参数三：圆的半径
                参数四：定义好的画笔
                */
        int r = getWidth()<getHeight()?getWidth():getHeight();
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, r/2, paint);
        paint.setColor(textColor);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text, getWidth() / 2, getHeight() / 2 + 25, paint);
    }
}
