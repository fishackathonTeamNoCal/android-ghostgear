package com.fishhackathon.ghostgear.views;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fishhackathon.ghostgear.R;
import com.fishhackathon.ghostgear.activity.ReportActivity;
import com.fishhackathon.ghostgear.activity.SubmittedActivity;
import com.fishhackathon.ghostgear.application.MyApplication;


public class NumOfStrands extends LinearLayout {

    EditText edtNumStrandInput;
    TextView btnOkInput;
    Context context;

    public NumOfStrands(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_num_of_strains, this);
        this.context = context;

        final EditText edtNumStrandInput = (EditText) findViewById(R.id.edtNumStrandInput);
        TextView btnOkInput = (TextView) findViewById(R.id.btnOkInput);

        btnOkInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                MyApplication application = (MyApplication)NumOfStrands.this.context.getApplicationContext();
                application.netInput.numberOfStrands = Integer.parseInt(edtNumStrandInput.getText().toString());
                application.complexPreferences.putObject("ghostGearPref", application.netInput);
                application.complexPreferences.commit();

                ReportActivity reportActivity = (ReportActivity) NumOfStrands.this.context;
                Intent i1 = new Intent(NumOfStrands.this.context, SubmittedActivity.class);
                reportActivity.startActivity(i1);
            }
        });

    }
}