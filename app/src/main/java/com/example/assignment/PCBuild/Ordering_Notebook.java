package com.example.assignment.PCBuild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment.HomePage;
import com.example.assignment.LoginPage;
import com.example.assignment.OrderHistory;
import com.example.assignment.R;

public class Ordering_Notebook extends AppCompatActivity {

    private TextView CPU,GTX,DISPLAY,RAM,KEYBOARD,SSD1,SSD2,HDD,OS,WARRANTY,TOTAL;

    private Button Ordering;

    private String cputype="",gtxtype="",displaytype="",ramtype="",keyboardtype="",
            firstssdtype="",secondssdtype="",harddisktype="",ostype="",warrantytype="";

    private String text;

    private int totalprice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_notebook);
        getData();
        getViews();
        setData();
        setListener();


    }

    private void setListener(){
        Ordering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set to database, remember add date into database

                Intent i = new Intent(Ordering_Notebook.this, OrderHistory.class);
                Toast.makeText(Ordering_Notebook.this, "Order Successfully", Toast.LENGTH_LONG).show();
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);

                //set to database
            }
        });
    }

    private void getViews(){
        CPU=findViewById(R.id.setcpu);
        GTX=findViewById(R.id.setgtx);
        DISPLAY=findViewById(R.id.setdisplay);
        RAM=findViewById(R.id.setram);
        KEYBOARD=findViewById(R.id.setkeyboard);
        SSD1=findViewById(R.id.setssd1);
        SSD2=findViewById(R.id.setssd2);
        HDD=findViewById(R.id.sethdd);
        OS=findViewById(R.id.setos);
        WARRANTY=findViewById(R.id.setwarranty);
        TOTAL=findViewById(R.id.settotal);

        Ordering=findViewById(R.id.btnordering);
    }

    private void getData(){
        totalprice = getIntent().getIntExtra("totalprice",0);
        cputype = getIntent().getStringExtra("cpu");
        gtxtype= getIntent().getStringExtra("graphicscard");
        displaytype=getIntent().getStringExtra("display");
        ramtype=getIntent().getStringExtra("ram");
        keyboardtype=getIntent().getStringExtra("keyboard");
        firstssdtype=getIntent().getStringExtra("ssd1");
        secondssdtype=getIntent().getStringExtra("ssd2");
        harddisktype=getIntent().getStringExtra("harddisk");
        ostype=getIntent().getStringExtra("os");
        warrantytype=getIntent().getStringExtra("warranty");


    }

    private void setData(){
        CPU.setText(cputype);
        GTX.setText(gtxtype);
        DISPLAY.setText(displaytype);
        RAM.setText(ramtype);
        KEYBOARD.setText(keyboardtype);
        SSD1.setText(firstssdtype);
        SSD2.setText(secondssdtype);
        HDD.setText(harddisktype);
        OS.setText(ostype);
        WARRANTY.setText(warrantytype);
        TOTAL.setText("RM "+totalprice);
    }
}
