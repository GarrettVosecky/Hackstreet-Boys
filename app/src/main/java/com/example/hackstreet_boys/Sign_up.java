package com.example.hackstreet_boys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Sign_up extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailField, passwordField;
    private Button signUpButton, backButton, signInButton;
    //private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.inputText);
        passwordField = findViewById(R.id.inputText2);
        signInButton = findViewById(R.id.btnContinue);
        backButton = findViewById(R.id.btnBack);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                Intent intent = new Intent(Sign_up.this, Sign_inScreen.class);
                startActivity(intent);
                finish(); // Optional: closes the current activity
            }
        });

        backButton.setOnClickListener(view -> {
            finish(); // Goes back to the previous activity
        });
    }

    private void registerUser() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                   // progressDialog.dismiss();

                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(Sign_up.this, "Welcome " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        FirebaseFirestore  db = FirebaseFirestore.getInstance();
                        Map<String, Object> data = new HashMap<>();
                        data.put("VerificationLevel", 1);
                        
                        db.collection("Users").document(user.getUid()).set(data);


                    } else {
                        Toast.makeText(Sign_up.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
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
