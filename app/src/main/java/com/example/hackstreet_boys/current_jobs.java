package com.example.hackstreet_boys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class current_jobs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_jobs);

        setupJob(1);
        setupJob(2);
        setupJob(3);
        setupJob(4);
        setupJob(5);
    }

    private void setupJob(int jobNumber) {
        int dropdownId = getResources().getIdentifier("dropdown" + jobNumber, "id", getPackageName());
        int detailId = getResources().getIdentifier("jobDetails" + jobNumber, "id", getPackageName());

        //int completeId =  getResources().getIdentifier("complete" + jobNumber, "id", getPackageName());

        Button dropdown = findViewById(dropdownId);
        LinearLayout details = findViewById(detailId);

        //Button to mark a job has completed
        Button complete = findViewById(R.id.complete1);

        dropdown.setOnClickListener(v -> {
            if (details.getVisibility() == View.GONE) {
                details.setVisibility(View.VISIBLE);
            } else {
                details.setVisibility(View.GONE);
            }
        });

        //Completed button click function
        complete.setOnClickListener(v -> {
            findViewById(R.id.Description1).setVisibility(View.GONE);
            findViewById(R.id.dropdown1).setVisibility(View.GONE);
            findViewById(R.id.jobDetails1).setVisibility(View.GONE);
            findViewById(R.id.complete1).setVisibility(View.GONE);
            //Intent intent = new Intent(current_jobs.this, CompletedJobsPage.class);

        });
    }
}
