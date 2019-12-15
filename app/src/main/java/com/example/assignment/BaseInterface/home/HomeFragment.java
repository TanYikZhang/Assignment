package com.example.assignment.BaseInterface.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.assignment.Database.DBHelper;
import com.example.assignment.FragmentAdmin.AdminFrag;
import com.example.assignment.Model.Customer;
import com.example.assignment.Model.Global;
import com.example.assignment.PCBuild.DesktopBuild;
import com.example.assignment.PCBuild.OrderHistory;
import com.example.assignment.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private Button Build, Logout, Admin, ChangePassword, History;
    private ArrayList<Customer> customerlist = new ArrayList<Customer>();
    private com.example.assignment.Database.DBHelper DBHelper;
    private String fullname;
    private TextView tvfullname;
    private View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.activity_home_page, container, false);
        findViews();
        setListeners();
        setUpDatabase();
        findName();
        return root;
    }


    private void findViews() {
        Build = root.findViewById(R.id.btn_build);
        Admin = root.findViewById(R.id.btn_admin);
        History = root.findViewById(R.id.btn_history);
        tvfullname = root.findViewById(R.id.homefullname);
    }

    private void setListeners() {
        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AdminFrag.class);
                startActivity(i);
            }
        });

        Build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DesktopBuild.class);
                startActivity(i);

            }
        });

        History.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), OrderHistory.class);
                startActivity(i);

            }
        });
    }

    private void findName() {
        Global global = (Global) getActivity().getApplicationContext();
        int id = global.getId();
        for (int i = 0; i < customerlist.size(); i++) {
            if (id == customerlist.get(i).getId()) {
                tvfullname.setText(customerlist.get(i).getFullname());
                if (customerlist.get(i).getAccessControl() != 1) {
                    Admin.setVisibility(View.GONE);
                }
            }
        }
    }


    private void setUpDatabase() {
        DBHelper = new DBHelper(getActivity());
        customerlist = DBHelper.getAllPerson();
    }
}

