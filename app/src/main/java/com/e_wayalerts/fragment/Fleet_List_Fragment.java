package com.e_wayalerts.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.adaptor.BusibessListSpinnerAdapter;
import com.e_wayalerts.adaptor.FleetListAdapter;
import com.e_wayalerts.model.FleetListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fleet_List_Fragment extends Fragment {
	
	ApiInterface apiInterface;
	
	Spinner businessListSpinner;
	
	RecyclerView fleetList;
	
	List<BusinessListResponse.Datum> businessArrayList = new ArrayList<>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_fleet__list_, container, false);
		
		init(view);
		
		return view;
	}
	
	private void init(View view) {
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
		businessListSpinner = view.findViewById(R.id.businessListSpinner);
		fleetList = view.findViewById(R.id.fleetList);
		
		businessListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				
				if (businessArrayList.get(position).getFldBid() != 0) {
					
					getFleetList(businessArrayList.get(position).getFldBid());
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
		});
		businessList();
	}
	
	private void getFleetList(Integer fldBid) {
		String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<FleetListModel> call = apiInterface.getFleetList(userid, String.valueOf(fldBid));
		call.enqueue(new Callback<FleetListModel>() {
			@SuppressLint ("NotifyDataSetChanged")
			@Override
			public void onResponse(@NonNull Call<FleetListModel> call,
			                       @NonNull Response<FleetListModel> response) {
				Log.e("TAG", "response 33: " + String.valueOf(
						Objects.requireNonNull(response.body()).getStatus()));
				
				if (response.isSuccessful()) {
					
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						if (response.body().getData().size() > 0) {
							
							FleetListAdapter fleetListAdapter = new FleetListAdapter((ArrayList<FleetListModel.Datum>) response.body().getData());
							fleetList.setHasFixedSize(true);
							fleetList.setAdapter(fleetListAdapter);
						}
						
						
					}
				} else {
					Log.e("Error===>", Objects.requireNonNull(response.errorBody()).toString());
				}
			}
			
			@Override
			public void onFailure(@NonNull Call<FleetListModel> call, @NonNull Throwable t) {
				Toast.makeText(requireActivity(), t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				
			}
		});
	}
	
	private void businessList() {
		businessArrayList.clear();
		BusinessListResponse.Datum catbean = new BusinessListResponse.Datum();
		catbean.setFldBusinessName(getString(R.string.select_business));
		catbean.setFldBid(0);
		businessArrayList.add(catbean);
		
		String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<BusinessListResponse> call = apiInterface.BusinessList(userid, "1");
		call.enqueue(new Callback<BusinessListResponse>() {
			@SuppressLint ("NotifyDataSetChanged")
			@Override
			public void onResponse(@NonNull Call<BusinessListResponse> call,
			                       @NonNull Response<BusinessListResponse> response) {
				Log.e("TAG", "response 33: " + String.valueOf(
						Objects.requireNonNull(response.body()).getStatus()));
				
				if (response.isSuccessful()) {
					
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						if (response.body().getData().size() > 0) {
							
							for (int i = 0; i < response.body().getData().size(); i++) {
								BusinessListResponse.Datum catbean =
										new BusinessListResponse.Datum();
								catbean.setFldBusinessName(
										response.body().getData().get(i).getFldBusinessName());
								catbean.setFldBid(response.body().getData().get(i).getFldBid());
								businessArrayList.add(catbean);
							}
							
							BusibessListSpinnerAdapter aa =
									new BusibessListSpinnerAdapter(requireActivity(),
											android.R.layout.simple_spinner_item,
											businessArrayList);
							aa.setDropDownViewResource(
									android.R.layout.simple_spinner_dropdown_item);
							//Setting the ArrayAdapter data on the Spinner
							businessListSpinner.setAdapter(aa);
						}
						
						
					}
				} else {
					Log.e("Error===>", Objects.requireNonNull(response.errorBody()).toString());
				}
			}
			
			@Override
			public void onFailure(@NonNull Call<BusinessListResponse> call, @NonNull Throwable t) {
				Toast.makeText(requireActivity(), t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				
			}
		});
		
		
	}
}