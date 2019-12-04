package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Customer;
import com.example.assignment.Model.Global;

import java.security.MessageDigest;
import java.util.ArrayList;

public class LoginPage extends AppCompatActivity {
    private DBHelper DBHelper;
    private EditText EditUsername, EditPassword;
    private Button btnLogin;
    private TextView Register ;
    private String username = "", password=setSHA256("12345678");
    private ArrayList<Customer>  customerlist =new ArrayList<Customer>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        findViews();
        setUpDatabase();
        setListener();
    }
    @Override
    protected void onResume() {
        super.onResume();
        customerlist = DBHelper.getAllCustomer();
    }

    private void findViews(){
        EditUsername = findViewById(R.id.edit_username);
        EditPassword = findViewById(R.id.edit_password);
        btnLogin = findViewById(R.id.btn_login);
        Register = findViewById(R.id.tv_register);

    }
    private void setListener(){
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = EditUsername.getText().toString();
                String inputPassword = setSHA256(EditPassword.getText().toString());

                int login=0;
                for (int i=0; i<customerlist.size(); i++){
                    if (inputUsername.equals(customerlist.get(i).getEmail()) && inputPassword.equals(customerlist.get(i).getPassword())) {
                        Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_LONG).show();
                        System.out.println("//////////////////////////////////////////////");
                        Intent x = new Intent(LoginPage.this, HomePage.class);
                        Global global = (Global) getApplicationContext();
                        global.setId(customerlist.get(i).getId());
                        startActivity(x);
                        finish();
                        login=1;
                    } else if (login!=1){
                        Toast.makeText(LoginPage.this, "Login Fail", Toast.LENGTH_LONG).show();
                    }
                }



            }

        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginPage.this, RegisterPage.class);
                startActivity(i);
                finish();
            }


        });
    }
    private void setUpDatabase(){
        DBHelper = new DBHelper(this);
        customerlist = DBHelper.getAllCustomer();
    }
    private String setSHA256(String x){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(x.getBytes());

            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
}
