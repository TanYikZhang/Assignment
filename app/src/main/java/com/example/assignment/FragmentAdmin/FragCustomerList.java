package com.example.assignment.FragmentAdmin;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.assignment.Adapter.CustomerAdapter;
import com.example.assignment.Database.DBHelper;
import com.example.assignment.Model.Customer;
import com.example.assignment.R;

import java.util.ArrayList;

public class FragCustomerList extends Fragment {
    private View mView;
    private ListView ListViewData;
    private ArrayList<Customer> CustomerList = new ArrayList<>();
    private CustomerAdapter adapter;
    private DBHelper dbHelper;
    private EditText Search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.activity_customer_list, null);
        findViews();
        setListener();
        setUpDatabase();

        if (CustomerList.size() > 0) {
            setUpAdapter();
        }
        return mView;

    }

    private void findViews() {
        ListViewData = mView.findViewById(R.id.lvcustomer);
        Search = mView.findViewById(R.id.edit_searchcus);
    }

    private void setListener() {
        Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = Search.getText().toString();
                CustomerList.clear();
                adapter.clear();
                if (text.isEmpty()) {
                    setUpAdapter();
                } else if (!text.isEmpty()) {
                    CustomerList = dbHelper.getCustomer(text);
                    adapter = new CustomerAdapter(CustomerList, getActivity());
                    ListViewData.setAdapter(adapter);
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void setUpDatabase() {
        dbHelper = new DBHelper(getActivity());
        CustomerList = dbHelper.getAllCustomer();
    }

    private void setUpAdapter() {
        CustomerList = dbHelper.getAllCustomer();
        System.out.println(CustomerList.get(0).getFullname());
        adapter = new CustomerAdapter(CustomerList, getActivity());
        ListViewData.setAdapter(adapter);
    }
}
