package jobs.accionlabs.com.accionjobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import jobs.accionlabs.com.accionjobs.data.JobListItems;

import static jobs.accionlabs.com.accionjobs.utils.Constant.JOB_ID;

/**
 * Created by Navin on 12/01/17.
 */

public class JobListingActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView jobLists;
    private JobListAdapter mAdapter;
    private List<JobListItems> jobListItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joblisting);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.accion_icon);

        jobListItems = new ArrayList<>();
        jobListItems.add(new JobListItems("JOB_ID", "Mobility Opening (Android)", "Job for android and front End developer", "5yrs-7yrs"));
        jobListItems.add(new JobListItems("JOB_ID", "Mobility Opening (iOS)", "Job for iOS and back End developer", "1yrs-3yrs"));
        jobListItems.add(new JobListItems("JOB_ID", "Core java developer", "Job for Core java and web services", "2yrs-3yrs"));

        jobLists = (RecyclerView) findViewById(R.id.job_lists);
        jobLists.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new JobListAdapter(this, jobListItems);
        jobLists.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.main_holder) {
            String jobId = (String) v.getTag();

            Intent intent = new Intent(this, JobDescriptionActivity.class);
            intent.putExtra(JOB_ID, jobId);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.singapore:
                return true;

            case R.id.india:
                return true;

            case R.id.malaysia:
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
