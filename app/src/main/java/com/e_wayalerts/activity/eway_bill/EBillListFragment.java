package com.e_wayalerts.activity.eway_bill;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.add_business.AddBusinesskFragment;
import com.e_wayalerts.activity.add_business.BusinessListFragment;
import com.e_wayalerts.activity.alert_group.AddAlertGroupFragment;
import com.e_wayalerts.adaptor.BusinessAdaptor;
import com.e_wayalerts.adaptor.EWayBilldapter;
import com.e_wayalerts.adaptor.GroupAdapter;
import com.e_wayalerts.model.AddEwayBillModel;
import com.e_wayalerts.model.AddGroupModel;
import com.e_wayalerts.model.EwayBillRecponce;
import com.e_wayalerts.model.EwaybillModal;
import com.e_wayalerts.model.GroupListRecponce;
import com.e_wayalerts.model.GroupModal;
import com.e_wayalerts.model.StatusResponce;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EBillListFragment extends Fragment {

    Context mContext;
    ApiInterface apiInterface;
    RecyclerView mListView;
    CardView cardview;
    EWayBilldapter adaptor;
    FloatingActionButton mImgAddNew;
    LinearLayout Addbutton;
    public static EBillListFragment instance;

    public EBillListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_e_bill_list, container, false);
        View view = inflater.inflate(R.layout.fragment_e_bill_list, container, false);

        mListView = (RecyclerView)view.findViewById(R.id.rv_storage_yards);
        cardview = view.findViewById(R.id.cardview);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        mContext = getContext();
        instance = EBillListFragment.this;
        mImgAddNew = view.findViewById(R.id.mImgAddNew);
        Addbutton = view.findViewById(R.id.ll_continue);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(linearLayoutManager);

        mImgAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEwayBillFragment fragment = new AddEwayBillFragment();
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
                AddEwayBillFragment fragment = new AddEwayBillFragment();
                Bundle bundle = new Bundle();
                bundle.putString("b_id","0");
                fragment.setArguments(bundle);
                Utility.loadFragment(requireActivity(), fragment,
                        true,
                        null);

            }
        });
        BillList();
        return view;
    }
    private void BillList() {
        String userid= Utility.getSharedPreferences(mContext, Constant.User_id);

        Call<EwayBillRecponce> call = apiInterface.ListEwayBill(userid);
        call.enqueue(new Callback<EwayBillRecponce>() {
            @Override
            public void onResponse(@NonNull Call<EwayBillRecponce> call, @NonNull
                    Response<EwayBillRecponce> response) {
                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        if (response.body().getData().size()>0){
                            cardview.setVisibility(View.GONE);
                            mListView.setVisibility(View.VISIBLE);
                            adaptor = new EWayBilldapter(mContext, (ArrayList<EwaybillModal>) response.body().getData());
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
            public void onFailure(@NonNull Call<EwayBillRecponce> call, @NonNull Throwable t) {
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
        Call<AddEwayBillModel> call = apiInterface.ewaydelete(userid,s_id);
        call.enqueue(new Callback<AddEwayBillModel>() {
            @Override
            public void onResponse(Call<AddEwayBillModel> call, Response<AddEwayBillModel> response) {
                Log.e("TAG", "response 33: " + String.valueOf(response.body()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        BillList();
                    }
                } else {
                    Log.e("Error===>", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<AddEwayBillModel> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });

    }
    public void ChangeStatusBusiness(String S_id,String status){
        String userid= Utility.getSharedPreferences(mContext,Constant.User_id);
        Call<StatusResponce> call = apiInterface.Ewaystatus(userid,S_id,status);
        call.enqueue(new Callback<StatusResponce>() {
            @Override
            public void onResponse(Call<StatusResponce> call, Response<StatusResponce> response) {
                Log.e("TAG", "response 33: " + String.valueOf(response.body()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        BillList();
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
}