package com.e_wayalerts.activity.dropdown;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.e_wayalerts.R;

import java.util.ArrayList;


public class DropDownAdapter extends BaseAdapter {
    Context context;
    public ArrayList<DropDownModal> arrayState;
    LayoutInflater inflter;

    public DropDownAdapter(Context applicationContext, ArrayList<DropDownModal> dropitem) {
        this.context = applicationContext;

        this.arrayState = dropitem;
        inflter = (LayoutInflater.from(applicationContext));
    }
public int getpossion(String item){
        int possion = 0;
    for (int i = 0; i < arrayState.size(); i++) {


        if(arrayState.get(i).getmStrId().toString().equals(item)){
           possion = i;
           break;
        }
    }
    Log.e("indexof", String.valueOf(arrayState.indexOf(item)));
        return  possion;
}
    @Override
    public int getCount() {
        return arrayState.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.drop_downlayout, null);
        //ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
       // icon.setImageResource(flags[i]);
        names.setText(arrayState.get(i).getmStrValue());
        return view;
    }
}