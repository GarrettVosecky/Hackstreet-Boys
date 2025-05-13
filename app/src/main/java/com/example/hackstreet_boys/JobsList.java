package com.example.hackstreet_boys;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class JobsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_completed_jobs_page);

        Button logoutButton = findViewById(R.id.signout);
        ImageButton addJob = findViewById(R.id.addJobSwitch);

        addJob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(JobsList.this, AddJobs.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JobsList.this);

                // Set the message show for the Alert time
                builder.setMessage("Are you sure you want to sign out?");

                builder.setTitle("Confirmation");

                builder.setCancelable(false);

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    auth.signOut();
                    Intent intent = new Intent(JobsList.this, Sign_inScreen.class);
                    startActivity(intent);
                    finish();
                    dialog.cancel();
                });

                AlertDialog alertDialog = builder.create();

                // Show the Alert Dialog box
                alertDialog.show();
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.jobRecycler);

        List<JobCard> jobList = new ArrayList<>();

        db.collection("Jobs").get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            jobList.add(new JobCard(document.getId(), document.getString("Title"), document.getString("Location"), document.getString("Description"), document.getString("OwnerName"), document.getBoolean("Completed"), document.getString("ApplicantName")));
                        }
                        recyclerView.setLayoutManager(new LinearLayoutManager(this));

                        JobAdapter adapter = new JobAdapter(jobList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.w("Firestore", "Error getting documents.", task.getException());
                    }
                });
    }
}
