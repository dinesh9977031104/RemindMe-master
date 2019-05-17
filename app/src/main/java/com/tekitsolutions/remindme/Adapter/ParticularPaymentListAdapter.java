package com.tekitsolutions.remindme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tekitsolutions.remindme.Interface.HamburgerMenuInterface;
import com.tekitsolutions.remindme.Model.ParticularPayment;
import com.tekitsolutions.remindme.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ParticularPaymentListAdapter extends RecyclerView.Adapter<ParticularPaymentListAdapter.MyViewHolder> {

    private HamburgerMenuInterface menuInterface;
    private Context context;
    private List<ParticularPayment> cardList;

    public ParticularPaymentListAdapter(Context context, List<ParticularPayment> cardList, HamburgerMenuInterface menuInterface) {
        this.context = context;
        this.cardList = cardList;
        this.menuInterface = menuInterface;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_payment_list, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ParticularPayment particularPayment = cardList.get(position);
        holder.icon.setImageResource(particularPayment.getPaymentIcon());
        holder.tvAliasName.setText(particularPayment.getAliasName());
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvAliasName;
        private ImageView icon, hamburgerMenu;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvAliasName = itemView.findViewById(R.id.tv_alias_name);
            icon = itemView.findViewById(R.id.row_icon);
            hamburgerMenu = itemView.findViewById(R.id.hamburger_menu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    menuInterface.onClickListItem(getAdapterPosition());
                }
            });

            hamburgerMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (menuInterface != null) {
                        menuInterface.onClickHamburger(getAdapterPosition());
                    }
                }
            });
        }
    }
}
