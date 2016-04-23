package com.fishhackathon.ghostgear.network;

import android.content.Context;

import com.parse.Parse;

public final class ParseUtils {
    private ParseUtils() {};

    public static void initializeParse(Context context) {
        Parse.initialize(new Parse.Configuration.Builder(context)
                        .applicationId("t40pLblmf3Tc1Lu8VL2eLup2VV0k8oyvbM5KFHNF")
                        .clientKey("8CfNx0LHxFmi7BhkyibUChuP8s0g7O8FzDgj4VNu")
                        .build()
        );
    }
}
