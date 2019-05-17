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

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;
    private Context mContext;
    private List<General> items;
    private int mResource;
    private TextView tvSpinnerItem;
    private ImageView imageViewIcon;
    private View view;

    public CustomSpinnerAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.mInflater = LayoutInflater.from(context);
        this.items = objects;
    }

    @Override
    public boolean isEnabled(int position) {
        return position != 0;
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
        tvSpinnerItem = view.findViewById(R.id.tv_spinner_item);

        General general = items.get(position);
        tvSpinnerItem.setText(general.getName());

        if (position == 0) {
            tvSpinnerItem.setTextColor(mContext.getResources().getColor(R.color.colorhint));
        }
        return view;
    }
}
