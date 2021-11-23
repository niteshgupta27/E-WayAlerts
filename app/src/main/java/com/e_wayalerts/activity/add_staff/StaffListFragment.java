package com.e_wayalerts.activity.add_staff;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.activity.checkout.CheckoutFragment;

import androidx.fragment.app.Fragment;

public class StaffListFragment extends Fragment {
	
	LinearLayout addStaffBtn;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_staff_list, container, false);
		
		init(view);
		listner();
		return view;
	}
    
    private void listner() {
        addStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utility.loadFragment(requireActivity(), new AddStaffFragment(), true,
                        null);
            }
        });
    }
    
    private void init(View view) {
        addStaffBtn = view.findViewById(R.id.addStaffBtn);
        
    }
    
}