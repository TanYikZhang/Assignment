package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.assignment.Adapter.PCHistoryAdapter;
import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Global;
import com.example.assignment.Model.PC;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {
    private ListView ListViewData;
    private ArrayList<PC> OrderList= new ArrayList<>();
    private PCHistoryAdapter adapter;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        findViews();
        setListener();
        setUpDatabase();
        setUpAdapter();
    }

    private void findViews(){
        ListViewData = findViewById(R.id.lvcusorderhistory);
    }
    private void setListener(){

    }
    private void setUpDatabase(){
        dbHelper = new DBHelper(this);
    }
    private void setUpAdapter(){
        Global global = (Global) getApplicationContext();
        int ID = global.getId();
        OrderList = dbHelper.getOrderHistory(ID);
        adapter = new PCHistoryAdapter(OrderList,this);
        ListViewData.setAdapter(adapter);
    }
}
