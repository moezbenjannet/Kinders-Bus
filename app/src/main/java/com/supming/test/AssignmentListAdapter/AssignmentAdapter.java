package com.supming.test.AssignmentListAdapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.supming.test.PartyListAdapter.Party;
import com.supming.test.R;

import java.util.ArrayList;

public class AssignmentAdapter extends BaseAdapter {

    Context c;
    ArrayList<Assignment> assignments ;
    LayoutInflater inflater;

    public AssignmentAdapter(Context c, ArrayList<Assignment> assignments) {
        this.c=c ;
        this.assignments=assignments;
    }
    @Override
    public int getCount() {
        return assignments.size();
    }
    @Override
    public Object getItem(int position) {
        return assignments.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.list_assignment,parent,false);
        }
        TextView Aclasse= (TextView) convertView.findViewById(R.id.assignment_classe);
        TextView teachername= (TextView) convertView.findViewById(R.id.teacher_name);
        TextView teachernum= (TextView) convertView.findViewById(R.id.teacher_num);
        TextView children= (TextView) convertView.findViewById(R.id.children_name);
        final String Classe=assignments.get(position).getclasse();
        final String Tname=assignments.get(position).getteachername();
        final String Tnum=assignments.get(position).getteachernum();
        final String Cmane=assignments.get(position).getchildren();
        Aclasse.setText(Classe);
        teachername.setText(Tname);
        teachernum.setText(Tnum);
        children.setText(Cmane);
        return convertView;
    }
}
