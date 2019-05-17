package com.tekitsolutions.remindme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ProviderListAdapter extends RecyclerView.Adapter<ProviderListAdapter.MyViewHolder> {

    private Context context;
    private List<General> providerList;
    private View view;

    public ProviderListAdapter(Context context, List<General> providerList) {
        this.context = context;
        this.providerList = providerList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_general_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        General general = providerList.get(position);
        holder.textViewItem.setText(general.getName());
        holder.imageViewIcon.setImageResource(general.getIcon());
    }

    @Override
    public int getItemCount() {
        return providerList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewItem;
        private ImageView imageViewIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            textViewItem = itemView.findViewById(R.id.tv_category_item);
            imageViewIcon = itemView.findViewById(R.id.iv_category_icon);
        }
    }
}
