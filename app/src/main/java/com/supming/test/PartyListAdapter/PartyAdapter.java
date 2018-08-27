package com.supming.test.PartyListAdapter;


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

import com.supming.test.EventActivity;
import com.supming.test.EventsListAdapter.Events;
import com.supming.test.GartenActivity;
import com.supming.test.R;

import java.util.ArrayList;

public class PartyAdapter extends BaseAdapter {

    Context c;
    ArrayList<Party> party ;
    LayoutInflater inflater;

    public PartyAdapter(Context c, ArrayList<Party> party) {
        this.c=c ;
        this.party=party;
    }
    @Override
    public int getCount() {
        return party.size();
    }
    @Override
    public Object getItem(int position) {
        return party.get(position);
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
            convertView=inflater.inflate(R.layout.list_party,parent,false);
        }
        TextView Ptitle= (TextView) convertView.findViewById(R.id.partydate);
        ImageView Pimage= (ImageView) convertView.findViewById(R.id.partyimage);
        final String title=party.get(position).getpartytitle();
        final int jour=party.get(position).getpartyjour();
        final int mois=party.get(position).getpartymois();
        final int annee=party.get(position).getpartyannee();
        final byte[] image=party.get(position).getpartyimage();
        Ptitle.setText(title + " ["+jour+"/"+mois+"/"+annee+"] :");
        Bitmap bitmap = BitmapFactory.decodeByteArray(image , 0 , image.length);
        Pimage.setImageBitmap(bitmap);
       /* convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, EventActivity.class);
                intent.putExtra("title",title);
                c.startActivity(intent);
            }
        });*/
        return convertView;
    }
}
