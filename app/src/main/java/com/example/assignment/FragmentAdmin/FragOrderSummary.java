package com.example.assignment.FragmentAdmin;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.assignment.Admin.DateBetweenPC;
import com.example.assignment.Admin.Summary;
import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.PC;
import com.example.assignment.R;

import java.time.Year;
import java.util.ArrayList;

public class FragOrderSummary extends Fragment {

    private Spinner monthspin, yearspin;
    private int year = 0, month = 0, totalpc, totalprice, averageprice, typeofdivide;
    private double pcbuildpertype;
    private TextView summarytime, totalpcbuild, tvtotalprice, tvaverageprice, textpcbuild, pcbuild;
    private String selectedmonth = "Default", selectedyear = "Default";
    private com.example.assignment.Database.DBHelper DBHelper;
    private ArrayList<PC> PClist;
    private Button Search;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.activity_summary, null);
        findViews();
        setMonthSpinner();
        setYearSpinner();
        setListener();
        setResult();
        return mView;
    }

    private void findViews() {
        monthspin = (Spinner) mView.findViewById(R.id.monthSpinner);
        yearspin = (Spinner) mView.findViewById(R.id.yearSpinner);
        summarytime = mView.findViewById(R.id.summarytime);
        totalpcbuild = mView.findViewById(R.id.settotalbuildpc);
        tvtotalprice = mView.findViewById(R.id.settotalprice);
        tvaverageprice = mView.findViewById(R.id.setaverageprice);
        Search = mView.findViewById(R.id.btnsearch);
        textpcbuild = mView.findViewById(R.id.setpcbuildtext);
        pcbuild = mView.findViewById(R.id.setpcbuild);
    }

    private void setResult() {
        DBHelper = new DBHelper(getActivity());

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
                Intent i = new Intent(getActivity(), DateBetweenPC.class);
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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, yearlist);
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

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, monthlist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthspin.setAdapter(arrayAdapter);

    }


}


