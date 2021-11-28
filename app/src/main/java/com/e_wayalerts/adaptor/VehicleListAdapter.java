package com.e_wayalerts.adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.model.VehicleListModel;

import java.util.List;

public class VehicleListAdapter extends ArrayAdapter<VehicleListModel.Datum> {
    Context context;
    private List<VehicleListModel.Datum> items;

    public VehicleListAdapter(Context context,
                              int resourceId, List<VehicleListModel.Datum> aritems) {
        super(context,resourceId,aritems);


        this.context = context;
        this.items=aritems;
    }
    private class ViewHolder {
        TextView itemName;
        TextView itemDropdown;
    }

    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public VehicleListModel.Datum getItem(int position) {
//		Log.v("", "items.get("+position+")= "+items.get(position));
        return items.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
//		final BloodGroupPojo mytempojo = getItem(position);
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.result_spinner_item, null);
            holder = new ViewHolder();
            holder.itemName = convertView.findViewById(R.id.tv_item_name);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemName.setText(items.get(position).getFldMakeName());
        return convertView;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.convert_spinner_item, null);
            holder = new ViewHolder();
            holder.itemDropdown = convertView.findViewById(R.id.tv_item_name_drop_down);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.itemDropdown.setText(items.get(position).getFldMakeName());
        return convertView;
    }

}