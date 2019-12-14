package com.example.assignment.PCBuild;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Adapter.PCHistoryAdapter;
import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Global;
import com.example.assignment.Model.PC;
import com.example.assignment.R;

import java.util.ArrayList;

public class OrderHistory extends AppCompatActivity {
    private ListView ListViewData;
    private EditText Search;
    private ArrayList<PC> OrderList = new ArrayList<>();
    private PCHistoryAdapter adapter;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);
        findViews();
        setUpDatabase();
        setUpAdapter();
        setListener();
    }

    private void findViews() {
        ListViewData = findViewById(R.id.lvcusorderhistory);
        Search = findViewById(R.id.edit_searchmypc);
    }

    private void setListener() {
        ListViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PC PC = (PC) parent.getAdapter().getItem(position);

                Global global = (Global) getApplicationContext();
                global.setViewaccess(2);

                Intent i = new Intent(OrderHistory.this, PCHistoryDetails.class);
                i.putExtra("cusid", PC.getCusid());
                i.putExtra("pcid", PC.getId());
                i.putExtra("date", PC.getDateBuild());
                i.putExtra("process", PC.getProcess());
                i.putExtra("totalprice", PC.getTotalPrice());
                i.putExtra("psu", PC.getTypePowerSupply());
                i.putExtra("chassis", PC.getTypeChassis());
                i.putExtra("motherboard", PC.getTypeMotherBoard());
                i.putExtra("cpu", PC.getTypeCPU());
                i.putExtra("graphicscard", PC.getTypeGraphicscard());
                i.putExtra("ram", PC.getTypeRAM());
                i.putExtra("ssd1", PC.getTypeFirstSlotSSD());
                i.putExtra("ssd2", PC.getTypeSecondSlotSSD());
                i.putExtra("harddisk", PC.getTypeHardDrive());
                i.putExtra("coolingsystem", PC.getTypeCoolingSystem());
                i.putExtra("caselight", PC.getTypeCasesLighting());
                i.putExtra("wirelesslan", PC.getTypeWirelessLan());
                i.putExtra("os", PC.getTypeOS());
                i.putExtra("warranty", PC.getTypeWarrantyPackage());
                startActivity(i);
            }
        });
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = Search.getText().toString();
                Global global = (Global) getApplicationContext();
                int ID = global.getId();
                OrderList.clear();
                adapter.clear();
                if (text.isEmpty()) {
                    setUpAdapter();
                } else if (!text.isEmpty()) {
                    int id = Integer.parseInt(text);
                    OrderList = dbHelper.getOrderHistory(ID, id);
                    adapter = new PCHistoryAdapter(OrderList, OrderHistory.this);
                    ListViewData.setAdapter(adapter);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setUpDatabase() {
        dbHelper = new DBHelper(this);
    }

    private void setUpAdapter() {
        Global global = (Global) getApplicationContext();
        int ID = global.getId();
        OrderList = dbHelper.getAllOrderHistory(ID);
        adapter = new PCHistoryAdapter(OrderList, this);
        ListViewData.setAdapter(adapter);


    }
}
