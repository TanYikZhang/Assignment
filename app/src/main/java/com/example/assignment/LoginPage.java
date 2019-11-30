package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.MessageDigest;

public class LoginPage extends AppCompatActivity {
    private EditText EditUsername, EditPassword;
    private Button btnLogin;
    private TextView Register ;
    private String username = "", password=setSHA256("12345678");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        findViews();
        setListener();
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

                if (inputUsername.equals(username) && inputPassword.equals(password)) {
                    Toast.makeText(LoginPage.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginPage.this, HomePage.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginPage.this, "Login Fail", Toast.LENGTH_LONG).show();
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
