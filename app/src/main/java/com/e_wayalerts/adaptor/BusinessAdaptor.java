package com.e_wayalerts.adaptor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.e_wayalerts.R;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BusinessAdaptor extends RecyclerView.Adapter<BusinessAdaptor.ViewHolder> {

        private final ArrayList<BusinessListResponse.Datum> arMediIn;
        private final Context context;

    public BusinessAdaptor(Context context_, ArrayList<BusinessListResponse.Datum> arTestReport_) {
            arMediIn = arTestReport_;
            context = context_;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            View contactView = inflater.inflate(R.layout.businesslayout_item, parent, false);
            return new ViewHolder(contactView);
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
            // Get the data model based on position

            final BusinessListResponse.Datum maincat = arMediIn.get(position);

            viewHolder.bname.setText(maincat.getFldBusinessName());

           

        }

        @Override
        public int getItemCount() {
            return arMediIn.size();
        }




        public class ViewHolder extends RecyclerView.ViewHolder {
            public ImageView ivSelect;
            TextView bname,bcity,bdate;
            LinearLayout rlMain, medicine_linear;

            public ViewHolder(View itemView) {
                super(itemView);
                bname = itemView.findViewById(R.id.bname);
                bcity = itemView.findViewById(R.id.bcity);
                bdate = itemView.findViewById(R.id.bdate);
                //medicine_linear = itemView.findViewById(R.id.medicine_linear);
            }
        }
    }


