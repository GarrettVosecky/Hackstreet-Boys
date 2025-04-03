package com.example.hackstreet_boys;

import android.os.Bundle;
import android.util.Log;
import android.graphics.*;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Signature extends AppCompatActivity {
    private DrawingView drawingView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signature);
        Canvas drawCanvas = new Canvas();
        Log.d("Setup", "hi");
        drawingView = findViewById(R.id.pad);


        Button ClearButton = findViewById(R.id.clear);

        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawingView.clearDrawing();
            }
        });
    }
}
