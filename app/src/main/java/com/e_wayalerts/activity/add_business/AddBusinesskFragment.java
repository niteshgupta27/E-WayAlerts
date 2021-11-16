package com.e_wayalerts.activity.add_business;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.ApiClient;
import com.e_wayalerts.WebService.ApiInterface;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.add_business.businessModal.BusinessListResponse;
import com.e_wayalerts.activity.dropdown.DropDownAdapter;
import com.e_wayalerts.activity.dropdown.DropDownModal;
import com.e_wayalerts.activity.dropdown.SateResponce;
import com.e_wayalerts.activity.loginmodule.Model.VarifyOTPModel;
import com.e_wayalerts.adaptor.BusinessAdaptor;

import org.json.JSONObject;

import java.util.ArrayList;


public class AddBusinesskFragment extends Fragment implements View.OnClickListener {
 
    Context mContext;
    EditText extbname,extgst,extaddress1,extaddres2,extcity,
            extpincode,extownerfirstname,extownerlasstname,extmobile,extemail,extstate;
    
    RelativeLayout Submitbtn;
    ApiInterface apiInterface;
    Spinner mSpinnerState;
    public  ArrayList<DropDownModal> arrayState = new ArrayList<DropDownModal>();
    String selectedStateID = "0";
    public   ArrayAdapter AdaptorState ;
    DropDownAdapter customAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_businessk, container, false);
        
        
        // Inflate the layout for this fragment
        mContext = getActivity();
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    
        Init(view);
        
        return view;
    }
    
   
    
    public void Init(View view){
        extbname = view.findViewById(R.id.bname);
        extgst = view.findViewById(R.id.gstnumber);
        extaddress1 = view.findViewById(R.id.address1);
        extaddres2 = view.findViewById(R.id.address2);
        extcity = view.findViewById(R.id.bcity);
        extpincode = view.findViewById(R.id.pincode);
        mSpinnerState = view.findViewById(R.id.mSpinnerState);
        extownerfirstname = view.findViewById(R.id.firstname);
        extownerlasstname = view.findViewById(R.id.lastname);
        extmobile = view.findViewById(R.id.mobile);
        extemail = view.findViewById(R.id.email);
        Submitbtn = view.findViewById(R.id.mLayoutSubmit);
        StateList();
        Submitbtn.setOnClickListener(this);
        customAdapter=new DropDownAdapter(getContext(),arrayState);
        mSpinnerState.setAdapter(customAdapter);
        mSpinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectedStateID = arrayState.get(i).getmStrId();



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            
            case R.id.mLayoutSubmit:
                if (extbname.getText().toString().isEmpty()){
                    Utility.ShowToast(mContext, getString(R.string.enter_business_name));
                }else  if (extgst.getText().toString().isEmpty()){
                    Utility.ShowToast(mContext, getString(R.string.enter_gst_number));
                }else  if (extaddress1.getText().toString().isEmpty()){
                    Utility.ShowToast(mContext, getString(R.string.please_enter_address));
                }else  if (extcity.getText().toString().isEmpty()){
                    Utility.ShowToast(mContext, getString(R.string.please_enter_city));
                }else  if (extpincode.getText().toString().isEmpty()){
                    Utility.ShowToast(mContext, getString(R.string.please_enter_pincode));
                }else  if (selectedStateID.equals("0")){
                    Utility.ShowToast(mContext, getString(R.string.please_enter_state));
                }else  if (extownerfirstname.getText().toString().isEmpty()){
                    Utility.ShowToast(mContext, getString(R.string.please_enter_first_name));
                }else  if (extownerlasstname.getText().toString().isEmpty()){
                    Utility.ShowToast(mContext, getString(R.string.please_enter_last_name));
                }else  if (extmobile.getText().toString().isEmpty()){
                    Utility.ShowToast(mContext, getString(R.string.please_enter_mobile_number));
                }else  if (!Utility.isValidMobile(extmobile.getText().toString().toString())){
                    Utility.ShowToast(mContext, getString(R.string.enter_valid_mobile_number));
                }else {
                    AddBusiness(extbname.getText().toString().trim(),
                            extgst.getText().toString().trim(),
                            extaddress1.getText().toString().trim(),
                            extaddres2.getText().toString().trim(),
                            extcity.getText().toString().trim(),
                            extpincode.getText().toString().trim(),
                            selectedStateID,
                            extownerfirstname.getText().toString().trim(),
                            extownerlasstname.getText().toString().trim(),
                            extmobile.getText().toString().trim(),
                            extemail.getText().toString().trim());
                }
                
                break;
        }
    }
    
    private void AddBusiness(String extbname, String extgst, String extaddress1, String extaddres2, String extcity,
                             String extpincode, String extstate, String extownerfirstname,
                             String extownerlasstname, String extmobile, String extemail) {
        String userid= Utility.getSharedPreferences(mContext, Constant.User_id);
        Call<VarifyOTPModel> call =
                apiInterface.AddBusiness(userid,extbname,extgst,
                extaddress1,extaddres2,extcity,extpincode,extstate,extownerfirstname,
                extownerlasstname,extmobile,extemail);
        call.enqueue(new Callback<VarifyOTPModel>() {
            @Override
            public void onResponse(Call<VarifyOTPModel> call, Response<VarifyOTPModel> response) {
                Log.e("TAG", "response 33: " + response.body().toString());
            
                if (response.isSuccessful()) {
                
                    if (String.valueOf(response.body().getStatus()).equals("200")) {
                        getActivity().onBackPressed();
                    }
                } else {
                    Log.e("Error===>", response.errorBody().toString());
                }
            }
        
            @Override
            public void onFailure(Call<VarifyOTPModel> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE
            
            }
        });
    }
    private void StateList() {
        String userid= Utility.getSharedPreferences(mContext,Constant.User_id);
        Call<SateResponce> call = apiInterface.StateList(userid);
        call.enqueue(new Callback<SateResponce>() {
            @Override
            public void onResponse(Call<SateResponce> call, Response<SateResponce> response) {
                Log.e("TAG", "response satae 33: " + String.valueOf(response.body().getStatus()));

                if (response.isSuccessful()) {

                    if (String.valueOf(response.body().getStatus()).equals("200")) {

                        for (int j = 0; j<response.body().getData().size();j++) {
if(j== 0){
    selectedStateID = response.body().getData().get(j).getFldStateId().toString();
}
                            DropDownModal ra = new DropDownModal();
                            ra.setmStrId(response.body().getData().get(j).getFldStateId().toString());
                            ra.setmStrValue(response.body().getData().get(j).getFldStateName());
                            arrayState.add(ra);
                        }
customAdapter.notifyDataSetChanged();
                    }
                } else {
                    Log.e("Error===>", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<SateResponce> call, Throwable t) {
                Toast.makeText(mContext, t.toString(),
                        Toast.LENGTH_SHORT).show(); // ALL NETWORK ERROR HERE

            }
        });


    }
}