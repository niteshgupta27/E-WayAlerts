package com.e_wayalerts.activity.add_business;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.e_wayalerts.R;


public class AddBusinesskFragment extends Fragment {
    EditText extbname,extgst,extaddress1,extaddres2,extcity,extpincode,extownerfirstname,extownerlasstname,extmobile,extemail;
    Spinner spstate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_businessk, container, false);
        extbname = view.findViewById(R.id.bname);
        extgst =view.findViewById(R.id.gstnumber);
        extaddress1 = view.findViewById(R.id.address1);
        extaddres2 = view.findViewById(R.id.address2);
        extcity = view.findViewById(R.id.bcity);
        extpincode = view.findViewById(R.id.pincode);
        spstate = view.findViewById(R.id.mSpinnerstate);
        extownerfirstname = view.findViewById(R.id.firstname);
        extownerlasstname = view.findViewById(R.id.lastname);
        extmobile = view.findViewById(R.id.mobile);
        extemail = view.findViewById(R.id.email);

        // Inflate the layout for this fragment
        return view;
    }

}