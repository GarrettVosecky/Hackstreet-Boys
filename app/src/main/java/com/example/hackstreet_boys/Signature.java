package com.example.hackstreet_boys;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Signature extends AppCompatActivity {
    private com.example.hackstreet_boys.DrawingView drawingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.signature);

        DrawingView drawingView = findViewById(R.id.pad);
        if (drawingView == null) {
            Log.e("tag", "null");
        }

        Button ClearButton = findViewById(R.id.clear);

        ClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
