package com.example.boss.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BOSS on 2016/11/10.
 */
public class spinnerAdapter extends BaseAdapter {
    private List<student> students;
    private Context context;
    private Spinner spinner;
    public spinnerAdapter(Context context, List<student> students) {
        super();
        this.students = students;
        this.context = context;

    }
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public View getView(int i, View view, ViewGroup viewGroup)
    {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.dm_list, viewGroup, false);
        }
        TextView tvName = (TextView) view.findViewById(R.id.dm_name);
        TextView tvID=(TextView)view.findViewById(R.id.dm_id);
        Spinner spinner=(Spinner)view.findViewById(R.id.spinner);
        tvName.setText("姓名  " + students.get(i).getName());
        tvID.setText("学号  " + students.get(i).getId());
        return view;
    }
}

