package com.zowie.zowieevents.items;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.zowie.zowieevents.MainActivity;
import com.zowie.zowieevents.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Didek on 03/05/2017.
 */

public class ItemAdapter extends BaseAdapter {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Item> mDataSource = new ArrayList<>();
    private static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>() {{
        put("Available", R.color.colorAvailable);
        put("Not available", R.color.colorNotAvailable);
    }};

    public ItemAdapter(Context context) {
        mContext = context;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Item> getList() {
        return mDataSource;
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_item, parent, false);
            holder = new ViewHolder();
            holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.item_list_thumbnail);
            holder.titleTextView = (TextView) convertView.findViewById(R.id.item_list_title);
            holder.subtitleTextView = (TextView) convertView.findViewById(R.id.item_list_subtitle);
            holder.detailTextView = (TextView) convertView.findViewById(R.id.item_list_detail);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        TextView titleTextView = holder.titleTextView;
        TextView subtitleTextView = holder.subtitleTextView;
        TextView detailTextView = holder.detailTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        Item item = (Item) getItem(position);

        titleTextView.setText(item.getTitle());
        subtitleTextView.setText(item.getDescription());
        detailTextView.setText(item.getAvailability());

        Picasso.with(mContext).load(item.getImage()).placeholder(R.mipmap
                .ic_launcher).into(thumbnailImageView);

        Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/JosefinSans-Bold.ttf");
        titleTextView.setTypeface(titleTypeFace);
        Typeface subtitleTypeFace = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/JosefinSans-SemiBoldItalic.ttf");
        subtitleTextView.setTypeface(subtitleTypeFace);
        Typeface detailTypeFace = Typeface.createFromAsset(mContext.getAssets(),
                "fonts/Quicksand-Bold.otf");
        detailTextView.setTypeface(detailTypeFace);
        detailTextView.setTextColor(android.support.v4.content.ContextCompat.getColor(mContext, LABEL_COLORS
                .get(item.getAvailability())));

        return convertView;
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleTextView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }
}
