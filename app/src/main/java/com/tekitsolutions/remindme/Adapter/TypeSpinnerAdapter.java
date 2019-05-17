package com.tekitsolutions.remindme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TypeSpinnerAdapter extends ArrayAdapter<General> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<General> items;
    private int mResource;
    private TextView customView;
    private ImageView imageView;


    public TypeSpinnerAdapter(@NonNull Context context, int resource, List reminderTypeList) {
        super(context, resource, reminderTypeList);
        this.mContext = context;
        this.mResource = resource;
        this.mInflater = LayoutInflater.from(context);
        this.items = reminderTypeList;
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
        View view = mInflater.inflate(mResource, parent, false);

        customView = view.findViewById(R.id.custom_view);
        imageView = view.findViewById(R.id.imageView);

        General reminderType = items.get(position);
        customView.setText(reminderType.getName());
        imageView.setImageResource(reminderType.getIcon());

        return view;
    }
}