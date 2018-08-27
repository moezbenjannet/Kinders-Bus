package com.supming.test.GridAdapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.supming.test.EventActivity;
import com.supming.test.EventsListAdapter.Events;
import com.supming.test.GartenActivity;
import com.supming.test.R;

import java.util.ArrayList;

public class MediaAdapter extends BaseAdapter {

    Context c;
    LayoutInflater inflater;
    private ArrayList<String> image;

    public MediaAdapter(Context c, ArrayList<String> image) {
        this.c=c ;
        this.image=image;
    }
    @Override
    public int getCount() {
        return image.size();
    }
    @Override
    public Object getItem(int position) {
        return image.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ImageView Mimage= new  ImageView(c);
        Mimage.setLayoutParams(new GridView.LayoutParams(300,300));
        Mimage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Mimage.setPadding(1,1,1,1);
        //final int image=media.get(position).getmediaimage();
       // Bitmap bitmap = BitmapFactory.decodeByteArray(image , 0 , image.length);
       // Mimage.setImageBitmap(bitmap);
        //Mimage.setImageResource(image.get(position));
        Picasso.with(c).load(image.get(position)).into(Mimage);
        return Mimage;
    }

}
