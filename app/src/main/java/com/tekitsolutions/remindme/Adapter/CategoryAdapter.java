package com.tekitsolutions.remindme.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.R;

import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    private Context context;
    private List<General> generalList;

    public CategoryAdapter(Context context, List<General> generalList) {
        this.context = context;
        this.generalList = generalList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_general_list, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        General general = generalList.get(position);
        holder.textViewItem.setText(general.getName());
        holder.imageViewIcon.setImageResource(general.getIcon());
    }

    @Override
    public int getItemCount() {
        return generalList.size();
    }

    public Bitmap bitmapADD() {
        return bitmapADD();
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
