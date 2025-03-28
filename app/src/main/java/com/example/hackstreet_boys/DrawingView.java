package com.example.hackstreet_boys;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.*;
import android.content.Context;

public class DrawingView extends View {

    private Path drawPath;
    private Paint drawPaint;
    private Paint canvasPaint;
    private Canvas drawCanvas;

    public DrawingView(Context context) {
        super(context);
        Log.d("Setup", "hi");
        setupDrawing();
        Log.d("Setup", "done");
    }

    private void setupDrawing() {
        drawPath = new Path();
        drawPaint = new Paint();
        drawCanvas = new Canvas();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
        drawCanvas.drawLine(0,0,100,100, drawPaint);
        Log.d("Setup", "cool");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        drawCanvas = new Canvas();
    }

    protected void onDraw(Canvas canvas) {
        Log.d("Tag", "drawing.");
        canvas.drawPath(drawPath, drawPaint);
        canvas.drawPath(drawPath, canvasPaint);
    }

    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        Log.d("Tag", touchX + " " + touchY);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;
            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);
                Log.d("Tag", touchX + " " + touchY);
                break;
            case MotionEvent.ACTION_UP:
                drawCanvas.drawPath(drawPath, drawPaint);
                drawPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }
}
