package com.example.hackstreet_boys;

public class JobCard {
    private String JobTitle;
    private String Location;
    private String Description;
    private String Status;
    private int StatusId;

    public JobCard(String JobTitle, String Location, String Description, String Status, int StatusId)
    {
        this.JobTitle = JobTitle;
        this.Location = Location;
        this.Description = Description;
        this.Status = Status;
        this.StatusId = StatusId;
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
        return Status;
    }

    public String getStatus()
    {
        return Status;
    }

    public int getStatusId()
    {
        return StatusId;
    }
}
