package com.e_wayalerts.activity.add_staff;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;
import com.e_wayalerts.activity.add_staff.adapter.SpinAdapter;
import com.e_wayalerts.activity.add_staff.model.UserRollListModel;
import com.e_wayalerts.adaptor.BusinessCheckAdaptor;
import com.e_wayalerts.model.AddStaffModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStaffFragment extends Fragment {
	
	EditText staffFirstName, staffLastName, staffMobileNumber, staffEmailAddress;
	
	Spinner jobTitleSpinner;
	
	CheckBox pushCheckboxB1, emailCheckboxB1, smsCheckboxB1;
	
	RelativeLayout mLayoutSubmit;
	
	RecyclerView businessList;
	
	ApiInterface apiInterface;
	
	int selectedUserRoll = 0;
	
	String businessID, inAPP, inSms, inEmail;
	String S_id;
	List<UserRollListModel.UserRollListItem> UserRollList = new ArrayList<>();
	
	List<BusinessListResponse.Datum> businessArrayList = new ArrayList<>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_staff, container, false);
		
		init(view);
		listner();
		Bundle args = getArguments();
		S_id = args.getString("b_id");
		if(!S_id.equals("0")){
			Setvalue((StaffModal) args.getSerializable("data"));
		}
		return view;
	}
	
	private void init(View view) {
		
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
		staffFirstName = view.findViewById(R.id.staffFirstName);
		staffLastName = view.findViewById(R.id.staffLastName);
		staffMobileNumber = view.findViewById(R.id.staffMobileNumber);
		staffEmailAddress = view.findViewById(R.id.staffEmailAddress);
		jobTitleSpinner = view.findViewById(R.id.jobTitleSpinner);
		pushCheckboxB1 = view.findViewById(R.id.pushCheckboxB1);
		emailCheckboxB1 = view.findViewById(R.id.emailCheckboxB1);
		smsCheckboxB1 = view.findViewById(R.id.smsCheckboxB1);
		businessList = view.findViewById(R.id.businessList);
		mLayoutSubmit = view.findViewById(R.id.mLayoutSubmit);
		
		businessList();
		userRoll();
	}
	public  void Setvalue(StaffModal bundle){
		staffFirstName.setText(bundle.getFldFname());
		staffLastName.setText(bundle.getFldLname());
		staffMobileNumber.setText(bundle.getFldMobile());
		staffEmailAddress.setText(bundle.getFldEmail().trim());
		if (bundle.getFldNEmail() == 1){
			emailCheckboxB1.setChecked(true);
		}else {
			emailCheckboxB1.setChecked(false);
		}
		if (bundle.getFldNInApp() == 1){
			pushCheckboxB1.setChecked(true);
		}else {
			pushCheckboxB1.setChecked(false);
		}
		if (bundle.getFldNInSms() == 1){
			smsCheckboxB1.setChecked(true);
		}else {
			smsCheckboxB1.setChecked(false);
		}
		businessID = bundle.getFldBusinessId().toString();
		selectedUserRoll = bundle.getFldRoleId();
	}
	public void listner() {
		
		jobTitleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (!String.valueOf(UserRollList.get(position).getFldRoleId()).equals("0")) {
					selectedUserRoll = UserRollList.get(position).getFldRoleId();
					Log.e("selectedUserRoll", String.valueOf(selectedUserRoll));
				}
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			
			}
		});
		
		mLayoutSubmit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (Utility.isNetworkConnected(requireActivity())) {
					AddBusiness();
				} else {
					Utility.ShowToast(requireActivity(),
							requireActivity().getString(R.string.checkInternet));
				}
			}
		});
		
		
	}
	
	private void businessList() {
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
							businessArrayList = response.body().getData();
							BusinessCheckAdaptor adaptor =
									new BusinessCheckAdaptor(requireActivity(), businessArrayList);
							businessList.setAdapter(adaptor);
							adaptor.notifyDataSetChanged();
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
	
	
	private void staffList() {
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
							businessArrayList = response.body().getData();
							BusinessCheckAdaptor adaptor =
									new BusinessCheckAdaptor(requireActivity(), businessArrayList);
							businessList.setAdapter(adaptor);
							adaptor.notifyDataSetChanged();
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
	
	private void userRoll() {
		UserRollListModel.UserRollListItem catbean = new UserRollListModel.UserRollListItem();
		catbean.setFldRoleName(getString(R.string.selectUserRoll));
		catbean.setFldRoleId(0);
		catbean.setFldRoleCode("00");
		UserRollList.add(catbean);
		String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<UserRollListModel> call = apiInterface.getUserRoll(userid);
		call.enqueue(new Callback<UserRollListModel>() {
			@Override
			public void onResponse(@NonNull Call<UserRollListModel> call,
			                       @NonNull Response<UserRollListModel> response) {
				assert response.body() != null;
				Log.e("TAG", "response 33: " + String.valueOf(response.body().getStatus()));
				
				if (response.isSuccessful()) {
					
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						if (response.body().getData().size() > 0) {
							
							for (int i = 0; i < response.body().getData().size(); i++) {
								UserRollListModel.UserRollListItem catbean = new UserRollListModel.UserRollListItem();
								catbean.setFldRoleName(
										response.body().getData().get(i).getFldRoleName());
								catbean.setFldRoleId(
										response.body().getData().get(i).getFldRoleId());
								catbean.setFldRoleCode(
										response.body().getData().get(i).getFldRoleCode());
								UserRollList.add(catbean);
							}
							
							SpinAdapter aa = new SpinAdapter(requireActivity(),
									android.R.layout.simple_spinner_item, UserRollList);
							aa.setDropDownViewResource(
									android.R.layout.simple_spinner_dropdown_item);
							//Setting the ArrayAdapter data on the Spinner
							jobTitleSpinner.setAdapter(aa);
							if (selectedUserRoll != 0){
								int possion = aa.getpossion(selectedUserRoll);
								jobTitleSpinner.setSelection(possion);
							}
						}
						
						
					}
				} else {
					Log.e("Error===>", Objects.requireNonNull(response.errorBody()).toString());
				}
			}
			
			@Override
			public void onFailure(@NonNull Call<UserRollListModel> call, @NonNull Throwable t) {
				Toast.makeText(requireActivity(), t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				
			}
		});
		
		
	}
	
	private void AddBusiness() {
		
		businessID = "";
		if (businessArrayList.size() > 0) {
			for (int i = 0; i < businessArrayList.size(); i++) {
				if (businessArrayList.get(i).isIschecked()) {
					if (TextUtils.isEmpty(businessID)) {
						businessID =
								 String.valueOf(businessArrayList.get(i).getFldBid()) ;
						
					} else {
						businessID = businessID + ","  + String.valueOf(
								businessArrayList.get(i).getFldBid()) ;
					}
				}
				
			}
		}
		
		Log.e("", "businessID= " + businessID);
		
		if (staffFirstName.getText().toString().isEmpty()) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.please_enter_first_name));
		} else if (staffLastName.getText().toString().isEmpty()) {
			Utility.ShowToast(requireActivity(),
					Objects.requireNonNull(requireActivity()).getString(
							R.string.please_enter_last_name));
		} else if (staffMobileNumber.getText().toString().isEmpty()) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.please_enter_mobile_number));
		} else if (!Utility.isValidMobile(staffMobileNumber.getText().toString())) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.enter_valid_mobile_number));
		} else if (staffEmailAddress.getText().toString().isEmpty()) {
			Utility.ShowToast(requireActivity(),
					Objects.requireNonNull(requireActivity()).getString(
							R.string.enter_email_Address));
		} else if (!Utility.isValidEmail(staffEmailAddress.getText().toString())) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.enter_valid_email_address));
		} else if (TextUtils.isEmpty(String.valueOf(selectedUserRoll))) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.selectUserRoll));
		} else if (TextUtils.isEmpty(businessID)) {
			Utility.ShowToast(requireActivity(),
					requireActivity().getString(R.string.pleaseSelectbusiness));
		} else {
			
			String userid = Utility.getSharedPreferences(requireActivity(), Constant.User_id);
			
			if (pushCheckboxB1.isChecked()) {
				inAPP = "1";
			} else {
				inAPP = "0";
			}
			
			if (emailCheckboxB1.isChecked()) {
				inSms = "1";
			} else {
				inSms = "0";
			}
			
			if (smsCheckboxB1.isChecked()) {
				inEmail = "1";
			} else {
				inEmail = "0";
			}
			addingbusiness(userid, staffFirstName.getText().toString().trim(),
					staffLastName.getText().toString().trim(),
					staffMobileNumber.getText().toString().trim(),
					staffEmailAddress.getText().toString().trim(),
					String.valueOf(selectedUserRoll),
					businessID, inAPP, inEmail, inSms);
		}
		
		
	}
	
	private void addingbusiness(String userid, String staffFirstName, String staffLastName,
	                            String staffMobileNumber, String staffEmailAddress,
	                            String selectedUserRoll, String businessID, String inAPP,
	                            String inEmail, String inSms) {
		
		Call<AddStaffModel> call =
				apiInterface.addstaff(userid, staffFirstName, staffLastName, staffMobileNumber,
						staffEmailAddress, selectedUserRoll, businessID, inAPP, inEmail, inSms);
		call.enqueue(new Callback<AddStaffModel>() {
			@SuppressLint ("NotifyDataSetChanged")
			@Override
			public void onResponse(@NonNull Call<AddStaffModel> call,
			                       @NonNull Response<AddStaffModel> response) {
				Log.e("response", response.body().toString());
				if (response.isSuccessful()) {
					
					assert response.body() != null;
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						
						requireActivity().onBackPressed();
						
					}
				} else {
					Log.e("Error===>", Objects.requireNonNull(response.errorBody()).toString());
				}
			}
			
			@Override
			public void onFailure(@NonNull Call<AddStaffModel> call, @NonNull Throwable t) {
				Toast.makeText(requireActivity(), t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				
			}
		});
		
	}
}