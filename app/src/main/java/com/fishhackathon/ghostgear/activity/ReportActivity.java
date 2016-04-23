package com.fishhackathon.ghostgear.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.fishhackathon.ghostgear.R;
import com.fishhackathon.ghostgear.adapter.ReportPagerAdapter;
import com.viewpagerindicator.CirclePageIndicator;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        PagerAdapter mPagerActivity = new ReportPagerAdapter(this);
        ViewPager vpViewPager = (ViewPager)findViewById(R.id.vpPager);
        vpViewPager.setAdapter(new ReportPagerAdapter(this));

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(vpViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
