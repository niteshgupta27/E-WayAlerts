package com.e_wayalerts.activity.eway_bill;

import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.activity.add_business.AddBusinesskFragment;
import com.e_wayalerts.activity.add_business.BusinessListFragment;
import com.e_wayalerts.adaptor.BusinessAdaptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EBillListFragment extends Fragment {

    Context mContext;
    ApiInterface apiInterface;
    RecyclerView mListView;
    CardView cardview;
    BusinessAdaptor adaptor;
    FloatingActionButton mImgAddNew;
    LinearLayout Addbutton;
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
        //instance = BusinessListFragment.this;
        mImgAddNew = view.findViewById(R.id.mImgAddNew);
        Addbutton = view.findViewById(R.id.ll_continue);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mListView.setLayoutManager(linearLayoutManager);
        mImgAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.loadFragment(requireActivity(), new AddEwayBillFragment(),
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
                Utility.loadFragment(requireActivity(), new AddEwayBillFragment(),
                        true,
                        null);

            }
        });
        //BusinessList();

        // Inflate the layout for this fragment
        return view;
    }

}