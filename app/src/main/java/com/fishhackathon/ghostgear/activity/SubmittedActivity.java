package com.fishhackathon.ghostgear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fishhackathon.ghostgear.R;
import com.fishhackathon.ghostgear.application.MyApplication;
import com.fishhackathon.ghostgear.models.NetInput;
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

        MyApplication myApplication = (MyApplication) getApplication();
        NetSearchResult netSearchResult = myApplication.netReport.net.netSearchResult;
        String submissionSummary = "Submitted info for: \n";

        if (myApplication.complexPreferences.getObject("ghostGearPref", NetInput.class).getSingleMeshSize() != null) {
            submissionSummary = submissionSummary + "  Mesh diameter - " +
                    myApplication.complexPreferences.getObject("ghostGearPref", NetInput.class).getSingleMeshSize() + "cm" +
                    "\n";
        }
        if   (myApplication.complexPreferences.getObject("ghostGearPref", NetInput.class).numberOfStrands != null) {
            submissionSummary = submissionSummary + "  Number of strands - " + myApplication.complexPreferences.getObject("ghostGearPref", NetInput.class).numberOfStrands + "\n";
        }

        String submissionDescription = "";
        if (netSearchResult != null) {
            submissionDescription =
                    "  Net Code: " + netSearchResult.netCode + "\n" +
                            "  Country of Origin: " + netSearchResult.origin;
            Log.i("Ghost Gear", submissionDescription);
        }
        tvGearInfo.setText(submissionSummary + submissionDescription);
    }
}
