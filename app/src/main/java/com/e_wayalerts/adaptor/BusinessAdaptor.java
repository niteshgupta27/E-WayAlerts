package com.e_wayalerts.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BusinessAdaptor extends RecyclerView.Adapter<BusinessAdaptor.ViewHolder> {
	
	private final ArrayList<BusinessListResponse.Datum> arMediIn;
	
	private final Context context;
	
	public BusinessAdaptor(Context context_, ArrayList<BusinessListResponse.Datum> arTestReport_) {
		arMediIn = arTestReport_;
		context = context_;
	}
	
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		Context context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);
		View contactView = inflater.inflate(R.layout.businesslayout_item, parent, false);
		return new ViewHolder(contactView);
	}
	
	// Involves populating data into the item through holder
	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
		// Get the data model based on position
		
		final BusinessListResponse.Datum maincat = arMediIn.get(position);
		
		viewHolder.bname.setText(maincat.getFldBusinessName());
		
		
	}
	
	@Override
	public int getItemCount() {
		return arMediIn.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		public ImageView ivSelect;
		
		TextView bname, bcity, bdate;
		
		LinearLayout rlMain, medicine_linear;
		
		public ViewHolder(View itemView) {
			super(itemView);
			bname = itemView.findViewById(R.id.bname);
			bcity = itemView.findViewById(R.id.bcity);
			bdate = itemView.findViewById(R.id.bdate);
			//medicine_linear = itemView.findViewById(R.id.medicine_linear);
		}
	}
}


