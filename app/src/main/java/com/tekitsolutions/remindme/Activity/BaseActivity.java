package com.tekitsolutions.remindme.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Receiver.ConnectivityReceiver;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.tekitsolutions.remindme.Utils.CommonUtils.FRIDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.MONDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SATURDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SUNDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.THURSDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.TUESDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.WEDNESDAY;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = BaseActivity.class.getSimpleName();
    private androidx.appcompat.app.AlertDialog.Builder builder;

    public static String getSubString(String str, int length) {
        if (str.length() > length) {
            str = str.substring(0, length) + "...";
        }
        return str;
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean isValidName(String name) {
        String regx = "^[\\p{L} .'-]+$";
        Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        return matcher.find();
    }

    public static String getDateTime() {
        String dateStr = "";
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateStr = dateFormat.format(date);
        return dateStr;
    }

    public static String getDate() {
        String dateStr = "";
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateStr = dateFormat.format(date);
        return dateStr;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void setToolbar(Toolbar toolBar, String title) {
        setSupportActionBar(toolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar.setTitle(title);
    }

    protected void setThemeAndLoadLocale(Context mContext) {
        if (ReminderApp.getInstance().isNightModeEnabled()) {
            setTheme(R.style.darktheme);
        } else {
            setTheme(R.style.AppTheme);
        }

        ReminderApp.getInstance().loadLocale(mContext);
    }


    protected void showAlert(String title, String msg, final Boolean close) {
        if (builder == null) {
            builder = new androidx.appcompat.app.AlertDialog.Builder(this, R.style.dialogBoxStyle);
        }
        builder.setCancelable(false);
        builder.setTitle(title).setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (close)
                    finish();
            }
        }).show();
    }

    protected int stringToIntDayConverter(String getCurrentDay) {
        int currentDayIndex = 0;

        switch (getCurrentDay) {
            case SUNDAY:
                currentDayIndex = 0;
                break;
            case MONDAY:
                currentDayIndex = 1;
                break;
            case TUESDAY:
                currentDayIndex = 2;
                break;
            case WEDNESDAY:
                currentDayIndex = 3;
                break;
            case THURSDAY:
                currentDayIndex = 4;
                break;
            case FRIDAY:
                currentDayIndex = 5;
                break;
            case SATURDAY:
                currentDayIndex = 6;
                break;
        }

        return currentDayIndex;
    }

    protected boolean checkConnection(Context mContext) {
        boolean isConnected = ConnectivityReceiver.isConnected(mContext);
        showNetworkStatus(isConnected);
        if (isConnected) {
            return true;
        } else {
            showToast(BaseActivity.this, "Please check your Internet connection");
            return false;
        }
    }

    private void showNetworkStatus(boolean isConnected) {
        if (isConnected) {
        } else {
        }
    }
}
