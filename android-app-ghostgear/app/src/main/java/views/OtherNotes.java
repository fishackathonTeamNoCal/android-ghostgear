package views;

import android.content.Context;
import android.widget.RelativeLayout;

import ghostgear.fishhackathon.com.ghostgear.R;


public class OtherNotes extends RelativeLayout {

    public OtherNotes(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_other_notes, this);

    }
}