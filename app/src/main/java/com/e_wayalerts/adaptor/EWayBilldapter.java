package com.e_wayalerts.adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.activity.eway_bill.EBillListFragment;
import com.e_wayalerts.model.EwaybillModal;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class EWayBilldapter extends RecyclerView.Adapter<EWayBilldapter.ViewHolder> {
	
	private final ArrayList<EwaybillModal> ewaybillModalList;
	
	private final Context context;
	
	EBillListFragment ListFragment;
	
	public EWayBilldapter(Context context_, ArrayList<EwaybillModal> arTestReport_) {
		ewaybillModalList = arTestReport_;
		context = context_;
		ListFragment = EBillListFragment.instance;
	}
	
	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view =
				LayoutInflater.from(parent.getContext()).inflate(R.layout.eway_bill_item_layout,
				parent, false);
		EWayBilldapter.ViewHolder viewHolder = new EWayBilldapter.ViewHolder(view);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
		viewHolder.bind(ewaybillModalList.get(position));
/*
        viewHolder.bname.setText(maincat.getFldBusinessName());
        viewHolder.bcity.setText(maincat.getFldCity());
        viewHolder.bdate.setText(maincat.getFldEdate());

        viewHolder.edit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment.editBusiness(maincat);
            }
        });
        viewHolder.delete_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment.deleteBusiness(maincat.getFldBid().toString());
            }
        });*/
	}
	
	@Override
	public int getItemCount() {
		return ewaybillModalList.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		TextView billnumber, billdate, destinationname, carnumber, drivername, statusTxt;
		
		LinearLayout editBtn, deleteBtn,updateBtn;
		
		public ViewHolder(View itemView) {
			super(itemView);
			billnumber = itemView.findViewById(R.id.billnumber);
			billdate = itemView.findViewById(R.id.billdate);
			destinationname = itemView.findViewById(R.id.destinationname);
			carnumber = itemView.findViewById(R.id.carnumber);
			drivername = itemView.findViewById(R.id.drivername);
			statusTxt = itemView.findViewById(R.id.statusTxt);
			editBtn = itemView.findViewById(R.id.editBtn);
			deleteBtn = itemView.findViewById(R.id.deleteBtn);
			updateBtn = itemView.findViewById(R.id.updateBtn);
		}
		
		public void bind(EwaybillModal ewaybillModal) {
			billnumber.setText(String.valueOf(ewaybillModal.getFldBillNumber()));
			billdate.setText(String.valueOf(ewaybillModal.getFldValidUntil()));
			destinationname.setText(ewaybillModal.getFldPlaceOfDelivery());
			carnumber.setText(String.valueOf(ewaybillModal.getFldVehicleId()));
			drivername.setText(String.valueOf(ewaybillModal.getFldDriverName()));
			//	1- inprogress, 2-extended 3- completed
			if (String.valueOf(ewaybillModal.getFldStatus()).equals("1")) {
				statusTxt.setText(itemView.getContext().getString(R.string.inprogress));
			} else if (String.valueOf(ewaybillModal.getFldStatus()).equals("2")) {
				statusTxt.setText(itemView.getContext().getString(R.string.extended));
			} else if (String.valueOf(ewaybillModal.getFldStatus()).equals("3")) {
				statusTxt.setText(itemView.getContext().getString(R.string.completed));
			}
			if (compareDateTime(ewaybillModal.getFldValidUntil())){
			       updateBtn.setVisibility(View.VISIBLE);
			}else {
				updateBtn.setVisibility(View.GONE);
			}
			
		}
		
		
		
		
		private boolean compareDateTime(String date) {
			try {
				Calendar calendar = Calendar.getInstance();
				boolean graterThanDate;
				String todayDate = Utility.yyyy_MM_dd.format(calendar.getTime());
				String selectDate = Utility.yyyy_MM_dd.format(
						Utility.yyyy_MM_dd.parse(date));
				Date selectDateDate = Utility.yyyy_MM_dd.parse(selectDate);
				Date todayDateDate = Utility.yyyy_MM_dd.parse(todayDate);
				Log.e("selectDateDate", String.valueOf(selectDateDate));
				Log.e("todayDateDate", String.valueOf(todayDateDate));
				assert selectDateDate != null;
				if (selectDateDate.equals(todayDateDate)) {
					graterThanDate = true;  // If two dates are equal.
				} else // If start date is after the end date.
				{
					assert selectDateDate != null;
					graterThanDate = selectDateDate.after(todayDateDate);
				}
				return graterThanDate;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return false;
		}
		
	}
	
	
	
}


