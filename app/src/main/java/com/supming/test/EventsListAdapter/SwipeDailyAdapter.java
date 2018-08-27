package com.supming.test.EventsListAdapter;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;
import com.supming.test.R;

import java.util.ArrayList;

public class SwipeDailyAdapter extends PagerAdapter {

    Context c;
    ArrayList<String> image ;
    LayoutInflater layoutInflater;

    public SwipeDailyAdapter(Context c, ArrayList<String> image) {
        this.c=c ;
        this.image=image;
    }

    @Override
    public int getCount() {
        return image.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View item_view = layoutInflater.inflate(R.layout.swipe_daily_layout,container,false);
        ImageView imageView = (ImageView) item_view.findViewById(R.id.garten_dailyphoto);

        Picasso.with(c).load(image.get(position)).into(imageView);
        //imageView.setImageResource(image.get(position));

        container.addView(item_view);

        return item_view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }

}
