package jobs.accionlabs.com.accionjobs.data;


/**
 * Created by Navin on 12/01/17.
 */

public class JobListItems {
    private String jobId;
    private String jobHeading;
    private String jobText;
    private String experience;

    public JobListItems(String jobId, String jobHeading, String jobText, String experience) {
        this.experience = experience;
        this.jobHeading = jobHeading;
        this.jobText = jobText;
        this.jobId = jobId;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobHeading() {
        return jobHeading;
    }

    public void setJobHeading(String jobHeading) {
        this.jobHeading = jobHeading;
    }

    public String getJobText() {
        return jobText;
    }

    public void setJobText(String jobText) {
        this.jobText = jobText;
    }

}
