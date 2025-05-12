package com.example.hackstreet_boys;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CurrentJobs extends AppCompatActivity {

    private LinearLayout jobsContainer;
    private DatabaseReference jobsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.current_jobs);

        // Set padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        jobsContainer = findViewById(R.id.jobsContainer);
        jobsRef = FirebaseDatabase.getInstance().getReference("jobs");

        loadJobsFromFirebase();

        // Navigate to completed jobs
        Button btnCompletedJobs = findViewById(R.id.completedjobs);
        btnCompletedJobs.setOnClickListener(view -> {
            Intent intent = new Intent(CurrentJobs.this, CompletedJobsPage.class);
            startActivity(intent);
        });

        // Show AddFragment to add a new job
        FloatingActionButton fab = findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .commit();

            findViewById(R.id.fragmentContainer).setVisibility(View.VISIBLE);
        });
    }

    private void loadJobsFromFirebase() {
        jobsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                jobsContainer.removeAllViews();

                for (DataSnapshot jobSnapshot : snapshot.getChildren()) {
                    Job job = jobSnapshot.getValue(Job.class);
                    if (job != null) {
                        View jobCard = createJobCard(job);
                        jobsContainer.addView(jobCard);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle error
                // Optionally show a Toast message here
            }
        });
    }

    private View createJobCard(Job job) {
        // Inflate the job item layout
        View jobCardView = LayoutInflater.from(CurrentJobs.this)
                .inflate(R.layout.job_item, jobsContainer, false);

        // Find the TextViews in the inflated layout
        TextView jobInfoText = jobCardView.findViewById(R.id.jobInfoText);
        TextView applicatorIdText = jobCardView.findViewById(R.id.applicatorIdText);
        TextView locationText = jobCardView.findViewById(R.id.locationText);

        // Set the job data
        jobInfoText.setText("Job Info: " + job.getJobInfo());
        applicatorIdText.setText("Applicator ID: " + job.getApplicatorId());
        locationText.setText("Location: " + job.getLocation());

        return jobCardView;
    }
}
