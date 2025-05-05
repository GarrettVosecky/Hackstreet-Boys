package com.example.hackstreet_boys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CurrentJobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.current_jobs);

        // Handle window insets for better layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set up dropdown toggles for jobs and make sure all jobs are visible by default
        for (int i = 1; i <= 5; i++) {
            setupJob(i);
        }

        // Button to navigate to completed jobs page
        Button btnCompletedJobs = findViewById(R.id.completedjobs);
        btnCompletedJobs.setOnClickListener(view -> {
            Intent intent = new Intent(CurrentJobs.this, CompletedJobsPage.class);
            startActivity(intent);
        });

        // FloatingActionButton to open AddFragment
        FloatingActionButton fab = findViewById(R.id.floatingActionButton3);
        fab.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new AddFragment())
                    .addToBackStack(null)
                    .commit();

            // Make fragment container visible
            findViewById(R.id.fragmentContainer).setVisibility(View.VISIBLE);
        });
    }

    private void setupJob(int jobNumber) {
        int dropdownId = getResources().getIdentifier("dropdown" + jobNumber, "id", getPackageName());
        int detailId = getResources().getIdentifier("jobDetails" + jobNumber, "id", getPackageName());

        Button dropdown = findViewById(dropdownId);
        LinearLayout details = findViewById(detailId);

        // Make the job details visible by default
        if (details != null) {
            details.setVisibility(View.VISIBLE); // Ensure details are visible by default
        }

        // Set up the dropdown to toggle job details visibility
        if (dropdown != null && details != null) {
            dropdown.setOnClickListener(v -> {
                if (details.getVisibility() == View.GONE) {
                    details.setVisibility(View.VISIBLE);
                } else {
                    details.setVisibility(View.GONE);
                }
            });
        }
    }
}
