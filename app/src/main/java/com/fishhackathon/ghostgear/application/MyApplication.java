package com.fishhackathon.ghostgear.application;
import android.app.Application;

import com.fishhackathon.ghostgear.network.ParseUtils;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ParseUtils.initializeParse(this);
    }
}
