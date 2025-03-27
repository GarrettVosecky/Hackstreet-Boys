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

        SignaturePanel.setOnTouchListener(new View.OnTouchListener() {
            int LastX;
            int LastY;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        break;
                    case MotionEvent.ACTION_MOVE:
                        drawCanvas.drawLine(LastX, LastY, x, y, drawPaint);
                        Log.d("Tag", x + " " + y);
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }



                return true;
            }
        });
        {

        };

    }
}
