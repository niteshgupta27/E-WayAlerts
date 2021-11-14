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
		
		
	}
	
	@Override
	public int getItemCount() {
		return arMediIn.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		TextView bname, bcity, bdate;
		
		public ViewHolder(View itemView) {
			super(itemView);
			bname = itemView.findViewById(R.id.bname);
			bcity = itemView.findViewById(R.id.bcity);
			bdate = itemView.findViewById(R.id.bdate);
		}
	}
}


