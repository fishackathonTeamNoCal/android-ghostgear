package com.fishhackathon.ghostgear.network;

import android.content.Context;

import com.parse.Parse;
import com.parse.ParseObject;

public final class ParseUtils {
    private ParseUtils() {};

    public static void initializeParse(Context context) {
        Parse.initialize(new Parse.Configuration.Builder(context)
                        .applicationId("t40pLblmf3Tc1Lu8VL2eLup2VV0k8oyvbM5KFHNF")
                        .clientKey("8CfNx0LHxFmi7BhkyibUChuP8s0g7O8FzDgj4VNu")
                        .build()
        );
    }

    public static void putIfNotNull(ParseObject parseObject, String key, Object value) {
        if (value != null) {
            parseObject.put(key, value);
        }
    }

    /**
     * Many of our enums like Color and Animal.Type are stored in Parse with the first letter
     * capitalized.
     */
    public static String upperCaseFirstLetter(String string) {
        String lowerCaseString = string.toLowerCase();
        return lowerCaseString.substring(0, 1).toUpperCase() + lowerCaseString.substring(1);
    }
}
