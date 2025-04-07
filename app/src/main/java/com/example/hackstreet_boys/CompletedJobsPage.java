package com.example.hackstreet_boys; // Replace with your actual package name

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hackstreet_boys.R;

public class CompletedJobsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_jobs_page);  // Ensure the layout is properly set

        // Set up dropdown functionality
        setupDropdown(R.id.dropdown1, R.id.jobDetails1);
        setupDropdown(R.id.dropdown2, R.id.jobDetails2);
        setupDropdown(R.id.dropdown3, R.id.jobDetails3);
        setupDropdown(R.id.dropdown4, R.id.jobDetails4);
        setupDropdown(R.id.dropdown5, R.id.jobDetails5);
    }

    private void setupDropdown(int buttonId, int detailsId) {
        Button dropdownButton = findViewById(buttonId);
        TextView jobDetails = findViewById(detailsId);

        if (dropdownButton != null && jobDetails != null) {   // Null safety check
            dropdownButton.setOnClickListener(v -> {
                if (jobDetails.getVisibility() == View.GONE) {
                    jobDetails.setVisibility(View.VISIBLE);
                    dropdownButton.setText("▲");  // Switch to up arrow
                } else {
                    jobDetails.setVisibility(View.GONE);
                    dropdownButton.setText("▼");  // Switch to down arrow
                }
            });
        }
    }
}
