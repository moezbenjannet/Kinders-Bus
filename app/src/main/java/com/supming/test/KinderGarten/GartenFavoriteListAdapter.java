package com.supming.test.KinderGarten;

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

import com.supming.test.GartenActivity;
import com.supming.test.R;

import java.util.ArrayList;

/**
 * Created by Omar Bouhamed on 15/11/2017.
 */

public class GartenFavoriteListAdapter extends BaseAdapter {

    /**
     * Create a new {@link GartenFavoriteListAdapter} object.
     *
     * @param context is the current context (i.e. Activity) that the adapter is being created in.
     * @param Gartens is the list of {@link Gartens}s to be displayed.

     */

    Context c;
    ArrayList<Gartens> gartens;
    LayoutInflater inflater;
    DatabaseHelper databaseHelper;

    public GartenFavoriteListAdapter(Context c, ArrayList<Gartens> gartens) {
        this.c=c ;
        this.gartens=gartens;
    }
    @Override
    public int getCount() {
        return gartens.size();
    }
    @Override
    public Object getItem(int position) {
        return gartens.get(position);
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
            convertView=inflater.inflate(R.layout.list_item,parent,false);
        }
        databaseHelper = new DatabaseHelper(c);
        TextView GName= (TextView) convertView.findViewById(R.id.Garten_name_text_view);
        TextView GCity= (TextView) convertView.findViewById(R.id.city_text_view);
        TextView GContact= (TextView) convertView.findViewById(R.id.contact_text_view);
        ImageView GLogo= (ImageView) convertView.findViewById(R.id.logo_garten);
        final ImageView fav= (ImageView) convertView.findViewById(R.id.favorite);
        final String name=gartens.get(position).getName();
        final String city=gartens.get(position).getCity();
        final long contact=gartens.get(position).getContact();
        final byte[] logo=gartens.get(position).getImage();
        final  int id = gartens.get(position).getId();
        GName.setText(name);
        GCity.setText("City : " + city);
        GContact.setText("Contact : " + contact);
       // GLogo.setImageResource(logo);
        Bitmap bitmap = BitmapFactory.decodeByteArray(logo , 0 , logo.length);
        GLogo.setImageBitmap(bitmap);
        if (databaseHelper.updatefavorite(id) == 1) {
            fav.setImageResource(R.drawable.ic_favorite_black_selected);
        }
        else gartens.remove(position);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (databaseHelper.updatefavorite(id)== 1) {
                    databaseHelper.updateData(id,0);
                    if(databaseHelper.updatefavorite(id)==0)
                    {fav.setImageResource(R.drawable.ic_favorite_border_black);
                        gartens.get(position).setFavorite(0);
                        gartens.remove(position);
                    }
                    else
                        Toast.makeText(c,"Data not Updated",Toast.LENGTH_LONG).show();
                }
                else {
                    databaseHelper.updateData(id,1);
                    if (databaseHelper.updatefavorite(id)==1)
                    {fav.setImageResource(R.drawable.ic_favorite_black_selected);
                        gartens.get(position).setFavorite(1);}
                    else
                        Toast.makeText(c, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }

            }
        });

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(c, GartenActivity.class);
 		        intent.putExtra("name",name);
                intent.putExtra("logo",logo);
                intent.putExtra("id",id);
                c.startActivity(intent);
            }
        });
        return convertView;
    }

    public void setFilter(ArrayList<Gartens> garten) {
        gartens = new ArrayList<>();
        gartens.addAll(garten);
        notifyDataSetChanged();
    }

}
