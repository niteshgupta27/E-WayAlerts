package com.e_wayalerts.activity.eway_bill;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
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
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffRecponce;
import com.e_wayalerts.adaptor.BusibessListSpinnerAdapter;
import com.e_wayalerts.adaptor.DriverNameSpinnerAdapter;
import com.e_wayalerts.adaptor.VehicleNumberSpinnerAdapter;
import com.e_wayalerts.model.AddEwayBillModel;
import com.e_wayalerts.model.FleetListModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEwayBillFragment extends Fragment {
	
	RadioButton singleBillRadio, consoilatedBillRadio, intervalonehour, intervaltwohour;
	
	EditText ewayBillNumber, generatedBy, origin, delivery;
	
	TextView validFormDate, validUntilDate;
	
	Spinner vehicleNumberSpinner, driverNameSpinner, businessIDSpinner;
	
	String businessID, vehicleNumberID, staffID, billType;
	
	List<BusinessListResponse.Datum> businessArrayList = new ArrayList<>();
	
	List<StaffModal> staffModalList = new ArrayList<>();
	
	List<FleetListModel.Datum> vehicleNumberList = new ArrayList<>();
	
	ApiInterface apiInterface;
	
	RelativeLayout validFormRelative, validUntillRelative, submitBtn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_add_eway_bill, container, false);
		
		Init(view);
		listner();
		businessList();
		return view;
	}
	
	private void Init(View view) {
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
		singleBillRadio = view.findViewById(R.id.singleBillRadio);
		consoilatedBillRadio = view.findViewById(R.id.consoilatedBillRadio);
		ewayBillNumber = view.findViewById(R.id.ewayBillNumber);
		generatedBy = view.findViewById(R.id.generatedBy);
		origin = view.findViewById(R.id.origin);
		delivery = view.findViewById(R.id.delivery);
		validFormDate = view.findViewById(R.id.validform_date);
		validUntilDate = view.findViewById(R.id.validUntilTxt);
		vehicleNumberSpinner = view.findViewById(R.id.vehicleNumberSpinner);
		driverNameSpinner = view.findViewById(R.id.driverNameSpinner);
		businessIDSpinner = view.findViewById(R.id.businessIDSpinner);
		validFormRelative = view.findViewById(R.id.validform_relative);
		validUntillRelative = view.findViewById(R.id.valid_untill_relative);
		intervalonehour = view.findViewById(R.id.intervalonehour);
		intervaltwohour = view.findViewById(R.id.intervaltwohour);
		submitBtn = view.findViewById(R.id.submitBtn);
	}
	
	private void listner() {
		businessIDSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (!String.valueOf(businessArrayList.get(position).getFldBid()).equals("0")) {
					businessID = String.valueOf(businessArrayList.get(position).getFldBid());
					
					getVehicleNumber(businessID);
					getDriverName(businessID);
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		vehicleNumberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (!String.valueOf(vehicleNumberList.get(position).getFldFltId()).equals("0")) {
					vehicleNumberID = String.valueOf(vehicleNumberList.get(position).getFldFltId());
					
					
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		driverNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (!String.valueOf(staffModalList.get(position).getFldUid()).equals("0")) {
					staffID = String.valueOf(staffModalList.get(position).getFldUid());
					
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		validFormRelative.setOnClickListener(v -> {
			ChooseDate("0");
		});
		
		validUntillRelative.setOnClickListener(v -> {
			ChooseDate("1");
		});
		
		submitBtn.setOnClickListener(v -> {
			if (singleBillRadio.isChecked()) {
				billType = "0";
			} else if (consoilatedBillRadio.isChecked()) {
				billType = "1";
			}
			
			if (TextUtils.isEmpty(billType)) {
				Utility.ShowToast(getActivity(), getString(R.string.select_bill_type));
			} else if (ewayBillNumber.getText().toString().trim().isEmpty()) {
				Utility.ShowToast(getActivity(), getString(R.string.e_way_bill_number));
			} else if (generatedBy.getText().toString().trim().isEmpty()) {
				Utility.ShowToast(getActivity(), getString(R.string.Generated_By));
			} else if (origin.getText().toString().trim().isEmpty()) {
				Utility.ShowToast(getActivity(), getString(R.string.place_of_origin));
			} else if (delivery.getText().toString().trim().isEmpty()) {
				Utility.ShowToast(getActivity(), getString(R.string.Place_Of_Delivery));
			} else if (validFormDate.getText().toString().trim().isEmpty()) {
				Utility.ShowToast(getActivity(), getString(R.string.valid_from_date));
			} else if (validUntilDate.getText().toString().trim().isEmpty()) {
				Utility.ShowToast(getActivity(), getString(R.string.valid_till_date));
			} else if (TextUtils.isEmpty(businessID)) {
				Utility.ShowToast(getActivity(), getString(R.string.select_business));
			} else if (TextUtils.isEmpty(vehicleNumberID)) {
				Utility.ShowToast(getActivity(), getString(R.string.select_vehicle_number));
			}/*else if (TextUtils.isEmpty(staffID)){
			Utility.ShowToast(getActivity(),getString(R.string.select_driver_name));
		}*/ else {
				addEwayBill(billType, ewayBillNumber.getText().toString().trim(),
						generatedBy.getText().toString().trim(),
						origin.getText().toString().trim(),
						delivery.getText().toString().trim(),
						validFormDate.getText().toString().trim(),
						validUntilDate.getText().toString().trim(), businessID, vehicleNumberID,
						"4");
			}
			
		});
	}
	
	private void addEwayBill(String billType, String ewayBillNumber, String generatedBy,
	                         String origin, String delivery, String validFormDate,
	                         String validUntilDate, String businessID, String vehicleNumberID,
	                         String DriverID) {
		String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<AddEwayBillModel> call =
				apiInterface.addEwayBill(userid, billType, ewayBillNumber, generatedBy, origin,
						delivery, validFormDate, validUntilDate, businessID, vehicleNumberID,
						DriverID);
		call.enqueue(new Callback<AddEwayBillModel>() {
			@SuppressLint ("NotifyDataSetChanged")
			@Override
			public void onResponse(@NonNull Call<AddEwayBillModel> call,
			                       @NonNull Response<AddEwayBillModel> response) {
				Log.e("TAG",
						"response 33: " + Objects.requireNonNull(response.body()).getStatus());
				if (response.isSuccessful()) {
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						requireActivity().onBackPressed();
					}
				} else {
					Log.e("Error===>", Objects.requireNonNull(response.errorBody()).toString());
				}
			}
			
			@Override
			public void onFailure(@NonNull Call<AddEwayBillModel> call, @NonNull Throwable t) {
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
				Log.e("TAG",
						"response 33: " + Objects.requireNonNull(response.body()).getStatus());
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
							
							getVehicleNumber(String.valueOf(businessArrayList.get(1).getFldBid()));
							getDriverName(String.valueOf(businessArrayList.get(1).getFldBid()));
						}
						BusibessListSpinnerAdapter aa =
								new BusibessListSpinnerAdapter(requireActivity(),
										android.R.layout.simple_spinner_item, businessArrayList);
						businessIDSpinner.setAdapter(aa);
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
	
	private void getVehicleNumber(String businessID) {
		FleetListModel.Datum catbean = new FleetListModel.Datum();
		catbean.setFldNumber(getString(R.string.select_vehicle_number));
		catbean.setFldFltId(0);
		vehicleNumberList.add(catbean);
		String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<FleetListModel> call = apiInterface.getFleetList(userid, businessID);
		call.enqueue(new Callback<FleetListModel>() {
			@SuppressLint ("NotifyDataSetChanged")
			@Override
			public void onResponse(@NonNull Call<FleetListModel> call,
			                       @NonNull Response<FleetListModel> response) {
				Log.e("TAG",
						"response 33: " + Objects.requireNonNull(response.body()).getStatus());
				if (response.isSuccessful()) {
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						if (response.body().getData().size() > 0) {
							for (int i = 0; i < response.body().getData().size(); i++) {
								FleetListModel.Datum catbean = new FleetListModel.Datum();
								catbean.setFldNumber(
										response.body().getData().get(i).getFldNumber());
								catbean.setFldFltId(response.body().getData().get(i).getFldFltId());
								vehicleNumberList.add(catbean);
							}
							
						}
						VehicleNumberSpinnerAdapter aa =
								new VehicleNumberSpinnerAdapter(requireActivity(),
										android.R.layout.simple_spinner_item, vehicleNumberList);
						vehicleNumberSpinner.setAdapter(aa);
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
	
	private void getDriverName(String businessID) {
		StaffModal catbean = new StaffModal();
		catbean.setFldFname(getString(R.string.select_driver_name));
		catbean.setFldLname("");
		catbean.setFldUid(0);
		staffModalList.add(catbean);
		String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<StaffRecponce> call = apiInterface.StaffList(userid, "6", businessID);
		call.enqueue(new Callback<StaffRecponce>() {
			@Override
			public void onResponse(@NonNull Call<StaffRecponce> call,
			                       @NonNull Response<StaffRecponce> response) {
				if (response.isSuccessful()) {
					
					assert response.body() != null;
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						
						if (response.body().getData().size() > 0) {
							for (int i = 0; i < response.body().getData().size(); i++) {
								StaffModal catbean = new StaffModal();
								catbean.setFldFname(response.body().getData().get(i).getFldFname());
								catbean.setFldLname(response.body().getData().get(i).getFldLname());
								catbean.setFldUid(response.body().getData().get(i).getFldUid());
								staffModalList.add(catbean);
							}
							
						}
						
					} else {
						Log.e("Error===>", response.body().getMessage());
					}
					
					DriverNameSpinnerAdapter aa = new DriverNameSpinnerAdapter(requireActivity(),
							android.R.layout.simple_spinner_item, staffModalList);
					driverNameSpinner.setAdapter(aa);
					
					
				} else {
					assert response.errorBody() != null;
					Log.e("Error===>", response.errorBody().toString());
				}
				
			}
			
			@Override
			public void onFailure(@NonNull Call<StaffRecponce> call, @NonNull Throwable t) {
				Toast.makeText(requireActivity(), t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				
			}
		});
		
		
	}
	
	private void ChooseDate(String valid) {
		final Calendar c = Calendar.getInstance();
		int mYear = c.get(Calendar.YEAR);
		int mMonth = c.get(Calendar.MONTH);
		int mDay = c.get(Calendar.DAY_OF_MONTH);
		
		// Launch Date Picker Dialog
		DatePickerDialog dpd =
				new DatePickerDialog(requireActivity(), new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
					                      int dayOfMonth) {
						// Display Selected date in textbox
						if (valid.equals("0")) {
							validFormDate.setText(
									dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
						} else {
							validUntilDate.setText(
									dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
						}
					}
				}, mYear, mMonth, mDay);
		dpd.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
		dpd.show();
		
	}
}