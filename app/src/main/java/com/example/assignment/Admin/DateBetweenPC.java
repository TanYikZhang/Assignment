package com.example.assignment.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.assignment.Adapter.PCHistoryAdapter;
import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Global;
import com.example.assignment.Model.PC;
import com.example.assignment.PCBuild.PCHistoryDetails;
import com.example.assignment.R;

import java.util.ArrayList;


public class DateBetweenPC extends AppCompatActivity {
    private ListView ListViewData;
    private ArrayList<PC> OrderList = new ArrayList<>();
    private PCHistoryAdapter adapter;
    private DBHelper dbHelper;
    private EditText Search;
    private TextView Date;
    private int month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_between_pc);
        month = getIntent().getIntExtra("month", 0);
        year = getIntent().getIntExtra("year", 0);
        findViews();
        setDate();
        setListener();
        setUpDatabase();
        if (OrderList.size() > 0) {
            setUpAdapter();
        }
        Global global = (Global) getApplicationContext();
        global.setViewaccess(1);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpDatabase();
        setUpAdapter();
    }

    private void setDate() {
        String smonth = "";
        switch (month) {
            case 1:
                smonth = "January";
                break;
            case 2:
                smonth = "February";
                break;
            case 3:
                smonth = "March";
                break;
            case 4:
                smonth = "April";
                break;
            case 5:
                smonth = "May";
                break;
            case 6:
                smonth = "June";
                break;
            case 7:
                smonth = "July";
                break;
            case 8:
                smonth = "August";
                break;
            case 9:
                smonth = "September";
                break;
            case 10:
                smonth = "October";
                break;
            case 11:
                smonth = "November";
                break;
            case 12:
                smonth = "December";
                break;
        }
        if (year == 0 || month == 0) {
            Date.setText("All-Days History");
        } else {
            Date.setText(smonth + " " + Integer.toString(year) + " History");
        }

    }

    private void findViews() {
        ListViewData = findViewById(R.id.lvsearchorder);
        Search = findViewById(R.id.edit_searchpc);
        Date = findViewById(R.id.dateorder);
    }

    private void setListener() {
        ListViewData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PC PC = (PC) parent.getAdapter().getItem(position);

                Intent i = new Intent(DateBetweenPC.this, PCHistoryDetails.class);
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

                OrderList.clear();
                adapter.clear();
                if (text.isEmpty()) {
                    setUpAdapter();
                } else if (!text.isEmpty()) {
                    int id = Integer.parseInt(text);
                    OrderList = dbHelper.getOrder(id);
                    adapter = new PCHistoryAdapter(OrderList, DateBetweenPC.this);
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
        if (year == 0 || month == 0) {
            OrderList = dbHelper.getAllOrder();
        } else {
            OrderList = dbHelper.getDatebetween(year, month);
        }
    }

    private void setUpAdapter() {
        adapter = new PCHistoryAdapter(OrderList, this);
        ListViewData.setAdapter(adapter);
    }
}
