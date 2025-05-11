package com.example.hackstreet_boys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;



public class AddFragment extends Fragment {

    private EditText jobInfoEditText, applicatorIdEditText, locationEditText;
    private Button saveButton;
    private DatabaseReference jobsRef;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);

        // Initialize Firebase reference
        jobsRef = FirebaseDatabase.getInstance().getReference("jobs");

        // Link views
        jobInfoEditText = view.findViewById(R.id.editTextText);
        applicatorIdEditText = view.findViewById(R.id.editTextText2);
        locationEditText = view.findViewById(R.id.editTextText3);
        saveButton = view.findViewById(R.id.button2);

        saveButton.setOnClickListener(v -> saveJobToFirebase());

        return view;
    }

    private void saveJobToFirebase() {
        String jobInfo = jobInfoEditText.getText().toString().trim();
        String applicatorId = applicatorIdEditText.getText().toString().trim();
        String location = locationEditText.getText().toString().trim();

        if (jobInfo.isEmpty() || applicatorId.isEmpty() || location.isEmpty()) {
            Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new job object
        String jobId = jobsRef.push().getKey(); // unique key
        Job job = new Job(jobId, jobInfo, applicatorId, location);

        if (jobId != null) {
            jobsRef.child(jobId).setValue(job)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(getContext(), "Job saved successfully", Toast.LENGTH_SHORT).show();

                        // Remove this fragment (AddFragment) from the container
                        requireActivity().getSupportFragmentManager().beginTransaction()
                                .remove(AddFragment.this)
                                .commit();
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(getContext(), "Failed to save job: " + e.getMessage(), Toast.LENGTH_SHORT).show());
        }
    }

    // Inner class for Job object
    public static class Job {
        public String jobId;
        public String jobInfo;
        public String applicatorId;
        public String location;

        public Job() {
            // Default constructor required for calls to DataSnapshot.getValue(Job.class)
        }

        public Job(String jobId, String jobInfo, String applicatorId, String location) {
            this.jobId = jobId;
            this.jobInfo = jobInfo;
            this.applicatorId = applicatorId;
            this.location = location;
        }
    }
}
