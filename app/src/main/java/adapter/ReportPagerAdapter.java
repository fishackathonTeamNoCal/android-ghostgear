package adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import views.MeshSize;
import views.NumOfStrands;

/**
 * Created by gemma on 4/23/16.
 */


public class ReportPagerAdapter extends PagerAdapter {

    private Context context;

    public ReportPagerAdapter(Context context) {
        super();
        this.context = context;
    }


    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = null;
        switch (position){
            case 0:
                view = new MeshSize(context);
                break;
            case 1:
                view = new NumOfStrands(context);
                break;
//            case 2:
//                view = CpuView.getView(context, collection);
//                break;
        }

        collection.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }
}
