package com.example.hackstreet_boys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_up extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailField, passwordField;
    private Button signInButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.newPasswordEditText);
        passwordField = findViewById(R.id.confirmPasswordEditText);
        signInButton = findViewById(R.id.btnContinue);
        backButton = findViewById(R.id.btnBack);

        signInButton.setOnClickListener(view -> {
            signInUser();
            Intent intent = new Intent(Sign_up.this, Sign_inScreen.class);
            startActivity(intent);
            finish(); // Optional: closes the current activity
        });

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(Sign_up.this, Sign_inScreen.class);
            startActivity(intent);
            finish(); // Optional: closes the current activity
        });
    }

    private void signInUser() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(Sign_up.this, "Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Sign_up.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
