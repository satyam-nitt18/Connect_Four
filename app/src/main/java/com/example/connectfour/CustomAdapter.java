package com.example.connectfour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    private Context mContext;
    private final int[] ImageId;
    LayoutInflater inflater;

    public CustomAdapter(Context c,int[] ImageId ) {
        mContext = c;
        this.ImageId = ImageId;
        inflater=(LayoutInflater.from(c));
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return ImageId.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View grid, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        grid = inflater.inflate(R.layout.grid_single, null);
        ImageView imageView = (ImageView) grid.findViewById(R.id.grid_image);
        imageView.setImageResource(ImageId[position]);
        return grid;
    }


}
