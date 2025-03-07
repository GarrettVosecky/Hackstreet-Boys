package com.example.hackstreet_boys;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class Sign_inScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);

        // Get references to UI elements
        //TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.forgotPassButton);

        // Set button click event
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start SecondActivity
                Intent intent = new Intent(Sign_inScreen.this, forget_passwordScreen.class);
                startActivity(intent);
            }
        });
    }
}
