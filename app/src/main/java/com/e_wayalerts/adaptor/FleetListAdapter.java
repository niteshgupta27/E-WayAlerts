package com.e_wayalerts.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.model.FleetListModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FleetListAdapter extends RecyclerView.Adapter<FleetListAdapter.ViewHolder> {
	
	private final ArrayList<FleetListModel.Datum> arMediIn;
	
	private final Context context;
	
	public FleetListAdapter(Context context_, ArrayList<FleetListModel.Datum> arTestReport_) {
		arMediIn = arTestReport_;
		context = context_;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		
		View view =
				LayoutInflater.from(parent.getContext()).inflate(R.layout.fleetlayout_item, parent,
						false);
		
		FleetListAdapter.ViewHolder viewHolder = new FleetListAdapter.ViewHolder(view);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
		final FleetListModel.Datum maincat = arMediIn.get(position);
		
		viewHolder.vehicleNumber.setText(maincat.getFldNumber());
		viewHolder.vehicleName.setText(maincat.getFldMake());
		viewHolder.vehicleType.setText(maincat.getFldType());
		
	}
	
	@Override
	public int getItemCount() {
		return arMediIn.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		TextView vehicleNumber, vehicleName, vehicleType;
		
		public ViewHolder(View itemView) {
			super(itemView);
			vehicleNumber = itemView.findViewById(R.id.vehicleNumber);
			vehicleName = itemView.findViewById(R.id.vehicleName);
			vehicleType = itemView.findViewById(R.id.vehicleType);
			
		}
	}
}


