package com.example.hackstreet_boys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Sign_inScreen extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText usernameEditText, passwordEditText;
    private Button loginButton, registerButton, forgotPassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_screen);  // Your sign-in XML layout

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance();

        // Link UI elements
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        loginButton = findViewById(R.id.loginButton);
        registerButton = findViewById(R.id.registerButton);
        forgotPassButton = findViewById(R.id.forgotPassButton);

        // Set button click listeners
        loginButton.setOnClickListener(view -> handleLogin());

        registerButton.setOnClickListener(view -> {
            Intent intent = new Intent(Sign_inScreen.this, Sign_up.class);
            startActivity(intent);
            finish(); // Optional: closes the current activity
        });

        // Adjust padding for system bars (optional)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Handle Login
    private void handleLogin() {
        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase login method
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(this, "Login successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        // You can now navigate to another activity or update UI
                        Intent intent = new Intent(Sign_inScreen.this, JobsList.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Login failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Handle Registration
    private void handleRegister() {
        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase register method
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        Toast.makeText(this, "Registration successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                        // You can now navigate to another activity or update UI
                    } else {
                        Toast.makeText(this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}


