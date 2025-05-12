package com.example.hackstreet_boys;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;


public class AddFragment extends AppCompatActivity {

    private EditText jobInfoEditText, titleIdEditText, locationEditText;
    private Button saveButton;

    private FirebaseAuth auth;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.fragment_add);
        // Initialize Firebase reference
        auth = FirebaseAuth.getInstance();

        // Link views
        jobInfoEditText = findViewById(R.id.editJobDescription);
        titleIdEditText = findViewById(R.id.editJobTitle);
        locationEditText = findViewById(R.id.editJobLocation);
        saveButton = findViewById(R.id.addButton);

        saveButton.setOnClickListener(v -> saveJobToFirebase());
    }

    private void saveJobToFirebase() {
        String jobInfo = jobInfoEditText.getText().toString().trim();
        String titleId = titleIdEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();

        if (jobInfo.isEmpty() || titleId.isEmpty() || location.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseUser user = auth.getCurrentUser();

        DocumentReference docRef = db.collection("Users").document(user.getEmail());

        docRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Document was successfully fetched
                        DocumentSnapshot document = task.getResult();
                        if (document != null && document.exists()) {
                            // Step 5: Get the field from the document
                            Map<String, Object> data = new HashMap<>();
                            data.put("Title", titleId);
                            data.put("Location", location);
                            data.put("Description", jobInfo);
                            data.put("OwnerName", document.getString("FirstName") + " " + document.getString("LastName"));
                            data.put("Completed", false);
                            data.put("ApplicantName", "");

                            db.collection("Jobs").add(data);
                            Toast.makeText(this, "Job successfully added", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this, CompletedJobsPage.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Log.d("Firestore", "No such document");
                        }
                    } else {
                        // Handle failure
                        Log.w("Firestore", "Error getting document.", task.getException());
                    }
                });
    }
}
