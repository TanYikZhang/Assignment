package com.example.assignment.PCBuild;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Database.DBHelper;
import com.example.assignment.HomePage;
import com.example.assignment.Model.Global;
import com.example.assignment.Model.PC;
import com.example.assignment.R;

public class Ordering_PC extends AppCompatActivity {

    private TextView PSU, CHASSIS, MOTHERBOARD, CPU, GTX, RAM, SSD1, SSD2, HDD, COOLINGSYSTEM, CASELIGHT, WIRELESSLAN, OS, WARRANTY, TOTAL;

    private Button Ordering;


    private DBHelper OrderDBHelper;
    private PC PC;
    private String
            psutype = "", chassistype = "", motherboardtype = "", cputype = "", gtxtype = "", ramtype = "",
            firstssdtype = "", secondssdtype = "", harddisktype = "", coolingsystemtype = "", caselighttype = "", wirelesslantype = "", ostype = "", warrantytype = "";

    private String text, process = "Building";

    private int totalprice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_pc);
        getData();
        getViews();
        setData();
        setListener();


    }

    private void setListener() {
        Ordering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set to database, remember add date into database
                OrderDBHelper = new DBHelper(Ordering_PC.this);
                insertData();
                OrderDBHelper.insertOrderPC(PC);
                Intent i = new Intent(Ordering_PC.this, HomePage.class);
                Toast.makeText(Ordering_PC.this, "Order Successfully", Toast.LENGTH_LONG).show();
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
                //set to database
            }
        });
    }

    private void getViews() {
        PSU = findViewById(R.id.setpsu);
        CHASSIS = findViewById(R.id.setchassis);
        MOTHERBOARD = findViewById(R.id.setmotherboard);
        CPU = findViewById(R.id.setcpu);
        GTX = findViewById(R.id.setgtx);
        RAM = findViewById(R.id.setram);
        SSD1 = findViewById(R.id.setssd1);
        SSD2 = findViewById(R.id.setssd2);
        HDD = findViewById(R.id.sethdd);
        COOLINGSYSTEM = findViewById(R.id.setcoolingsystem);
        CASELIGHT = findViewById(R.id.setcaselight);
        WIRELESSLAN = findViewById(R.id.setwirelesslan);
        OS = findViewById(R.id.setos);
        WARRANTY = findViewById(R.id.setwarranty);
        TOTAL = findViewById(R.id.settotal);

        Ordering = findViewById(R.id.btnordering);
    }

    private void getData() {
        totalprice = getIntent().getIntExtra("totalprice", 0);
        psutype = getIntent().getStringExtra("psu");
        chassistype = getIntent().getStringExtra("chassis");
        motherboardtype = getIntent().getStringExtra("motherboard");
        cputype = getIntent().getStringExtra("cpu");
        gtxtype = getIntent().getStringExtra("graphicscard");
        ramtype = getIntent().getStringExtra("ram");
        firstssdtype = getIntent().getStringExtra("ssd1");
        secondssdtype = getIntent().getStringExtra("ssd2");
        harddisktype = getIntent().getStringExtra("harddisk");
        coolingsystemtype = getIntent().getStringExtra("coolingsystem");
        caselighttype = getIntent().getStringExtra("caselight");
        wirelesslantype = getIntent().getStringExtra("wirelesslan");
        ostype = getIntent().getStringExtra("os");
        warrantytype = getIntent().getStringExtra("warranty");


    }

    private void setData() {
        PSU.setText(psutype);
        CHASSIS.setText(chassistype);
        MOTHERBOARD.setText(motherboardtype);
        CPU.setText(cputype);
        GTX.setText(gtxtype);
        RAM.setText(ramtype);
        SSD1.setText(firstssdtype);
        SSD2.setText(secondssdtype);
        HDD.setText(harddisktype);
        COOLINGSYSTEM.setText(coolingsystemtype);
        CASELIGHT.setText(caselighttype);
        WIRELESSLAN.setText(wirelesslantype);
        OS.setText(ostype);
        WARRANTY.setText(warrantytype);
        TOTAL.setText("RM " + totalprice);
    }

    private void insertData() {

        PC = new PC();
        Global global = (Global) getApplicationContext();

        int CusID = global.getId();

        PC.setCusid(CusID);
        PC.setProcess(process);
        PC.setTypeChassis(chassistype);
        PC.setTypeMotherBoard(motherboardtype);
        PC.setTypeCPU(cputype);
        PC.setTypeRAM(ramtype);
        PC.setTypeGraphicscard(gtxtype);
        PC.setTypeFirstSlotSSD(firstssdtype);
        PC.setTypeSecondSlotSSD(secondssdtype);
        PC.setTypeHardDrive(harddisktype);
        PC.setTypeCoolingSystem(coolingsystemtype);
        PC.setTypeCasesLighting(caselighttype);
        PC.setTypePowerSupply(psutype);
        PC.setTypeWirelessLan(wirelesslantype);
        PC.setTypeOS(ostype);
        PC.setTypeWarrantyPackage(warrantytype);
        PC.setTotalPrice(totalprice);

    }

}
