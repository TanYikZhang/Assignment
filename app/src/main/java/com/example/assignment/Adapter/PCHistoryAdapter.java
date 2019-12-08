package com.example.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment.Model.PC;
import com.example.assignment.R;

import java.util.ArrayList;

public class PCHistoryAdapter extends ArrayAdapter<PC>{
    private ArrayList<PC> dataSet;
    private Context context;

    private static class ViewHolder{
        TextView tvid;
        TextView tvdate;
        TextView tvprice;
        TextView tvprocess;
    }

    public PCHistoryAdapter(ArrayList<PC> data, Context context){
        super(context,R.layout.pc_item,data);
        this.dataSet = data;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PC PC = getItem(position);

        ViewHolder holder;

        if (convertView== null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.pc_item,parent,false);

            holder.tvid = convertView.findViewById(R.id.setpcid);
            holder.tvdate = convertView.findViewById(R.id.setpcdate);
            holder.tvprice = convertView.findViewById(R.id.setpcprice);
            holder.tvprocess = convertView.findViewById(R.id.setpcprocess);


            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();

        }
        holder.tvid.setText(Integer.toString(PC.getId()));
        holder.tvdate.setText(PC.getDateBuild());
        holder.tvprice.setText("RM "+Integer.toString(PC.getTotalPrice()));
        holder.tvprocess.setText(PC.getProcess());

        return convertView;
    }
}
