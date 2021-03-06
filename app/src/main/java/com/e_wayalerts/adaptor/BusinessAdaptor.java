package com.e_wayalerts.adaptor;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.activity.add_business.AddBusinesskFragment;
import com.e_wayalerts.activity.add_business.BusinessListFragment;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BusinessAdaptor extends RecyclerView.Adapter<BusinessAdaptor.ViewHolder> {
	
	private final ArrayList<BusinessListResponse.Datum> arMediIn;
	
	private final Context context;
	BusinessListFragment businessListFragment;
	public BusinessAdaptor(Context context_, ArrayList<BusinessListResponse.Datum> arTestReport_) {
		arMediIn = arTestReport_;
		context = context_;
		businessListFragment = BusinessListFragment.instance;
	}
	

	@Override
	public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.businesslayout_item, parent, false);

		BusinessAdaptor.ViewHolder viewHolder =
				new BusinessAdaptor.ViewHolder(view);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
		final BusinessListResponse.Datum maincat = arMediIn.get(position);
		
		viewHolder.bname.setText(maincat.getFldBusinessName());
		viewHolder.bcity.setText(maincat.getFldCity());
		viewHolder.bdate.setText(maincat.getFldEdate());
		if(maincat.getFldisactive() ==1){
			viewHolder.bActive.setChecked(true);
			viewHolder.renewel.setVisibility(View.GONE);
		}
		else {
			viewHolder.bActive.setChecked(false);
			viewHolder.renewel.setVisibility(View.VISIBLE);

		}
		viewHolder.edit_but.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				businessListFragment.editBusiness(maincat);
			}
		});
		viewHolder.delete_but.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				businessListFragment.deleteBusiness(maincat.getFldBid().toString());
			}
		});
		viewHolder.bActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					// The toggle is enabled
					businessListFragment.ChangeStatusBusiness(maincat.getFldBid().toString(),"1");
				} else {
					// The toggle is disabled
					businessListFragment.ChangeStatusBusiness(maincat.getFldBid().toString(),"0");
				}
			}
		});
	}
	
	@Override
	public int getItemCount() {
		return arMediIn.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		TextView bname, bcity, bdate;
		Switch bActive;
		LinearLayout renewel,delete_but,edit_but;
		public ViewHolder(View itemView) {
			super(itemView);
			bname = itemView.findViewById(R.id.bname);
			bcity = itemView.findViewById(R.id.bcity);
			bdate = itemView.findViewById(R.id.bdate);
			bActive = itemView.findViewById(R.id.bactive);
			renewel= itemView.findViewById(R.id.renewel);
			delete_but = itemView.findViewById(R.id.delete_but);
			edit_but = itemView.findViewById(R.id.edit_but);
		}
	}
}


