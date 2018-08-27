package com.supming.test.EventsListAdapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.supming.test.EventActivity;
import com.supming.test.GartenActivity;
import com.supming.test.KinderGarten.Gartens;
import com.supming.test.R;

import java.util.ArrayList;

public class EventsAdapter extends BaseAdapter {

    Context c;
    ArrayList<Events> events ;
    LayoutInflater inflater;
    private int Gid;

    public EventsAdapter(Context c, ArrayList<Events> events , int Gid) {
        this.c=c ;
        this.events=events;
        this.Gid=Gid;
    }
    @Override
    public int getCount() {
        return events.size();
    }
    @Override
    public Object getItem(int position) {
        return events.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(inflater==null)
        {
            inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.list_events,parent,false);
        }
        TextView Etitle= (TextView) convertView.findViewById(R.id.event_title);
        TextView Etimeplace= (TextView) convertView.findViewById(R.id.event_time_place);
        TextView Edate= (TextView) convertView.findViewById(R.id.event_date);
        ImageView Eimage= (ImageView) convertView.findViewById(R.id.event_image);
        final int id = events.get(position).geteventid();
        final String title=events.get(position).geteventtitle();
        final int jour=events.get(position).geteventjour();
        final int mois=events.get(position).geteventmois();
        final int annee=events.get(position).geteventannee();
        final int minute=events.get(position).geteventminute();
        final int heure=events.get(position).geteventheure();
        final String place=events.get(position).geteventplace();
        final byte[] image=events.get(position).geteventsimage();
        Etitle.setText(title);
        Etimeplace.setText("at " + heure + ":" + minute + " in " + place);
        Edate.setText(jour+"/"+mois+"/"+annee);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image , 0 , image.length);
        Eimage.setImageBitmap(bitmap);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, EventActivity.class);
                intent.putExtra("position",position);
                intent.putExtra("id",Gid);
                intent.putExtra("Eid",id);
                c.startActivity(intent);
            }
        });
        return convertView;
    }
}
