package com.e_wayalerts.adaptor;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_wayalerts.R;

import com.e_wayalerts.model.GroupModal;

import java.util.List;

public class GroupCheckAdaptor extends RecyclerView.Adapter<GroupCheckAdaptor.ViewHolder> {

    public final List<GroupModal> businessList;


    public GroupCheckAdaptor(Context context_, List<GroupModal> arTestReport_) {
        businessList = arTestReport_;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.businesslist_item, parent, false);

        GroupCheckAdaptor.ViewHolder viewHolder =
                new GroupCheckAdaptor.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final GroupModal datum = businessList.get(position);
        Log.e("fff",datum.getFldGroupName());
        viewHolder.bussnessNameCheck.setText(datum.getFldGroupName());
        if (datum.isChecked()) {
            viewHolder.bussnessNameCheck.setChecked(true);
        } else {
            viewHolder.bussnessNameCheck.setChecked(false);
        }

        viewHolder.bussnessNameCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (datum.isChecked()) {
                    datum.setChecked(false);
                } else {
                    datum.setChecked(true);
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return businessList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox bussnessNameCheck;
        public ViewHolder(View itemView) {
            super(itemView);
            bussnessNameCheck = itemView.findViewById(R.id.bussnessNameCheck);
        }
    }
}
