package com.example.hackstreet_boys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.graphics.*;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Signature extends AppCompatActivity {
    private DrawingView drawing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signature);
        View SignaturePanel = findViewById(R.id.panel);
        Path drawPath = new Path();
        Canvas drawCanvas = new Canvas();

        Paint drawPaint = new Paint();
        drawPaint.setColor(Color.BLACK);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);
        Log.d("Setup", "hi");
        drawing = new DrawingView(findViewById(R.id.panel).getContext());
        drawing.onDraw(drawCanvas);
        Log.d("Setup", "ok");
    }
}
