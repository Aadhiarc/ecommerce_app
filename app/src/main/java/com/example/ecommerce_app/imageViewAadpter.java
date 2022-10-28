package com.example.ecommerce_app;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import com.squareup.picasso.Picasso;


public class imageViewAadpter extends PagerAdapter {
    private Context context;
    private JSONArray imageurls;

    public imageViewAadpter(Context context, JSONArray imageurls) {
        this.context = context;
        this.imageurls = imageurls;
    }

    @Override
    public int getCount() {
        return imageurls.length();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        try {
            Picasso.with(context)
                    .load((String.valueOf(imageurls.get(position)))).into(imageView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
