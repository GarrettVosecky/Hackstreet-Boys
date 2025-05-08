package com.example.hackstreet_boys;

public class Job {
    private String jobId;
    private String jobInfo;
    private String applicatorId;
    private String location;

    // Required empty constructor for Firebase
    public Job() {
    }

    public Job(String jobId, String jobInfo, String applicatorId, String location) {
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
