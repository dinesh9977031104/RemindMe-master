package com.tekitsolutions.remindme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tekitsolutions.remindme.Interface.AdapterInterface;
import com.tekitsolutions.remindme.Interface.HamburgerMenuInterface;
import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.Model.Reminder;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.CATEGORY_ID;

public class ReminderListAdapter extends RecyclerView.Adapter<ReminderListAdapter.MyViewHolder> {

    private AdapterInterface listener;
    private HamburgerMenuInterface menuInterface;
    private String repeatText = "", repeatIntervalType, repeatType, repeatEndDate, repeatDays;
    private int favorite = 0, repeatInterval, repeatCount;
    private Context context;
    private List<Reminder> reminderList;
    private DatabaseAdapter dbAdapter;
    private Reminder reminder;

    public ReminderListAdapter(Context context, List<Reminder> reminderList, AdapterInterface listener, HamburgerMenuInterface menuInterface) {
        this.context = context;
        this.reminderList = reminderList;
        this.listener = listener;
        this.menuInterface = menuInterface;
        this.dbAdapter = ReminderApp.getInstance().adapter;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_reminder_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        reminder = reminderList.get(position);
        holder.tvTitle.setText(reminder.getTitle());
        holder.tvTime.setText(reminder.getDueDate() + " " + reminder.getAlarmTime());

        if (reminder.getCategoryId() > 0) {
            General general = dbAdapter.getCategoryById(CATEGORY_ID, reminder.getCategoryId()).get(0);
            holder.ivReminder.setImageResource(general.getIcon());
        }

        if (reminder.getRepeat() == 1) {
            repeatIntervalType = reminder.getRepeatIntervalType();
            repeatInterval = reminder.getRepeatInterval();
            repeatType = reminder.getRepeatType();
            repeatText = "Every " + repeatInterval + " " + repeatIntervalType;

            if (repeatType.equals("End Date") || repeatType.equals("अंतिम तिथि")) {
                repeatEndDate = reminder.getRepeatEndDate();
                if (repeatEndDate != null) {
                    repeatText += " Until" + " " + repeatEndDate;
                }
            }
            if (repeatIntervalType.equals("week") || repeatIntervalType.equals("सप्ताह")) {
                repeatDays = reminder.getRepeatDays();
                repeatText += " & " + repeatDays;
            }

        } else {
            repeatText = "No Repeat";
        }
        holder.tvRepeat.setText(repeatText);

        if (reminder.getFavoriteId() == 1) {
            holder.toggleButton.setChecked(true);
        } else {
            holder.toggleButton.setChecked(false);
        }

        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {


                if (isChecked) {
                    favorite = 1;
                } else {
                    favorite = 0;
                }

                if (listener != null) {
                    listener.onClickFavorite(favorite, reminder.getReminderId());

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvTime, tvRepeat;
        private ImageView ivReminder, ivNotification, hamburgerMenu;
        private ToggleButton toggleButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvRepeat = itemView.findViewById(R.id.tv_repeat);
            ivReminder = itemView.findViewById(R.id.iv_reminder);
            /*       ivNotification = itemView.findViewById(R.id.iv_notification);*/
            toggleButton = itemView.findViewById(R.id.toggleButton);
            hamburgerMenu = itemView.findViewById(R.id.hamburger_menu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //        menuInterface.onClickListItem(getAdapterPosition());
                    listener.onClickReminderList(getAdapterPosition());
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


