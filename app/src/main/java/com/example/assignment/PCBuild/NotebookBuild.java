package com.example.assignment.PCBuild;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.assignment.Model.Notebook;
import com.example.assignment.R;

public class NotebookBuild extends AppCompatActivity {
    private RadioGroup radioGroupCPU,radioGroupGTX,radioGroupDisplay,radioGroupRAM,radioGroupKeyboard,
            radioGroupSSD1,radioGroupSSD2,radioGroupHarddisk,radioGroupOS,radioGroupWarranty;

    private RadioButton radioButtoni5,radioButtoni7,
            radioButtongtx1050,radioButtongtx1650,
            radioButtonDisplay15_6,radioButtonDisplay17_3,
            radioButtonram8gbx1,radioButtonram8gbx2,radioButtonram16gbx1,radioButtonram16gbx2,
            radioButtonkeyboardred,radioButtonkeyboardrgb,
            radioButtonSSD256GB1,radioButtonSSD512GB1,radioButtonSSD1TB1,
            radioButtonSSDnone2,radioButtonSSD256GB2,radioButtonSSD512GB2,radioButtonSSD1TB2,
            radioButtonHardDisknone,radioButtonHardDisk1TB,radioButtonHardDisk2TB,
            radioButtonOSunactive,radioButtonOSwindows10home,radioButtonOSwindows10professional,
            radioButton2yearswarranty,radioButton3yearswarranty;


    private ImageView CPUImage,GTXImage,DisplayImage,RAMImage,KeyboardImage,
            FirstSSDImage,SecondSSDImage,HarddiskImage,OSImage,WarrantyImage;

    private int cpuprice=0,gtxprice=0,displayprice=0,ramprice=0,keyboardprice=0,
            firstssdprice=0,secondssdprice=0,harddiskprice=0,osprice=0,warrantyprice=0;

    private String cputype="",gtxtype="",displaytype="",ramtype="",keyboardtype="",
            firstssdtype="",secondssdtype="",harddisktype="",ostype="",warrantytype="";

    private Notebook notebook = new Notebook();

    private Button Order;

    private int defaultprice=2500;
    private int totalprice=0;
    private TextView TVPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook_build);
        findViews();
        setDefault();
        setListeners();


    }

    private void findViews(){
        radioGroupCPU=findViewById(R.id.notebookcpu);
        radioButtoni5=radioGroupCPU.findViewById(R.id.cpui5);
        radioButtoni7=radioGroupCPU.findViewById(R.id.cpui7);

        radioGroupGTX=findViewById(R.id.notebookgtx);
        radioButtongtx1050=radioGroupGTX.findViewById(R.id.gtx1050);
        radioButtongtx1650=radioGroupGTX.findViewById(R.id.gtx1650);

        radioGroupDisplay=findViewById(R.id.notebookdisplay);
        radioButtonDisplay15_6=radioGroupDisplay.findViewById(R.id.display15_6);
        radioButtonDisplay17_3=radioGroupDisplay.findViewById(R.id.display17_3);

        radioGroupRAM=findViewById(R.id.notebookram);
        radioButtonram8gbx1=radioGroupRAM.findViewById(R.id.ram_8gb);
        radioButtonram8gbx2=radioGroupRAM.findViewById(R.id.ram_8gbx2);
        radioButtonram16gbx1=radioGroupRAM.findViewById(R.id.ram_16gb);
        radioButtonram16gbx2=radioGroupRAM.findViewById(R.id.ram_16gbx2);

        radioGroupKeyboard=findViewById(R.id.notebookkeyboard);
        radioButtonkeyboardred=radioGroupKeyboard.findViewById(R.id.keyboardred);
        radioButtonkeyboardrgb=radioGroupKeyboard.findViewById(R.id.keyboardrgb);

        radioGroupSSD1=findViewById(R.id.notebookssd1);
        radioButtonSSD256GB1=radioGroupSSD1.findViewById(R.id.ssd256gb);
        radioButtonSSD512GB1=radioGroupSSD1.findViewById(R.id.ssd512gb);
        radioButtonSSD1TB1=radioGroupSSD1.findViewById(R.id.ssd1tb);

        radioGroupSSD2=findViewById(R.id.notebookssd2);
        radioButtonSSDnone2=radioGroupSSD2.findViewById(R.id.ssdnone2);
        radioButtonSSD256GB2=radioGroupSSD2.findViewById(R.id.ssd256gb2);
        radioButtonSSD512GB2=radioGroupSSD2.findViewById(R.id.ssd512gb2);
        radioButtonSSD1TB2=radioGroupSSD2.findViewById(R.id.ssd1tb2);

        radioGroupHarddisk=findViewById(R.id.notebookharddisk);
        radioButtonHardDisknone=radioGroupHarddisk.findViewById(R.id.harddisknone);
        radioButtonHardDisk1TB=radioGroupHarddisk.findViewById(R.id.harddisk1tb);
        radioButtonHardDisk2TB=radioGroupHarddisk.findViewById(R.id.harddisk2tb);

        radioGroupOS=findViewById(R.id.notebookos);
        radioButtonOSunactive=radioGroupOS.findViewById(R.id.windowsunactive);
        radioButtonOSwindows10home=radioGroupOS.findViewById(R.id.windows10home);
        radioButtonOSwindows10professional=radioGroupOS.findViewById(R.id.windows10professional);

        radioGroupWarranty=findViewById(R.id.notebookwarranty);
        radioButton2yearswarranty=radioGroupWarranty.findViewById(R.id.warranty2years);
        radioButton3yearswarranty=radioGroupWarranty.findViewById(R.id.warranty3years);

        CPUImage=findViewById(R.id.imagecpu);
        GTXImage=findViewById(R.id.imagegtx);
        DisplayImage=findViewById(R.id.imagedisplay);
        RAMImage=findViewById(R.id.imageram);
        KeyboardImage=findViewById(R.id.imagekeyboard);
        FirstSSDImage=findViewById(R.id.imagessd1);
        SecondSSDImage=findViewById(R.id.imagessd2);
        HarddiskImage=findViewById(R.id.imageharddisk);
        OSImage=findViewById(R.id.imageos);
        WarrantyImage=findViewById(R.id.imagewarranty);

        TVPrice=findViewById(R.id.price);
        Order=findViewById(R.id.btnorder);

    }

    private void setDefault(){
        //-----Image-----//
        CPUImage.setImageResource(R.drawable.i5_9th);
        GTXImage.setImageResource(R.drawable.gtx);
        DisplayImage.setImageResource(R.drawable.display);
        RAMImage.setImageResource(R.drawable.ram);
        KeyboardImage.setImageResource(R.drawable.redkeyboard);
        FirstSSDImage.setImageResource(R.drawable.ssd);
        SecondSSDImage.setImageResource(R.drawable.ssd);
        HarddiskImage.setImageResource(R.drawable.hdd);
        OSImage.setImageResource(R.drawable.os);
        WarrantyImage.setImageResource(R.drawable.warranty2);
        //-----Image-----//
        //-----Button-----//
        radioButtoni5.setChecked(true);
        radioButtongtx1050.setChecked(true);
        radioButtonDisplay15_6.setChecked(true);
        radioButtonram8gbx1.setChecked(true);
        radioButtonkeyboardred.setChecked(true);
        radioButtonSSD256GB1.setChecked(true);
        radioButtonSSDnone2.setChecked(true);
        radioButtonHardDisknone.setChecked(true);
        radioButtonOSunactive.setChecked(true);
        radioButton2yearswarranty.setChecked(true);
        //-----Button-----//
        //-----Type-----//
        cputype=radioButtoni5.getText().toString();
        gtxtype=radioButtongtx1050.getText().toString();
        displaytype=radioButtonDisplay15_6.getText().toString();
        ramtype=radioButtonram8gbx1.getText().toString();
        keyboardtype=radioButtonkeyboardred.getText().toString();
        firstssdtype=radioButtonSSD256GB1.getText().toString();
        secondssdtype=radioButtonSSDnone2.getText().toString();
        harddisktype=radioButtonHardDisknone.getText().toString();
        ostype=radioButtonOSunactive.getText().toString();
        warrantytype=radioButton2yearswarranty.getText().toString();
        //-----Type-----//
        totalprice=defaultprice;
        TVPrice.setText(RMChange(defaultprice));
    }

    private void setListeners(){
        /////////////////////////-------RadioGroup-------/////////////////////////////////
        radioGroupCPU.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtoni5.isChecked()){

                    cputype=radioButtoni5.getText().toString();
                    cpuprice=0;
                    TotalPrice();

                    CPUImage.setImageResource(R.drawable.i5_9th);

                }else if (radioButtoni7.isChecked()){

                    cputype=radioButtoni7.getText().toString();
                    cpuprice = 330;
                    TotalPrice();


                    CPUImage.setImageResource(R.drawable.i7_9th);
                }
            }
        });

        radioGroupGTX.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtongtx1050.isChecked()){

                    gtxtype=radioButtongtx1050.getText().toString();
                    gtxprice=0;
                    TotalPrice();


                }else if (radioButtongtx1650.isChecked()){

                    gtxtype=radioButtongtx1650.getText().toString();
                    gtxprice=279;
                    TotalPrice();

                }
            }
        });

        radioGroupDisplay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonDisplay15_6.isChecked()){

                    displaytype=radioButtonDisplay15_6.getText().toString();
                    displayprice=0;
                    TotalPrice();


                }else if (radioButtonDisplay17_3.isChecked()){

                    displaytype=radioButtonDisplay17_3.getText().toString();
                    displayprice=349;
                    TotalPrice();

                }
            }
        });

        radioGroupRAM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonram8gbx1.isChecked()){

                    ramtype=radioButtonram8gbx1.getText().toString();
                    ramprice=0;
                    TotalPrice();


                }else if (radioButtonram8gbx2.isChecked()){

                    ramtype=radioButtonram8gbx2.getText().toString();
                    ramprice=139;
                    TotalPrice();

                }
                else if (radioButtonram16gbx1.isChecked()){

                    ramtype=radioButtonram16gbx1.getText().toString();
                    ramprice=149;
                    TotalPrice();


                }else if (radioButtonram16gbx2.isChecked()){

                    ramtype=radioButtonram16gbx2.getText().toString();
                    ramprice=439;
                    TotalPrice();

                }
            }
        });

        radioGroupKeyboard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonkeyboardred.isChecked()){

                    keyboardtype=radioButtonkeyboardred.getText().toString();
                    keyboardprice=0;
                    TotalPrice();

                    KeyboardImage.setImageResource(R.drawable.redkeyboard);

                }else if (radioButtonkeyboardrgb.isChecked()){

                    keyboardtype=radioButtonkeyboardrgb.getText().toString();
                    keyboardprice=100;
                    TotalPrice();

                    KeyboardImage.setImageResource(R.drawable.rgbkeyboard);
                }
            }
        });

        radioGroupSSD1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonSSD256GB1.isChecked()){

                    firstssdtype=radioButtonSSD256GB1.getText().toString();
                    firstssdprice=0;
                    TotalPrice();


                }else if (radioButtonSSD512GB1.isChecked()){

                    firstssdtype=radioButtonSSD512GB1.getText().toString();
                    firstssdprice=149;
                    TotalPrice();

                }else if (radioButtonSSD1TB1.isChecked()){

                    firstssdtype=radioButtonSSD1TB1.getText().toString();
                    firstssdprice=599;
                    TotalPrice();

                }
            }
        });

        radioGroupSSD2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonSSDnone2.isChecked()){

                    secondssdtype=radioButtonSSDnone2.getText().toString();
                    secondssdprice=0;
                    TotalPrice();

                }else if (radioButtonSSD256GB2.isChecked()){

                    secondssdtype=radioButtonSSD256GB2.getText().toString();
                    secondssdprice=329;
                    TotalPrice();

                }else if (radioButtonSSD512GB2.isChecked()){

                    secondssdtype=radioButtonSSD512GB2.getText().toString();
                    secondssdprice=479;
                    TotalPrice();

                }else if (radioButtonSSD1TB2.isChecked()){

                    secondssdtype=radioButtonSSD1TB2.getText().toString();
                    secondssdprice=899;
                    TotalPrice();

                }
            }
        });

        radioGroupHarddisk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonHardDisknone.isChecked()){

                    harddisktype=radioButtonHardDisknone.getText().toString();
                    harddiskprice=0;
                    TotalPrice();

                }else if (radioButtonHardDisk1TB.isChecked()){

                    harddisktype=radioButtonHardDisk1TB.getText().toString();
                    harddiskprice=229;
                    TotalPrice();

                }else if (radioButtonHardDisk2TB.isChecked()){

                    harddisktype=radioButtonHardDisk2TB.getText().toString();
                    harddiskprice=349;
                    TotalPrice();

                }
            }
        });

        radioGroupOS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonOSunactive.isChecked()){

                    ostype=radioButtonOSunactive.getText().toString();
                    osprice=0;
                    TotalPrice();

                }else if (radioButtonOSwindows10home.isChecked()){

                    ostype=radioButtonOSwindows10home.getText().toString();
                    osprice=479;
                    TotalPrice();

                }else if (radioButtonOSwindows10professional.isChecked()){

                    ostype=radioButtonOSwindows10professional.getText().toString();
                    osprice=609;
                    TotalPrice();

                }
            }
        });

        radioGroupWarranty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButton2yearswarranty.isChecked()){

                    warrantytype=radioButton2yearswarranty.getText().toString();
                    warrantyprice=0;
                    WarrantyImage.setImageResource(R.drawable.warranty2);
                    TotalPrice();

                }else if (radioButton3yearswarranty.isChecked()){

                    warrantytype=radioButton3yearswarranty.getText().toString();
                    warrantyprice=299;
                    WarrantyImage.setImageResource(R.drawable.warranty3);
                    TotalPrice();
                }
            }
        });
        /////////////////////////-------RadioGroup-------/////////////////////////////////

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult();
                Intent i = new Intent(NotebookBuild.this, Ordering_Notebook.class);
                i.putExtra("totalprice",notebook.getTotalPrice());
                i.putExtra("cpu",notebook.getTypeCPU());
                i.putExtra("graphicscard",notebook.getTypeGraphicscard());
                i.putExtra("display",notebook.getTypeDisplay());
                i.putExtra("ram",notebook.getTypeRAM());
                i.putExtra("keyboard",notebook.getTypeKeyboard());
                i.putExtra("ssd1",notebook.getTypeFirstM2SSDBaY());
                i.putExtra("ssd2",notebook.getTypeSecondM2SSDBay());
                i.putExtra("harddisk",notebook.getTypeHardDrive());
                i.putExtra("os",notebook.getTypeOS());
                i.putExtra("warranty",notebook.getTypeWarrantyPackage());
                startActivity(i);
            }
        });
    }

    private void TotalPrice(){
        totalprice=defaultprice
                +gtxprice
                +cpuprice
                +displayprice
                +ramprice
                +keyboardprice
                +firstssdprice
                +secondssdprice
                +harddiskprice
                +osprice
                +warrantyprice;
        TVPrice.setText(RMChange(totalprice));

    }

    private String RMChange(int RM){
        String totalprice="RM "+RM+".00";
        return totalprice;
    }

    private String CutRM(String x){

        if (x.lastIndexOf("(")!=-1){
            int y=x.lastIndexOf("(");
            x=x.substring(0,y);
        }

        return x;
    }

    private void setResult(){
        notebook.setTotalPrice(totalprice);
        notebook.setTypeCPU(CutRM(CutRM(cputype)));
        notebook.setTypeGraphicscard(CutRM(gtxtype));
        notebook.setTypeDisplay(CutRM(displaytype));
        notebook.setTypeRAM(CutRM(ramtype));
        notebook.setTypeKeyboard(CutRM(keyboardtype));
        notebook.setTypeFirstM2SSDBaY(CutRM(firstssdtype));
        notebook.setTypeSecondM2SSDBay(CutRM(secondssdtype));
        notebook.setTypeHardDrive(CutRM(harddisktype));
        notebook.setTypeOS(CutRM(ostype));
        notebook.setTypeWarrantyPackage(CutRM(warrantytype));

    }

}