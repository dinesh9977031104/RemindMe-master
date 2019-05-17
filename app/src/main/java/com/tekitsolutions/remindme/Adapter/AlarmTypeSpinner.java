package com.tekitsolutions.remindme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AlarmTypeSpinner extends ArrayAdapter<General> {

    private int mResource;
    private LayoutInflater mInflater;
    private Context mContext;
    private List<General> items;
    private TextView customView;
    private View view;

    public AlarmTypeSpinner(@NonNull Context context, int resource, List alarmRepeatList) {
        super(context, resource, alarmRepeatList);
        this.mContext = context;
        this.mResource = resource;
        this.mInflater = LayoutInflater.from(context);
        this.items = alarmRepeatList;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent) {
        view = mInflater.inflate(mResource, parent, false);
        customView = view.findViewById(R.id.custom_view);

        General alarmType = items.get(position);
        customView.setText(alarmType.getName());

        return view;
    }
}