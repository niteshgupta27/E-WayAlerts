package com.e_wayalerts.adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.fragment.AddVehicleFragment;
import com.e_wayalerts.model.FleetListModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
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
		
		viewHolder.bind(maincat);
	}
	
	@Override
	public int getItemCount() {
		return arMediIn.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		TextView vehicleNumber, vehicleName, vehicleType;
		LinearLayout fleetEdit,fleetDelete;
		
		public ViewHolder(View itemView) {
			super(itemView);
			vehicleNumber = itemView.findViewById(R.id.vehicleNumber);
			vehicleName = itemView.findViewById(R.id.vehicleName);
			vehicleType = itemView.findViewById(R.id.vehicleType);
			fleetEdit = itemView.findViewById(R.id.fleetEdit);
			fleetDelete = itemView.findViewById(R.id.fleetDelete);
		}
		
		public void bind(FleetListModel.Datum maincat) {
			vehicleNumber.setText(maincat.getFldNumber());
			vehicleName.setText(maincat.getFldMake());
			vehicleType.setText(maincat.getFldType());
			
			
			fleetEdit.setOnClickListener(v -> {
				Fragment fragment = new AddVehicleFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("FleetModel", maincat);
				fragment.setArguments(bundle);
				Utility.loadFragment(
						(FragmentActivity) itemView.getContext(), fragment, false, null);
				
			});
			
			fleetDelete.setOnClickListener(v -> {
			
			});
		}
	}
}


