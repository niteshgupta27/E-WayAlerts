package com.e_wayalerts.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BusinessListAdaptor extends RecyclerView.Adapter<BusinessListAdaptor.ViewHolder> {
	
	private final List<BusinessListResponse.Datum> businessList;
	
	private final Context context;
	
	public BusinessListAdaptor(Context context_, List<BusinessListResponse.Datum> arTestReport_) {
		businessList = arTestReport_;
		context = context_;
	}
	

	@Override
	public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.businesslist_item, parent, false);

		BusinessListAdaptor.ViewHolder viewHolder =
				new BusinessListAdaptor.ViewHolder(view);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
		final BusinessListResponse.Datum datum = businessList.get(position);
		
		viewHolder.bussnessNameCheck.setText(datum.getFldBusinessName());
		
		
		viewHolder.bussnessNameCheck.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (datum.isIschecked()) {
					datum.setIschecked(false);
				} else {
					datum.setIschecked(true);
				}
				
				
			}
		});
	}
	
	@Override
	public int getItemCount() {
		return businessList.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		CheckBox bussnessNameCheck;
		public ViewHolder(View itemView) {
			super(itemView);
			bussnessNameCheck = itemView.findViewById(R.id.bussnessNameCheck);
		}
	}
}


