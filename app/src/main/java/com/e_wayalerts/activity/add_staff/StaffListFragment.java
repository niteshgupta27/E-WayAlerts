package com.e_wayalerts.activity.add_staff;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.add_business.AddBusinesskFragment;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffRecponce;
import com.e_wayalerts.activity.dropdown.DropDownAdapter;
import com.e_wayalerts.activity.dropdown.DropDownModal;
import com.e_wayalerts.adaptor.BusinessAdaptor;
import com.e_wayalerts.adaptor.StaffAdaptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StaffListFragment extends Fragment {
    Context mContext;
    ApiInterface apiInterface;
    RecyclerView mListView;
    CardView cardview;
    StaffAdaptor adaptor;
    FloatingActionButton mImgAddNew;
    LinearLayout Addbutton;
    String selectedStateID = "0";
    DropDownAdapter customAdapter;
    Spinner mSpinnerbusiness;
    public ArrayList<DropDownModal> arraybusiness = new ArrayList<DropDownModal>();
    public StaffListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_staff_list, container, false);

        mListView = (RecyclerView)view.findViewById(R.id.rv_storage_yards);
        cardview = view.findViewById(R.id.cardview);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mContext = getContext();
        mImgAddNew = view.findViewById(R.id.mImgAddNew);
        Addbutton = view.findViewById(R.id.ll_continue);
        mSpinnerbusiness = view.findViewById(R.id.mSpinnerState);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(linearLayoutManager);
        mImgAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.loadFragment(requireActivity(), new AddStaffFragment(),
                        true,
                        null);
            }
        });
        Addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* Utility.loadFragment(getActivity(),
                        AddBusinesskFragment.newInstance(groupChannel.getUrl(), true), true,
                        ConversationFragment.class.getSimpleName());*/
                Utility.loadFragment(requireActivity(), new AddStaffFragment(),
                        true,
                        null);

            }
        });
       // StaffList();
        DropDownModal ra = new DropDownModal();
        ra.setmStrId(
                "0");
        ra.setmStrValue("Select Business");
        arraybusiness.add(ra);
        BusinessList();
        customAdapter = new DropDownAdapter(getContext(), arraybusiness);
        mSpinnerbusiness.setAdapter(customAdapter);
        mSpinnerbusiness.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedStateID = arraybusiness.get(i).getmStrId();
                StaffList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        // Inflate the layout for this fragment
        return view;
    }
    private void StaffList() {
        String userid= Utility.getSharedPreferences(mContext, Constant.User_id);
        Call<StaffRecponce> call = apiInterface.StaffList(userid,"","");
        call.enqueue(new Callback<StaffRecponce>() {
            @Override
            public void onResponse(Call<StaffRecponce> call, Response<StaffRecponce> response) {
                Log.e("TAG", "response 33: " + String.valueOf(response.body().getStatus()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        if (response.body().getData().size()>0){
                            cardview.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
                            adaptor = new StaffAdaptor(mContext, (ArrayList<StaffModal>) response.body().getData());
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
            public void onFailure(Call<StaffRecponce> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });


    }
    private void BusinessList() {
        String userid= Utility.getSharedPreferences(mContext,Constant.User_id);
        Call<BusinessListResponse> call = apiInterface.BusinessList(userid,"");
        call.enqueue(new Callback<BusinessListResponse>() {
            @Override
            public void onResponse(Call<BusinessListResponse> call, Response<BusinessListResponse> response) {
                Log.e("TAG", "response 33: " + String.valueOf(response.body().getStatus()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        if (response.body().getData().size()>0){
                            cardview.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
//                            adaptor = new BusinessAdaptor(mContext, (ArrayList<BusinessListResponse.Datum>) response.body().getData());
//                            mListView.setAdapter(adaptor);
//                            adaptor.notifyDataSetChanged();
                            for (int j = 0; j < response.body().getData().size(); j++) {
//                                if (j == 0) {
//                                    selectedStateID =
//                                            response.body().getData().get(j).getFldBid().toString();
//                                }
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
            public void onFailure(Call<BusinessListResponse> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });


    }
}