package com.example.hackstreet_boys;

public class jobs {
    public String jobId;
    public String jobInfo;
    public String applicatorId;
    public String location;

    // Required empty constructor
    public jobs() {
    }

    public jobs(String jobId, String jobInfo, String applicatorId, String location) {
        this.jobId = jobId;
        this.jobInfo = jobInfo;
        this.applicatorId = applicatorId;
        this.location = location;
    }

    public String getJobId() {
        return jobId;
    }

    public String getJobInfo() {
        return jobInfo;
    }

    public String getApplicatorId() {
        return applicatorId;
    }

    public String getLocation() {
        return location;
    }
}
