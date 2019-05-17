package com.tekitsolutions.remindme.Receiver;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.tekitsolutions.remindme.Activity.AddReminderActivity;
import com.tekitsolutions.remindme.Activity.ReminderDetailActivity;
import com.tekitsolutions.remindme.Model.Reminder;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;

import java.util.Calendar;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.FRIDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.MONDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SATURDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SUNDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.THURSDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.TUESDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.WEDNESDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.milisecondsFromDate;


@SuppressWarnings("ALL")
public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getSimpleName();

    PendingIntent mPendingIntent;
    private DatabaseAdapter dbAdapter;
    private Context context;
    private String mTitle, selectedRepeatDays = "", getDueDate, getEndDate;
    private Intent editIntent;
    private Reminder reminder;
    private int repeatCommonInterval, mReceivedId, mMinute, mHour, mDay, mMonth, mYear, repeat, notifyDay = 0;
    private long reminderId;
    private NotificationManager mNotificationManager;
    private boolean isAlarmSet = false;
    private String[] mDateSplit, repeatDays;
    private Calendar mCalendar;

    @Override
    public void onReceive(Context context, Intent intent) {
        mReceivedId = intent.getIntExtra(REMINDER_ID, 0);
        showLog("mReceivedId: " + mReceivedId);
        this.context = context;
        dbAdapter = new DatabaseAdapter(context);
        getIntentData();
        editReminder();

         /*TODO:-> If End date is less than current date && there is repeat case with End date,
                  than all future alarm will be canceled*/
        reachedEndDate(context);

        /*TODO:-> If Due date is greater than current date && there is no repeat case with Due date,
                  than next alarm will be automatically set*/
        setNextAlarmForNotify(context, intent);

        /*TODO:-> If Array length is greater than 1, then only Alarm will be set based on next index*/
        nextWeekDays(context, intent);

        /*TODO:-> Set next Alarm for Month*/
        setAlarmForMonthOrYear(context, intent);

    }

    private void setAlarmForMonthOrYear(Context context, Intent intent) {
        if (reachedEndDate(context) && repeat == 1) {
            showLog("Inside Month / Year");
            new AddReminderActivity().setAlarm(mReceivedId, intent, context, null);
        }
    }

    private void nextWeekDays(Context context, Intent intent) {
        if (reachedEndDate(context)) {
            if (repeatDays != null && repeatDays.length > 1) {
                String getCurrentDay = getCurrentDayOfWeek();
                new AddReminderActivity().setAlarm(mReceivedId, intent, context, getCurrentDay);
            }
        }
    }

    private String getCurrentDayOfWeek() {
        mCalendar = Calendar.getInstance();
        String[] strDays = new String[]{SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};
        String getCurrentDay = strDays[mCalendar.get(Calendar.DAY_OF_WEEK) - 1];
        return getCurrentDay;
    }


    private void setNextAlarmForNotify(Context context, Intent intent) {
        mCalendar = Calendar.getInstance();
        long dueDateReminder = milisecondsFromDate(getDueDate);
        if (dueDateReminder > mCalendar.getTimeInMillis() && repeat == 0) {
            new AddReminderActivity().setAlarm(mReceivedId, intent, context, null);
        }
    }

    private boolean reachedEndDate(Context context) {
        mCalendar = Calendar.getInstance();
        if (getEndDate != null && !getEndDate.isEmpty()) {
            long endDateReminder = milisecondsFromDate(getEndDate);
            if (endDateReminder < mCalendar.getTimeInMillis()) {
                this.cancelAlarm(context, mReceivedId);
                showLog("canceled");
                return false;
            }
        }

        return true;
    }

    private void getIntentData() {
        if (mReceivedId != 0) {
            reminder = dbAdapter.getReminderById(REMINDER_ID, mReceivedId).get(0);
        }
    }

    private void editReminderInit() {
        repeat = reminder.getRepeat();
        notifyDay = reminder.getNotifyDay();
        reminderId = reminder.getReminderId();
        getDueDate = reminder.getDueDate();
        getEndDate = reminder.getRepeatEndDate();
        selectedRepeatDays = reminder.getRepeatDays();
        repeatCommonInterval = reminder.getRepeatInterval();

        if (selectedRepeatDays != null && !selectedRepeatDays.equals("")) {
            repeatDays = this.selectedRepeatDays.split(",");
        }
    }

    private void editReminder() {
        if (reminder != null) {
            editReminderInit();
            createNotification();
        }
    }


    private void createNotification() {
        mTitle = reminder.getTitle();
        final int NOTIFY_ID = 0; // ID of notification
        String id = "1";
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = mNotificationManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, mTitle, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mNotificationManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(context, id);
            editIntent = new Intent(context, ReminderDetailActivity.class);
            editIntent.putExtra(REMINDER_ID, mReceivedId);
            editIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, editIntent, 0);
            builder.setContentTitle(mTitle)

                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .setContentTitle(mTitle)
                    .setSmallIcon(R.drawable.ic_alarm_black_24dp) // required
                    .setContentText("Have you completed your task?") // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)

            /*.setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})*/;
            builder.setColor(ContextCompat.getColor(context, R.color.colorPrimary));

        } else {
            builder = new NotificationCompat.Builder(context, id);
            editIntent = new Intent(context, ReminderDetailActivity.class);
            editIntent.putExtra(REMINDER_ID, mReceivedId);
            editIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, editIntent, 0);
            builder.setContentTitle(mTitle)// required

                    .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                    .setSmallIcon(R.drawable.ic_alarm_black_24dp)// required
                    .setContentText("Have you completed your task?") // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                    .setContentIntent(pendingIntent)
                    .setContentTitle(mTitle)
                    /*.setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})*/
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        mNotificationManager.notify(mReceivedId, notification);

    }


    public void createExactAlarm(Context context, Calendar calendar, long id) {
        AlarmManager mAlarmManager;
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(REMINDER_ID, id);

        /*TODO:-> FLAG_UPDATE_CURRENT is necessary to work in edit case*/
        mPendingIntent = PendingIntent.getBroadcast(context, (int) id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*TODO:-> set & setExact & setRepeating working in all cases*/
        mAlarmManager.setExact(AlarmManager.RTC, calendar.getTimeInMillis(), mPendingIntent);

    }


    public void createRepeatAlarm(Context context, Calendar calendar, long id, long repeatTime) {
        AlarmManager mAlarmManager;
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra(REMINDER_ID, id);

        mPendingIntent = PendingIntent.getBroadcast(context, (int) id, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        mAlarmManager.setRepeating(AlarmManager.RTC, calendar.getTimeInMillis(), repeatTime, mPendingIntent);

    }

    public void cancelAlarm(Context context, long ID) {
        AlarmManager mAlarmManager;
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        mPendingIntent = PendingIntent.getBroadcast(context, (int) ID, new Intent(context, AlarmReceiver.class), 0);
        mAlarmManager.cancel(mPendingIntent);

    }


    private void showLog(String msg) {
        Log.d(TAG, msg);
    }

    private void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}