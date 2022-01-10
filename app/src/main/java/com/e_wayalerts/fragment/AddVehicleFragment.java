package com.e_wayalerts.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.adaptor.BusibessListSpinnerAdapter;
import com.e_wayalerts.adaptor.VehicleListAdapter;
import com.e_wayalerts.adaptor.VehicleTypeAdapter;
import com.e_wayalerts.model.AddVehicleModel;
import com.e_wayalerts.model.FleetListModel;
import com.e_wayalerts.model.VehicleListModel;
import com.e_wayalerts.model.VehicleTypeModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddVehicleFragment extends Fragment {
	
	AppCompatEditText vehicleNumber;
	
	Spinner makeVehicleSpinner, businessListSpinner, vehicleTypeSpinner;
	
	TextView mTxer;
	
	RelativeLayout addVehiclebtn;
	
	ApiInterface apiInterface;
	
	List<VehicleListModel.Datum> vehicleList = new ArrayList<>();
	
	List<VehicleTypeModel.Datum> vehicleTypeList = new ArrayList<>();
	
	List<BusinessListResponse.Datum> businessArrayList = new ArrayList<>();
	
	String vehicleName, vehicleType;
	int businessID = 0;
	FleetListModel.Datum fleetModel;
	
	int vhicaleID = 0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_vehicle, container, false);
		init(view);
		listener();
		getVehicleList();
		businessList();
		getVehicleType();
		return view;
	}
	
	private void init(View view) {
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
		vehicleNumber = view.findViewById(R.id.vehicle_number);
		makeVehicleSpinner = view.findViewById(R.id.makeVehicleSpinner);
		businessListSpinner = view.findViewById(R.id.businessList);
		vehicleTypeSpinner = view.findViewById(R.id.vehicletype);
		addVehiclebtn = view.findViewById(R.id.addVehiclebtn);
		mTxer = view.findViewById(R.id.mTxer);
		vehicleNumber.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
		if (getArguments() != null) {
			fleetModel = (FleetListModel.Datum) getArguments().getSerializable("FleetModel");
			vehicleNumber.setText(fleetModel.getFldNumber());
			businessID = fleetModel.getFldBusinessId();
			vehicleName = fleetModel.getFldMake();
			vehicleType = fleetModel.getFldType();
			vhicaleID = fleetModel.getFldFltId();
			mTxer.setText(R.string.edit_vehicle);
		}
	}
	
	private void listener() {
		makeVehicleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (!String.valueOf(vehicleList.get(position).getFldMakeId()).equals("0")) {
					vehicleName = String.valueOf(vehicleList.get(position).getFldMakeName());
					Log.e("vehicleName", String.valueOf(vehicleName));
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		businessListSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (!String.valueOf(businessArrayList.get(position).getFldBid()).equals("0")) {
					businessID = businessArrayList.get(position).getFldBid();
					Log.e("businessID", String.valueOf(businessID));
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		vehicleTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (!String.valueOf(vehicleTypeList.get(position).getFldTypeId()).equals("0")) {
					vehicleType = String.valueOf(vehicleTypeList.get(position).getFldTypeName());
					Log.e("vehicleType", String.valueOf(vehicleType));
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		addVehiclebtn.setOnClickListener(v -> {
			if (Utility.isNetworkConnected(requireActivity())) {
				if(vhicaleID != 0){
					updateVehicle();
				}else {
					AddVehicle();
				}

			} else {
				Utility.ShowToast(requireActivity(),
						requireActivity().getString(R.string.checkInternet));
			}
		});
	}
	
	private void getVehicleList() {
		VehicleListModel.Datum catbean = new VehicleListModel.Datum();
		catbean.setFldMakeName(getString(R.string.vehicleName));
		catbean.setFldMakeId(0);
		vehicleList.add(catbean);
		String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<VehicleListModel> call = apiInterface.getVehicleList(userid);
		call.enqueue(new Callback<VehicleListModel>() {
			@Override
			public void onResponse(@NonNull Call<VehicleListModel> call,
			                       @NonNull Response<VehicleListModel> response) {
				assert response.body() != null;
				Log.e("TAG", "response 33: " + String.valueOf(response.body().getStatus()));
				if (response.isSuccessful()) {
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						if (response.body().getData().size() > 0) {
							for (int i = 0; i < response.body().getData().size(); i++) {
								VehicleListModel.Datum catbean = new VehicleListModel.Datum();
								int id = response.body().getData().get(i).getFldMakeId();
								String title = response.body().getData().get(i).getFldMakeName();
								catbean.setFldMakeId(id);
								catbean.setFldMakeName(title);
								vehicleList.add(catbean);

							}
							VehicleListAdapter vehicleListAdapter =
									new VehicleListAdapter(requireActivity(),
											android.R.layout.simple_spinner_item, vehicleList);
							makeVehicleSpinner.setAdapter(vehicleListAdapter);
							if (!vehicleName.isEmpty()){
								int possion = vehicleListAdapter.getpossion(vehicleName);
								makeVehicleSpinner.setSelection(possion);
							}
							//vehicleListAdapter.notifyDataSetChanged();
						}
					}
				} else {
					Log.e("Error===>", Objects.requireNonNull(response.errorBody()).toString());
				}
			}
			
			@Override
			public void onFailure(@NonNull Call<VehicleListModel> call, @NonNull Throwable t) {
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
							businessListSpinner.setAdapter(aa);
							if (businessID != 0){
								int possion = aa.getpossion(businessID);
								businessListSpinner.setSelection(possion);
							}
							aa.notifyDataSetChanged();
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
	
	private void getVehicleType() {
		VehicleTypeModel.Datum catbean = new VehicleTypeModel.Datum();
		catbean.setFldTypeName(getString(R.string.select_vehicle_type));
		catbean.setFldTypeId(0);
		vehicleTypeList.add(catbean);
		String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<VehicleTypeModel> call = apiInterface.getVehicleType(userid);
		call.enqueue(new Callback<VehicleTypeModel>() {
			@Override
			public void onResponse(@NonNull Call<VehicleTypeModel> call,
			                       @NonNull Response<VehicleTypeModel> response) {
				assert response.body() != null;
				Log.e("TAG", "response 33: " + String.valueOf(response.body().getStatus()));
				if (response.isSuccessful()) {
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						if (response.body().getData().size() > 0) {
							for (int i = 0; i < response.body().getData().size(); i++) {
								VehicleTypeModel.Datum catbean = new VehicleTypeModel.Datum();
								catbean.setFldTypeName(
										response.body().getData().get(i).getFldTypeName());
								catbean.setFldTypeId(
										response.body().getData().get(i).getFldTypeId());
								vehicleTypeList.add(catbean);
							}
							VehicleTypeAdapter aa = new VehicleTypeAdapter(requireActivity(),
									android.R.layout.simple_spinner_item, vehicleTypeList);
							vehicleTypeSpinner.setAdapter(aa);
							if (!vehicleType.isEmpty()){
								int possion = aa.getpossion(vehicleType);
								vehicleTypeSpinner.setSelection(possion);
							}
							aa.notifyDataSetChanged();
						}
					}
				} else {
					Log.e("Error===>", Objects.requireNonNull(response.errorBody()).toString());
				}
			}
			
			@Override
			public void onFailure(@NonNull Call<VehicleTypeModel> call, @NonNull Throwable t) {
				Toast.makeText(requireActivity(), t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
			}
		});
	}
	private void updateVehicle() {
		if (vehicleNumber.getText().toString().trim().isEmpty()) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.Vehicle_Number));
		} else if (TextUtils.isEmpty(vehicleName)) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.vehicleName));
		} else if (businessID == 0) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.select_business));
		} else if (TextUtils.isEmpty(vehicleType)) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.select_vehicle_type));
		} else {
			String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
			Call<AddVehicleModel> call = apiInterface.updateVehicle(userid, businessID,
					vehicleNumber.getText().toString().trim(), vehicleName, vehicleType,vhicaleID);
			call.enqueue(new Callback<AddVehicleModel>() {
				@Override
				public void onResponse(@NonNull Call<AddVehicleModel> call,
									   @NonNull Response<AddVehicleModel> response) {
					assert response.body() != null;
					Log.e("TAG", "response 33: " + String.valueOf(response.body().getStatus()));
					if (response.isSuccessful()) {
						if (String.valueOf(response.body().getStatus()).equals("200")) {
							requireActivity().onBackPressed();
							//Utility.ShowToast(requireActivity(), response.body().getMessage());
						}
					} else {
						Log.e("Error===>",
								Objects.requireNonNull(response.errorBody()).toString());
					}
				}

				@Override
				public void onFailure(@NonNull Call<AddVehicleModel> call, @NonNull Throwable t) {
					Toast.makeText(requireActivity(), t.toString(),
							Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				}
			});
		}
	}
	private void AddVehicle() {
		if (vehicleNumber.getText().toString().trim().isEmpty()) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.Vehicle_Number));
		} else if (TextUtils.isEmpty(vehicleName)) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.vehicleName));
		} else if (businessID == 0) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.select_business));
		} else if (TextUtils.isEmpty(vehicleType)) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.select_vehicle_type));
		} else {
			String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
			Call<AddVehicleModel> call = apiInterface.addVehicle(userid, businessID,
					vehicleNumber.getText().toString().trim(), vehicleName, vehicleType);
			call.enqueue(new Callback<AddVehicleModel>() {
				@Override
				public void onResponse(@NonNull Call<AddVehicleModel> call,
				                       @NonNull Response<AddVehicleModel> response) {
					assert response.body() != null;
					Log.e("TAG", "response 33: " + String.valueOf(response.body().getStatus()));
					if (response.isSuccessful()) {
						if (String.valueOf(response.body().getStatus()).equals("200")) {
							requireActivity().onBackPressed();
							Utility.ShowToast(requireActivity(), response.body().getMessage());
						}
					} else {
						Log.e("Error===>",
								Objects.requireNonNull(response.errorBody()).toString());
					}
				}
				
				@Override
				public void onFailure(@NonNull Call<AddVehicleModel> call, @NonNull Throwable t) {
					Toast.makeText(requireActivity(), t.toString(),
							Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				}
			});
		}
	}
	
}