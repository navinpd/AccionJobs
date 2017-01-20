package jobs.accionlabs.com.accionjobs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import jobs.accionlabs.com.accionjobs.utils.SharedPreferenceUtils;

/**
 * Created by Navin on 12/01/17.
 */

public class SplashScreenActivity extends BaseActivity {
    private Handler handler;
    public static final int SPLASH_SCREEN_DELAY = 2000;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ((AppCompatActivity) this).getSupportActionBar().hide();
        handler = new Handler();
        handler.postDelayed(r, SPLASH_SCREEN_DELAY);
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            if (TextUtils.isEmpty(SharedPreferenceUtils.getStringPreference(SplashScreenActivity.this, SharedPreferenceUtils.EMAIL_ID))) {
                startActivity(new Intent(SplashScreenActivity.this, DashboardActivity.class));
            } else {
                startActivity(new Intent(SplashScreenActivity.this, JobListingActivity.class));
            }
            finish();
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        handler.removeCallbacks(r);
    }
}
