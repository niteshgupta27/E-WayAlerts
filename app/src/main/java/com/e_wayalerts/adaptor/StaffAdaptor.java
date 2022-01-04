package com.e_wayalerts.adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.add_staff.StaffListFragment;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;

import java.util.ArrayList;

public class StaffAdaptor extends RecyclerView.Adapter<StaffAdaptor.ViewHolder> {

    private final ArrayList<StaffModal> arMediIn;

    private final Context context;
StaffListFragment ListFragment;
    public StaffAdaptor(Context context_, ArrayList<StaffModal> arTestReport_) {
        arMediIn = arTestReport_;
        context = context_;
        ListFragment = StaffListFragment.instance;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.staff_ilem_layout, parent, false);

        StaffAdaptor.ViewHolder viewHolder =
                new StaffAdaptor.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final StaffModal maincat = arMediIn.get(position);

        viewHolder.sname.setText(maincat.getFldFname().trim() + " " +maincat.getFldLname().trim());
        viewHolder.bname.setText(maincat.getFldFname());
        viewHolder.mobile.setText(maincat.getFldMobile());
//        if(maincat.getFldisactive() ==1){
//            viewHolder.bActive.setChecked(true);
//            viewHolder.renewel.setVisibility(View.GONE);
//        }
//        else {
//            viewHolder.bActive.setChecked(false);
//            viewHolder.renewel.setVisibility(View.VISIBLE);
//
//        }
        viewHolder.edit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment.editStaff(maincat);
            }
        });
        viewHolder.delete_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment.deletestaff(maincat.getFldUid().toString());
            }
        });
        viewHolder.bActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    ListFragment.ChangeStatusBusiness(maincat.getFldUid().toString(),"1");
                } else {
                    // The toggle is disabled
                    ListFragment.ChangeStatusBusiness(maincat.getFldUid().toString(),"0");
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return arMediIn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bname, sname, mobile;
        LinearLayout renewel,delete_but,edit_but;
        Switch bActive;
        public ViewHolder(View itemView) {
            super(itemView);
            bname = itemView.findViewById(R.id.Bname);
            sname = itemView.findViewById(R.id.Sname);
            mobile = itemView.findViewById(R.id.mobileno);
            delete_but = itemView.findViewById(R.id.delete_but);
            edit_but = itemView.findViewById(R.id.edit_but);
            bActive = itemView.findViewById(R.id.bactive);
        }
    }
}