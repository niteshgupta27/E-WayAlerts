package com.e_wayalerts.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_business.BusinessListFragment;
import com.e_wayalerts.activity.add_staff.StaffModal.StaffModal;
import com.e_wayalerts.activity.alert_group.AlertGroupListFragment;
import com.e_wayalerts.model.GroupModal;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {

    private final ArrayList<GroupModal> arMediIn;

    private final Context context;
    AlertGroupListFragment ListFragment;
    public GroupAdapter(Context context_, ArrayList<GroupModal> arTestReport_) {
        arMediIn = arTestReport_;
        context = context_;
        ListFragment = AlertGroupListFragment.instance;
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

        viewHolder.edit_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment.editgroup(maincat);
            }
        });
        viewHolder.delete_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListFragment.deletegroup(maincat.getFldGrpId().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return arMediIn.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Gname, memberno, mobile;
        LinearLayout renewel,delete_but,edit_but;
        public ViewHolder(View itemView) {
            super(itemView);
            Gname = itemView.findViewById(R.id.groupname);
            memberno = itemView.findViewById(R.id.memberno);
            //mobile = itemView.findViewById(R.id.mobileno);
            delete_but = itemView.findViewById(R.id.fleetDelete);
            edit_but = itemView.findViewById(R.id.fleetEdit);
        }
    }
}
