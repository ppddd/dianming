package com.example.boss.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boss.myapplication.R;

import java.util.List;

/**
 * Created by BOSS on 2016/10/31.
 */
public class StudentAdapter extends BaseAdapter {
    private List<student> students;
    private Context context;

    public StudentAdapter(Context context, List<student> students) {
        super();
        this.students = students;
        this.context = context;
    }

    @Override
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

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        }
        ImageView imageView= (ImageView) view.findViewById(R.id.image);
        TextView tvName = (TextView) view.findViewById(R.id.name);
        TextView tvID=(TextView)view.findViewById(R.id.id);


        tvName.setText("姓名  " + students.get(i).getName());
        tvID.setText("学号  " + students.get(i).getId());

        return view;
    }
}
