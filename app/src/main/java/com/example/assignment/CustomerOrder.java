package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.assignment.Adapter.PCHistoryAdapter;
import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.PC;
import com.example.assignment.R;

import java.util.ArrayList;

public class CustomerOrder extends AppCompatActivity {
    private ListView ListViewData;
    private ArrayList<PC> OrderList= new ArrayList<>();
    private PCHistoryAdapter adapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order);
        findViews();
        setListener();
        setUpDatabase();
        setUpAdapter();
    }

    private void findViews(){
        ListViewData = findViewById(R.id.lvcusorder);
    }
    private void setListener(){

    }
    private void setUpDatabase(){
        dbHelper = new DBHelper(this);
    }
    private void setUpAdapter(){

        OrderList = dbHelper.getAllOrder();
        adapter = new PCHistoryAdapter(OrderList,this);
        ListViewData.setAdapter(adapter);
    }
}
