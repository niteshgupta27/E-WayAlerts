package com.e_wayalerts.adaptor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;

import java.util.List;

public class DriverNameSpinnerAdapter extends ArrayAdapter<StaffModal> {
	
	private final List<StaffModal> items;
	
	Context context;
	
	public DriverNameSpinnerAdapter(Context context, int resourceId, List<StaffModal> aritems) {
		super(context, resourceId, aritems);
		
		this.context = context;
		this.items = aritems;
	}
	
	@Override
	public int getCount() {
		return items.size();
	}
	
	@Override
	public StaffModal getItem(int position) {
		//		Log.v("", "items.get("+position+")= "+items.get(position));
		return items.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@SuppressLint ("SetTextI18n")
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		//		final BloodGroupPojo mytempojo = getItem(position);
		LayoutInflater mInflater =
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.result_spinner_item, null);
			holder = new ViewHolder();
			holder.itemName = convertView.findViewById(R.id.tv_item_name);
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Log.e("Name", items.get(position).getFldFname() + "" + items.get(position).getFldLname());
		holder.itemName.setText(
				items.get(position).getFldFname() + "" + items.get(position).getFldLname());
		return convertView;
	}
	
	@SuppressLint ("SetTextI18n")
	@Override
	public View getDropDownView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		LayoutInflater mInflater =
				(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.convert_spinner_item, null);
			holder = new ViewHolder();
			holder.itemDropdown = convertView.findViewById(R.id.tv_item_name_drop_down);
			convertView.setTag(holder);
			
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Log.e("Name2", items.get(position).getFldFname() + "" + items.get(position).getFldLname());
		holder.itemDropdown.setText(
				items.get(position).getFldFname() + "" + items.get(position).getFldLname());
		return convertView;
	}
	
	private class ViewHolder {
		
		TextView itemName;
		
		TextView itemDropdown;
	}
	
}