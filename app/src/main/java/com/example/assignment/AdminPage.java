package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Customer;
import com.example.assignment.Model.Global;

import java.util.ArrayList;

public class AdminPage extends AppCompatActivity {
    Button CusList,Logout,CusOrder;
    private ArrayList<Customer> customerlist =new ArrayList<Customer>();
    private DBHelper DBHelper;
    String fullname;
    TextView tvfullname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        findViews();
        setListeners();
        setUpDatabase();
        findName();
    }

    private void findViews(){
        CusList = findViewById(R.id.btn_cuslist);
        Logout = findViewById(R.id.btn_logout);
        CusOrder = findViewById(R.id.btn_cusorder);
        tvfullname = findViewById(R.id.adminfullname);
    }

    private void setListeners(){


        CusList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPage.this, CustomerList.class);
                startActivity(i);

            }
        });

        CusOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPage.this, CustomerOrder.class);
                startActivity(i);

            }
        });

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminPage.this, LoginPage.class);
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
                    }
                }

        }


    private void setUpDatabase(){
        DBHelper = new DBHelper(this);
        customerlist = DBHelper.getAllPerson();
    }
}
