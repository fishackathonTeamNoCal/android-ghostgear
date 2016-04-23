package com.fishhackathon.ghostgear.views;

import android.content.Context;
import android.widget.RelativeLayout;

import com.fishhackathon.ghostgear.R;

public class MeshSize extends RelativeLayout {

    public MeshSize(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_mesh_size, this);

    }
}
