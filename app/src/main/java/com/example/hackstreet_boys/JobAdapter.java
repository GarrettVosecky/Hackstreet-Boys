package com.example.hackstreet_boys;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder>
{
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<JobCard> jobList;
    public JobAdapter(List<JobCard> jobList) {
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jobcardlayout, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        JobCard jobCard = jobList.get(position);

        holder.titleView.setText(jobCard.getJobTitle());
        holder.descriptionView.setText(jobCard.getDescription());
        holder.locationView.setText(jobCard.getLocation());
        holder.ownerView.setText("Started by " + jobCard.getOwner());
        if (jobCard.isCompleted())
        {
            holder.statusView.setText("Finished by " + jobCard.getApplicant());
        } else {
            String applicant = jobCard.getApplicant();
            if (applicant.isEmpty())
            {
                holder.statusView.setText("Not yet started");
            } else {
                holder.statusView.setText("Being worked on by " + jobCard.getApplicant());
            }
        }

        holder.trashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("Jobs").document(jobCard.getID()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            jobList.remove(position);
                            notifyItemRemoved(position);
                            Toast.makeText(holder.descriptionLayout.getContext(), "Job has been successfully deleted", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public void addJobItem(JobCard job)
    {
        this.jobList.add(job);
        notifyDataSetChanged();
    }

    static class JobViewHolder extends RecyclerView.ViewHolder {

        TextView titleView, locationView, descriptionView, statusView, ownerView;
        ImageButton trashButton, dropButton;

        ConstraintLayout descriptionLayout;

        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.Title);
            locationView = itemView.findViewById(R.id.Location);
            descriptionView = itemView.findViewById(R.id.description);
            statusView = itemView.findViewById(R.id.Status);
            ownerView = itemView.findViewById(R.id.Owner);
            dropButton = itemView.findViewById(R.id.dropDown);
            trashButton = itemView.findViewById(R.id.trashButton);
            descriptionLayout = itemView.findViewById(R.id.Description);
            dropButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (descriptionLayout.getVisibility() == View.VISIBLE)
                    {
                        dropButton.setRotation(90);
                        descriptionLayout.setVisibility(View.GONE);
                    }
                    else
                    {
                        dropButton.setRotation(270);
                        descriptionLayout.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
