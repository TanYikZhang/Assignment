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

import java.security.MessageDigest;
import java.util.ArrayList;


public class RegisterPage extends AppCompatActivity {
    private EditText RegEmail, RegFullName, RegPhoneNumber, RegPassword, RegConfirmPassword;
    private TextView WarningEmail, WarningFullName, WarningPhoneNumber, WarningPassword, WarningConfirmPassword;
    private Button btnRegister, btnCancel;
    private boolean TOF = true;
    private String fullname, email, number, password, confirmpassword;
    private DBHelper DBHelper;
    private ArrayList<Customer> customerlist = new ArrayList<Customer>();


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registerpage);
        findViews();
        setUpDatabase();
        setListeners();

    }

    private void findViews() {
        RegEmail = findViewById(R.id.edit_signupemail);
        RegFullName = findViewById(R.id.edit_signupfullname);
        RegPhoneNumber = findViewById(R.id.edit_signupphone);
        RegPassword = findViewById(R.id.edit_signuppassword);
        RegConfirmPassword = findViewById(R.id.edit_signupconfirmpassword);

        WarningEmail = findViewById(R.id.warningemail);
        WarningFullName = findViewById(R.id.warningfullname);
        WarningPhoneNumber = findViewById(R.id.warningphonenumber);
        WarningPassword = findViewById(R.id.warningpassword);
        WarningConfirmPassword = findViewById(R.id.warningconfirmpassword);

        btnRegister = findViewById(R.id.btn_register);
        btnCancel = findViewById(R.id.btn_cancel);
    }

    private void setListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
                if (TOF) {
                    Toast.makeText(RegisterPage.this, "Register Successfully", Toast.LENGTH_LONG).show();

                    String password = setSHA256(RegPassword.getText().toString());
                    //add to database//
                    DBHelper dbHelper = new DBHelper(RegisterPage.this);
                    Customer customer = new Customer();
                    customer.setEmail(RegEmail.getText().toString());
                    customer.setFullname(RegFullName.getText().toString());
                    customer.setPhoneNumber(RegPhoneNumber.getText().toString());
                    customer.setPassword(password);
                    dbHelper.insertCustomer(customer);
                    RegisterPage.this.finish();

                    //add to database//
                } else {
                    Toast.makeText(RegisterPage.this, "Register Fail", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterPage.this, LoginPage.class);
                startActivity(i);
                finish();
            }


        });
    }

    private void Validate() {
        TOF = true;
        CheckFullname();
        CheckEmail();
        CheckPhoneNumber();
        CheckPassword();
        CheckConfirmPassword();
    }

    private void setUpDatabase() {
        DBHelper = new DBHelper(this);
        customerlist = DBHelper.getAllCustomer();
    }

    private void CheckFullname() {
        fullname = RegFullName.getText().toString();
        if (fullname.equals("")) {
            WarningFullName.setText("Full Name is Required");
            TOF = false;
        } else {
            WarningFullName.setText("");
        }
    }

    private void CheckEmail() {
        email = RegEmail.getText().toString();
        if (email.equals("")) {
            WarningEmail.setText("Email is Required");
            TOF = false;
        } else if (!email.contains("@") || !email.contains(".")) {
            WarningEmail.setText("Email must includes @ and .");
            TOF = false;
        } else if (email.indexOf("@") != email.lastIndexOf("@")) {
            WarningEmail.setText("Email can includes 1 @ only");
            TOF = false;
        } else if (email.indexOf(".") == 0 || email.lastIndexOf(".") == email.length() - 1) {
            WarningEmail.setText("The . must not be first/last position");
            TOF = false;
        } else if (email.indexOf("@") == 0 || email.lastIndexOf("@") == email.length() - 1) {
            WarningEmail.setText("The @ must not be first/last position");
            TOF = false;
        } else if (email.contains("@.") || email.contains(".@")) {
            WarningEmail.setText("The @ and . cannot be together");
            TOF = false;
        } else if (email != "") {
            /////////Database//////////
            for (int i = 0; i < customerlist.size(); i++) {
                if (email.equals(customerlist.get(i).getEmail())) {
                    WarningEmail.setText("Email already exist");
                    TOF = false;
                }
            }
            /////////Database//////////
        } else {
            WarningEmail.setText("");
        }

    }

    private void CheckPhoneNumber() {
        number = RegPhoneNumber.getText().toString();

        if (number == "") {
            WarningPhoneNumber.setText("Phone Number is Required");
            TOF = false;
        } else if (number.indexOf("0") != 0) {
            WarningPhoneNumber.setText("Invalid Phone Number");
            TOF = false;
        } else if (number.length() < 9) {
            WarningPhoneNumber.setText("Invalid Phone Number");
            TOF = false;
        } else {
            WarningPhoneNumber.setText("");
        }
    }

    private void CheckPassword() {
        password = RegPassword.getText().toString();

        if (password.equals("")) {
            WarningPassword.setText("Password is Required");
            TOF = false;
        } else if (password.length() < 6 || password.length() > 12) {
            WarningPassword.setText("Password must between 6-12 characters");
            TOF = false;
        } else {
            WarningPassword.setText("");
        }

    }

    private void CheckConfirmPassword() {
        password = RegPassword.getText().toString();
        confirmpassword = RegConfirmPassword.getText().toString();

        if (confirmpassword.equals("")) {
            WarningConfirmPassword.setText("Confirm Password is Required");
            TOF = false;
        } else if (!password.equals(confirmpassword)) {
            WarningConfirmPassword.setText("Password does not match");
            TOF = false;
        } else {
            WarningConfirmPassword.setText("");
        }
    }

    private String setSHA256(String x) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(x.getBytes());

            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
