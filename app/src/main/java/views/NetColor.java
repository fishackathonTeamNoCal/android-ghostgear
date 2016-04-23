package views;

import android.content.Context;
import android.widget.RelativeLayout;

import ghostgear.fishhackathon.com.ghostgear.R;

public class NetColor extends RelativeLayout {

    public NetColor(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_net_color, this);

    }
}
