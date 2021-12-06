package com.e_wayalerts.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.e_wayalerts.R;
import com.e_wayalerts.Utility.Utility;
import com.e_wayalerts.WebService.Constant;
import com.e_wayalerts.activity.add_business.BusinessListFragment;
import com.e_wayalerts.activity.add_staff.StaffListFragment;
import com.e_wayalerts.activity.eway_bill.AddEwayBillFragment;
import com.e_wayalerts.activity.eway_bill.EBillListFragment;
import com.e_wayalerts.activity.faq_contact.AboutUsFragment;
import com.e_wayalerts.activity.faq_contact.ContactUsFragment;
import com.e_wayalerts.activity.faq_contact.FAQFragment;
import com.e_wayalerts.activity.faq_contact.FeedbackFragment;
import com.e_wayalerts.activity.faq_contact.TermsConditionFragment;
import com.e_wayalerts.activity.loginmodule.LoginActivity;
import com.e_wayalerts.fragment.DashboardFragment;
import com.e_wayalerts.fragment.PasswordFragment;
import com.e_wayalerts.fragment.SettingFragment;
import com.e_wayalerts.widget.MenuItem;
import com.e_wayalerts.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    int color1=0;
    Class fragmentClass;
    public static Fragment fragment;
    Context mContext;
    public static  String token = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }
        mContext =  this;
        token = Utility.getSharedPreferences(this, Constant.Usertoken);
        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(getResources().getString(R.string.menu_dashboard),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_businesses),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_staff),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_ewaybill),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_contact_us),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_feedback),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_about_us),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_faq),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_settings),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_password),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_terms_and_conditions),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_logout),R.drawable.bg_building));
        sNavigationDrawer.setMenuItemList(menuItems);
        fragmentClass =  DashboardFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
        }


        sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
            @Override
            public void onMenuItemClicked(int position) {
                System.out.println("Position "+position);

                switch (position){
                    case 0:{
                        color1 = R.color.grey_color;
                        fragmentClass = AddEwayBillFragment.class;
                        break;
                    }
                    case 1:{
                        color1 = R.color.grey_color;
                        fragmentClass = BusinessListFragment.class;
                        break;
                    }
                    case 2:{
                        color1 = R.color.green;
                        fragmentClass = StaffListFragment.class;
                        break;
                    }
                    case 3:{
                        color1 = R.color.blue;
                        fragmentClass = EBillListFragment.class;
                        break;
                    }
                    case 4:{
                        color1 = R.color.blue;
                        fragmentClass = ContactUsFragment.class;
                        break;
                    }
                    case 5:{
                        color1 = R.color.blue;
                        fragmentClass = FeedbackFragment.class;
                        break;
                    }
                    case 6:{
                        color1 = R.color.blue;
                        fragmentClass = AboutUsFragment.class;
                        break;
                    }
                    case 7:{
                        color1 = R.color.blue;
                        fragmentClass = FAQFragment.class;
                        break;
                    }
                    case 8:{
                        color1 = R.color.blue;
                        fragmentClass = SettingFragment.class;
                        break;
                    }
                    case 9:{
                        color1 = R.color.blue;
                        fragmentClass = PasswordFragment.class;
                        break;
                    }
                    case 10:{
                        color1 = R.color.blue;
                        fragmentClass = TermsConditionFragment.class;
                        break;
                    }
                    case 11:{
                        Utility.setSharedPreference(mContext, Constant.User_id, "");

                        Utility.setSharedPreference(mContext,
                                Constant.UserFirstName,"");
                        Utility.setSharedPreference(mContext,
                                Constant.UserLastName,"");
                        Utility.setSharedPreference(mContext,
                                Constant.UserMobile,"");
                        Utility.setSharedPreference(mContext,
                                Constant.Usertoken,"");
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                    }

                }
                sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                    @Override
                    public void onDrawerOpened() {

                    }

                    @Override
                    public void onDrawerOpening(){

                    }

                    @Override
                    public void onDrawerClosing(){
                        System.out.println("Drawer closed");

                        try {
                            fragment = (Fragment) fragmentClass.newInstance();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getSupportFragmentManager();
                            fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                        }
                    }

                    @Override
                    public void onDrawerClosed() {

                    }

                    @Override
                    public void onDrawerStateChanged(int newState) {
                        System.out.println("State "+newState);
                    }
                });
            }
        });

    }
}
