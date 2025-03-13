package com.example.hackstreet_boys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Sign_inScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.sign_in_screen);

        // Get references to UI elements
        Button button = findViewById(R.id.forgotPassBtn);
        Button button2 = findViewById(R.id.signUpBtn);

        // Set button click event
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start SecondActivity
                Intent intent = new Intent(Sign_inScreen.this, forget_passwordScreen.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an Intent to start SecondActivity
                Intent intent = new Intent(Sign_inScreen.this, Sign_UpScreen.class);
                startActivity(intent);
            }
        });
    }
}


