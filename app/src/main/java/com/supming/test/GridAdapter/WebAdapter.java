package com.supming.test.GridAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.supming.test.R;

import java.util.ArrayList;

public class WebAdapter extends BaseAdapter {

    Context c;
    LayoutInflater inflater;
    private ArrayList<String> image =new ArrayList<>();

    public WebAdapter(Context c, ArrayList<String> image) {
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

        WebView Mimage= new WebView(c);
        Mimage.setLayoutParams(new GridView.LayoutParams(1080,600));
        Mimage.setPadding(1,1,1,1);
        Mimage.loadUrl(image.get(position));
        Mimage.layout(256,256,512,512);
        Mimage.getSettings().setJavaScriptEnabled(true);
        Mimage.setWebViewClient(new WebViewClient());
        return Mimage;
    }

}
