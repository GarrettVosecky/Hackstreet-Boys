package com.example.hackstreet_boys;

public class JobCard {
    private String JobTitle, Location, Description, Owner, Applicant, ID;

    private boolean Completed;

    public JobCard(String ID, String JobTitle, String Location, String Description, String Owner, boolean Completed, String Applicant)
    {
        this.ID = ID;
        this.JobTitle = JobTitle;
        this.Location = Location;
        this.Description = Description;
        this.Owner = Owner;
        this.Applicant = Applicant;
        this.Completed = Completed;
    }

    public String getID()
    {
        return ID;
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
