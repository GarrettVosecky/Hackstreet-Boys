package com.example.hackstreet_boys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Sign_inScreen extends AppCompatActivity {
    private static final String TAG = "Sign_inScreen";
    private FirebaseAuth auth;
    private EditText usernameEditText, passwordEditText;
    private Button loginButton, registerButton;
    private TextView errorTextView; // Add a TextView for the error message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);  // Your sign-in XML layout

        // Initialize FirebaseAuth instance
        auth = FirebaseAuth.getInstance();

        // Link UI elements
        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
      //  loginButton = findViewById(R.id.loginButton);
       // registerButton = findViewById(R.id.registerButton);

        // Set button click listeners
        loginButton.setOnClickListener(view -> handleLogin());
       // registerButton.setOnClickListener(view -> handleRegister());

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

        // Clear any previous error message
        errorTextView.setVisibility(View.INVISIBLE);
        errorTextView.setText("");

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase login method
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(Sign_inScreen.this, "Login successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            // You can now navigate to another activity or update UI
                        } else {
                            // Sign in fails
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthInvalidUserException e) {
                                // User not found
                                usernameEditText.setError("User not found");
                                usernameEditText.requestFocus();
                                errorTextView.setVisibility(View.VISIBLE);
                                errorTextView.setText("Error: User not found");
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                // Incorrect password or invalid email
                                // Use a generic error message for security
                                errorTextView.setVisibility(View.VISIBLE);
                                errorTextView.setText("Error: Invalid username or password.");
                            } catch (Exception e) {
                                // Handle other exceptions
                                Log.e(TAG, "Authentication error", e);
                                Toast.makeText(Sign_inScreen.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                errorTextView.setVisibility(View.VISIBLE);
                                errorTextView.setText("Error: Authentication failed.");
                            }
                        }
                    }
                });
    }

    // Handle Registration
    private void handleRegister() {
        String email = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Clear any previous error message
        errorTextView.setVisibility(View.INVISIBLE);
        errorTextView.setText("");

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }

        // Firebase register method
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(Sign_inScreen.this, "Registration successful: " + user.getEmail(), Toast.LENGTH_SHORT).show();
                            // You can now navigate to another activity or update UI
                        } else {
                            // Registration fails
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthUserCollisionException e) {
                                // User already exists
                                usernameEditText.setError("User already exists");
                                usernameEditText.requestFocus();
                                errorTextView.setVisibility(View.VISIBLE);
                                errorTextView.setText("Error: User already exists");
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                // Invalid email
                                usernameEditText.setError("Invalid email");
                                usernameEditText.requestFocus();
                                errorTextView.setVisibility(View.VISIBLE);
                                errorTextView.setText("Error: Invalid email");
                            } catch (Exception e) {
                                // Handle other exceptions
                                Log.e(TAG, "Registration error", e);
                                Toast.makeText(Sign_inScreen.this, "Registration failed.",
                                        Toast.LENGTH_SHORT).show();
                                errorTextView.setVisibility(View.VISIBLE);
                                errorTextView.setText("Error: Registration failed.");
                            }
                        }
                    }
                });
    }
}
