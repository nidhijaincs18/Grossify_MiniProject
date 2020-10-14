package com.example.grossify;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class SliderAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;
    private View view;
    private Object object;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.varities,
            R.drawable.time,
            R.drawable.money,
            R.drawable.delivery
    };

    public String[] slide_headings = {
            "Discover Items",
            "Save Time",
            "Easy Payment",
            "Fast Delivery"
    };
    public String[] slide_desc = {
            "Shop from over 18,000 local stores to your doorsteps.",
            "Save your time by ordering your daily needs on a single click",
            "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum",
            "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        this.view = view;
        this.object = object;
        return view == (RelativeLayout) object;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText((slide_desc[position]));

        container.addView(view);

        return view;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
