package com.example.assignment.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.PC;
import com.example.assignment.R;

import java.time.Year;
import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    private Spinner monthspin, yearspin;
    private int year = 0, month = 0, totalpc, totalprice, averageprice, typeofdivide;
    private double pcbuildpertype;
    private TextView summarytime, totalpcbuild, tvtotalprice, tvaverageprice, textpcbuild, pcbuild;
    private String selectedmonth = "Default", selectedyear = "Default";
    private DBHelper DBHelper;
    private ArrayList<PC> PClist;
    private Button Search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        findViews();
        setMonthSpinner();
        setYearSpinner();
        setListener();
        setResult();


    }


    private void findViews() {
        monthspin = (Spinner) findViewById(R.id.monthSpinner);
        yearspin = (Spinner) findViewById(R.id.yearSpinner);
        summarytime = findViewById(R.id.summarytime);
        totalpcbuild = findViewById(R.id.settotalbuildpc);
        tvtotalprice = findViewById(R.id.settotalprice);
        tvaverageprice = findViewById(R.id.setaverageprice);
        Search = findViewById(R.id.btnsearch);
        textpcbuild = findViewById(R.id.setpcbuildtext);
        pcbuild = findViewById(R.id.setpcbuild);
    }

    private void setResult() {
        DBHelper = new DBHelper(this);

        if (!selectedmonth.equals("Default") && !selectedyear.equals("Default")) {
            typeofdivide = 30;
            textpcbuild.setText("PC Build Per Days");
            summarytime.setText(selectedmonth + " " + selectedyear + " Summary");
            PClist = DBHelper.getDatebetween(year, month);
            getDatabaseInfo();


        } else {
            typeofdivide = 12;
            textpcbuild.setText("PC Build Per Month");
            summarytime.setText("All-Days Summary");
            PClist = DBHelper.getAllOrder();
            getDatabaseInfo();

        }

        totalpcbuild.setText(String.format("%s", totalpc));
        tvtotalprice.setText(String.format("%s", totalprice));
        tvaverageprice.setText(String.format("%s", averageprice));
        pcbuild.setText(String.format("%.2f", pcbuildpertype));
    }

    private void getDatabaseInfo() {
        totalpc = 0;
        totalprice = 0;
        averageprice = 0;
        pcbuildpertype = 0;
        for (int i = 0; i < PClist.size(); i++) {
            totalpc++;
            totalprice += PClist.get(i).getTotalPrice();
        }
        if (totalpc > 0) {
            averageprice = totalprice / totalpc;
            pcbuildpertype = (double) totalpc / (double) typeofdivide;
        }


    }

    private void setListener() {
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Summary.this, DateBetweenPC.class);
                i.putExtra("year", year);
                i.putExtra("month", month);
                startActivity(i);
            }

        });
        monthspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedmonth = parent.getSelectedItem().toString();
                if (selectedmonth.equals("Default")) {

                } else if (!selectedmonth.equals("Default")) {
                    switch (selectedmonth) {
                        case "January":
                            month = 1;
                            break;
                        case "February":
                            month = 2;
                            break;
                        case "March":
                            month = 3;
                            break;
                        case "April":
                            month = 4;
                            break;
                        case "May":
                            month = 5;
                            break;
                        case "June":
                            month = 6;
                            break;
                        case "July":
                            month = 7;
                            break;
                        case "August":
                            month = 8;
                            break;
                        case "September":
                            month = 9;
                            break;
                        case "October":
                            month = 10;
                            break;
                        case "November":
                            month = 11;
                            break;
                        case "December":
                            month = 12;
                            break;
                    }
                }
                ;
                setResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        yearspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedyear = parent.getSelectedItem().toString();
                if (selectedyear.equals("Default")) {

                } else if (!selectedyear.equals("Default")) {
                    year = Integer.parseInt(selectedyear);
                }
                ;
                setResult();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setYearSpinner() {
        int yearnow = Year.now().getValue();

        ArrayList<String> yearlist = new ArrayList<>();
        yearlist.add("Default");
        for (int i = yearnow; i >= 2018; i--) {
            String x = Integer.toString(i);
            yearlist.add(x);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, yearlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearspin.setAdapter(arrayAdapter);

    }

    private void setMonthSpinner() {

        ArrayList<String> monthlist = new ArrayList<>();
        monthlist.add("Default");
        monthlist.add("January");
        monthlist.add("February");
        monthlist.add("March");
        monthlist.add("April");
        monthlist.add("May");
        monthlist.add("June");
        monthlist.add("July");
        monthlist.add("August");
        monthlist.add("September");
        monthlist.add("October");
        monthlist.add("November");
        monthlist.add("December");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, monthlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthspin.setAdapter(arrayAdapter);

    }


}
