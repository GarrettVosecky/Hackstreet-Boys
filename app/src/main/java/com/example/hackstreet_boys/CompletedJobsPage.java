package com.example.hackstreet_boys;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CompletedJobsPage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completed_jobs_page);


        for (int i = 1; i <= 5; i++) {
            int layoutId = getResources().getIdentifier("completedJobLayout" + i, "id", getPackageName());
            View jobLayout = findViewById(layoutId);

            SharedPreferences prefs = getSharedPreferences("CompletedJobs", MODE_PRIVATE);
            boolean isCompleted = prefs.getBoolean("job" + i + "_completed", false);

            jobLayout.setVisibility(isCompleted ? View.VISIBLE : View.GONE);

        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        })

        ;

        // Fix: Button initialization and click listener
        Button btn = findViewById(R.id.CurrentJobBtn);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(CompletedJobsPage.this, current_jobs.class);
            startActivity(intent);
            finish();
        });
    }
}
