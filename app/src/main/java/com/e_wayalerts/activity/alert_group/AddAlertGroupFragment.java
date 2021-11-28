package com.e_wayalerts.activity.alert_group;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.adaptor.BusinessListAdaptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAlertGroupFragment extends Fragment {
	
	EditText groupName;
	
	RecyclerView businessList;
	
	String businessID;
	
	ApiInterface apiInterface;
	
	List<BusinessListResponse.Datum> businessArrayList = new ArrayList<>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_alert_group, container, false);
		
		init(view);
		businessList();
		
		return view;
	}
	
	private void init(View view) {
		apiInterface = ApiClient.getClient().create(ApiInterface.class);
		groupName = view.findViewById(R.id.groupName);
		businessList = view.findViewById(R.id.businessList);
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
							BusinessListAdaptor adaptor =
									new BusinessListAdaptor(requireActivity(), businessArrayList);
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
	
	public void addGroup() {
		businessID = "";
		if (businessArrayList.size() > 0) {
			for (int i = 0; i < businessArrayList.size(); i++) {
				if (businessArrayList.get(i).isIschecked()) {
					if (TextUtils.isEmpty(businessID)) {
						businessID =
								"[" + String.valueOf(businessArrayList.get(i).getFldBid()) + "]";
						
					} else {
						businessID = businessID + "," + "[" + String.valueOf(
								businessArrayList.get(i).getFldBid()) + "]";
					}
				}
				
			}
		}
		
		Log.e("", "businessID= " + businessID);
	}
}