package ghostgear.fishhackathon.com.ghostgear.activities;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import adapter.ReportPagerAdapter;
import ghostgear.fishhackathon.com.ghostgear.R;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        PagerAdapter mPagerActivity = new ReportPagerAdapter(this);
        ViewPager vpViewPager = (ViewPager)findViewById(R.id.vpPager);
        vpViewPager.setAdapter(new ReportPagerAdapter(this));

    }
}
