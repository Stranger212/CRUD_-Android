package com.example.thuchanh1.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.thuchanh1.R;

public class SpinnerAdapter extends BaseAdapter {
    private int[] imgs = {R.drawable.icon_bike, R.drawable.icon_car, R.drawable.icon_airport};
    private Context context;

    public SpinnerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item = LayoutInflater.from(context).inflate(R.layout.item_img, viewGroup, false);
        ImageView img = item.findViewById(R.id.img);
        img.setImageResource(imgs[i]);
        return item;
    }
}
