package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.assignment.Adapter.CustomerAdapter;
import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Customer;

import java.util.ArrayList;

public class CustomerList extends AppCompatActivity {
    private ListView ListViewData;
    private ArrayList<Customer> CustomerList= new ArrayList<>();
    private CustomerAdapter adapter;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        findViews();
        setListener();
        setUpDatabase();
        setUpAdapter();
    }


    private void findViews(){
        ListViewData = findViewById(R.id.lvcustomer);
    }
    private void setListener(){

    }
    private void setUpDatabase(){
        dbHelper = new DBHelper(this);
    }
    private void setUpAdapter(){
        CustomerList = dbHelper.getAllCustomer();
        adapter = new CustomerAdapter(CustomerList,this);
        ListViewData.setAdapter(adapter);
    }
}
