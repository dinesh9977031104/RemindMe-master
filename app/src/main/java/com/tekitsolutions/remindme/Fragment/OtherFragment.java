package com.tekitsolutions.remindme.Fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tekitsolutions.remindme.Activity.AddReminderActivity;
import com.tekitsolutions.remindme.Activity.ReminderDetailActivity;
import com.tekitsolutions.remindme.Adapter.ReminderListAdapter;
import com.tekitsolutions.remindme.Interface.AdapterInterface;
import com.tekitsolutions.remindme.Interface.HamburgerMenuInterface;
import com.tekitsolutions.remindme.Model.Reminder;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Receiver.AlarmReceiver;
import com.tekitsolutions.remindme.RestApi.ApiClient;
import com.tekitsolutions.remindme.RestApi.ApiInterface;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_TYPE_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.IS_EDIT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.OTHER;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_OTHER;

public class OtherFragment extends Fragment implements AdapterInterface, HamburgerMenuInterface, Callback<String> {
    private static final String TAG = OtherFragment.class.getSimpleName();

    View view;
    private DatabaseAdapter dbAdapter;
    private List<Reminder> listItems = new ArrayList<>();
    private ReminderListAdapter listAdapter;
    private RecyclerView listView;
    private TextView tvNoData;
    private HamburgerMenuInterface menuInterface;
    private Reminder reminder;
    private long reminderId = 0;

    public OtherFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.other_fragment, container, false);

        listView = view.findViewById(R.id.list_view);
        tvNoData = view.findViewById(R.id.tv_no_data);
        dbAdapter = new DatabaseAdapter(getContext());
        listAdapter = new ReminderListAdapter(getActivity(), listItems, OtherFragment.this, this);
        listView.setHasFixedSize(false);
        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(getActivity());
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        try {
            listView.setAdapter(listAdapter);
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        listView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(getContext(), listView, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        loadAllReminders();
    }

    private void loadAllReminders() {
        listItems.clear();
        List<Reminder> list = dbAdapter.getReminderById(REMINDER_TYPE_ID, OTHER);
        if (list.size() > 0) {
            tvNoData.setVisibility(View.GONE);
            listItems.addAll(list);
            Log.d("mTesting", "Other: " + listItems.size());
            listAdapter.notifyDataSetChanged();
        } else {
            tvNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClickFavorite(int isFavorite, long position) {
        reminderId = position;
        reminder.setFavoriteId(isFavorite);
        saveOnServer();
    }

    private void saveOnServer() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> reminderList = apiInterface.addReminder(reminder);
        reminderList.enqueue(this);
    }

    @Override
    public void onClickReminderList(int position) {
        reminder = listItems.get(position);
        Intent intent;
        final CharSequence[] items = {getResources().getString(R.string.view), getResources().getString(R.string.edit),
                getResources().getString(R.string.delete), getResources().getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.dialogBoxStyle);

        //builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(final DialogInterface dialog, int item) {
                Intent intent;
                if (items[item].equals(getResources().getString(R.string.view))) {
                    intent = new Intent(getContext(), ReminderDetailActivity.class);
                    intent.putExtra(REMINDER_ID, reminder.getReminderId());
                    startActivity(intent);
                } else if (items[item].equals(getResources().getString(R.string.edit))) {
                    intent = new Intent(getContext(), AddReminderActivity.class);
                    intent.putExtra(REMINDER_ID, reminder.getReminderId());
                    intent.putExtra(IS_EDIT, true);
                    startActivityForResult(intent, PICK_OTHER);

                } else if (items[item].equals(getResources().getString(R.string.delete))) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext(), R.style.dialogBoxStyle);

                    alertDialogBuilder.setTitle(getResources().getString(R.string.are_you_sure)).setMessage(getResources().getString(R.string.want_to_delete_reminder))
                            .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog2, int which) {
                                    deleteReminderFromServer(reminder.getReminderId());
                                    new AlarmReceiver().cancelAlarm(getContext(), reminder.getReminderId());
                                    Toast.makeText(getContext(), getResources().getString(R.string.reminder_deleted), Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                    dialog2.dismiss();
                                    loadAllReminders();
                                }
                            }).setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog2, int which) {
                            dialog.dismiss();
                            dialog2.dismiss();
                        }
                    }).show();
                } else if (items[item].equals(getResources().getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void deleteReminderFromServer(long id) {
        reminderId = id;
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<String> response = apiService.deleteReminder(reminderId);
        response.enqueue(this);
    }

    @Override
    public void onClickHamburger(int position) {

    }

    @Override
    public void onClickListItem(int position) {

    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();

        if (statusCode == 200) {
            if (dbAdapter.deleteReminder(reminderId)) {
                Toast.makeText(getActivity(), getString(R.string.reminder_deleted), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        showToast("Something went wrong");
    }

    private void showToast(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}