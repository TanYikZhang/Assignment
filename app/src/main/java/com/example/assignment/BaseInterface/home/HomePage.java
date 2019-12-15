package com.example.assignment.BaseInterface.home;

import android.content.Intent;
import android.os.Bundle;

import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Customer;
import com.example.assignment.Model.Global;
import com.example.assignment.R;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    private DrawerLayout drawer;
    private TextView tvfullname, tvemail;
    private AppBarConfiguration mAppBarConfiguration;
    private DBHelper DBHelper;
    private ArrayList<Customer> customerlist;
    private View headerview;
    private NavController navController;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final DrawerLayout drawer = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);
        headerview = navigationView.getHeaderView(0);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_changepassword, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                Intent i;
                switch (id) {
                    case R.id.nav_logout:
                        i = new Intent(HomePage.this, LoginPage.class);
                        startActivity(i);
                        finish();
                        break;
                    case R.id.nav_changepassword:
                        i = new Intent(HomePage.this, EditPassword.class);
                        startActivity(i);
                        drawer.closeDrawers();
                        break;
                }
                return true;
            }
        });

        findCusDetails();
    }

    @Override
    protected void onPause() {
        super.onPause();
        String x = navigationView.getCheckedItem().toString();
        if (x.equals("Logout")) {
            finish();
        }
    }


    private void findCusDetails() {
        tvfullname = headerview.findViewById(R.id.fullname);
        tvemail = headerview.findViewById(R.id.email);
        setUpDatabase();
        findName();


    }

    private void findName() {
        Global global = (Global) getApplicationContext();
        int id = global.getId();
        for (int i = 0; i < customerlist.size(); i++) {
            if (id == customerlist.get(i).getId()) {
                tvfullname.setText(customerlist.get(i).getFullname());
                tvemail.setText(customerlist.get(i).getEmail());
            }
        }
    }


    private void setUpDatabase() {
        DBHelper = new DBHelper(this);
        customerlist = DBHelper.getAllPerson();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
