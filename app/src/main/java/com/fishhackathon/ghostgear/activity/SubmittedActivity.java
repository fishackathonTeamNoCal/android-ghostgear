package com.fishhackathon.ghostgear.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fishhackathon.ghostgear.R;
import com.fishhackathon.ghostgear.application.MyApplication;
import com.fishhackathon.ghostgear.models.NetSearchResult;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SubmittedActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitted);

        ButterKnife.bind(this);
        mActionBarToolbar.setTitle("Successfully Submitted!");
        setSupportActionBar(mActionBarToolbar);


        ImageView ivTurtles = (ImageView) findViewById(R.id.ivTurtles);

        ivTurtles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(SubmittedActivity.this, ReportActivity.class);
                startActivity(i1);
            }
        });

        TextView tvGearInfo = (TextView) findViewById(R.id.tvCountry);

        SharedPreferences sharedpref = getSharedPreferences("GhostPref", Context.MODE_PRIVATE);

        String submissionSummary = "Submitted info for: ";
        tvGearInfo.setText(submissionSummary);

        MyApplication myApplication = (MyApplication) getApplication();
        NetSearchResult netSearchResult = myApplication.netReport.net.netSearchResult;
        if (netSearchResult != null) {
            String submissionDescription =
                    "Net Code: " + netSearchResult.netCode + "\n" +
                            "Country of Origin: " + netSearchResult.origin;
            Log.i("Ghost Gear", submissionDescription);
            tvGearInfo.setText(submissionDescription);
        }
    }
}
