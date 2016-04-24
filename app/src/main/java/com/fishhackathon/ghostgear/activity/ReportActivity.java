package com.fishhackathon.ghostgear.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.fishhackathon.ghostgear.network.ReportingApi;
import com.fishhackathon.ghostgear.views.CameraView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.Date;

public class ReportActivity extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;

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

        MyApplication myApplication = (MyApplication) getApplication();
        myApplication.netReport.timestamp = new Date();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            requestLocationUpdate();
        }

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
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocationUpdate();
                }
                return;
            }
        }
    }

    private void requestLocationUpdate() {
        LocationManager locManager;
        locManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L,
                500.0f, locationListener);
        Location location = locManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (location != null) {
            MyApplication myApplication = (MyApplication) getApplication();
            myApplication.netReport.latitude = location.getLatitude();
            myApplication.netReport.longitude = location.getLongitude();
        }
    }

    private void updateWithNewLocation(Location location) {
        String latLongString = "";
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            latLongString = "Lat:" + lat + "\nLong:" + lng;
        } else {
            latLongString = "No location found";
        }
        Log.i("GhostGear", "Your Current Position is:\n" + latLongString);
    }

    private final LocationListener locationListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            updateWithNewLocation(location);
        }

        public void onProviderDisabled(String provider) {
            updateWithNewLocation(null);
        }

        public void onProviderEnabled(String provider) {}

        public void onStatusChanged(String provider,int status,Bundle extras){}
    };

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

            // TODO(jchen): This is a hack to test the ReportingApi
            //ReportingApi.report(this);
        }
    }
}
