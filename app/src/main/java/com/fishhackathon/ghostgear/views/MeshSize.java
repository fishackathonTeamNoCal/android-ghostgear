package com.fishhackathon.ghostgear.views;

import android.content.Context;
import android.content.Intent;
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

//    LESS_THAN_ONE_FINGER,
//    ONE_FINGER,
//    TWO_FINGERS,
//    THREE_FINGERS,
//    FOUR_FINGERS,
//    FIST,
//    CLASPED_FIST,
//    OPEN_HAND,
//    GREATER_THAN_OPEN_HAND,

    public MeshSize(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_mesh_size, this);

        ButterKnife.bind(this);
        imgMeshSize1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (MeshSize.this);
                MyApplication application = (MyApplication)context.getApplicationContext();
                //        application.netInput.color = NetInput.Color.BROWN;
                        application.netInput.setMeshSize(NetInput.HandMeasurement.THREE_FINGERS);
                //        application.netInput.numberOfStrands = 2;
                //        application.netInput.twineDiameter = 0.5;   <-- double not range
                //        application.complexPreferences.putObject("ghostGearPref", netInput);
                //        application.complexPreferences.commit();
                ;
            }
        });


        ////Notes for saving
        //        MyApplication application = (MyApplication)context.getApplicationContext();
        //        application.netInput.color = Color.BROWN;
        //        application.netInput.setMeshSize(NetInput.HandMeasurement.THREE_FINGERS);
        //        application.netInput.numberOfStrands = 2;
        //        application.netInput.twineDiameter = 0.5;
        //        application.complexPreferences.putObject("ghostGearPref", application.netInput);
        //        application.complexPreferences.commit();

    }
}
