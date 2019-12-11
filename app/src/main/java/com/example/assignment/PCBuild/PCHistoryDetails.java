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

public class PCHistoryDetails extends AppCompatActivity {

    private TextView CUSID,PCID,DATEORDER,PROCESS,PSU,CHASSIS,MOTHERBOARD,CPU,GTX,RAM,SSD1,SSD2,HDD,COOLINGSYSTEM,CASELIGHT,WIRELESSLAN,OS,WARRANTY,TOTAL;

    private Button Complete;

    private String
            dateorder="",process="",psutype="",chassistype="",motherboardtype="",cputype = "", gtxtype = "",  ramtype = "",
            firstssdtype = "", secondssdtype = "", harddisktype = "", coolingsystemtype="",caselighttype="",wirelesslantype="",ostype = "", warrantytype = "";


    private int totalprice=0,pcid=0,cusid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_details);
        getData();
        getViews();
        setData();
        setListener();
        Global global = (Global) getApplicationContext();
        int ID = global.getViewaccess();
        if (ID!=1){
            Complete.setVisibility(View.GONE);
        }

    }

    private void setListener(){

    }

    private void getViews(){
        CUSID=findViewById(R.id.sethistorycusid);
        PCID=findViewById(R.id.sethistorypcid);
        DATEORDER=findViewById(R.id.sethistorydateorder);
        PROCESS=findViewById(R.id.sethistoryprocess);
        PSU=findViewById(R.id.sethistorypsu);
        CHASSIS=findViewById(R.id.sethistorychassis);
        MOTHERBOARD=findViewById(R.id.sethistorymotherboard);
        CPU=findViewById(R.id.sethistorycpu);
        GTX=findViewById(R.id.sethistorygtx);
        RAM=findViewById(R.id.sethistoryram);
        SSD1=findViewById(R.id.sethistoryssd1);
        SSD2=findViewById(R.id.sethistoryssd2);
        HDD=findViewById(R.id.sethistoryhdd);
        COOLINGSYSTEM=findViewById(R.id.sethistorycoolingsystem);
        CASELIGHT=findViewById(R.id.sethistorycaselight);
        WIRELESSLAN=findViewById(R.id.sethistorywirelesslan);
        OS=findViewById(R.id.sethistoryos);
        WARRANTY=findViewById(R.id.sethistorywarranty);
        TOTAL=findViewById(R.id.sethistorytotal);

        Complete=findViewById(R.id.btnbuildcomplete);
    }

    private void getData(){
        cusid = getIntent().getIntExtra("cusid",0);
        pcid = getIntent().getIntExtra("pcid",0);
        dateorder = getIntent().getStringExtra("date");
        process = getIntent().getStringExtra("process");
        totalprice = getIntent().getIntExtra("totalprice",0);
        psutype = getIntent().getStringExtra("psu");
        chassistype = getIntent().getStringExtra("chassis");
        motherboardtype = getIntent().getStringExtra("motherboard");
        cputype = getIntent().getStringExtra("cpu");
        gtxtype= getIntent().getStringExtra("graphicscard");
        ramtype=getIntent().getStringExtra("ram");
        firstssdtype=getIntent().getStringExtra("ssd1");
        secondssdtype=getIntent().getStringExtra("ssd2");
        harddisktype=getIntent().getStringExtra("harddisk");
        coolingsystemtype= getIntent().getStringExtra("coolingsystem");
        caselighttype= getIntent().getStringExtra("caselight");
        wirelesslantype= getIntent().getStringExtra("wirelesslan");
        ostype=getIntent().getStringExtra("os");
        warrantytype=getIntent().getStringExtra("warranty");


    }
    private void setData(){
        CUSID.setText(String.format("%d",cusid));
        PCID.setText(String.format("%d",pcid));
        DATEORDER.setText(dateorder);
        PROCESS.setText(process);
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
        TOTAL.setText("RM "+totalprice);
    }


}
