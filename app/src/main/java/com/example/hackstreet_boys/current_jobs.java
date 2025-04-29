package com.example.hackstreet_boys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        Button completedJobBtn = findViewById(R.id.CompletedJobs);

        completedJobBtn.setOnClickListener(v ->{

            Intent intent = new Intent(current_jobs.this, CompletedJobsPage.class);
            startActivity(intent);
            finish();

        });
    }

    private void setupJob(int jobNumber) {
        //int dropdownId = getResources().getIdentifier("dropdown" + jobNumber, "id", getPackageName());
        //int detailId = getResources().getIdentifier("jobDetails" + jobNumber, "id", getPackageName());

        int dropdownId = getResources().getIdentifier("dropdown" + jobNumber, "id", getPackageName());
        int detailId = getResources().getIdentifier("jobDetails" + jobNumber, "id", getPackageName());
        int completeId = getResources().getIdentifier("complete" + jobNumber, "id", getPackageName());
        int layoutId = getResources().getIdentifier("Job" + jobNumber, "id", getPackageName());
        int descId = getResources().getIdentifier("Description" + jobNumber, "id", getPackageName());

        Button dropdown = findViewById(dropdownId);
        LinearLayout details = findViewById(detailId);
        Button complete = findViewById(completeId);
        View layout = findViewById(layoutId);
        TextView description = findViewById(descId);


        dropdown.setOnClickListener(v -> {
            if (details.getVisibility() == View.GONE) {
                details.setVisibility(View.VISIBLE);
            } else {
                details.setVisibility(View.GONE);
            }
        });

        //Completed button click function
        complete.setOnClickListener(v -> {

            layout.setVisibility(View.GONE);

            SharedPreferences prefs = getSharedPreferences("CompletedJobs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean("job" + jobNumber + "_completed", true);
            editor.apply();

        });


    }



}
