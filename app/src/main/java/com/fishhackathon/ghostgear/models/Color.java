package com.fishhackathon.ghostgear.models;

import com.fishhackathon.ghostgear.network.ParseUtils;

public enum Color {
    BLACK,
    BLUE,
    BROWN,
    CLEAR,
    GREEN,
    GREY,
    RED,
    WHITE;

    /**
     * Output the color as it's represented in Parse.
     */
    public String toParseString() {
        return ParseUtils.upperCaseFirstLetter(toString());
    }
}
