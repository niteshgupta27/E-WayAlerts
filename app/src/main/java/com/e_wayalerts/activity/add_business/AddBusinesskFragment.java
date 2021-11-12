package com.e_wayalerts.activity.add_business;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.e_wayalerts.R;


public class AddBusinesskFragment extends Activity {
    EditText extbname,extgst,extaddress1,extaddres2,extcity,extpincode,extownerfirstname,extownerlasstname,extmobile,extemail;
    Spinner spstate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_businessk);

        extbname = findViewById(R.id.bname);
        extgst = findViewById(R.id.gstnumber);
        extaddress1 = findViewById(R.id.address1);
        extaddres2 = findViewById(R.id.address2);
        extcity = findViewById(R.id.bcity);
        extpincode = findViewById(R.id.pincode);
        spstate = findViewById(R.id.mSpinnerstate);
        extownerfirstname = findViewById(R.id.firstname);
        extownerlasstname = findViewById(R.id.lastname);
        extmobile = findViewById(R.id.mobile);
        extemail = findViewById(R.id.email);

        // Inflate the layout for this fragment

    }

}