package com.e_wayalerts.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;
import com.e_wayalerts.model.GroupModal;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private final ArrayList<GroupModal> arMediIn;

    private final Context context;

    public GroupAdapter(Context context_, ArrayList<GroupModal> arTestReport_) {
        arMediIn = arTestReport_;
        context = context_;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.group_list_layout_item, parent, false);

        ViewHolder viewHolder =
                new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final GroupModal maincat = arMediIn.get(position);

        viewHolder.Gname.setText(maincat.getFldGroupName());



    }

    @Override
    public int getItemCount() {
        return arMediIn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Gname, memberno, mobile;
        public ViewHolder(View itemView) {
            super(itemView);
            Gname = itemView.findViewById(R.id.groupname);
            memberno = itemView.findViewById(R.id.memberno);
            //mobile = itemView.findViewById(R.id.mobileno);
        }
    }
}
