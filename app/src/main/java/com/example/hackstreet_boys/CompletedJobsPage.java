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

        jobList.add(new JobCard("Job 3", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 54", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 2", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 3", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 54", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 2", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 3", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 54", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 2", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 3", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 54", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 2", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 3", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 54", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 2", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 3", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 54", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 2", "Nowhere", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 3", "AYO", "YO YO YO YO YO YO", "dude", 2));
        jobList.add(new JobCard("Job 54", "Anywhere", "BLAH BLAH BLAH BLAH", "bro", 4));
        jobList.add(new JobCard("Job 2", "Nowhere", "YO YO YO YO YO YO", "dude", 2));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JobAdapter adapter = new JobAdapter(jobList);
        recyclerView.setAdapter(adapter);
    }
}
