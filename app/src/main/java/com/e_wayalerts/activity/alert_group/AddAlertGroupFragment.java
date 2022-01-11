package com.e_wayalerts.activity.alert_group;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.e_wayalerts.activity.add_staff.StaffModal.StaffRecponce;
import com.e_wayalerts.activity.dropdown.DropDownAdapter;
import com.e_wayalerts.activity.dropdown.DropDownModal;
import com.e_wayalerts.adaptor.StaffAdaptor;
import com.e_wayalerts.adaptor.StaffCheckAdaptor;
import com.e_wayalerts.model.AddGroupModel;
import com.e_wayalerts.model.GroupModal;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAlertGroupFragment extends Fragment {
	
	EditText groupName;
	ApiInterface apiInterface;
	RecyclerView mListView;
	Spinner mSpinnerbusiness;
	DropDownAdapter customAdapter;
	String selectedbusinessID = "0",staffID = "0";
	StaffCheckAdaptor adaptor;
	RelativeLayout addGroupBtn;
	public ArrayList<DropDownModal> arraybusiness = new ArrayList<DropDownModal>();
	List<StaffModal>staffModalList = new ArrayList<>();
	String groupID = "0";
	//Integer businessID;
	//String StaffIDs;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_alert_group, container, false);
		
		init(view);
		listner();
		Bundle args = getArguments();
		groupID = args.getString("b_id");
		if(!groupID.equals("0")){
			Setvalue((GroupModal) args.getSerializable("data"));
		}
		return view;
	}
	
	
	
	private void init(View view) {
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
		groupName = view.findViewById(R.id.groupName);
		mListView = view.findViewById(R.id.rv_storage_yards);
		addGroupBtn = view.findViewById(R.id.addGroupBtn);
		mSpinnerbusiness = view.findViewById(R.id.mSpinnerState);
		LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
		linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mListView.setLayoutManager(linearLayoutManager);
		
	}
	public  void Setvalue(GroupModal bundle){
		groupName.setText(bundle.getFldGroupName());

		selectedbusinessID = bundle.getFldBusinessId().toString();
		staffID = bundle.getStaffId().toString();
	}
	private void listner() {
		DropDownModal ra = new DropDownModal();
		ra.setmStrId(
				"0");
		ra.setmStrValue(getString(R.string.select_business));
		arraybusiness.add(ra);
		BusinessList();
		customAdapter = new DropDownAdapter(getContext(), arraybusiness);
		mSpinnerbusiness.setAdapter(customAdapter);
		mSpinnerbusiness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				selectedbusinessID = arraybusiness.get(i).getmStrId();
				StaffList();
			}
			
			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});
		
		addGroupBtn.setOnClickListener(v -> {
			staffID = "";
			if (staffModalList.size() > 0) {
				for (int i = 0; i < staffModalList.size(); i++) {
					if (staffModalList.get(i).isChecked()) {
						if (TextUtils.isEmpty(staffID)) {
							staffID = String.valueOf(staffModalList.get(i).getFldUid());
							
						} else {
							staffID = staffID + "," +  String.valueOf(
									staffModalList.get(i).getFldUid()) ;
						}
					}
					
				}
			}
		
			if (groupName.getText().toString().trim().isEmpty()){
				Utility.ShowToast(requireActivity(),getString(R.string.enter_group_name));
			}else if (selectedbusinessID.isEmpty()){
				Utility.ShowToast(requireActivity(),getString(R.string.select_business));
			}else if (staffID.isEmpty()){
				Utility.ShowToast(requireActivity(),getString(R.string.select_staff));
			}else {
				if (groupID.equals("0")) {
					AddGroup();
				}else {
					UpdateGroup();
				}
			}
		
		});
	}
	
	
	private void StaffList() {
		staffModalList = new ArrayList<>();
		String userid= Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		String role_ID= Utility.getSharedPreferences(requireActivity(), Constant.role_ID);
		Call<StaffRecponce> call = apiInterface.StaffList(userid,role_ID,selectedbusinessID);
		call.enqueue(new Callback<StaffRecponce>() {
			@SuppressLint ("NotifyDataSetChanged")
			@Override
			public void onResponse(@NonNull Call<StaffRecponce> call, @NonNull
					Response<StaffRecponce> response) {
				if (response.isSuccessful()) {
					
					assert response.body() != null;
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						if (response.body().getData().size()>0){
							staffModalList = response.body().getData();
							mListView.setVisibility(View.VISIBLE);
							adaptor = new StaffCheckAdaptor(staffModalList);
							mListView.setAdapter(adaptor);
							adaptor.notifyDataSetChanged();
							if(!staffID.equals("0")){
								if (adaptor != null) {
									String[] separated = staffID.split(",");

									for (int j = 0; j < separated.length; j++) {
										String staffid = separated[j];
										for (int i = 0; i < adaptor.staffModalList.size(); i++) {

											Log.e("",staffid);
//Log.e("",adaptor.businessList.get(i).getFldBid());
											if(adaptor.staffModalList.get(i).getFldUid().toString().equals(staffid)){
												adaptor.staffModalList.get(i).setChecked(true);
											}
										}
									}
									adaptor.notifyDataSetChanged();
								}
							}
						}
						else{
							mListView.setVisibility(View.GONE);
						}
						
						
					}else{
						mListView.setVisibility(View.GONE);
					}
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
	
	private void BusinessList() {
		String userid= Utility.getSharedPreferences(requireActivity(),Constant.User_id);
		Call<BusinessListResponse> call = apiInterface.BusinessList(userid,"1");
		call.enqueue(new Callback<BusinessListResponse>() {
			@Override
			public void onResponse(@NonNull Call<BusinessListResponse> call, @NonNull
					Response<BusinessListResponse> response) {
				if (response.isSuccessful()) {
					
					assert response.body() != null;
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						if (response.body().getData().size()>0){
							mListView.setVisibility(View.VISIBLE);
							for (int j = 0; j < response.body().getData().size(); j++) {
								DropDownModal ra = new DropDownModal();
								ra.setmStrId(
										response.body().getData().get(j).getFldBid().toString());
								ra.setmStrValue(response.body().getData().get(j).getFldBusinessName());
								arraybusiness.add(ra);
							}
							customAdapter.notifyDataSetChanged();
							if (!selectedbusinessID.equals("0") ){
								int possion = customAdapter.getpossion(selectedbusinessID);
								mSpinnerbusiness.setSelection(possion);
							}
						}
						else{
							mListView.setVisibility(View.GONE);
						}
						
						
					}
				} else {
					assert response.errorBody() != null;
					Log.e("Error===>", response.errorBody().toString());
				}
			}
			
			@Override
			public void onFailure( @NonNull Call<BusinessListResponse> call, @NonNull Throwable t) {
				Toast.makeText(requireActivity(), t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				
			}
		});
		
		
	}
	private void UpdateGroup() {
		String userid= Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<AddGroupModel> call = apiInterface.updateGroup(userid,groupName.getText().toString().trim(),
				selectedbusinessID,staffID,groupID);
		call.enqueue(new Callback<AddGroupModel>() {
			@SuppressLint ("NotifyDataSetChanged")
			@Override
			public void onResponse(@NonNull Call<AddGroupModel> call, @NonNull
					Response<AddGroupModel> response) {
				if (response.isSuccessful()) {

					assert response.body() != null;
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						requireActivity().onBackPressed();
						Utility.ShowToast(requireActivity(),response.body().getMessage());
					}
				} else {
					assert response.errorBody() != null;
					Log.e("Error===>", response.errorBody().toString());
				}
			}

			@Override
			public void onFailure(@NonNull Call<AddGroupModel> call, @NonNull Throwable t) {
				Toast.makeText(requireActivity(), t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

			}
		});
	}
	private void AddGroup() {
		String userid= Utility.getSharedPreferences(requireActivity(), Constant.User_id);
		Call<AddGroupModel> call = apiInterface.addGroup(userid,groupName.getText().toString().trim(),
				selectedbusinessID,staffID);
		call.enqueue(new Callback<AddGroupModel>() {
			@SuppressLint ("NotifyDataSetChanged")
			@Override
			public void onResponse(@NonNull Call<AddGroupModel> call, @NonNull
					Response<AddGroupModel> response) {
				if (response.isSuccessful()) {
					
					assert response.body() != null;
					if (String.valueOf(response.body().getStatus()).equals("200")) {
						Utility.ShowToast(requireActivity(),response.body().getMessage());
						requireActivity().onBackPressed();

					}
				} else {
					assert response.errorBody() != null;
					Log.e("Error===>", response.errorBody().toString());
				}
			}
			
			@Override
			public void onFailure(@NonNull Call<AddGroupModel> call, @NonNull Throwable t) {
				Toast.makeText(requireActivity(), t.toString(),
						Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
				
			}
		});
	}
	
}