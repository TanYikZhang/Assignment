package com.example.assignment.PCBuild;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.assignment.R;

public class NotebookBuild extends AppCompatActivity {
    private RadioGroup radioGroupCPU,radioGroupGTX;
    private RadioButton radioButtoni5,radioButtoni7,radioButtongtx1050,radioButtongtx1650;
    private ImageView CPUImage,GTXImage;
    private int cpuprice=0,gtxprice=0;
    private String cputype="",gtxtype="";


    private int defaultprice=2000;
    private int totalprice=0;
    private TextView TVPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

        CPUImage=findViewById(R.id.imagecpu);
        GTXImage=findViewById(R.id.imagegtx);
        TVPrice=findViewById(R.id.price);
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
                    TVPrice.setText(RMChange(totalprice));

                }else if (radioButtoni7.isChecked()){

                    cputype=radioButtoni5.getText().toString();
                    cpuprice = 330;
                    TotalPrice();

                    CPUImage.setImageResource(R.drawable.i7_9th);
                    TVPrice.setText(RMChange(totalprice));
                }
            }
        });

        radioGroupGTX.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButtongtx1050.isChecked()){

                    gtxtype=radioButtoni5.getText().toString();
                    gtxprice=0;
                    TotalPrice();

                    TVPrice.setText(RMChange(totalprice));

                }else if (radioButtongtx1650.isChecked()){

                    gtxtype=radioButtoni5.getText().toString();
                    gtxprice=279;
                    TotalPrice();

                    TVPrice.setText(RMChange(totalprice));
                }
            }
        });
        /////////////////////////-------RadioGroup-------/////////////////////////////////

    }
    private void TotalPrice(){
        totalprice=defaultprice
                +gtxprice
                +cpuprice;
    }

    private void setDefault(){
        //-----Image-----//
        CPUImage.setImageResource(R.drawable.i5_9th);
        GTXImage.setImageResource(R.drawable.gtx);
        //-----Image-----//
        //-----Button-----//
        radioButtoni5.setChecked(true);
        radioButtongtx1050.setChecked(true);


        //-----Button-----//
        TVPrice.setText(RMChange(defaultprice));
    }

    private String RMChange(int RM){
        String totalprice="RM "+RM+".00";
        return totalprice;
    }

    private void printResult(){

    }

}


