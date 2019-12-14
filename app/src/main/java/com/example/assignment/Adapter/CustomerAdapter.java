package com.example.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment.Model.Customer;
import com.example.assignment.R;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<Customer> {
    private ArrayList<Customer> dataSet;
    private Context context;

    private static class ViewHolder {
        TextView tvcusid;
        TextView tvcusemail;
        TextView tvcusname;
        TextView tvphonenumber;
    }

    public CustomerAdapter(ArrayList<Customer> data, Context context) {
        super(context, R.layout.customer_item, data);
        this.dataSet = data;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Customer customer = getItem(position);

        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.customer_item, parent, false);

            holder.tvcusid = convertView.findViewById(R.id.setcusid);
            holder.tvcusemail = convertView.findViewById(R.id.setcusemail);
            holder.tvcusname = convertView.findViewById(R.id.setcusname);
            holder.tvphonenumber = convertView.findViewById(R.id.setcusphonenumber);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }

        holder.tvcusid.setText(Integer.toString(customer.getId()));
        holder.tvcusemail.setText(customer.getEmail());
        holder.tvcusname.setText(customer.getFullname());
        holder.tvphonenumber.setText(customer.getPhoneNumber());

        return convertView;
    }
}
