package com.example.karthik.mvp.Activity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.karthik.mvp.R;

import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Item> items;
    public ItemAdapter(Activity activity, List<Item> items) {
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.custom_layout, null);
        }
        TextView title = convertView.findViewById(R.id.tv_title);
        TextView date = convertView.findViewById(R.id.tv_date);

        //getting the data for a row
        Item item = items.get(position);
        title.setText(item.getTitle());
        date.setText(item.getDate());
        return convertView;
    }
}
