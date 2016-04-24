package com.fishhackathon.ghostgear.application;

import android.app.Application;

import com.fishhackathon.ghostgear.lib.ComplexPreferences;
import com.fishhackathon.ghostgear.models.NetInput;
import com.fishhackathon.ghostgear.models.NetReport;
import com.fishhackathon.ghostgear.network.ParseUtils;

public class MyApplication extends Application {

    public NetInput netInput = new NetInput();
    public NetReport netReport = new NetReport();
    public ComplexPreferences complexPreferences;


    @Override
    public void onCreate() {
        super.onCreate();
        ParseUtils.initializeParse(this);

        complexPreferences  = ComplexPreferences.getComplexPreferences(this, "ghostGearPref", MODE_PRIVATE);
    }




}
