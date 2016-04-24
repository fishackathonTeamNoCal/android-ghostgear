package com.fishhackathon.ghostgear.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fishhackathon.ghostgear.R;
import com.fishhackathon.ghostgear.adapter.ReportPagerAdapter;
import com.fishhackathon.ghostgear.application.MyApplication;
import com.fishhackathon.ghostgear.views.CameraView;
import com.viewpagerindicator.CirclePageIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ReportActivity extends AppCompatActivity {
    ReportPagerAdapter mPager;

    @Bind(R.id.vpPager)
    ViewPager vpViewPager;


    @Bind(R.id.indicator)
    CirclePageIndicator circlePageIndicator;

    @Bind(R.id.btSaveAndExit)
    Button btSaveAndExit;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    public Toolbar getToolbar() {
        return toolbar;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ButterKnife.bind(this);

        mPager = new ReportPagerAdapter(this);
        vpViewPager.setAdapter(mPager);
        toolbar.setTitle(mPager.getPageTitle(0));
        vpViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                toolbar.setTitle(mPager.getPageTitle(position));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        circlePageIndicator.setViewPager(vpViewPager);

        btSaveAndExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication app = (MyApplication) getApplication();
                app.complexPreferences.commit();
//                finish(); // ends viewAdapter, returning to activity that launches
            }
        }); 

    }

    public void moveToNextView () {
        int cur = vpViewPager.getCurrentItem();
        vpViewPager.setCurrentItem(cur + 1);
        mPager.notifyDataSetChanged();
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
