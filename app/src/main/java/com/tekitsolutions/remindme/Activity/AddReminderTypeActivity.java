package com.tekitsolutions.remindme.Activity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.tabs.TabLayout;
import com.tekitsolutions.remindme.Adapter.AlarmTypeSpinner;
import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.Model.Reminder;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.DAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.END_DATE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.FOREVER;
import static com.tekitsolutions.remindme.Utils.CommonUtils.FRIDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.GET_DAYS;
import static com.tekitsolutions.remindme.Utils.CommonUtils.MONDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.MONTH;
import static com.tekitsolutions.remindme.Utils.CommonUtils.NUMBER_OF_TIMES;
import static com.tekitsolutions.remindme.Utils.CommonUtils.REPEAT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.REPEAT_INTERVAL_DAYS;
import static com.tekitsolutions.remindme.Utils.CommonUtils.REPEAT_INTERVAL_TYPE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.REPEAT_TIMES;
import static com.tekitsolutions.remindme.Utils.CommonUtils.REPEAT_TYPE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SATURDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SUNDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.THURSDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.TUESDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.WEDNESDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.WEEK;
import static com.tekitsolutions.remindme.Utils.CommonUtils.YEAR;

public class AddReminderTypeActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    public static final int TAB_DAILY = 0;
    public static final int TAB_WEEKLY = 1;
    public static final int TAB_MONTHLY = 2;
    public static final int TAB_YEARLY = 3;
    private static final String TAG = AddReminderTypeActivity.class.getSimpleName();
    public String INTERVAL_TYPE = DAY;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.switch_repeat)
    Switch switchRepeat;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.tv_repeat_interval)
    TextView tvRepeatInterval;
    @BindView(R.id.btn_interval_increase)
    Button btnIntervalIncrease;
    @BindView(R.id.btn_interval_decrease)
    Button btnIntervalDecrease;
    @BindView(R.id.spin_list)
    Spinner spinList;
    @BindView(R.id.tv_end_date)
    TextView tvEndDate;
    @BindView(R.id.tv_repeat_count)
    TextView tvRepeatCount;
    @BindView(R.id.btn_count_increase)
    Button btnCountIncrease;
    @BindView(R.id.btn_count_decrease)
    Button btnCountDecrease;
    @BindView(R.id.layout_repeat_count)
    LinearLayout layoutRepeatCount;
    @BindView(R.id.toggleSunday)
    ToggleButton toggleSunday;
    @BindView(R.id.toggleMonday)
    ToggleButton toggleMonday;
    @BindView(R.id.toggleTuesday)
    ToggleButton toggleTuesday;
    @BindView(R.id.toggleWednesday)
    ToggleButton toggleWednesday;
    @BindView(R.id.toggleThursday)
    ToggleButton toggleThursday;
    @BindView(R.id.toggleFriday)
    ToggleButton toggleFriday;
    @BindView(R.id.toggleSaturday)
    ToggleButton toggleSaturday;
    @BindView(R.id.layout_days)
    LinearLayout layoutDays;
    @BindView(R.id.layout_repeat)
    LinearLayout layoutRepeat;
    @BindView(R.id.layoutOverlay)
    FrameLayout layoutOverlay;
    AlarmTypeSpinner spinnerAdapter;
    List<General> alarmRepeatList;

    private ActionBar actionBar;
    private int repeatInterval = 1, mDay, mYear, mMonth, repeatCount = 1, reminderId;
    private String repeatType = "", repeatIntervalType = "", getDays = "", repeatEndDate = "";
    private Reminder reminder;
    private DatabaseAdapter dbAdapter;
    private Intent repeatIntent;
    private boolean getData = false, daily = false, weekly = false, monthly = false, yearly = false;
    private androidx.appcompat.app.AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddReminderTypeActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder_type);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.title_alarm_type));
        tablayout.addOnTabSelectedListener(this);
        dbAdapter = ReminderApp.getInstance().adapter;
        setSpinner();
        setData();
        setSwitchRepeat();
    }

    private void setSwitchRepeat() {
        switchRepeat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    setViewsEnabled(layoutRepeat, true);
                    notifyInAdvAlert();
                } else {
                    setViewsEnabled(layoutRepeat, false);
                }
            }

        });
    }

    private void notifyInAdvAlert() {
        //show alert here
    }

    private void setData() {
        repeatIntent = getIntent();
        getData = repeatIntent.getBooleanExtra("HAS_EXTRA", false);
        if (getData) {
            repeatIntervalType = repeatIntent.getStringExtra(REPEAT_INTERVAL_TYPE);
            repeatType = repeatIntent.getStringExtra("repeat_type");
            repeatInterval = repeatIntent.getIntExtra(REPEAT_INTERVAL_DAYS, 0);
            INTERVAL_TYPE = repeatIntervalType;
            if (INTERVAL_TYPE.isEmpty() || INTERVAL_TYPE == null) {
                INTERVAL_TYPE = DAY;
            }

            getDays = repeatIntent.getStringExtra(GET_DAYS);
            repeatEndDate = repeatIntent.getStringExtra(END_DATE);
            repeatCount = repeatIntent.getIntExtra(REPEAT_TIMES, 0);

            switch (repeatIntervalType) {
                case DAY:
                    tablayout.getTabAt(0).select();
                    tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + repeatIntervalType);
                    break;
                case WEEK:
                    tablayout.getTabAt(1).select();
                    tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + repeatIntervalType);
                    setUncheckedToggle();
                    String[] days = getDays.split(",");
                    for (int i = 0; i < days.length; i++) {
                        switch (days[i]) {
                            case SUNDAY:
                                toggleSunday.setChecked(true);
                                break;
                            case MONDAY:
                                toggleMonday.setChecked(true);
                                break;
                            case TUESDAY:
                                toggleTuesday.setChecked(true);
                                break;
                            case WEDNESDAY:
                                toggleWednesday.setChecked(true);
                                break;
                            case THURSDAY:
                                toggleThursday.setChecked(true);
                                break;
                            case FRIDAY:
                                toggleFriday.setChecked(true);
                                break;
                            case SATURDAY:
                                toggleSaturday.setChecked(true);
                                break;
                        }
                    }
                    layoutDays.setVisibility(View.VISIBLE);
                    break;
                case MONTH:
                    tablayout.getTabAt(2).select();
                    tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + repeatIntervalType);
                    break;
                case YEAR:
                    tablayout.getTabAt(3).select();
                    tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + repeatIntervalType);
                    break;
            }


            if (repeatEndDate != null && !repeatEndDate.isEmpty()) {
                spinList.setSelection(1);
                tvEndDate.setText(repeatEndDate);
                tvEndDate.setVisibility(View.VISIBLE);
            } else if (repeatType.equals(NUMBER_OF_TIMES) || repeatType.equals("कई बार")) {
                spinList.setSelection(2);
                tvRepeatCount.setText(repeatCount + getResources().getString(R.string.time));
                layoutRepeat.setVisibility(View.VISIBLE);
            }

            if (!repeatIntervalType.equals("")) {
                switchRepeat.setChecked(true);
                setViewsEnabled(layoutRepeat, true);
            } else {
                switchRepeat.setChecked(false);
                setViewsEnabled(layoutRepeat, false);
            }

            if (repeatInterval == 0) {
                repeatInterval = 1;
            }

        } else {
            //INTERVAL_TYPE = "day";
            switchRepeat.setChecked(false);
            setViewsEnabled(layoutRepeat, false);
        }
    }


    private void setUncheckedToggle() {
        toggleSunday.setChecked(false);
        toggleMonday.setChecked(false);
        toggleFriday.setChecked(false);
        toggleTuesday.setChecked(false);
        toggleWednesday.setChecked(false);
        toggleThursday.setChecked(false);
        toggleSaturday.setChecked(false);
    }

    private void setSpinner() {
        alarmRepeatList = dbAdapter.getAllAlarmType();

        spinnerAdapter = new AlarmTypeSpinner(AddReminderTypeActivity.this,
                R.layout.row_alarm_type_spinner, alarmRepeatList);
        spinList.setAdapter(spinnerAdapter);

        if (getIntent().hasExtra(REMINDER_ID)) {
            reminderId = getIntent().getIntExtra(REMINDER_ID, 0);
            reminder = dbAdapter.getReminderById(REMINDER_ID, reminderId).get(0);
        }

        spinList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                switch (position) {
                    case 0:
                        tvEndDate.setVisibility(View.GONE);
                        layoutRepeatCount.setVisibility(View.GONE);
                        repeatType = FOREVER;
                        break;
                    case 1:
                        tvEndDate.setVisibility(View.VISIBLE);
                        layoutRepeatCount.setVisibility(View.GONE);
                        repeatType = END_DATE;
                        break;
                    /*case 2:
                        tvEndDate.setVisibility(View.GONE);
                        layoutRepeatCount.setVisibility(View.VISIBLE);
                        repeatType = "Number of times";
                        break;*/
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean checkValidation() {
        if (repeatType.equals(END_DATE) && tvEndDate.getText().toString().isEmpty()) {
            showAlert("End date error", "Please select valid End date", false);
            return false;
        }

        return true;
    }

    @OnClick({R.id.btn_interval_increase, R.id.btn_interval_decrease, R.id.tv_end_date, R.id.btn_count_increase, R.id.btn_count_decrease})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_interval_increase:
                switch (INTERVAL_TYPE) {
                    case DAY:
                        repeatInterval++;
                        tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + INTERVAL_TYPE);

                        break;

                    case WEEK:
                        repeatInterval++;
                        tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + INTERVAL_TYPE);

                        break;
                    case MONTH:
                        repeatInterval++;
                        tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + INTERVAL_TYPE);

                        break;

                    case YEAR:
                        repeatInterval++;
                        tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + INTERVAL_TYPE);

                        break;
                }

                break;
            case R.id.btn_interval_decrease:
                if (repeatInterval > 1) {
                    repeatInterval--;
                    tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + INTERVAL_TYPE);
                }
                break;
            case R.id.tv_end_date:
                setEndDate();
                break;
            case R.id.btn_count_increase:
                repeatCount++;
                tvRepeatCount.setText(repeatCount + getResources().getString(R.string.time));
                break;
            case R.id.btn_count_decrease:
                if (repeatCount > 1) {
                    repeatCount--;
                    tvRepeatCount.setText(repeatCount + getResources().getString(R.string.time));
                }
                break;
        }
    }


    private void setViewsEnabled(View view, Boolean enabled) {
        if (enabled)
            layoutOverlay.setVisibility(View.GONE);
        else
            layoutOverlay.setVisibility(View.VISIBLE);
        view.setEnabled(enabled);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View child = viewGroup.getChildAt(i);
                setViewsEnabled(child, enabled);
            }
        }
    }

    private void setEndDate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year,
                                          final int monthOfYear, final int dayOfMonth) {
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                        String strDate = mYear + "-" + (mMonth + 1) + "-" + mDay;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = format.parse(strDate);
                            tvEndDate.setText(format.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        datePickerDialog.setTitle("");
        datePickerDialog.show();
    }

    private void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminder, menu);

        return true;
    }

    public void showAlert(String title, String msg, final Boolean close) {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case android.R.id.home:
                super.onBackPressed();
                break;
            case R.id.action_check:
                if (!switchRepeat.isChecked()) {
                    intent = new Intent();
                    intent.putExtra(REPEAT, 0);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    if (checkValidation()) {
                        intent = new Intent();
                        intent.putExtra(REPEAT, 1);
                        intent.putExtra(REPEAT_INTERVAL_TYPE, INTERVAL_TYPE);
                        intent.putExtra(REPEAT_INTERVAL_DAYS, repeatInterval);
                        intent.putExtra(REPEAT_TYPE, repeatType);
                        if (spinList.getSelectedItemPosition() == 1) {
                            intent.putExtra(END_DATE, tvEndDate.getText().toString());
                        } else if (spinList.getSelectedItemPosition() == 2) {
                            intent.putExtra(REPEAT_TIMES, repeatCount);
                        }
                        if (tablayout.getSelectedTabPosition() == 1) {
                            List<String> days = new ArrayList<>();
                            List<ToggleButton> dayList = Arrays.asList(toggleSunday, toggleMonday, toggleTuesday, toggleWednesday, toggleThursday, toggleFriday, toggleSaturday);
                            int count = 0;
                            for (int i = 0; i < dayList.size(); i++) {
                                if (dayList.get(i).isChecked()) {
                                    count++;
                                    days.add(dayList.get(i).getText().toString());
                                }
                            }
                            if (count == 0) {
                                showToast("At least one day should be selected");
                                return false;
                            } else {
                                intent.putExtra("days", TextUtils.join(",", days));

                            }
                        }
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        repeatInterval = 1;
        layoutDays.setVisibility(View.GONE);
        spinList.setSelection(0);
        tvEndDate.setVisibility(View.GONE);
        layoutRepeatCount.setVisibility(View.GONE);
        tvRepeatCount.setText(getResources().getString(R.string.one_times));
        switch (tab.getPosition()) {
            case TAB_DAILY:
                daily = true;
                INTERVAL_TYPE = DAY;
                if (INTERVAL_TYPE.equals(repeatIntervalType)) {
                    setData();
                }
                break;
            case TAB_WEEKLY:
                weekly = true;
                layoutDays.setVisibility(View.VISIBLE);
                INTERVAL_TYPE = WEEK;
                if (INTERVAL_TYPE.equals(repeatIntervalType)) {
                    setData();
                }
                break;
            case TAB_MONTHLY:
                monthly = true;
                INTERVAL_TYPE = MONTH;
                if (INTERVAL_TYPE.equals(repeatIntervalType)) {
                    setData();
                }
                break;
            case TAB_YEARLY:
                yearly = true;
                INTERVAL_TYPE = YEAR;
                if (INTERVAL_TYPE.equals(repeatIntervalType)) {
                    setData();
                }
                break;
        }

        tvRepeatInterval.setText(getResources().getString(R.string.repeat_every) + repeatInterval + " " + INTERVAL_TYPE);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }

    @Override
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }
}