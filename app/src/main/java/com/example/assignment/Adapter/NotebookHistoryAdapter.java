package com.example.assignment.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.assignment.Model.Notebook;
import com.example.assignment.R;

import java.util.ArrayList;

public class NotebookHistoryAdapter extends ArrayAdapter<Notebook>{
    private ArrayList<Notebook> dataSet;
    private Context context;

    private static class ViewHolder{
        TextView tvtype;
        TextView tvdate;
        TextView tvprice;
        TextView tvprocess;
    }

    public NotebookHistoryAdapter(ArrayList<Notebook> data,Context context){
        super(context,R.layout.pc_item,data);
        this.dataSet = data;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Notebook notebook = getItem(position);

        ViewHolder holder;

        if (convertView== null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.pc_item,parent,false);

            holder.tvtype = convertView.findViewById(R.id.settype);
            holder.tvdate = convertView.findViewById(R.id.setdate);
            holder.tvprice = convertView.findViewById(R.id.setprice);
            holder.tvprocess = convertView.findViewById(R.id.setprocess);


            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();

        }
        holder.tvtype.setText(notebook.getType());
        holder.tvdate.setText(notebook.getDateBuild());
        holder.tvprice.setText(notebook.getTotalPrice());
        holder.tvprocess.setText(notebook.getProcess());

        return convertView;
    }
}
