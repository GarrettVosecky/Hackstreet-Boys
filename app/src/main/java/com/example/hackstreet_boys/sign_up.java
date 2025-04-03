package com.example.hackstreet_boys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;

public class sign_up extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailField, passwordField;
    private Button signUpButton, backButton;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.inputText);
        passwordField = findViewById(R.id.inputText2);
        signUpButton = findViewById(R.id.btnContinue);
        backButton = findViewById(R.id.btnBack);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Creating account...");
        progressDialog.setCancelable(false);

        signUpButton.setOnClickListener(view -> registerUser());

        backButton.setOnClickListener(view -> finish()); // Goes back to the previous activity
    }

    private void registerUser() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(sign_up.this, "Account created! Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();

                        // Navigate to another activity (e.g., main screen)
                        startActivity(new Intent(sign_up.this, Sign_inScreen.class));
                        finish(); // Close sign-up screen
                    } else {
                        handleSignUpError(task.getException());
                    }
                });
    }

    private void handleSignUpError(Exception e) {
        if (e instanceof FirebaseAuthWeakPasswordException) {
            Toast.makeText(this, "Weak password. Please use a stronger one.", Toast.LENGTH_SHORT).show();
        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
            Toast.makeText(this, "Invalid email format.", Toast.LENGTH_SHORT).show();
        } else if (e instanceof FirebaseAuthUserCollisionException) {
            Toast.makeText(this, "Email already in use. Try logging in.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Registration failed. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}
