package com.e_wayalerts.adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StaffCheckAdaptor extends RecyclerView.Adapter<StaffCheckAdaptor.ViewHolder> {
	
	public final List<StaffModal> staffModalList;
	
	
	public StaffCheckAdaptor( List<StaffModal> arTestReport_) {
		staffModalList = arTestReport_;
	}
	

	@Override
	public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.businesslist_item, parent, false);

		StaffCheckAdaptor.ViewHolder viewHolder =
				new StaffCheckAdaptor.ViewHolder(view);
		return viewHolder;
	}
	
	@SuppressLint ("SetTextI18n")
	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
		final StaffModal datum = staffModalList.get(position);
		
		viewHolder.staffNameCheck.setText(datum.getFldFname()+datum.getFldLname());
		if (datum.isChecked()) {
			viewHolder.staffNameCheck.setChecked(true);
		} else {
			viewHolder.staffNameCheck.setChecked(false);
		}
		
		viewHolder.staffNameCheck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (datum.isChecked()) {
					datum.setChecked(false);
				} else {
					datum.setChecked(true);
				}
				
				
			}
		});
	}
	
	@Override
	public int getItemCount() {
		return staffModalList.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		CheckBox staffNameCheck;
		public ViewHolder(View itemView) {
			super(itemView);
			staffNameCheck = itemView.findViewById(R.id.bussnessNameCheck);
		}
	}
}


