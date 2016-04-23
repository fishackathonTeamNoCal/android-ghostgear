package views;

import android.content.Context;
import android.widget.RelativeLayout;

import ghostgear.fishhackathon.com.ghostgear.R;

public class NumOfStrands extends RelativeLayout {

    public NumOfStrands(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_num_of_strains, this);

    }
}