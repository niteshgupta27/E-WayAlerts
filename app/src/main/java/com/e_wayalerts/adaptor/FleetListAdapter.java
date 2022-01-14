package com.e_wayalerts.adaptor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.activity.add_staff.StaffListFragment;
import com.e_wayalerts.fragment.AddVehicleFragment;
import com.e_wayalerts.fragment.Fleet_List_Fragment;
import com.e_wayalerts.model.FleetListModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


public class FleetListAdapter extends RecyclerView.Adapter<FleetListAdapter.ViewHolder> {
	
	private final ArrayList<FleetListModel.Datum> fleetList;
	Fleet_List_Fragment ListFragment;
	public FleetListAdapter(ArrayList<FleetListModel.Datum> fleetList) {
		this.fleetList = fleetList;
		ListFragment = Fleet_List_Fragment.instance;
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
		viewHolder.bind(fleetList.get(position));
	}
	
	@Override
	public int getItemCount() {
		return fleetList.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		TextView vehicleNumber, vehicleName, vehicleType;
		
		LinearLayout fleetEdit, fleetDelete;
		Switch bActive;
		public ViewHolder(View itemView) {
			super(itemView);
			vehicleNumber = itemView.findViewById(R.id.vehicleNumber);
			vehicleName = itemView.findViewById(R.id.vehicleName);
			vehicleType = itemView.findViewById(R.id.vehicleType);
			fleetEdit = itemView.findViewById(R.id.fleetEdit);
			fleetDelete = itemView.findViewById(R.id.fleetDelete);
			bActive = itemView.findViewById(R.id.bactive);
		}
		
		public void bind(FleetListModel.Datum datum) {
			vehicleName.setText(datum.getFldMake());
			vehicleNumber.setText(datum.getFldNumber());
			vehicleType.setText(datum.getFldType());
			if(datum.getFldisactive() ==1){
				bActive.setChecked(true);

			}
			else {
				bActive.setChecked(false);

			}
			fleetEdit.setOnClickListener(v -> {
				Fragment fragment = new AddVehicleFragment();
				Bundle bundle = new Bundle();
				bundle.putSerializable("FleetModel", datum);
				fragment.setArguments(bundle);
				Utility.loadFragment((FragmentActivity) itemView.getContext(), fragment, true,
						null);
			});
			fleetDelete.setOnClickListener(v -> {
				ListFragment.deleteFleet(datum.getFldFltId().toString());
			});
			bActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					if (isChecked) {
						// The toggle is enabled
						ListFragment.ChangeStatusBusiness(datum.getFldFltId().toString(),"1");
					} else {
						// The toggle is disabled
						ListFragment.ChangeStatusBusiness(datum.getFldFltId().toString(),"0");
					}
				}
			});
		}
		
	}
	
}


