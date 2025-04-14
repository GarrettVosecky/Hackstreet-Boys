package com.example.hackstreet_boys;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class forget_passwordScreen extends AppCompatActivity {

    private EditText newPasswordEditText, confirmPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.forget_password_screen);

        // Find the button by its ID
        Button ForgotPassBack = findViewById(R.id.ForgotPassBack);
        Button ConfirmPassBtn = findViewById(R.id.ConfirmPassBtn);

        // Set an OnClickListener to navigate back to MainActivity
        ForgotPassBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(forget_passwordScreen.this, Sign_inScreen.class);
                startActivity(intent);
                finish(); // Close SecondActivity to prevent stacking multiple instances
            }
        });


        ConfirmPassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText emailEditText = findViewById(R.id.emailEditText);  // Add this to your layout
                String email = emailEditText.getText().toString().trim();

                if (email.isEmpty()) {
                    Toast.makeText(forget_passwordScreen.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(forget_passwordScreen.this, "Check your email for the reset link", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(forget_passwordScreen.this, Sign_inScreen.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Log.e("TAG2", "Error sending reset email", task.getException());
                                Toast.makeText(forget_passwordScreen.this, "Failed to send reset email: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                }
            });
        }
    }

