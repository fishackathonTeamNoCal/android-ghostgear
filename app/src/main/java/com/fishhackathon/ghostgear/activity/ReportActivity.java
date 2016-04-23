package com.fishhackathon.ghostgear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import com.fishhackathon.ghostgear.R;
import com.fishhackathon.ghostgear.adapter.ReportPagerAdapter;
import com.fishhackathon.ghostgear.views.CameraView;
import com.viewpagerindicator.CirclePageIndicator;

public class ReportActivity extends AppCompatActivity {
    ReportPagerAdapter mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        mPager = new ReportPagerAdapter(this);
        ViewPager vpViewPager = (ViewPager)findViewById(R.id.vpPager);
        vpViewPager.setAdapter(mPager);

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        circlePageIndicator.setViewPager(vpViewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CameraView.CAPTURE_BIG_NET_IMAGE_ACTIVITY_REQUEST_CODE ||
                requestCode == CameraView.CAPTURE_SMALL_NET_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                CameraView v = (CameraView) mPager.views.get(0);
                v.setImage(requestCode);
            } else { // Result was a failure
                Toast.makeText(this, "Picture wasn't taken!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
