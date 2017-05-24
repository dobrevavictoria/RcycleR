package com.example.pc.rcycler;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends PagerAdapter {


    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private String[] stringArray;
    private Context context;
    public MyAdapter(Context context, ArrayList<Integer> images, String[] stringArray) {
        this.context = context;
        this.images=images;
        this.stringArray=stringArray;
// inflater = LayoutInflater.from(context);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        inflater = LayoutInflater.from(context);
        ViewGroup myImageLayout = (ViewGroup) inflater.inflate(R.layout.slide,view,false);
        ImageView myImage = (ImageView) myImageLayout.findViewById(R.id.image);
        myImage.setImageResource(images.get(position));
        TextView txt=(TextView) myImageLayout.findViewById(R.id.image_text);
        txt.setText(stringArray[position]);
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


}