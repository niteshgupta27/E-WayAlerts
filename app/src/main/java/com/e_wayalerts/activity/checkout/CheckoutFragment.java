package com.e_wayalerts.activity.checkout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e_wayalerts.R;


public class CheckoutFragment extends Fragment implements View.OnClickListener {
	
	TextView serviceStartDate,serviceEndDate,subscriptionAmount,
			affiliateAmount,subTotal,gstAmount,totalAmount;
	EditText affiliateCode;
	RelativeLayout makePaymentBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view =  inflater.inflate(R.layout.fragment_checkout, container, false);
		
		inIt(view);
		
		return view;
	}
	
	private void inIt(View view) {
		serviceStartDate = view.findViewById(R.id.serviceStartDate);
		serviceEndDate = view.findViewById(R.id.serviceEndDate);
		subscriptionAmount = view.findViewById(R.id.subscriptionAmount);
		affiliateAmount = view.findViewById(R.id.affiliateAmount);
		subTotal = view.findViewById(R.id.subTotal);
		gstAmount = view.findViewById(R.id.gstAmount);
		totalAmount = view.findViewById(R.id.totalAmount);
		affiliateCode = view.findViewById(R.id.affiliateCode);
		makePaymentBtn = view.findViewById(R.id.makePaymentBtn);
		
		makePaymentBtn.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.makePaymentBtn:
				
				break;
		}
	}
	
}