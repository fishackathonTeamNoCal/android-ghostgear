package views;

import android.content.Context;
import android.widget.RelativeLayout;

import ghostgear.fishhackathon.com.ghostgear.R;

/**
 * Created by gemma on 4/23/16.
 */
public class MeshSize extends RelativeLayout {

    public MeshSize(Context context) {
        super(context);

        inflate(getContext(), R.layout.view_mesh_size, this);

    }
}
