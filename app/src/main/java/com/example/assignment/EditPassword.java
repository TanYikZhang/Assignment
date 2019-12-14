package com.example.assignment;

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

public class EditPassword extends AppCompatActivity {
    private EditText ChgCurrentPassword, ChgPassword, ChgConfirmPassword;
    private TextView WarningCurrentPassword, WarningPassword, WarningConfirmPassword;
    private Button btnConfirm, btnCancel;
    private boolean TOF = true;
    private String currentpassword, DBcurrentpassword, password, confirmpassword;
    private com.example.assignment.Database.DBHelper DBHelper;
    private ArrayList<Customer> customerlist = new ArrayList<Customer>();
    private Customer customer;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_password);
        findViews();
        setUpDatabase();
        setListeners();

    }

    private void findViews() {
        ChgCurrentPassword = findViewById(R.id.edit_changecurrentpassword);
        ChgPassword = findViewById(R.id.edit_changepassword);
        ChgConfirmPassword = findViewById(R.id.edit_changeconfirmpassword);

        WarningCurrentPassword = findViewById(R.id.warningchangecurrentpassword);
        WarningPassword = findViewById(R.id.warningchangepassword);
        WarningConfirmPassword = findViewById(R.id.warningchangeconfirmpassword);

        btnConfirm = findViewById(R.id.btn_changeconfirm);
        btnCancel = findViewById(R.id.btn_changecancel);
    }

    private void setListeners() {
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate();
                if (TOF) {
                    Toast.makeText(EditPassword.this, "Change Successfully", Toast.LENGTH_LONG).show();
                    //add to database//
                    DBHelper dbHelper = new DBHelper(EditPassword.this);
                    customer.setPassword(setSHA256(password));
                    dbHelper.updateCustomer(customer);
                    EditPassword.this.finish();

                    //add to database//
                } else {
                    Toast.makeText(EditPassword.this, "Register Fail", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }


        });
    }

    private void Validate() {
        TOF = true;
        CheckCurrentPassword();
        CheckPassword();
        CheckConfirmPassword();
    }

    private void setUpDatabase() {
        DBHelper = new DBHelper(this);
        customerlist = DBHelper.getAllCustomer();
    }

    private void CheckCurrentPassword() {
        currentpassword = ChgCurrentPassword.getText().toString();

        Global global = (Global) getApplicationContext();
        int id = global.getId();
        customerlist = DBHelper.getAllPerson();
        for (int i = 0; i < customerlist.size(); i++) {
            System.out.println(customerlist.get(i).getId());
            if (id == customerlist.get(i).getId()) {
                DBcurrentpassword = customerlist.get(i).getPassword();
                customer = customerlist.get(i);
            }
        }
        if (currentpassword.equals("")) {
            WarningCurrentPassword.setText("Current Password is Required");
            TOF = false;
        } else if (!setSHA256(currentpassword).equals(DBcurrentpassword)) {
            WarningCurrentPassword.setText("Current Password Error");
            TOF = false;
        } else {
            WarningCurrentPassword.setText("");

        }
    }


    private void CheckPassword() {
        password = ChgPassword.getText().toString();

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
        password = ChgPassword.getText().toString();
        confirmpassword = ChgConfirmPassword.getText().toString();

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
