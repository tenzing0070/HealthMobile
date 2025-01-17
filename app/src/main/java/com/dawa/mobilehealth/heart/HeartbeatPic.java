package com.dawa.mobilehealth.heart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.dawa.mobilehealth.R;


public class HeartbeatPic extends View {

    private static final Matrix matrix = new Matrix();
    private static final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static Bitmap blackBitmap = null;
    private static Bitmap redBitmap = null;

    private static int parentWidth = 0;
    private static int parentHeight = 0;

    public HeartbeatPic(Context context, AttributeSet attr) {
        super(context, attr);

        blackBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blackheart);
        redBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.redheart);
    }

    public HeartbeatPic(Context context) {
        super(context);

        blackBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.blackheart);
        redBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.redheart);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(parentWidth, parentHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (canvas == null) throw new NullPointerException();

        Bitmap bitmap = null;
        if (HeartRateMonitorActivity.getCurrent() == HeartRateMonitorActivity.TYPE.GREEN) bitmap = blackBitmap;
        else bitmap = redBitmap;

        int bitmapX = bitmap.getWidth() / 2;
        int bitmapY = bitmap.getHeight() / 2;

        int parentX = parentWidth / 2;
        int parentY = parentHeight / 2;

        int centerX = parentX - bitmapX;
        int centerY = parentY - bitmapY;

        matrix.reset();
        matrix.postTranslate(centerX, centerY);
        canvas.drawBitmap(bitmap, matrix, paint);
    }
}
