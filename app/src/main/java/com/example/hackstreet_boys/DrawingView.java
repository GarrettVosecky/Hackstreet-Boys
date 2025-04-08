package com.example.hackstreet_boys;

import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.*;
import android.content.Context;
import android.widget.Button;

import androidx.annotation.Nullable;

public class DrawingView extends View {

    private Path drawPath;
    private Paint drawPaint;
    private Paint canvasPaint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Log.d("Setup", "hi");
        init();
    }

    private void init() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawCanvas = new Canvas();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(10f);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
        Log.d("Setup", "cool");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        canvas.drawPath(drawPath, drawPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                drawCanvas.drawPath(drawPath, drawPaint); // Draw on the canvas
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint); // Finalize the drawing
                drawPath.reset();
                invalidate();
                break;
            default:
                return false;
        }
        return true;
    }

    public void clearDrawing() {
        drawPath.reset();
        drawCanvas.drawColor(0, PorterDuff.Mode.CLEAR); // Clear the canvas
        invalidate();
    }
}
