package com.example.hackstreet_boys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CompletedJobsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completed_jobs_page);

        RecyclerView recyclerView = findViewById(R.id.jobRecycler);

        List<JobCard> jobList = new ArrayList<>();

        jobList.add(new JobCard("Job 1", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 2", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 3", "Arush's place", "Vandalism", "Vandalism", 2));
        jobList.add(new JobCard("Job 4", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 5", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 6", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 7", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 8", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 9", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 10", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 11", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 12", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 13", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 14", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 15", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 16", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 17", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 18", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 19", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 20", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 21", "Nowhere", "YO YO YO YO YO YO", "dude", 2));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JobAdapter adapter = new JobAdapter(jobList);
        recyclerView.setAdapter(adapter);
    }
}
