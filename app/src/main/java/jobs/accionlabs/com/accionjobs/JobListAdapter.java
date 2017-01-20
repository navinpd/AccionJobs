package jobs.accionlabs.com.accionjobs;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import jobs.accionlabs.com.accionjobs.data.JobListItems;

/**
 * Created by Navin on 12/01/17.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.NotificationHolder> {

    private Activity mContext;
    private List<JobListItems> jobsList;

    public JobListAdapter(Activity context, List<JobListItems> jobsList) {
        this.mContext = context;
        this.jobsList = jobsList;
    }

    @Override
    public NotificationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.job_list_adapter, parent, false);
        return new NotificationHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotificationHolder holder, final int position) {
        JobListItems item = jobsList.get(position);
        holder.jobHeading.setText(item.getJobHeading());
        holder.jobText.setText(item.getJobText());
        holder.experienceOfYears.setText(item.getExperience());


        JobListingActivity activity = (JobListingActivity) mContext;
        holder.mainHolder.setOnClickListener(activity);
        holder.mainHolder.setTag(item.getJobId());

    }

    @Override
    public int getItemCount() {
        return jobsList.size();
    }

    public class NotificationHolder extends RecyclerView.ViewHolder {
        private TextView jobHeading, jobText, experienceOfYears;
        private LinearLayout mainHolder;

        public NotificationHolder(View view) {
            super(view);
            mainHolder = (LinearLayout) view.findViewById(R.id.main_holder);
            jobHeading = (TextView) view.findViewById(R.id.job_heading);
            jobText = (TextView) view.findViewById(R.id.job_description);
            experienceOfYears = (TextView) view.findViewById(R.id.experience_text);
        }
    }
}
