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

public class SubProviderListAdapter extends RecyclerView.Adapter<SubProviderListAdapter.MyViewHolder> {

    private Context context;
    private List<General> subProviderList;


    public SubProviderListAdapter(Context context, List<General> subProviderList) {
        this.context = context;
        this.subProviderList = subProviderList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_general_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        General subProvider = subProviderList.get(position);
        holder.textViewItem.setText(subProvider.getName());
        holder.imageViewIcon.setImageResource(subProvider.getIcon());

    }

    @Override
    public int getItemCount() {
        return subProviderList.size();
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
