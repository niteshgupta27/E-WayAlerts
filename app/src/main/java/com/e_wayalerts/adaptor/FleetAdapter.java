package com.e_wayalerts.adaptor;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e_wayalerts.R;

public class FleetAdapter extends BaseAdapter {
    Activity context;
    @Override
    public int getCount() {
        return 0;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final FleetAdapter.ViewHolder holder;
        if (convertView == null) {
            LayoutInflater mInflater = LayoutInflater.from(context);
            convertView = mInflater.inflate(R.layout.fleet_item_layout, null);
        }
        return null;
    }
    private class ViewHolder {
        RelativeLayout mLayoutItem;
        TextView mTextAddresss;
        TextView mTextOwnerName;
        TextView mTextTitle;
        ImageView mImgCall;
        ImageView mImgPhoto;
        ImageView mImgbillupload;
        ImageView mImgdelete;
        TextView amountdate;
    }
}
