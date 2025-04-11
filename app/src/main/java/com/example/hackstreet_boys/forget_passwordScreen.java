package com.example.hackstreet_boys;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class forget_passwordScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.forget_password_screen);

        // Find the button by its ID
        Button backButton = findViewById(R.id.ForgotPassBack);
        Button backButton2 = findViewById(R.id.ConfirmPassBtn);

        // Set an OnClickListener to navigate back to MainActivity
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forget_passwordScreen.this, Sign_inScreen.class);
                startActivity(intent);
                finish(); // Close SecondActivity to prevent stacking multiple instances
            }
        });


        backButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forget_passwordScreen.this, Sign_inScreen.class);
                startActivity(intent);
                finish(); // Close SecondActivity to prevent stacking multiple instances
            }
        });
    }
}

