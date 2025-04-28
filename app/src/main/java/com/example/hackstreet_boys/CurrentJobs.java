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

        // Setup dropdown toggles for jobs
        setupJob(1);
        setupJob(2);
        setupJob(3);
        setupJob(4);
        setupJob(5);

        // Setup button to CompletedJobsPage
        Button btnCompletedJobs = findViewById(R.id.completedjobs);
        btnCompletedJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getApplicationContext(), CompletedJobsPage.class);
                startActivity(myIntent);
            }
        });

        // Setup FloatingActionButton to open AddFragment
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the AddFragment
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, new AddFragment())
                        .addToBackStack(null)
                        .commit();

                // Make the FrameLayout visible
                findViewById(R.id.fragmentContainer).setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupJob(int jobNumber) {
        int dropdownId = getResources().getIdentifier("dropdown" + jobNumber, "id", getPackageName());
        int detailId = getResources().getIdentifier("jobDetails" + jobNumber, "id", getPackageName());

        Button dropdown = findViewById(dropdownId);
        LinearLayout details = findViewById(detailId);

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
