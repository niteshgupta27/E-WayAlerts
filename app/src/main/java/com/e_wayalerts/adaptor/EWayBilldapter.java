package com.e_wayalerts.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_business.BusinessListFragment;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.eway_bill.EBillListFragment;
import com.e_wayalerts.model.EwaybillModal;

import java.util.ArrayList;


public class EWayBilldapter extends RecyclerView.Adapter<EWayBilldapter.ViewHolder> {

    private final ArrayList<EwaybillModal> arMediIn;

    private final Context context;
    EBillListFragment ListFragment;
    public EWayBilldapter(Context context_, ArrayList<EwaybillModal> arTestReport_) {
        arMediIn = arTestReport_;
        context = context_;
        ListFragment = EBillListFragment.instance;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eway_bill_item_layout, parent, false);

        EWayBilldapter.ViewHolder viewHolder =
                new EWayBilldapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final EwaybillModal maincat = arMediIn.get(position);
/*
        viewHolder.bname.setText(maincat.getFldBusinessName());
        viewHolder.bcity.setText(maincat.getFldCity());
        viewHolder.bdate.setText(maincat.getFldEdate());

        viewHolder.edit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment.editBusiness(maincat);
            }
        });
        viewHolder.delete_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment.deleteBusiness(maincat.getFldBid().toString());
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return arMediIn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bname, bcity, bdate;
        Switch bActive;
        LinearLayout renewel,delete_but,edit_but;
        public ViewHolder(View itemView) {
            super(itemView);
            bname = itemView.findViewById(R.id.bname);
            bcity = itemView.findViewById(R.id.bcity);
            bdate = itemView.findViewById(R.id.bdate);
            bActive = itemView.findViewById(R.id.bactive);
            renewel= itemView.findViewById(R.id.renewel);
            delete_but = itemView.findViewById(R.id.delete_but);
            edit_but = itemView.findViewById(R.id.edit_but);
        }
    }
}


