package com.e_wayalerts.adaptor;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;

import java.util.ArrayList;

public class StaffAdaptor extends RecyclerView.Adapter<StaffAdaptor.ViewHolder> {

    private final ArrayList<StaffModal> arMediIn;

    private final Context context;

    public StaffAdaptor(Context context_, ArrayList<StaffModal> arTestReport_) {
        arMediIn = arTestReport_;
        context = context_;
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

        viewHolder.sname.setText(maincat.getFldFname() + "" +maincat.getFldLname());
        viewHolder.bname.setText(maincat.getFldFname());
        viewHolder.mobile.setText(maincat.getFldMobile());



    }

    @Override
    public int getItemCount() {
        return arMediIn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bname, sname, mobile;
        public ViewHolder(View itemView) {
            super(itemView);
            bname = itemView.findViewById(R.id.Bname);
            sname = itemView.findViewById(R.id.Sname);
            mobile = itemView.findViewById(R.id.mobileno);
        }
    }
}