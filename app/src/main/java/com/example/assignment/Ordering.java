package com.example.assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Ordering extends AppCompatActivity {

    private TextView Data;

    private String cputype="",gtxtype="",displaytype="",ramtype="",keyboardtype="",
            firstssdtype="",secondssdtype="",harddisktype="",ostype="",warrantytype="";

    private int totalprice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering);

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

        Data=findViewById(R.id.tvtry);

        Data.setText(totalprice+" "+cputype);

    }
}
