package com.fishhackathon.ghostgear.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

        MyApplication myApplication = (MyApplication) getApplication();
        NetSearchResult netSearchResult = myApplication.netReport.net.netSearchResult;
        if (netSearchResult != null) {
            String submissionDescription =
                    "Net Code: " + netSearchResult.netCode + "\n" +
                    "Country of Origin: " + netSearchResult.origin;
            Log.i("Ghost Gear", submissionDescription);
        }
    }
}
