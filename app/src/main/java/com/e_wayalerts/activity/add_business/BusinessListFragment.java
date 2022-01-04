package com.e_wayalerts.activity.add_business;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.MainActivity;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.loginmodule.LoginActivity;
import com.e_wayalerts.activity.loginmodule.Model.LoginResponse;
import com.e_wayalerts.adaptor.BusinessAdaptor;
import com.e_wayalerts.fragment.SettingFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BusinessListFragment extends Fragment {
    Context mContext;
    ApiInterface apiInterface;
    RecyclerView mListView;
    CardView cardview;
    BusinessAdaptor adaptor;
    FloatingActionButton mImgAddNew;
    LinearLayout Addbutton;
    public static BusinessListFragment instance;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business_list, container, false);

        mListView = (RecyclerView)view.findViewById(R.id.rv_storage_yards);
        cardview = view.findViewById(R.id.cardview);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mContext = getContext();
        instance = BusinessListFragment.this;
        mImgAddNew = view.findViewById(R.id.mImgAddNew);
        Addbutton = view.findViewById(R.id.ll_continue);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(linearLayoutManager);
        mImgAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddBusinesskFragment fragment = new AddBusinesskFragment();
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
                AddBusinesskFragment fragment = new AddBusinesskFragment();
                Bundle bundle = new Bundle();
                bundle.putString("b_id","0");
                fragment.setArguments(bundle);
                Utility.loadFragment(requireActivity(), fragment,
                        true,
                        null);
                
            }
        });
        BusinessList();

        // Inflate the layout for this fragment
        return view;

    }
    public void ChangeStatusBusiness(String b_id,String status){
        String userid= Utility.getSharedPreferences(mContext,Constant.User_id);
        Call<BusinessListResponse> call = apiInterface.Businessstatus(userid,b_id,status);
        call.enqueue(new Callback<BusinessListResponse>() {
            @Override
            public void onResponse(Call<BusinessListResponse> call, Response<BusinessListResponse> response) {
                Log.e("TAG", "response 33: " + String.valueOf(response.body()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        BusinessList();
                    }
                } else {
                    Log.e("Error===>", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BusinessListResponse> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });

    }
    public void editBusiness(BusinessListResponse.Datum maincat){
        AddBusinesskFragment fragment = new AddBusinesskFragment();
        Bundle bundle = new Bundle();
        bundle.putString("b_id",maincat.getFldBid().toString());
        bundle.putSerializable("data", (Serializable) maincat);
        fragment.setArguments(bundle);
        Utility.loadFragment(requireActivity(), fragment,
                true,
                null);
    }
    public void deleteBusiness(String b_id){

        Call<BusinessListResponse> call = apiInterface.Businessdelete(b_id);
        call.enqueue(new Callback<BusinessListResponse>() {
            @Override
            public void onResponse(Call<BusinessListResponse> call, Response<BusinessListResponse> response) {
                Log.e("TAG", "response 33: " + String.valueOf(response.body()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                       BusinessList();
                    }
                } else {
                    Log.e("Error===>", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<BusinessListResponse> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });

    }
    private void BusinessList() {
        String userid= Utility.getSharedPreferences(mContext,Constant.User_id);
        Call<BusinessListResponse> call = apiInterface.BusinessList(userid,"1");
        call.enqueue(new Callback<BusinessListResponse>() {
            @Override
            public void onResponse(Call<BusinessListResponse> call, Response<BusinessListResponse> response) {
                Log.e("TAG", "response 33: " + String.valueOf(response.body()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        if (response.body().getData().size()>0){
                            cardview.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
                            adaptor = new BusinessAdaptor(mContext, (ArrayList<BusinessListResponse.Datum>) response.body().getData());
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
            public void onFailure(Call<BusinessListResponse> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });


    }
}