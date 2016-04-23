package com.fishhackathon.ghostgear.views;

import android.content.Context;
import android.widget.RelativeLayout;

import com.fishhackathon.ghostgear.R;


public class NumOfStrands extends RelativeLayout {

    public NumOfStrands(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_num_of_strains, this);

    }
}