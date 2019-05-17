package com.tekitsolutions.remindme.Activity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_FAVORITE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.IS_EDIT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_REMINDER;


public class AddFavoriteReminder extends BaseActivity implements AdapterInterface, HamburgerMenuInterface, Callback<String> {
    private static final String TAG = AddFavoriteReminder.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list_view)
    RecyclerView listView;
    private ActionBar actionBar;
    private DatabaseAdapter dbAdapter;
    private ReminderListAdapter listAdapter;
    private List<Reminder> listItems = new ArrayList<>();
    private int favoriteId = 1;
    private long reminderId = 0;
    private AdapterInterface listener;
    private HamburgerMenuInterface menuInterface;
    private Reminder reminder;

    public AddFavoriteReminder() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddFavoriteReminder.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_favorite_reminder);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.fav_reminders));

        listAdapter = new ReminderListAdapter(this, listItems, AddFavoriteReminder.this, this);
        listView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        try {
            listView.setAdapter(listAdapter);
        } catch (Exception exp) {
            exp.printStackTrace();
        }

        listView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, listView, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        dbAdapter = new DatabaseAdapter(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadAllFavoriteReminders();
    }

    private void loadAllFavoriteReminders() {
        listItems.clear();
        List<Reminder> list = dbAdapter.getReminderById(REMINDER_FAVORITE, favoriteId);
        if (list.size() > 0) {
            listItems.addAll(list);
            listAdapter.notifyDataSetChanged();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            /*case R.id.search:
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClickFavorite(int isFavorite, long position) {
        if (isFavorite == 0) {
            reminder.setFavoriteId(isFavorite);
            reminderId = position;
            saveOnServer();
            listAdapter.notifyDataSetChanged();
            loadAllFavoriteReminders();
        }

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
        AlertDialog.Builder builder = new AlertDialog.Builder(AddFavoriteReminder.this, R.style.dialogBoxStyle);
        builder.setCancelable(false);
        //builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(final DialogInterface dialog, int item) {
                Intent intent;
                if (items[item].equals(getResources().getString(R.string.view))) {
                    intent = new Intent(AddFavoriteReminder.this, ReminderDetailActivity.class);
                    intent.putExtra(REMINDER_ID, reminder.getReminderId());
                    startActivity(intent);
                } else if (items[item].equals(getResources().getString(R.string.edit))) {
                    intent = new Intent(AddFavoriteReminder.this, AddReminderActivity.class);
                    intent.putExtra(REMINDER_ID, reminder.getReminderId());
                    intent.putExtra(IS_EDIT, true);
                    startActivityForResult(intent, PICK_REMINDER);
                } else if (items[item].equals(getResources().getString(R.string.delete))) {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(AddFavoriteReminder.this, R.style.dialogBoxStyle);
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setTitle(getResources().getString(R.string.are_you_sure)).setMessage(getResources().getString(R.string.want_to_delete_reminder))
                            .setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog2, int which) {
                                    deleteReminderFromServer(reminder.getReminderId());
                                    new AlarmReceiver().cancelAlarm(AddFavoriteReminder.this, reminder.getReminderId());
                                    Toast.makeText(AddFavoriteReminder.this, getResources().getString(R.string.reminder_deleted), Toast.LENGTH_SHORT).show();

                                    dialog.dismiss();
                                    dialog2.dismiss();
                                    loadAllFavoriteReminders();
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
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }


    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        int statusCode = response.code();

        if (statusCode == 201) {
            dbAdapter.addReminder(reminder, reminderId);
        }

        if (statusCode == 200) {
            if (dbAdapter.deleteReminder(reminderId)) {
                Toast.makeText(this, getString(R.string.reminder_deleted), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        showToast("Something went wrong");
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}