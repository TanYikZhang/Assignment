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

import com.example.assignment.Model.PC;
import com.example.assignment.R;

public class DesktopBuild extends AppCompatActivity {
    private RadioGroup
            radioGroupPSU,radioGroupChassis,radioGroupMotherBoard, radioGroupCPU, radioGroupGTX, radioGroupRAM,
            radioGroupSSD1, radioGroupSSD2, radioGroupHarddisk,radioGroupCoolingSystem ,radioGroupCaseLight,radioGroupWirelessLan,radioGroupOS, radioGroupWarranty;

    private RadioButton
            radioButtonPSU600W,radioButtonPSU650W,
            radioButtonchassisblack, radioButtonchassisrgb,
            radioButtonMboard1,radioButtonMboard2,
            radioButtoni5, radioButtoni7,
            radioButtongtx1050, radioButtongtx1650,
            radioButtonram8gbx1, radioButtonram8gbx2, radioButtonram16gbx1, radioButtonram16gbx2,
            radioButtonSSD256GB1, radioButtonSSD512GB1, radioButtonSSD1TB1,
            radioButtonSSDnone2, radioButtonSSD256GB2, radioButtonSSD512GB2, radioButtonSSD1TB2,
            radioButtonHardDisknone, radioButtonHardDisk1TB, radioButtonHardDisk2TB,
            radioButtonBaseCooling , radioButtonPremiumCooling, radioButtonWaterCoolingRGB,
            radioButtonLightnone,radioButtonLightLED,
            radioButtonWirelessLanNone,radioButtonWirelessLanAC56,radioButtonWirelessLanAC68,radioButtonWirelessLanAC88,
            radioButtonOSunactive, radioButtonOSwindows10home, radioButtonOSwindows10professional,
            radioButton2yearswarranty, radioButton3yearswarranty;


    private ImageView
            PSUImage,ChassisImage,MotherBoardImage, CPUImage, GTXImage, RAMImage,
            FirstSSDImage, SecondSSDImage, HarddiskImage, CoolingImage,CaseLightImage,WirelessLanImage,OSImage, WarrantyImage;

    private int
            psuprice,chassisprice = 0,motherboardprice=0,cpuprice = 0, gtxprice = 0,  ramprice = 0,
            firstssdprice = 0, secondssdprice = 0, harddiskprice = 0,coolingsystemprice=0, caselightprice=0,wirelesslanprice=0,osprice = 0, warrantyprice = 0;

    private String
            psutype="",chassistype="",motherboardtype="",cputype = "", gtxtype = "",  ramtype = "",
            firstssdtype = "", secondssdtype = "", harddisktype = "", coolingsystemtype="",caselighttype="",wirelesslantype="",ostype = "", warrantytype = "";

    private PC PC = new PC();

    private Button Order;

    private int defaultprice = 2500;
    private int totalprice = 0;
    private TextView TVPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop_build);
        findViews();
        setDefault();
        setListeners();

    }

    private void findViews() {
        radioGroupPSU = findViewById(R.id.pcpsu);
        radioButtonPSU600W = radioGroupPSU.findViewById(R.id.psu600w);
        radioButtonPSU650W = radioGroupPSU.findViewById(R.id.psu650w);

        radioGroupChassis = findViewById(R.id.pcchassis);
        radioButtonchassisblack = radioGroupChassis.findViewById(R.id.chassisblack);
        radioButtonchassisrgb = radioGroupChassis.findViewById(R.id.chassisrgb);

        radioGroupMotherBoard=findViewById(R.id.pcmotherboard);
        radioButtonMboard1 = radioGroupMotherBoard.findViewById(R.id.mboard1);
        radioButtonMboard2 = radioGroupMotherBoard.findViewById(R.id.mboard2);

        radioGroupCPU = findViewById(R.id.pccpu);
        radioButtoni5 = radioGroupCPU.findViewById(R.id.cpui5);
        radioButtoni7 = radioGroupCPU.findViewById(R.id.cpui7);

        radioGroupGTX = findViewById(R.id.pcgtx);
        radioButtongtx1050 = radioGroupGTX.findViewById(R.id.gtx1050);
        radioButtongtx1650 = radioGroupGTX.findViewById(R.id.gtx1650);

        radioGroupRAM = findViewById(R.id.pcram);
        radioButtonram8gbx1 = radioGroupRAM.findViewById(R.id.ram_8gb);
        radioButtonram8gbx2 = radioGroupRAM.findViewById(R.id.ram_8gbx2);
        radioButtonram16gbx1 = radioGroupRAM.findViewById(R.id.ram_16gb);
        radioButtonram16gbx2 = radioGroupRAM.findViewById(R.id.ram_16gbx2);

        radioGroupSSD1 = findViewById(R.id.pcssd1);
        radioButtonSSD256GB1 = radioGroupSSD1.findViewById(R.id.ssd256gb);
        radioButtonSSD512GB1 = radioGroupSSD1.findViewById(R.id.ssd512gb);
        radioButtonSSD1TB1 = radioGroupSSD1.findViewById(R.id.ssd1tb);

        radioGroupSSD2 = findViewById(R.id.pcssd2);
        radioButtonSSDnone2 = radioGroupSSD2.findViewById(R.id.ssdnone2);
        radioButtonSSD256GB2 = radioGroupSSD2.findViewById(R.id.ssd256gb2);
        radioButtonSSD512GB2 = radioGroupSSD2.findViewById(R.id.ssd512gb2);
        radioButtonSSD1TB2 = radioGroupSSD2.findViewById(R.id.ssd1tb2);

        radioGroupHarddisk = findViewById(R.id.pcharddisk);
        radioButtonHardDisknone = radioGroupHarddisk.findViewById(R.id.harddisknone);
        radioButtonHardDisk1TB = radioGroupHarddisk.findViewById(R.id.harddisk1tb);
        radioButtonHardDisk2TB = radioGroupHarddisk.findViewById(R.id.harddisk2tb);

        radioGroupCoolingSystem = findViewById(R.id.pccoolingsystem);
        radioButtonBaseCooling = radioGroupCoolingSystem.findViewById(R.id.aircoolingbase);
        radioButtonPremiumCooling = radioGroupCoolingSystem.findViewById(R.id.aircoolingpremium);
        radioButtonWaterCoolingRGB = radioGroupCoolingSystem.findViewById(R.id.watercoolingrgb);

        radioGroupCaseLight = findViewById(R.id.pccaselight);
        radioButtonLightnone = radioGroupCaseLight.findViewById(R.id.lightnone);
        radioButtonLightLED = radioGroupCaseLight.findViewById(R.id.lightled);

        radioGroupWirelessLan = findViewById(R.id.pcwirelesslan);
        radioButtonWirelessLanNone = radioGroupWirelessLan.findViewById(R.id.wirelesslannone);
        radioButtonWirelessLanAC56 = radioGroupWirelessLan.findViewById(R.id.wirelesslanAC56);
        radioButtonWirelessLanAC68 = radioGroupWirelessLan.findViewById(R.id.wirelesslanAC68);
        radioButtonWirelessLanAC88 = radioGroupWirelessLan.findViewById(R.id.wirelesslanAC88);

        radioGroupOS = findViewById(R.id.pcos);
        radioButtonOSunactive = radioGroupOS.findViewById(R.id.windowsunactive);
        radioButtonOSwindows10home = radioGroupOS.findViewById(R.id.windows10home);
        radioButtonOSwindows10professional = radioGroupOS.findViewById(R.id.windows10professional);

        radioGroupWarranty = findViewById(R.id.pcwarranty);
        radioButton2yearswarranty = radioGroupWarranty.findViewById(R.id.warranty2years);
        radioButton3yearswarranty = radioGroupWarranty.findViewById(R.id.warranty3years);

        PSUImage = findViewById(R.id.imagepsu);
        ChassisImage=findViewById(R.id.imagechassis);
        MotherBoardImage=findViewById(R.id.imagemotherboard);
        CPUImage = findViewById(R.id.imagecpu);
        GTXImage = findViewById(R.id.imagegtx);
        RAMImage = findViewById(R.id.imageram);
        FirstSSDImage = findViewById(R.id.imagessd1);
        SecondSSDImage = findViewById(R.id.imagessd2);
        HarddiskImage = findViewById(R.id.imageharddisk);
        CoolingImage = findViewById(R.id.imagecoolingsystem);
        CaseLightImage = findViewById(R.id.imagecaselight);
        WirelessLanImage = findViewById(R.id.imagewirelesslan);
        OSImage = findViewById(R.id.imageos);
        WarrantyImage = findViewById(R.id.imagewarranty);

        TVPrice = findViewById(R.id.price);
        Order = findViewById(R.id.btnorder);

    }

    private void setDefault() {
        //-----Image-----//
        PSUImage.setImageResource(R.drawable.psu);
        ChassisImage.setImageResource(R.drawable.chassisblack);
        MotherBoardImage.setImageResource(R.drawable.motherboard);
        CPUImage.setImageResource(R.drawable.i5_9th);
        GTXImage.setImageResource(R.drawable.gtx);
        RAMImage.setImageResource(R.drawable.ram);
        FirstSSDImage.setImageResource(R.drawable.ssd);
        SecondSSDImage.setImageResource(R.drawable.ssd);
        HarddiskImage.setImageResource(R.drawable.hdd);
        CoolingImage.setImageResource(R.drawable.basecooler);
        CaseLightImage.setImageResource(R.drawable.lightnone);
        WirelessLanImage.setImageResource(R.drawable.wirelesslannone);
        OSImage.setImageResource(R.drawable.os);
        WarrantyImage.setImageResource(R.drawable.warranty2);
        //-----Image-----//
        //-----Button-----//
        radioButtonPSU600W.setChecked(true);
        radioButtonchassisblack.setChecked(true);
        radioButtonMboard1.setChecked(true);
        radioButtoni5.setChecked(true);
        radioButtongtx1050.setChecked(true);
        radioButtonram8gbx1.setChecked(true);
        radioButtonSSD256GB1.setChecked(true);
        radioButtonSSDnone2.setChecked(true);
        radioButtonHardDisknone.setChecked(true);
        radioButtonBaseCooling.setChecked(true);
        radioButtonLightnone.setChecked(true);
        radioButtonWirelessLanNone.setChecked(true);
        radioButtonOSunactive.setChecked(true);
        radioButton2yearswarranty.setChecked(true);
        //-----Button-----//
        //-----Type-----//
        psutype = radioButtonPSU600W.getText().toString();
        chassistype = radioButtonchassisblack.getText().toString();
        motherboardtype = radioButtonMboard1.getText().toString();
        cputype = radioButtoni5.getText().toString();
        gtxtype = radioButtongtx1050.getText().toString();
        ramtype = radioButtonram8gbx1.getText().toString();
        firstssdtype = radioButtonSSD256GB1.getText().toString();
        secondssdtype = radioButtonSSDnone2.getText().toString();
        harddisktype = radioButtonHardDisknone.getText().toString();
        coolingsystemtype = radioButtonBaseCooling.getText().toString();
        caselighttype = radioButtonLightnone.getText().toString();
        wirelesslantype = radioButtonWirelessLanNone.getText().toString();
        ostype = radioButtonOSunactive.getText().toString();
        warrantytype = radioButton2yearswarranty.getText().toString();
        //-----Type-----//
        totalprice = defaultprice;
        TVPrice.setText(RMChange(defaultprice));
    }

    private void setListeners() {
        /////////////////////////-------RadioGroup-------/////////////////////////////////
        radioGroupPSU.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonPSU600W.isChecked()) {

                    psutype= radioButtonPSU650W.getText().toString();
                    psuprice = 139;
                    TotalPrice();


                } else if (radioButtonPSU650W.isChecked()) {

                    psutype= radioButtonPSU650W.getText().toString();
                    psuprice = 139;
                    TotalPrice();

                }
            }
        });
        radioGroupChassis.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonchassisblack.isChecked()) {

                    chassistype = radioButtonchassisblack.getText().toString();
                    chassisprice = 0;
                    TotalPrice();

                    ChassisImage.setImageResource(R.drawable.chassisblack);

                } else if (radioButtonchassisrgb.isChecked()) {

                    chassistype = radioButtonchassisrgb.getText().toString();
                    chassisprice = 39;
                    TotalPrice();


                    ChassisImage.setImageResource(R.drawable.chassisrgb);
                }
            }
        });

        radioGroupMotherBoard.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonMboard1.isChecked()) {

                    motherboardtype = radioButtonMboard1.getText().toString();
                    motherboardprice = 0;
                    TotalPrice();


                } else if (radioButtonMboard2.isChecked()) {

                    motherboardtype = radioButtonMboard2.getText().toString();
                    motherboardprice = 49;
                    TotalPrice();

                }
            }
        });

        radioGroupCPU.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtoni5.isChecked()) {

                    cputype = radioButtoni5.getText().toString();
                    cpuprice = 0;
                    TotalPrice();

                    CPUImage.setImageResource(R.drawable.i5_9th);

                } else if (radioButtoni7.isChecked()) {

                    cputype = radioButtoni7.getText().toString();
                    cpuprice = 329;
                    TotalPrice();


                    CPUImage.setImageResource(R.drawable.i7_9th);
                }
            }
        });

        radioGroupGTX.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtongtx1050.isChecked()) {

                    gtxtype = radioButtongtx1050.getText().toString();
                    gtxprice = 0;
                    TotalPrice();


                } else if (radioButtongtx1650.isChecked()) {

                    gtxtype = radioButtongtx1650.getText().toString();
                    gtxprice = 279;
                    TotalPrice();

                }
            }
        });


        radioGroupRAM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonram8gbx1.isChecked()) {

                    ramtype = radioButtonram8gbx1.getText().toString();
                    ramprice = 0;
                    TotalPrice();


                } else if (radioButtonram8gbx2.isChecked()) {

                    ramtype = radioButtonram8gbx2.getText().toString();
                    ramprice = 139;
                    TotalPrice();

                } else if (radioButtonram16gbx1.isChecked()) {

                    ramtype = radioButtonram16gbx1.getText().toString();
                    ramprice = 149;
                    TotalPrice();


                } else if (radioButtonram16gbx2.isChecked()) {

                    ramtype = radioButtonram16gbx2.getText().toString();
                    ramprice = 439;
                    TotalPrice();

                }
            }
        });



        radioGroupSSD1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonSSD256GB1.isChecked()) {

                    firstssdtype = radioButtonSSD256GB1.getText().toString();
                    firstssdprice = 0;
                    TotalPrice();


                } else if (radioButtonSSD512GB1.isChecked()) {

                    firstssdtype = radioButtonSSD512GB1.getText().toString();
                    firstssdprice = 149;
                    TotalPrice();

                } else if (radioButtonSSD1TB1.isChecked()) {

                    firstssdtype = radioButtonSSD1TB1.getText().toString();
                    firstssdprice = 599;
                    TotalPrice();

                }
            }
        });

        radioGroupSSD2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonSSDnone2.isChecked()) {

                    secondssdtype = radioButtonSSDnone2.getText().toString();
                    secondssdprice = 0;
                    TotalPrice();

                } else if (radioButtonSSD256GB2.isChecked()) {

                    secondssdtype = radioButtonSSD256GB2.getText().toString();
                    secondssdprice = 329;
                    TotalPrice();

                } else if (radioButtonSSD512GB2.isChecked()) {

                    secondssdtype = radioButtonSSD512GB2.getText().toString();
                    secondssdprice = 479;
                    TotalPrice();

                } else if (radioButtonSSD1TB2.isChecked()) {

                    secondssdtype = radioButtonSSD1TB2.getText().toString();
                    secondssdprice = 899;
                    TotalPrice();

                }
            }
        });

        radioGroupHarddisk.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonHardDisknone.isChecked()) {

                    harddisktype = radioButtonHardDisknone.getText().toString();
                    harddiskprice = 0;
                    TotalPrice();

                } else if (radioButtonHardDisk1TB.isChecked()) {

                    harddisktype = radioButtonHardDisk1TB.getText().toString();
                    harddiskprice = 229;
                    TotalPrice();

                } else if (radioButtonHardDisk2TB.isChecked()) {

                    harddisktype = radioButtonHardDisk2TB.getText().toString();
                    harddiskprice = 349;
                    TotalPrice();

                }
            }
        });
        radioGroupCoolingSystem.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonBaseCooling.isChecked()) {

                    coolingsystemtype = radioButtonBaseCooling.getText().toString();
                    coolingsystemprice = 0;
                    TotalPrice();

                    CoolingImage.setImageResource(R.drawable.basecooler);

                } else if (radioButtonPremiumCooling.isChecked()) {

                    coolingsystemtype = radioButtonBaseCooling.getText().toString();
                    coolingsystemprice = 109;
                    TotalPrice();

                    CoolingImage.setImageResource(R.drawable.premiumcooler);

                }else if (radioButtonWaterCoolingRGB.isChecked()) {

                    coolingsystemtype = radioButtonWaterCoolingRGB.getText().toString();
                    coolingsystemprice = 209;
                    TotalPrice();

                    CoolingImage.setImageResource(R.drawable.liquidcooler);

                }

            }
        });

        radioGroupCaseLight.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonLightnone.isChecked()) {

                    caselighttype = radioButtonLightnone.getText().toString();
                    caselightprice = 0;
                    TotalPrice();

                    CaseLightImage.setImageResource(R.drawable.lightnone);

                } else if (radioButtonLightLED.isChecked()) {

                    caselighttype = radioButtonLightnone.getText().toString();
                    caselightprice =99;
                    TotalPrice();

                    CaseLightImage.setImageResource(R.drawable.lightled);

                }

            }
        });
        radioGroupWirelessLan.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonWirelessLanNone.isChecked()) {

                    wirelesslantype = radioButtonWirelessLanNone.getText().toString();
                    wirelesslanprice = 0;
                    TotalPrice();

                    WirelessLanImage.setImageResource(R.drawable.wirelesslannone);

                } else if (radioButtonWirelessLanAC56.isChecked()) {

                    wirelesslantype = radioButtonWirelessLanAC56.getText().toString();
                    wirelesslanprice = 299;
                    TotalPrice();

                    WirelessLanImage.setImageResource(R.drawable.wirelesslanac56);

                }else if (radioButtonWirelessLanAC68.isChecked()) {

                    wirelesslantype = radioButtonWirelessLanAC68.getText().toString();
                    wirelesslanprice = 419;
                    TotalPrice();

                    WirelessLanImage.setImageResource(R.drawable.wirelesslanac68);

                }else if (radioButtonWirelessLanAC88.isChecked()) {

                    wirelesslantype = radioButtonWirelessLanAC88.getText().toString();
                    wirelesslanprice = 509;
                    TotalPrice();

                    WirelessLanImage.setImageResource(R.drawable.wirelesslanac88);

                }

            }
        });

        radioGroupOS.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtonOSunactive.isChecked()) {

                    ostype = radioButtonOSunactive.getText().toString();
                    osprice = 0;
                    TotalPrice();

                } else if (radioButtonOSwindows10home.isChecked()) {

                    ostype = radioButtonOSwindows10home.getText().toString();
                    osprice = 479;
                    TotalPrice();

                } else if (radioButtonOSwindows10professional.isChecked()) {

                    ostype = radioButtonOSwindows10professional.getText().toString();
                    osprice = 609;
                    TotalPrice();

                }
            }
        });

        radioGroupWarranty.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButton2yearswarranty.isChecked()) {

                    warrantytype = radioButton2yearswarranty.getText().toString();
                    warrantyprice = 0;
                    WarrantyImage.setImageResource(R.drawable.warranty2);
                    TotalPrice();

                } else if (radioButton3yearswarranty.isChecked()) {

                    warrantytype = radioButton3yearswarranty.getText().toString();
                    warrantyprice = 299;
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
                Intent i = new Intent(DesktopBuild.this, Ordering_PC.class);
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
    }

    private void TotalPrice() {
        totalprice = defaultprice
                + psuprice
                + chassisprice
                + motherboardprice
                + gtxprice
                + cpuprice
                + ramprice
                + firstssdprice
                + secondssdprice
                + harddiskprice
                + coolingsystemprice
                + caselightprice
                + wirelesslanprice
                + osprice
                + warrantyprice;
        TVPrice.setText(RMChange(totalprice));

    }

    private String RMChange(int RM) {
        String totalprice = "RM " + RM + ".00";
        return totalprice;
    }

    private String CutRM(String x) {

        if (x.lastIndexOf("(") != -1) {
            int y = x.lastIndexOf("(");
            x = x.substring(0, y);
        }

        return x;
    }

    private void setResult() {
        PC.setTotalPrice(totalprice);
        PC.setTypePowerSupply(psutype);
        PC.setTypeChassis(chassistype);
        PC.setTypeMotherBoard(motherboardtype);
        PC.setTypeCPU(CutRM(CutRM(cputype)));
        PC.setTypeGraphicscard(CutRM(gtxtype));
        PC.setTypeRAM(CutRM(ramtype));
        PC.setTypeFirstSlotSSD(CutRM(firstssdtype));
        PC.setTypeSecondSlotSSD(CutRM(secondssdtype));
        PC.setTypeHardDrive(CutRM(harddisktype));
        PC.setTypeCoolingSystem(coolingsystemtype);
        PC.setTypeCasesLighting(caselighttype);
        PC.setTypeWirelessLan(wirelesslantype);
        PC.setTypeOS(CutRM(ostype));
        PC.setTypeWarrantyPackage(CutRM(warrantytype));

    }

}