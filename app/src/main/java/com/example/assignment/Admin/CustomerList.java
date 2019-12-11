package com.example.assignment.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import com.example.assignment.Adapter.CustomerAdapter;
import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Customer;
import com.example.assignment.R;

import java.util.ArrayList;

public class CustomerList extends AppCompatActivity {
    private ListView ListViewData;
    private ArrayList<Customer> CustomerList= new ArrayList<>();
    private CustomerAdapter adapter;
    private DBHelper dbHelper;
    private EditText Search;
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
        Search = findViewById(R.id.edit_searchcus);
    }
    private void setListener(){
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = Search.getText().toString();
                CustomerList.clear();
                adapter.clear();
                if (text.isEmpty()){
                    setUpAdapter();
                }else if (!text.isEmpty()){
                    CustomerList = dbHelper.getCustomer(text);
                    adapter = new CustomerAdapter(CustomerList,CustomerList.this);
                    ListViewData.setAdapter(adapter);
                }

            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
    private void setUpDatabase(){
        dbHelper = new DBHelper(this);
    }
    private void setUpAdapter(){
        CustomerList = dbHelper.getAllCustomer();
        System.out.println(CustomerList.get(0).getFullname());
        adapter = new CustomerAdapter(CustomerList,this);
        ListViewData.setAdapter(adapter);
    }
}
