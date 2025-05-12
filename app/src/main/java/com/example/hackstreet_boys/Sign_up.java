package com.example.hackstreet_boys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private EditText emailField, passwordField, confirmPasswordField, firstnameField, lastnameField;
    private Button signUpButton, backButton, signInButton;
    //private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.emailInput);
        passwordField = findViewById(R.id.passwordInput);
        confirmPasswordField = findViewById(R.id.confirmPasswordInput);
        firstnameField = findViewById(R.id.firstNameInput);
        lastnameField = findViewById(R.id.lastNameInput);
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
        String passwordConfirmation = confirmPasswordField.getText().toString().trim();
        String firstname = firstnameField.getText().toString().trim();
        String lastname = lastnameField.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        if ((passwordConfirmation.isEmpty()) || (!passwordConfirmation.equals(password))) {
            Toast.makeText(this, "Password confirmation doesn't match the password", Toast.LENGTH_SHORT).show();
            return;
        }

        if (firstname.isEmpty() || lastname.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
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
                        data.put("FirstName", firstname);
                        data.put("LastName", lastname);

                        db.collection("Users").document(user.getEmail()).set(data);


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
