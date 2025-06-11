package com.example.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View {
    private Paint paint;
    private float direction = 0;
    private Bitmap compassNeedle;

    public CompassView(Context context) {
        super(context);
        init();
    }
    public CompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        compassNeedle = BitmapFactory.decodeResource(getResources(), R.drawable.ic_compass_needle);
    }

    public void updateDirection(float degrees) {
        // Làm mượt animation
        direction = (direction + ((degrees - direction + 360) % 360) * 0.1f) % 360;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        canvas.save();
        canvas.translate(width / 2, height / 2);
        canvas.rotate(-direction);
        canvas.drawBitmap(compassNeedle, -compassNeedle.getWidth() / 2, -compassNeedle.getHeight() / 2, paint);
        canvas.restore();
    }
}