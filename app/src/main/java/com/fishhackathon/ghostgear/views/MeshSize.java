package com.fishhackathon.ghostgear.views;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fishhackathon.ghostgear.R;
import com.fishhackathon.ghostgear.activity.ReportActivity;
import com.fishhackathon.ghostgear.activity.SubmittedActivity;
import com.fishhackathon.ghostgear.application.MyApplication;
import com.fishhackathon.ghostgear.models.NetInput;
import com.fishhackathon.ghostgear.network.ReportingApi;

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

        this.context = context;

        inflate(getContext(), R.layout.view_mesh_size, this);

        final MyApplication application = (MyApplication) context.getApplicationContext();

        ButterKnife.bind(this);
        imgMeshSize1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.LESS_THAN_ONE_FINGER);
                afterSelection();
            }
        });

        imgMeshSize2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.ONE_FINGER);
                afterSelection();
            }
        });

        imgMeshSize3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.TWO_FINGERS);
                afterSelection();
            }
        });

        imgMeshSize4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.THREE_FINGERS);
                afterSelection();
            }
        });

        imgMeshSize5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.FOUR_FINGERS);
                afterSelection();
            }
        });

        imgMeshSize6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.FIST);
                afterSelection();
            }
        });

        imgMeshSize7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.CLASPED_FIST);
                afterSelection();
            }
        });

        imgMeshSize8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.OPEN_HAND);
                afterSelection();
            }
        });

        imgMeshSize9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                application.netInput.setMeshSize(NetInput.HandMeasurement.GREATER_THAN_OPEN_HAND);
                afterSelection();
            }
        });

    }

    private void afterSelection() {
        MyApplication application = (MyApplication) context.getApplicationContext();
        application.complexPreferences.putObject("ghostGearPref", application.netInput);
        application.complexPreferences.commit();

        ReportActivity reportActivity = (ReportActivity) MeshSize.this.context;

        // To add more views, replace the rest of the code with the following        
        // reportActivity.moveToNextView();

        ReportingApi.report(reportActivity);
        Intent i1 = new Intent(context, SubmittedActivity.class);
        reportActivity.startActivity(i1);
    }
}
