package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.PCBuild.DesktopBuild;
import com.example.assignment.PCBuild.NotebookBuild;

public class ChoosePage extends AppCompatActivity {
    Button Desktop,Notebook;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosepage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        findViews();
        setListeners();
    }

    private void findViews(){
        Desktop = findViewById(R.id.btn_desktop);
        Notebook = findViewById(R.id.btn_notebook);
    }

    private void setListeners(){
        Desktop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChoosePage.this, DesktopBuild.class);
                startActivity(i);

            }
        });

        Notebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ChoosePage.this, NotebookBuild.class);
                startActivity(i);

            }
        });
    }
}
