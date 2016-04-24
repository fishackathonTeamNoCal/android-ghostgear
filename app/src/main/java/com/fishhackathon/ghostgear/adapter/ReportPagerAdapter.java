package com.fishhackathon.ghostgear.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.fishhackathon.ghostgear.views.AnimalsEndangered;
import com.fishhackathon.ghostgear.views.CameraView;
import com.fishhackathon.ghostgear.views.MeshSize;
import com.fishhackathon.ghostgear.views.NetColor;
import com.fishhackathon.ghostgear.views.OtherNotes;
import com.fishhackathon.ghostgear.views.TwineDiameter;

import java.util.ArrayList;

/**
 * Created by gemma on 4/23/16.
 */


public class ReportPagerAdapter extends PagerAdapter {

    private Context context;
    public ArrayList<View> views;
    public ArrayList<CharSequence> titles;

    public ReportPagerAdapter(Context context) {
        super();
        this.context = context;
        views = new ArrayList<View>();
        titles = new ArrayList<>();
        views.add(new CameraView(context));
        views.add(new MeshSize(context));
        views.add(new TwineDiameter(context));
        views.add(new NetColor(context));
        views.add(new AnimalsEndangered(context));
        views.add(new OtherNotes(context));

        titles.add("1. Take Picture");
        titles.add("2. Mesh Size");
        titles.add("3. Number of Strands");
        titles.add("4. Twine Diameter");
        titles.add("5. Net Color");
        titles.add("6. Wildlife");
    }


    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View view = views.get(position);
        collection.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

}


