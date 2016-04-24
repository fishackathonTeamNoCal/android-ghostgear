package com.fishhackathon.ghostgear.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fishhackathon.ghostgear.R;
import com.fishhackathon.ghostgear.application.MyApplication;
import com.fishhackathon.ghostgear.models.NetInput;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MeshSize extends LinearLayout {

    @Bind(R.id.img_meshSize1)
    ImageView imgMeshSize1;
    @Bind(R.id.img_meshSize2)
    ImageView imgMeshSize2;
    @Bind(R.id.img_meshSize3)
    ImageView imgMeshSize3;

    @Bind(R.id.img_meshSize4)
    ImageView imgMeshSize4;
    @Bind(R.id.img_meshSize5)
    ImageView imgMeshSize5;
    @Bind(R.id.img_meshSize6)
    ImageView imgMeshSize6;

    @Bind(R.id.img_meshSize7)
    ImageView imgMeshSize7;
    @Bind(R.id.img_meshSize8)
    ImageView imgMeshSize8;
    @Bind(R.id.img_meshSize9)
    ImageView imgMeshSize9;

    Context context;

//    1. LESS_THAN_ONE_FINGER,
//    2. ONE_FINGER,
//    3. TWO_FINGERS,
//    4. THREE_FINGERS,
//    5. FOUR_FINGERS,
//    6. FIST,
//    7. CLASPED_FIST,
//    8. OPEN_HAND,
//    9. GREATER_THAN_OPEN_HAND,

    public MeshSize(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_mesh_size, this);
        
        final MyApplication application = (MyApplication)context.getApplicationContext();

        ButterKnife.bind(this);
        imgMeshSize1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.THREE_FINGERS);
                application.netInput.numberOfStrands = 2;
                application.complexPreferences.putObject("ghostGearPref", application.netInput);
                application.complexPreferences.commit();

                // increment pageAdapter view (+1, unless it's the last one)
//                pageAdapter.setCurrentItem(num);
//                pageAdapter.notifyDataSetChanged()
            }
        });
    }
}
