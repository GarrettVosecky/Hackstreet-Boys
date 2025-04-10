package com.example.hackstreet_boys;


import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Log.d("TEST", "Opened forgot Password page");
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.forget_password_screen);

        // Find the button by its ID
        Button ForgetPassBack = findViewById(R.id.ForgotPassBack);
        Button ConfirmPassBtn = findViewById(R.id.ConfirmPassBtn);


        // Set an OnClickListener to navigate back to MainActivity
        ForgetPassBack.setOnClickListener(new View.OnClickListener() {
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

                String newPassword = newPasswordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();

                newPasswordEditText = findViewById(R.id.newPasswordEditText);
                confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);

                if (!newPassword.equals(confirmPassword)) {
                    //errorMessageTextView.setVisibility(View.VISIBLE);
                    return;
                } else {
                    //errorMessageTextView.setVisibility(View.GONE);

                }

                if (newPassword.length() < 6) {
                    Toast.makeText(forget_passwordScreen.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }

                ResetPassword(newPassword);
            }
        });
    }

    private void ResetPassword(String newPassword){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) {
            Toast.makeText(this, "No user logged in. Please Sign in", Toast.LENGTH_LONG).show();
            Log.d("NO_USER", "No User");
            return;
        }

        if(user != null) {
            Log.d("ResetTest", "Reset Password");

                user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                // Password updated successfully
                                //Log.d(TAG, "User password updated.");

                                //Changes to the next screen
                                Intent intent = new Intent(forget_passwordScreen.this, Sign_inScreen.class);
                                startActivity(intent);
                                finish();

                            } else {
                                // Incorrect Password
                                //Log.e(TAG, "Error updating password", task.getException());
                            }
                        }
                    });
                }
    }
}

