package com.e_wayalerts.activity;

import android.os.Bundle;

import com.e_wayalerts.activity.widget.MenuItem;
import com.e_wayalerts.activity.widget.SNavigationDrawer;
import com.e_wayalerts.databinding.ActivityMainBinding;
import com.e_wayalerts.fragment.AboutUsFragment;
import com.e_wayalerts.fragment.BusinessListFragment;
import com.e_wayalerts.fragment.ContactUsFragment;
import com.e_wayalerts.fragment.DashboardFragment;
import com.e_wayalerts.fragment.FAQFragment;
import com.e_wayalerts.fragment.FeedbackFragment;
import com.e_wayalerts.fragment.PasswordFragment;
import com.e_wayalerts.fragment.SettingFragment;
import com.e_wayalerts.fragment.StaffListFragment;
import com.e_wayalerts.fragment.TermsConditionFragment;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;




import com.e_wayalerts.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SNavigationDrawer sNavigationDrawer;
    int color1=0;
    Class fragmentClass;
    public static Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().hide();
        }

        sNavigationDrawer = findViewById(R.id.navigationDrawer);
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem(getResources().getString(R.string.menu_dashboard),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_businesses),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_staff),R.drawable.bg_building));
        menuItems.add(new MenuItem(getString(R.string.menu_subscription),R.drawable.bg_building));
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
                        fragmentClass = DashboardFragment.class;
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
                        fragmentClass = BusinessListFragment.class;
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
