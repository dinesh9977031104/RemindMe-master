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

public class BankListAdapter extends RecyclerView.Adapter<BankListAdapter.MyViewHolder> {

    private Context context;
    private List<General> bankList;
    private View view;

    public BankListAdapter(Context context, List<General> bankList) {
        this.context = context;
        this.bankList = bankList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_general_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        General general = bankList.get(position);
        holder.tvBankName.setText(general.getName());
        holder.ivBankIcon.setImageResource(general.getIcon());
    }

    @Override
    public int getItemCount() {
        return bankList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvBankName;
        private ImageView ivBankIcon;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvBankName = itemView.findViewById(R.id.tv_category_item);
            ivBankIcon = itemView.findViewById(R.id.iv_category_icon);
        }
    }
}