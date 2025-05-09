package com.example.hackstreet_boys;

public class JobCard {
    private String JobTitle, Location, Description, Owner, Applicant;

    private boolean Completed;

    public JobCard(String JobTitle, String Location, String Description, String Owner, boolean Completed, String Applicant)
    {
        this.JobTitle = JobTitle;
        this.Location = Location;
        this.Description = Description;
        this.Owner = Owner;
        this.Applicant = Applicant;
        this.Completed = Completed;
    }

    public String getJobTitle()
    {
        return JobTitle;
    }

    public String getLocation()
    {
        return Location;
    }

    public String getDescription()
    {
        return Description;
    }

    public String getOwner()
    {
        return Owner;
    }

    public String getApplicant()
    {
        return Applicant;
    }

    public boolean isCompleted()
    {
        return Completed;
    }
}
