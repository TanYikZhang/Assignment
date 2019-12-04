package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Customer;
import com.example.assignment.Model.Global;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    Button Build,Logout,Admin,History;
    private ArrayList<Customer> customerlist =new ArrayList<Customer>();
    private DBHelper DBHelper;
    String fullname;
    TextView tvfullname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        findViews();
        setListeners();
        setUpDatabase();
        findName();
    }

    private void findViews(){
        Build = findViewById(R.id.btn_build);
        Logout = findViewById(R.id.btn_logout);
        Admin = findViewById(R.id.btn_admin);
        History = findViewById(R.id.btn_history);
        tvfullname = findViewById(R.id.fullname);
    }

    private void setListeners(){
        Build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, ChoosePage.class);
                startActivity(i);

            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, LoginPage.class);
                startActivity(i);
                finish();
            }
        });
    }
    private  void findName(){
        Global global = (Global) getApplicationContext();
        int id = global.getId();
        for (int i=0; i<customerlist.size(); i++){
                if(id==customerlist.get(i).getId()){
                    tvfullname.setText(customerlist.get(i).getFullname());
                    if(customerlist.get(i).getAccessControl()!=1){
                        Admin.setVisibility(View.GONE);
                    }
                }
            }
        }


    private void setUpDatabase(){
        DBHelper = new DBHelper(this);
        customerlist = DBHelper.getAllCustomer();
    }
}
