package com.fishhackathon.ghostgear.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.fishhackathon.ghostgear.R;

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
    }
}
