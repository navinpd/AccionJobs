package jobs.accionlabs.com.accionjobs;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import jobs.accionlabs.com.accionjobs.utils.Constant;
import jobs.accionlabs.com.accionjobs.utils.SharedPreferenceUtils;

import static jobs.accionlabs.com.accionjobs.utils.Constant.JOB_ID;
import static jobs.accionlabs.com.accionjobs.utils.Constant.MAIL_ID_SINGPAORE;
import static jobs.accionlabs.com.accionjobs.utils.SharedPreferenceUtils.EMP_ID;

/**
 * Created by Navin on 12/01/17.
 */

public class JobDescriptionActivity extends BaseActivity {
    private String jobId;
    private EditText empId;
    private Button attachButton;
    private Button submitButton;
    private TextView cvPath;
    private Button shareBtn;
    private String filePath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);
        jobId = getIntent().getStringExtra(JOB_ID);

        empId = (EditText) findViewById(R.id.emp_id);
        attachButton = (Button) findViewById(R.id.attache_cv);
        submitButton = (Button) findViewById(R.id.submit_btn);
        shareBtn = (Button) findViewById(R.id.share_btn);
        cvPath = (TextView) findViewById(R.id.file_location);

        empId.setText(SharedPreferenceUtils.getStringPreference(this, EMP_ID));

        shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = String.format(getString(R.string.custom_message), Constant.PLAY_STORE_LINK, empId.getText().toString());

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.message_chooser)));
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                thankYouDialog();

                String pathname = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(pathname, filePath);
                List<File> path = new ArrayList<>();
                if (!TextUtils.isEmpty(filePath))
                    path.add(file);
                String accionMailId = MAIL_ID_SINGPAORE;

                String body = String.format(getString(R.string.mail_body), empId.getText().toString());

                SendEmail(SharedPreferenceUtils.getStringPreference(JobDescriptionActivity.this, accionMailId),
                        getString(R.string.mail_heading), body, path);
            }
        });

        attachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
    }

    public void thankYouDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(true);
        dialog.setMessage(getString(R.string.thank_you));
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    public void SendEmail(String emailTo, String subject, String body, List<File> filePaths) {
        final Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/html");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{emailTo});
        //emailIntent.putExtra(android.content.Intent.EXTRA_CC,new String[]{emailCC});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        emailIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        if (!TextUtils.isEmpty(filePath)) {
            filePaths.get(0).setReadable(true, false);
            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(filePaths.get(0)));
            Log.d("ACCION", filePaths.get(0).toString());
        }

        startActivity(Intent.createChooser(emailIntent, getString(R.string.email_chooser_message)));
    }

    public void openFolder() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath());
        intent.setDataAndType(uri, "csv/doc/pdf/docx");
        startActivityForResult(Intent.createChooser(intent, "Open folder"), 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 100) {
            Uri selectedFileURI = data.getData();
            File file = new File(selectedFileURI.getPath().toString());
            filePath = file.getPath().toString();

            StringTokenizer tokens = new StringTokenizer(filePath, ":");
            String first = tokens.nextToken();
            String file_1 = tokens.nextToken().trim();

            Log.d("---", "" + filePath);
            cvPath.setVisibility(View.VISIBLE);
            cvPath.setText(file_1);
        }

    }
}
