package com.e_wayalerts.activity.alert_group;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.add_business.AddBusinesskFragment;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.add_staff.AddStaffFragment;
import com.e_wayalerts.activity.add_staff.StaffListFragment;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffRecponce;
import com.e_wayalerts.activity.dropdown.DropDownAdapter;
import com.e_wayalerts.activity.dropdown.DropDownModal;
import com.e_wayalerts.adaptor.GroupAdapter;
import com.e_wayalerts.adaptor.StaffAdaptor;
import com.e_wayalerts.model.AddGroupModel;
import com.e_wayalerts.model.AddStaffModel;
import com.e_wayalerts.model.GroupListRecponce;
import com.e_wayalerts.model.GroupModal;
import com.e_wayalerts.model.StatusResponce;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlertGroupListFragment extends Fragment {
    Context mContext;
    ApiInterface apiInterface;
    RecyclerView mListView;
    CardView cardview;
    GroupAdapter adaptor;
    FloatingActionButton mImgAddNew;
    LinearLayout Addbutton;
    String selectedbusinessID = "0";
    DropDownAdapter customAdapter;
    Spinner mSpinnerbusiness;
    public ArrayList<DropDownModal> arraybusiness = new ArrayList<DropDownModal>();
    public static AlertGroupListFragment instance;

    public AlertGroupListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alert_list, container, false);

        mListView = (RecyclerView)view.findViewById(R.id.rv_storage_yards);
        cardview = view.findViewById(R.id.cardview);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mContext = getContext();
        mImgAddNew = view.findViewById(R.id.mImgAddNew);
        Addbutton = view.findViewById(R.id.addStaffBtn);
        mSpinnerbusiness = view.findViewById(R.id.mSpinnerState);
        instance = AlertGroupListFragment.this;
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(linearLayoutManager);
        mImgAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAlertGroupFragment fragment = new AddAlertGroupFragment();
                Bundle bundle = new Bundle();
                bundle.putString("b_id","0");
                fragment.setArguments(bundle);
                Utility.loadFragment(requireActivity(), fragment,
                        true,
                        null);
            }
        });
        Addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAlertGroupFragment fragment = new AddAlertGroupFragment();
                Bundle bundle = new Bundle();
                bundle.putString("b_id","0");
                fragment.setArguments(bundle);
                Utility.loadFragment(requireActivity(), fragment,
                        true,
                        null);

            }
        });
        // StaffList();

        BusinessList();
        customAdapter = new DropDownAdapter(getContext(), arraybusiness);
        mSpinnerbusiness.setAdapter(customAdapter);
        mSpinnerbusiness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedbusinessID = arraybusiness.get(i).getmStrId();
                GroupList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void GroupList() {
        String userid= Utility.getSharedPreferences(mContext, Constant.User_id);

        Call<GroupListRecponce> call = apiInterface.GroupList(userid,"00",selectedbusinessID);
        call.enqueue(new Callback<GroupListRecponce>() {
            @Override
            public void onResponse(@NonNull Call<GroupListRecponce> call, @NonNull
                    Response<GroupListRecponce> response) {
                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        if (response.body().getData().size()>0){
                            cardview.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
                            adaptor = new GroupAdapter(mContext, (ArrayList<GroupModal>) response.body().getData());
                            mListView.setAdapter(adaptor);
                            adaptor.notifyDataSetChanged();
                        }
                        else{
                            cardview.setVisibility(View.VISIBLE);
                            mListView.setVisibility(View.GONE);
                        }


                    }

                } else {
                    Log.e("Error===>", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GroupListRecponce> call, @NonNull Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });


    }
    public void editgroup(GroupModal maincat){
        AddAlertGroupFragment fragment = new AddAlertGroupFragment();
        Bundle bundle = new Bundle();
        bundle.putString("b_id",maincat.getFldUid().toString());
        bundle.putSerializable("data", (Serializable) maincat);
        fragment.setArguments(bundle);
        Utility.loadFragment(requireActivity(), fragment,
                true,
                null);
    }
    public void deletegroup(String s_id){
        String userid= Utility.getSharedPreferences(mContext,Constant.User_id);
        Call<AddGroupModel> call = apiInterface.groupdelete(userid,s_id);
        call.enqueue(new Callback<AddGroupModel>() {
            @Override
            public void onResponse(Call<AddGroupModel> call, Response<AddGroupModel> response) {
                Log.e("TAG", "response 33: " + String.valueOf(response.body()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        GroupList();
                    }
                } else {
                    Log.e("Error===>", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<AddGroupModel> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });

    }
    public void ChangeStatusBusiness(String S_id,String status){
        String userid= Utility.getSharedPreferences(mContext,Constant.User_id);
        Call<StatusResponce> call = apiInterface.AlertGroupstatus(userid,S_id,status);
        call.enqueue(new Callback<StatusResponce>() {
            @Override
            public void onResponse(Call<StatusResponce> call, Response<StatusResponce> response) {
                Log.e("TAG", "response 33: " + String.valueOf(response.body()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        GroupList();
                    }
                } else {
                    Log.e("Error===>", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<StatusResponce> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });

    }
    private void BusinessList() {
        arraybusiness.clear();
        DropDownModal ra = new DropDownModal();
        ra.setmStrId(
                "0");
        ra.setmStrValue(getString(R.string.select_business));
        arraybusiness.add(ra);
        String userid= Utility.getSharedPreferences(mContext, Constant.User_id);
        Call<BusinessListResponse> call = apiInterface.BusinessList(userid,"1");
        call.enqueue(new Callback<BusinessListResponse>() {
            @Override
            public void onResponse(@NonNull Call<BusinessListResponse> call, @NonNull
                    Response<BusinessListResponse> response) {
                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        if (response.body().getData().size()>0){
                            cardview.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
                            for (int j = 0; j < response.body().getData().size(); j++) {
                                DropDownModal ra = new DropDownModal();
                                ra.setmStrId(
                                        response.body().getData().get(j).getFldBid().toString());
                                ra.setmStrValue(response.body().getData().get(j).getFldBusinessName());
                                arraybusiness.add(ra);
                            }
                            customAdapter.notifyDataSetChanged();
                        }
                        else{
                            cardview.setVisibility(View.VISIBLE);
                            mListView.setVisibility(View.GONE);
                        }


                    }
                } else {
                    Log.e("Error===>", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<BusinessListResponse> call, @NonNull Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });


    }
}
