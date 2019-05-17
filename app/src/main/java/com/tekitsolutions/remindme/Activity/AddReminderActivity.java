package com.tekitsolutions.remindme.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.tekitsolutions.remindme.Adapter.TypeSpinnerAdapter;
import com.tekitsolutions.remindme.Dialogs.NotificationCustomDialog;
import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.Model.ParticularPayment;
import com.tekitsolutions.remindme.Model.Reminder;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Receiver.AlarmReceiver;
import com.tekitsolutions.remindme.RestApi.ApiClient;
import com.tekitsolutions.remindme.RestApi.ApiInterface;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ACCOUNT_NUMBER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ALIAS_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_OWNER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_NUMBER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_SUB_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SUB_PROVIDER_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.CASH_ACCOUNT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.CREDIT_CARD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.CUSTOM;
import static com.tekitsolutions.remindme.Utils.CommonUtils.DAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.DEBIT_CARD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.EDIT_CATEGORY_INTENT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.ELECTRICITY_BILL;
import static com.tekitsolutions.remindme.Utils.CommonUtils.END_DATE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.FRIDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.GAS;
import static com.tekitsolutions.remindme.Utils.CommonUtils.GETDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.GET_DAYS;
import static com.tekitsolutions.remindme.Utils.CommonUtils.HOME;
import static com.tekitsolutions.remindme.Utils.CommonUtils.INSURANCE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.IS_EDIT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.LANDLINE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.MONDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.MONTH;
import static com.tekitsolutions.remindme.Utils.CommonUtils.NET_BANKING;
import static com.tekitsolutions.remindme.Utils.CommonUtils.NUMBER_OF_TIMES;
import static com.tekitsolutions.remindme.Utils.CommonUtils.OFFICE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.OTHER;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_CATEGORY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_PAYMENT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_REPEAT_TYPE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.REPEAT_INTERVAL_DAYS;
import static com.tekitsolutions.remindme.Utils.CommonUtils.REPEAT_INTERVAL_TYPE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.REPEAT_TIMES;
import static com.tekitsolutions.remindme.Utils.CommonUtils.REPEAT_TYPE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SATURDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SUNDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.THURSDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.TUESDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.WATER_BILL;
import static com.tekitsolutions.remindme.Utils.CommonUtils.WEDNESDAY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.WEEK;
import static com.tekitsolutions.remindme.Utils.CommonUtils.YEAR;

public class AddReminderActivity extends BaseActivity implements View.OnClickListener, NotificationCustomDialog.OnDialogSelectorListener, Callback<String> {
    private static final long milDay = 86400000L;
    private static final String TAG = AddReminderActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.layout_title)
    RelativeLayout layoutTitle;
    @BindView(R.id.iv_category)
    ImageView ivCategory;
    @BindView(R.id.tv_selected_category)
    TextView tvSelectedCategory;
    @BindView(R.id.tv_alias_name)
    TextView tvAliasName;
    @BindView(R.id.category_image)
    ImageView categoryImage;
    @BindView(R.id.layout_set_category)
    RelativeLayout layoutSetCategory;
    @BindView(R.id.layout_category)
    RelativeLayout layoutCategory;
    @BindView(R.id.iv_payment_mode)
    ImageView ivPaymentMode;
    @BindView(R.id.tv_selected_payment_mode)
    TextView tvSelectedPaymentMode;
    @BindView(R.id.payment_image)
    ImageView paymentImage;
    @BindView(R.id.layout_payment_option)
    LinearLayout layoutPayment;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.tv_card_alias)
    TextView tvCardAlias;
    @BindView(R.id.btn_remove_provider)
    ImageButton btnRemoveProvider;
    @BindView(R.id.btn_remove_layout)
    ImageButton btnRemoveCard;
    @BindView(R.id.layout_payment_info)
    CardView layoutPaymentInfo;
    @BindView(R.id.layout_select_spinner_item)
    RelativeLayout layoutSelectSpinnerItem;
    @BindView(R.id.spin_list)
    Spinner spinList;
    @BindView(R.id.iv_amount)
    ImageView ivAmount;
    @BindView(R.id.layout_amount)
    RelativeLayout layoutAmount;
    @BindView(R.id.iv_due_date)
    ImageView ivDueDate;
    @BindView(R.id.tv_due_date)
    TextView tvDueDate;
    @BindView(R.id.layout_due_date)
    RelativeLayout layoutDueDate;
    @BindView(R.id.iv_alarm)
    ImageView ivAlarm;
    @BindView(R.id.tv_notification)
    TextView tvNotification;
    @BindView(R.id.notification_layout)
    LinearLayout notificationLayout;
    @BindView(R.id.iv_repeat)
    ImageView ivRepeat;
    @BindView(R.id.layout_repeat_toggle)
    LinearLayout layoutRepeatToggle;
    @BindView(R.id.date_layout)
    LinearLayout dateLayout;
    @BindView(R.id.iv_note)
    ImageView ivNote;
    @BindView(R.id.layout_notes)
    RelativeLayout layoutNotes;
    @BindView(R.id.row_icon)
    ImageView ivCard;
    @BindView(R.id.et_amount)
    EditText etAmount;
    @BindView(R.id.tvRepeat)
    TextView tvRepeat;
    @BindView(R.id.et_note)
    EditText etNote;
    @BindView(R.id.tv_alarm_time)
    TextView tvAlarmTime;
    @BindView(R.id.tv_error_alarm_time)
    TextView tvErrorAlarmTime;
    @BindView(R.id.layout_alarm_time)
    LinearLayout layoutAlarmTime;
    @BindView(R.id.iv_provider)
    ImageView ivProvider;
    @BindView(R.id.tv_provider)
    TextView tvProvider;
    @BindView(R.id.layoutProvider)
    CardView layoutProvider;

    /* @BindView(R.id.tv_error_msg)
     TextView tvErrorMsg;*/
    @BindView(R.id.tv_title_error)
    TextView tvTitleError;
    @BindView(R.id.tv_time_error)
    TextView tvAlarmTimeError;
    @BindView(R.id.tv_due_date_error)
    TextView tvDueDateError;

    private String mTime, paymentType, providerName, ownerName, providerNum, aliasName, getNotification, getAlarmTime, getTitle, getAmount, getDueDate, repeatIntervalType = "", repeatType = "", repeatEndDate = "", selectedRepeatDays = "", repeatText = "", accountNum, mDateForDueDate;
    private int currentIndex;
    private int setWeekDay = -1;
    private int nearestPast;
    private int nearestFuture;
    private int dayOfWeekCount = 0;
    private int count = 0;
    private int dayForDueDate;
    private int editNotifyDay = 1;
    private int notifyInAdv = 1;
    private int reminderCount = 0;
    private long deleteRCardId;
    private long paymentId;
    private int repeat = 0;
    private long subProviderId;
    private long providerId;
    private long particularPaymentId = 0;
    private long categoryId = 0;
    private int notificationDay = 0;
    private int mHour;
    private int mMinute;
    private int mYearForDueDate;
    private int mMonthForDueDate;
    private int mDayForDueDate;
    private int repeatCommonInterval = 0;
    private int repeatCount = 0;
    private long reminderTypeId = 1;
    private int reminderTypePos = 0;
    private int spinnerImage = 0;
    private long tempReminderId = 0, reminderId = 0, milliseconds, mRepeatTime, dayOfIndexTime;
    private boolean isEditReminder, isNearest = false, stop = false, isFutureCase = false, getFirstIndex = false, stopLoop = false, getData = false, edit = false, isResultOk = true, isCategoryEditIntent, editInsurance;
    private TypeSpinnerAdapter spinnerAdapter;
    private ParticularPayment particularPayment;
    private General category, provider, subProvider, payment;
    private Reminder reminder;
    private Bundle bundle;
    private DatabaseAdapter dbAdapter;
    private Calendar mCalendar;
    private List<General> reminderTypeList;
    private List<Reminder> reminders = new ArrayList<>();
    private androidx.appcompat.app.AlertDialog.Builder builder;
    private Intent categoryIntent, repeatIntent;
    private String[] mDateSplitForDueDate, mTimeSplit, repeatDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddReminderActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.title_add_reminder));
        mCalendar = Calendar.getInstance();
        dbAdapter = ReminderApp.getInstance().adapter;
        GETDAY = 1;
        setAmount();
        getIntentData();
        editReminder();
        setClickListener();
        setSpinner();
    }

    private void getIntentData() {
        if (getIntent().hasExtra(REMINDER_ID)) {
            reminderId = getIntent().getLongExtra(REMINDER_ID, 0);
            isEditReminder = getIntent().getBooleanExtra(IS_EDIT, false);
            showLog("edit Reminder Id: " + reminderId + "\n" + "Boolean: " + isEditReminder);
            if (reminderId != 0) {
                reminder = dbAdapter.getReminderById(REMINDER_ID, reminderId).get(0);
            }
        }
    }

    private void setSpinner() {
        reminderTypeList = dbAdapter.getAllReminderType();

        spinnerAdapter = new TypeSpinnerAdapter(AddReminderActivity.this,
                R.layout.row_custom_spinner, reminderTypeList);
        spinList.setAdapter(spinnerAdapter);

        if (reminder != null) {
            if (reminderTypeId == HOME) {
                spinList.setSelection(0);
            } else if (reminderTypeId == OFFICE) {
                spinList.setSelection(1);
            } else if (reminderTypeId == OTHER) {
                spinList.setSelection(2);
            }
        }


        spinList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {

                General reminderType = reminderTypeList.get(position);
                reminderTypePos = position;
                spinnerImage = reminderType.getIcon();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setAmount() {
        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (etAmount.getText().toString().matches("^0") || etAmount.getText().toString().equals(".")) {
                    etAmount.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void editReminderInit() {
        reminderId = reminder.getReminderId();
        showLog("editReminderInit: " + reminderId);
        deleteRCardId = reminder.getParticularPaymentId();
        particularPaymentId = reminder.getParticularPaymentId();
        providerId = reminder.getProviderId();
        subProviderId = reminder.getSubProviderId();
        providerName = reminder.getProviderName();
        categoryId = reminder.getCategoryId();
        paymentId = reminder.getPaymentId();
        notificationDay = reminder.getNotifyDay();
        reminderTypeId = reminder.getReminderTypeId();
        aliasName = reminder.getAliasName();
        providerName = reminder.getProviderName();
        accountNum = reminder.getAccountNo();
        ownerName = reminder.getOwnerName();
        providerNum = reminder.getProviderNo();
        getVariableValue();
    }

    private void getVariableValue() {
        repeat = reminder.getRepeat();
        repeatIntervalType = reminder.getRepeatIntervalType();
        repeatType = reminder.getRepeatType();
        selectedRepeatDays = reminder.getRepeatDays();
        repeatCommonInterval = reminder.getRepeatInterval();
    }

    private void editReminder() {
        if (reminder != null) {
            edit = true;
            getSupportActionBar().setTitle(getResources().getString(R.string.edit_reminder));
            editReminderInit();

            /*TODO:-> Title*/
            etTitle.setText(reminder.getTitle());

            /*TODO:-> Payment*/
            setPaymentData();

            /*TODO:-> Category*/
            setCategoryData();

            /*TODO:-> Amount*/
            etAmount.setText(reminder.getAmount());

            /*TODO:-> Due Date*/
            tvDueDate.setText(reminder.getDueDate());

            /*TODO:-> Alarm Time*/
            tvAlarmTime.setText(reminder.getAlarmTime());

            /*TODO:-> Notify in adv*/
            setNotificationStatus();

            /*TODO:-> Repeat*/
            repeat();

            /*TODO:-> Note*/
            etNote.setText(reminder.getNotes());
        }
    }

    private void repeat() {
        if (reminder.getRepeat() == 1) {
            repeatIntervalType = reminder.getRepeatIntervalType();
            repeatCommonInterval = reminder.getRepeatInterval();
            repeatType = reminder.getRepeatType();
            repeatText = getResources().getString(R.string.every) + repeatCommonInterval + " " + repeatIntervalType;

            if (repeatType.equals("End Date") || repeatType.equals("अंतिम तिथि")) {
                repeatEndDate = reminder.getRepeatEndDate();
                if (repeatEndDate != null) {
                    repeatText += "\n " + getResources().getString(R.string.until) + " " + repeatEndDate;
                }
            }
            if (repeatIntervalType.equals("week") || repeatIntervalType.equals("सप्ताह")) {
                selectedRepeatDays = reminder.getRepeatDays();
                repeatText += "\n" + selectedRepeatDays;
            }
            tvRepeat.setText(repeatText);
        }
    }

    private void setNotificationStatus() {
        GETDAY = notificationDay + 1;
        String[] notificationItemList = getResources().getStringArray(R.array.notification_type);
        tvNotification.setText(notificationItemList[notificationDay + 1]);
    }

    private void setCategoryData() {
        if (providerId != 0) {
            provider = dbAdapter.getProviderById(PROVIDER_ID, providerId).get(0);
        }
        if (subProviderId != 0) {
            subProvider = dbAdapter.getSubProvidersById(SUB_PROVIDER_ID, subProviderId).get(0);
        }
        if (categoryId != 0) {
            category = dbAdapter.getCategoryById(CATEGORY_ID, categoryId).get(0);
        }

        if (category != null) {
            switch (category.getName()) {
                case INSURANCE:
                    if (subProvider != null) {
                        tvProvider.setText(subProvider.getName());
                        ivProvider.setImageResource(subProvider.getIcon());
                    }
                    break;

                case CUSTOM:
                    tvProvider.setText(providerName);
                    ivProvider.setImageResource(R.drawable.ic_add_circle_black_24dp);
                    break;

                default:
                    if (provider != null) {
                        tvProvider.setText(provider.getName());
                        ivProvider.setImageResource(provider.getIcon());
                    }
                    break;
            }

            tvSelectedCategory.setText(category.getName());
            categoryImage.setImageResource(category.getIcon());
            tvAliasName.setText(aliasName);
            layoutProvider.setVisibility(View.VISIBLE);
        }
    }

    private void setPaymentData() {
        if (edit && isResultOk) {
            particularPaymentId = reminder.getParticularPaymentId();
        }

        if (particularPaymentId != 0) {
            particularPayment = dbAdapter.getParticularPaymentById(PARTICULAR_PAYMENT_ID, particularPaymentId, null).get(0);

            if (particularPayment != null) {
                paymentId = particularPayment.getPaymentId();

                if (paymentId != 0) {
                    payment = dbAdapter.getPaymentById(PAYMENT_ID, paymentId).get(0);
                }

                tvCardAlias.setText(particularPayment.getAliasName());
                tvCardNumber.setText(particularPayment.getPaymentNumber());
                ivCard.setImageResource(particularPayment.getPaymentIcon());
                tvSelectedPaymentMode.setText(particularPayment.getPaymentType());
                if (payment != null) {
                    paymentImage.setImageResource(payment.getIcon());
                }
                layoutPaymentInfo.setVisibility(View.VISIBLE);
            }
        }

        if (particularPaymentId == 0) {
            layoutPaymentInfo.setVisibility(View.GONE);
            tvSelectedPaymentMode.setText(getResources().getString(R.string.select_payment_mode));
            paymentImage.setImageResource(R.drawable.ic_icon_category);
        }
    }

    private void setClickListener() {
        layoutCategory.setOnClickListener(this);
        layoutDueDate.setOnClickListener(this);
        layoutRepeatToggle.setOnClickListener(this);
        btnRemoveProvider.setOnClickListener(this);
        btnRemoveCard.setOnClickListener(this);
        layoutPayment.setOnClickListener(this);
        layoutProvider.setOnClickListener(this);
        layoutPaymentInfo.setOnClickListener(this);
        layoutAlarmTime.setOnClickListener(this);
        notificationLayout.setOnClickListener(this);
        dateLayout.setOnClickListener(this);
    }

    private void removeCategory() {
        categoryIntent = null;
        removeCategoryValues();
        layoutProvider.setVisibility(View.GONE);
        tvSelectedCategory.setText(getResources().getString(R.string.select_category));
        categoryImage.setImageResource(R.drawable.ic_icon_category);
    }

    private void removeCategoryValues() {
        providerId = 0;
        subProviderId = 0;
        categoryId = 0;
        providerNum = null;
        aliasName = null;
        ownerName = null;
        providerName = null;
        accountNum = null;
    }

    private void removePayment() {
        reminder.setParticularPaymentId(0);
        saveOnServer();

        layoutPaymentInfo.setVisibility(View.GONE);
        tvSelectedPaymentMode.setText(getResources().getString(R.string.select_payment_mode));
        paymentImage.setImageResource(R.drawable.ic_icon_category);
    }

    public void setDueDate() {
        if (mYearForDueDate == 0) {
            final Calendar c = Calendar.getInstance();
            mYearForDueDate = c.get(Calendar.YEAR);
            mMonthForDueDate = c.get(Calendar.MONTH);
            mDayForDueDate = c.get(Calendar.DAY_OF_MONTH);
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, final int year,
                                          final int monthOfYear, final int dayOfMonth) {
                        mYearForDueDate = year;
                        mMonthForDueDate = monthOfYear;
                        mDayForDueDate = dayOfMonth;
                        String strDate = mYearForDueDate + "-" + (mMonthForDueDate + 1) + "-" + mDayForDueDate;
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        try {
                            Date date = format.parse(strDate);
                            tvDueDate.setText(format.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYearForDueDate, mMonthForDueDate, mDayForDueDate);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        datePickerDialog.setTitle("");
        datePickerDialog.show();

    }

    private long convertDateToLong(String date) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    private void setReminderTime() {
        if (isSelectedDueDate()) {
        } else {
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MINUTE, 5);


            TimePickerDialog timePickerDialog = new TimePickerDialog(AddReminderActivity.this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {


                            if (convertDateToLong(tvDueDate.getText().toString()) > System.currentTimeMillis()) {

                                int hour = hourOfDay % 12;
                                mHour = hourOfDay;
                                mMinute = minute;
                                String strDate = getDate() + " " + mHour + ":" + mMinute + ":00";
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = format.parse(strDate);
                                    tvAlarmTime.setText(format2.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Calendar datetime = Calendar.getInstance();
                                Calendar c = Calendar.getInstance();
                                datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                datetime.set(Calendar.MINUTE, minute);
                                if (datetime.getTimeInMillis() >= c.getTimeInMillis()) {
                                    //it's after current
                                    mHour = hourOfDay;
                                    mMinute = minute;
                                    String strDate = getDate() + " " + mHour + ":" + mMinute + ":00";
                                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
                                    try {
                                        Date date = format.parse(strDate);
                                        tvAlarmTime.setText(format2.format(date));
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    //it's before current'
                                    showAlert(getResources().getString(R.string.alarm_time_error), getResources().getString(R.string.pleas_select_valid_time), false);
                                }
                            }

                        }
                    }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);

            timePickerDialog.show();
        }


    }

    private boolean isSelectedDueDate() {
        getDueDate = tvDueDate.getText().toString();
        if (getDueDate.isEmpty()) {
            showAlert(getResources().getString(R.string.alarm_time_error), getResources().getString(R.string.pleas_select_due_date_before_set_an_alarm_time), false);
            return true;
        }

        return false;

    }

    public void openCategory(View view) {
        Intent intent = new Intent(this, CategoryListActivity.class);
        startActivityForResult(intent, PICK_CATEGORY);
    }

    private void openNotificationDialog() {
        NotificationCustomDialog notificationCustomDialog = new NotificationCustomDialog();
        notificationCustomDialog.show(getSupportFragmentManager(), "notificationDialog");
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id) {
            case R.id.layout_category:
                openCategory(layoutCategory);
                break;

            case R.id.notification_layout:
                openNotificationDialog();
                break;

            case R.id.date_layout:
                setDueDate();
                break;

            case R.id.layout_alarm_time:
                setReminderTime();
                break;

            case R.id.layout_repeat_toggle:
                editRepeat();
                break;

            case R.id.layout_payment_option:
                sendToPaymentOption(PaymentListActivity.class);
                break;

            case R.id.btn_remove_provider:
                removeCategory();
                break;

            case R.id.btn_remove_layout:
                removePayment();
                break;

            case R.id.layoutProvider:
                editCategory();
                break;

            case R.id.layout_payment_info:
                editPayment();
                break;
        }
    }

    private void sendToPaymentOption(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        if (particularPaymentId != 0) {
            intent.putExtra(PARTICULAR_PAYMENT_ID, particularPaymentId);
        }
        startActivityForResult(intent, PICK_PAYMENT);
    }

    private void editRepeat() {
        if (reminder != null && repeatIntent == null) {
            repeatIntervalType = reminder.getRepeatIntervalType();
            repeatCommonInterval = reminder.getRepeatInterval();
            repeatType = reminder.getRepeatType();
            selectedRepeatDays = reminder.getRepeatDays();
            repeatEndDate = reminder.getRepeatEndDate();
            if (repeatIntervalType != null) {
                getData = true;
            }
        }
        repeatIntent = new Intent(this, AddReminderTypeActivity.class);
        repeatIntent.putExtra("HAS_EXTRA", getData);
        repeatIntent.putExtra(REPEAT_INTERVAL_TYPE, repeatIntervalType);
        repeatIntent.putExtra(REPEAT_INTERVAL_DAYS, repeatCommonInterval);
        repeatIntent.putExtra(REPEAT_TYPE, repeatType);
        repeatIntent.putExtra(GET_DAYS, selectedRepeatDays);
        repeatIntent.putExtra(END_DATE, repeatEndDate);
        repeatIntent.putExtra(REPEAT_TIMES, repeatCount);
        startActivityForResult(repeatIntent, PICK_REPEAT_TYPE);
    }

    private void editPayment() {
        if (particularPayment != null) {
            particularPaymentId = particularPayment.getParticularPaymentId();
            paymentType = particularPayment.getPaymentType();
        } else if (reminder != null) {
            particularPaymentId = reminder.getParticularPaymentId();
            particularPayment = dbAdapter.getParticularPaymentById(PARTICULAR_PAYMENT_ID, particularPaymentId, null).get(0);
            paymentType = particularPayment.getPaymentType();
        }
        getPaymentType(paymentType);
    }

    private void editCategory() {
        switch (category.getName()) {
            case ELECTRICITY_BILL:
                sendDataToActivity(AddElectricityBillActivity.class);
                break;

            case INSURANCE:
                sendDataToActivity(AddInsuranceActivity.class);
                break;

            case LANDLINE:
                sendDataToActivity(AddLandlineActivity.class);
                break;

            case GAS:
                sendDataToActivity(AddGasActivity.class);
                break;

            case WATER_BILL:
                sendDataToActivity(AddWaterBillActivity.class);
                break;

            case CUSTOM:
                sendDataToActivity(AddCustomCategory.class);
                break;
        }
    }

    private void sendDataToActivity(Class<?> cls) {
        Intent intent = new Intent(AddReminderActivity.this, cls);
        intent.putExtra(REMINDER_CATEGORY_PROVIDER_ID, providerId);
        intent.putExtra(REMINDER_CATEGORY_SUB_PROVIDER_ID, subProviderId);
        intent.putExtra(REMINDER_CATEGORY_ID, categoryId);
        intent.putExtra(REMINDER_CATEGORY_PROVIDER_NAME, providerName);
        intent.putExtra(REMINDER_CATEGORY_PROVIDER_NUMBER, providerNum);
        intent.putExtra(REMINDER_CATEGORY_ACCOUNT_NUMBER, accountNum);
        intent.putExtra(REMINDER_CATEGORY_OWNER_NAME, ownerName);
        intent.putExtra(REMINDER_CATEGORY_ALIAS_NAME, aliasName);

        isCategoryEditIntent = true;
        intent.putExtra(EDIT_CATEGORY_INTENT, isCategoryEditIntent);
        startActivityForResult(intent, PICK_CATEGORY);
    }

    private void getPaymentType(String paymentType) {
        switch (paymentType) {
            case CREDIT_CARD:
                sendActivity(AddCardActivity.class, PICK_PAYMENT);
                break;

            case DEBIT_CARD:
                sendActivity(AddCardActivity.class, PICK_PAYMENT);
                break;

            case NET_BANKING:
                sendActivity(AddNetBankingActivity.class, PICK_PAYMENT);
                break;

            case CASH_ACCOUNT:
                sendActivity(AddCashActivity.class, PICK_PAYMENT);
                break;
        }
    }

    private void sendActivity(Class<?> cls, int requestCode) {
        Intent intent = new Intent(AddReminderActivity.this, cls);
        intent.putExtra(PARTICULAR_PAYMENT_ID, particularPaymentId);
        intent.putExtra(IS_EDIT, true);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_PAYMENT:
                    if (data.hasExtra(PARTICULAR_PAYMENT_ID)) {
                        particularPaymentId = data.getLongExtra(PARTICULAR_PAYMENT_ID, 0);
                        isResultOk = false;
                        setPaymentData();
                    }
                    break;

                case PICK_CATEGORY:
                    if (data != null) {
                        providerId = data.getLongExtra(REMINDER_CATEGORY_PROVIDER_ID, 0);
                        subProviderId = data.getLongExtra(REMINDER_CATEGORY_SUB_PROVIDER_ID, 0);
                        categoryId = data.getLongExtra(REMINDER_CATEGORY_ID, 0);
                        providerNum = data.getStringExtra(REMINDER_CATEGORY_PROVIDER_NUMBER);
                        ownerName = data.getStringExtra(REMINDER_CATEGORY_OWNER_NAME);
                        aliasName = data.getStringExtra(REMINDER_CATEGORY_ALIAS_NAME);
                        providerName = data.getStringExtra(REMINDER_CATEGORY_PROVIDER_NAME);
                        accountNum = data.getStringExtra(REMINDER_CATEGORY_ACCOUNT_NUMBER);
                        setCategoryData();
                    }

                    break;

                case PICK_REPEAT_TYPE:
                    repeatIntent = data;
                    if (data != null) {
                        repeat = data.getIntExtra("repeat", 0);
                        if (repeat == 1) {
                            bundle = data.getExtras();
                            getData = true;

                            repeatIntervalType = bundle.getString(REPEAT_INTERVAL_TYPE);
                            repeatCommonInterval = bundle.getInt(REPEAT_INTERVAL_DAYS);
                            repeatType = bundle.getString("repeat_type");
                            repeatText = getResources().getString(R.string.every) + repeatCommonInterval + " " + repeatIntervalType;
                            repeatEndDate = bundle.getString(END_DATE);

                            selectedRepeatDays = bundle.getString(GET_DAYS);
                            repeatCount = bundle.getInt(REPEAT_TIMES, 0);


                            if (repeatType.equals(END_DATE) || repeatType.equals("अंतिम तिथि")) {
                                if (!repeatEndDate.isEmpty()) {
                                    repeatText += "\n " + getResources().getString(R.string.until) + " " + repeatEndDate;
                                }

                            } else if (repeatType.equals(NUMBER_OF_TIMES) || repeatType.equals("कई बार")) {

                                repeatText += "\n" + repeatCount + getResources().getString(R.string.time);

                            }

                            if (repeatIntervalType.equals(WEEK) || repeatIntervalType.equals("सप्ताह")) {
                                repeatText += "\n" + selectedRepeatDays;
                            }
                            tvRepeat.setText(repeatText);

                        } else {
                            repeatText = "None";
                            repeatCommonInterval = 1;
                            repeatIntervalType = "";
                            repeatType = "";
                            selectedRepeatDays = "";
                            repeatEndDate = "";
                            repeatCount = 1;
                            tvRepeat.setText(repeatText);
                        }
                    }
                    break;

            }
        } else {
            switch (requestCode) {
                case PICK_CATEGORY:
                    setCategoryData();
                    break;

                case PICK_PAYMENT:
                    if (data != null) {
                        particularPaymentId = data.getLongExtra(PARTICULAR_PAYMENT_ID, 0);
                        setPaymentData();
                    }

            }
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
    public void onSelectedOption(int index, String text) {
        notificationDay = index - 1;
        tvNotification.setText(text);
        GETDAY = notificationDay + 1;
    }

    public int getNotificationDay() {
        return GETDAY;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reminder, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                super.onBackPressed();
                GETDAY = 1;
                break;
            case R.id.action_check:
                if (checkValidation()) {
                    addReminderData();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addReminderData() {
        GETDAY = 1;

        reminder = new Reminder();
        if (isEditReminder == true) {
            tempReminderId = reminderId;
        } else {
            tempReminderId = System.currentTimeMillis();
        }
        reminder.setReminderId(tempReminderId);
        reminder.setUserId("9714594200");
        reminder.setTitle(etTitle.getText().toString());
        reminder.setAmount(etAmount.getText().toString());
        reminder.setDueDate(tvDueDate.getText().toString());
        reminder.setAlarmTime(tvAlarmTime.getText().toString());
        reminder.setNotifyDay(notificationDay);
        reminder.setNotes(etNote.getText().toString());
        reminder.setProviderId(providerId);
        reminder.setSubProviderId(subProviderId);
        reminder.setProviderNo(providerNum);
        reminder.setAliasName(aliasName);
        reminder.setProviderName(providerName);
        reminder.setOwnerName(ownerName);
        reminder.setAccountNo(accountNum);
        reminder.setPaymentId(paymentId);

        if (reminderTypePos == 0) {
            reminderTypeId = 1;
        } else if (reminderTypePos == 1) {
            reminderTypeId = 2;
        } else if (reminderTypePos == 2) {
            reminderTypeId = 3;
        }

        reminder.setReminderTypeId(reminderTypeId);

        if (categoryId > 0) {
            reminder.setCategoryId(categoryId);
        }

        if (particularPaymentId > 0) {
            reminder.setParticularPaymentId(particularPaymentId);
        }

        reminder.setRepeat(repeat);
        reminder.setRepeatIntervalType(repeatIntervalType);
        reminder.setRepeatCount(repeatCommonInterval);
        reminder.setRepeatType(repeatType);
        reminder.setRepeatEndDate(repeatEndDate);
        reminder.setRepeatDays(selectedRepeatDays);
        reminder.setCreatedAt(getDateTime());

        if (checkConnection(AddReminderActivity.this)) {
            addToServer();
        }

    }

    private void addToServer() {
        saveOnServer();

        String message = "";
        if (!isEditReminder) {
            message = getResources().getString(R.string.reminder_added_successfully);
        } else {
            message = getResources().getString(R.string.reminder_update_successfully);
            reminderId = tempReminderId;
            new AlarmReceiver().cancelAlarm(this, reminderId);
        }

        showLog("New Reminder Id: " + reminderId);
        if (reminderId != 0) {
            showLog("ID: " + reminderId);
            //setAlarm(reminderId, null, AddReminderActivity.this, null);
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("").setMessage(message).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                intent.putExtra(REMINDER_ID, reminderId);
                setResult(RESULT_OK, intent);
                finish();
            }
        }).show();
    }

    private void saveOnServer() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> reminderList = apiInterface.addReminder(reminder);
        reminderList.enqueue(this);
    }

    private boolean checkTimeBefore() {
        String mDate = tvDueDate.getText().toString();
        if (mDate.equals("")) {
            mDate = getDate();
        }
        String mTime = tvAlarmTime.getText().toString();

        String[] mDateSplit = mDate.split("-");
        String[] mTimeSplit = mTime.split(":");

        mDayForDueDate = Integer.parseInt(mDateSplit[2]);
        mMonthForDueDate = Integer.parseInt(mDateSplit[1]) - 1;
        mYearForDueDate = Integer.parseInt(mDateSplit[0]);
        mHour = Integer.parseInt(mTimeSplit[0]);
        mMinute = Integer.parseInt(mTimeSplit[1]);

        Calendar calAlarm = Calendar.getInstance();
        calAlarm.set(Calendar.DAY_OF_MONTH, mDayForDueDate);
        calAlarm.set(Calendar.MONTH, mMonthForDueDate);
        calAlarm.set(Calendar.YEAR, mYearForDueDate);
        calAlarm.set(Calendar.HOUR_OF_DAY, mHour);
        calAlarm.set(Calendar.MINUTE, mMinute);
        calAlarm.set(Calendar.SECOND, 0);

        Calendar calNow = Calendar.getInstance();

        return calAlarm.getTimeInMillis() >= calNow.getTimeInMillis();
    }

    private String getCurrentDayOfWeek() {
        mCalendar = Calendar.getInstance();
        String[] strDays = new String[]{SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY};
        String getCurrentDay = strDays[mCalendar.get(Calendar.DAY_OF_WEEK) - 1];
        return getCurrentDay;
    }

    public void setAlarm(long reminderId, Intent intent, Context context, String getCurrentDay) {
        dbAdapter = ReminderApp.getInstance().adapter;
        mCalendar = Calendar.getInstance();
        Reminder reminder = dbAdapter.getReminderById(REMINDER_ID, reminderId).get(0);
        if (reminder != null) {
            repeat = reminder.getRepeat();
            repeatIntervalType = reminder.getRepeatIntervalType();
            repeatType = reminder.getRepeatType();
            repeatCommonInterval = reminder.getRepeatInterval();
            selectedRepeatDays = reminder.getRepeatDays();
            if (repeat == 0) {
                mDateForDueDate = reminder.getDueDate();
            } else {
                mDateForDueDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            }
            mTime = reminder.getAlarmTime();
        }

        currentIndex = stringToIntDayConverter(getCurrentDayOfWeek());
        nearestPast = currentIndex;

        if (mDateForDueDate.equals("")) {
            mDateForDueDate = getDate();
        }

        mDateSplitForDueDate = mDateForDueDate.split("-");
        mTimeSplit = mTime.split(":");

        mDayForDueDate = Integer.parseInt(mDateSplitForDueDate[2]);
        mMonthForDueDate = Integer.parseInt(mDateSplitForDueDate[1]) - 1;
        mYearForDueDate = Integer.parseInt(mDateSplitForDueDate[0]);
        mHour = Integer.parseInt(mTimeSplit[0]);
        mMinute = Integer.parseInt(mTimeSplit[1]);

        //get time in milliseconds
        dayOfIndexTime = getTimeInMillis(mYearForDueDate, mMonthForDueDate, mDayForDueDate, mHour, mMinute);

        // Check repeat type
        mRepeatTime = 0;

        // Create a new notification
        dayForDueDate = mDayForDueDate - notificationDay;

        /*TODO:-> Either Non repeat case -OR- Repeat case will work*/
        nonRepeatCase(reminderId, intent, context);
        repeatCase(reminderId, intent, context, getCurrentDay);

    }

    private long getTimeInMillis(int mYear, int mMonth, int mDay, int mHour, int mMinute) {
        Calendar calAlarm = Calendar.getInstance();
        calAlarm.set(Calendar.DAY_OF_MONTH, mDay);
        calAlarm.set(Calendar.MONTH, mMonth);
        calAlarm.set(Calendar.YEAR, mYear);
        calAlarm.set(Calendar.HOUR_OF_DAY, mHour);
        calAlarm.set(Calendar.MINUTE, mMinute);
        calAlarm.set(Calendar.SECOND, 0);
        return calAlarm.getTimeInMillis();
    }

    private void nonRepeatCase(long reminderId, Intent intent, Context context) {
        if (repeat == 0) {
            notifyInAdv(reminderId, intent, context);
        }
    }

    public void notifyInAdv(long reminderId, Intent intent, Context context) {
        if (notificationDay < 1 || intent != null) {
            if (intent != null) {
                mCalendar.set(Calendar.DAY_OF_MONTH, mDayForDueDate);
            } else {
                mCalendar.set(Calendar.DAY_OF_MONTH, mDayForDueDate - notificationDay);
            }
            mCalendar.set(Calendar.MONTH, mMonthForDueDate);
            mCalendar.set(Calendar.YEAR, mYearForDueDate);
            mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
            mCalendar.set(Calendar.MINUTE, mMinute);
            mCalendar.set(Calendar.SECOND, 0);
            new AlarmReceiver().createExactAlarm(context, mCalendar, reminderId);
        } else if (notificationDay > 0) {
            mCalendar.set(Calendar.DAY_OF_MONTH, mDayForDueDate - notificationDay);
            mCalendar.set(Calendar.MONTH, mMonthForDueDate);
            mCalendar.set(Calendar.YEAR, mYearForDueDate);
            mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
            mCalendar.set(Calendar.MINUTE, mMinute);
            mCalendar.set(Calendar.SECOND, 0);
            new AlarmReceiver().createExactAlarm(context, mCalendar, reminderId);
        }
    }

    private void repeatCase(long reminderId, Intent intent, Context context, String getCurrentDay) {
        if (repeat == 1) {
            switch (repeatIntervalType) {
                case DAY:
                    setDayAlarm(reminderId, context);
                    break;

                case WEEK:
                    if (!selectedRepeatDays.equals("")) {
                        repeatDays = this.selectedRepeatDays.split(",");
                        if (intent == null) {
                            getUpcomingDay(context, reminderId);
                        } else {
                            getNextIndex(context, reminderId, getCurrentDay);
                        }
                    }
                    break;

                case MONTH:
                    setMonthAlarm(reminderId, context);
                    break;

                case YEAR:
                    setYearAlarm(reminderId, context);
                    break;
            }
        }
    }

    private void setNextIndexAlarm(Context context, long reminderId, String getCurrentDay) {
        int setDayOfWeek = stringToIntDayConverter(getCurrentDay);
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.DAY_OF_WEEK, setDayOfWeek + 1);
        if (stop == false) {
            mCalendar.add(Calendar.DAY_OF_WEEK, 7);
        }
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);

        showLog("setNextIndexAlarm mCalendar: " + mCalendar.getTimeInMillis());
        showLog("Done");
        new AlarmReceiver().createExactAlarm(context, mCalendar, reminderId);
    }

    private void getNextIndex(Context context, long reminderId, String getCurrentDay) {
        showLog("Inside getNextIndex");
        String getNextExactDay = "";
        stop = false;
        for (int k = 0; k < repeatDays.length; k++) {
            showLog("getCurrentDay: " + getCurrentDay);
            showLog("repeatDays: " + repeatDays[k]);
            getNextExactDay = repeatDays[k];
            if (stop == true) {
                showLog("Inside stop: " + getNextExactDay);
                setNextIndexAlarm(context, reminderId, getNextExactDay);
                break;
            } else if (getCurrentDay.equals(repeatDays[k])) {
                stop = true;
                showLog("stop: " + stop);
            }

            /*TODO:-> If next index is not available, and loop is about to stop,
                      we'll get first index again from DB and set it*/
            showLog("K Val: " + k);
            showLog("length: " + repeatDays.length);
            if (k + 1 == repeatDays.length) {
                for (int j = 0; j < 1; j++) {
                    getNextExactDay = repeatDays[j];
                    stop = false;
                    showLog("getNextExactDay: " + getNextExactDay);
                }
                setNextIndexAlarm(context, reminderId, getNextExactDay);
            }
        }
    }

    private void createAlarm(Context context, long reminderId) {
        int numberOfWeek = 7 * (repeatCommonInterval - 1);
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DAY_OF_WEEK, nearestPast + numberOfWeek);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);
        showLog("Final check: " + mCalendar.getTimeInMillis());
        new AlarmReceiver().createExactAlarm(context, mCalendar, reminderId);
    }

    private void getUpcomingDay(Context context, long reminderId) {
        nearestPast = stringToIntDayConverter(repeatDays[0]);
        nearestFuture = stringToIntDayConverter(repeatDays[0]);
        for (int k = 0; k < repeatDays.length; k++) {
            int getArrayIndex = stringToIntDayConverter(repeatDays[k]);
            showLog(repeatDays[k]);
            Calendar calNow = Calendar.getInstance();

            if (currentIndex == getArrayIndex && calNow.getTimeInMillis() <= dayOfIndexTime) {
                nearestPast = 0;
                showLog("compare time");
                isNearest = false;
                break;

            } else if (currentIndex < getArrayIndex) {
                nearestPast = getArrayIndex - currentIndex;
                showLog("Future case");
                isNearest = false;
                break;
            } else {
                showLog("Past case");
                isNearest = true;

            }
        }

        if (isNearest) {
            nearestPast = 7 - currentIndex + nearestPast;
        }
        showLog("nearestPast: " + nearestPast);
        createAlarm(context, reminderId);
    }

    private void setDayAlarm(long reminderId, Context context) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);
        mRepeatTime = repeatCommonInterval * milDay;
        new AlarmReceiver().createRepeatAlarm(context, mCalendar, reminderId, mRepeatTime);
    }

    private void setMonthAlarm(long reminderId, Context context) {
        Calendar mCalendar = Calendar.getInstance();
        int currentMonth = mCalendar.get(Calendar.MONTH);
        mCalendar.set(Calendar.MONTH, currentMonth + repeatCommonInterval);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);
        showLog("Get Month: " + mCalendar.getTimeInMillis());
        new AlarmReceiver().createExactAlarm(context, mCalendar, reminderId);
    }

    private void setYearAlarm(long reminderId, Context context) {
        Calendar mCalendar = Calendar.getInstance();
        int currentYear = mCalendar.get(Calendar.YEAR);
        mCalendar.set(Calendar.YEAR, currentYear + repeatCommonInterval);
        mCalendar.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendar.set(Calendar.MINUTE, mMinute);
        mCalendar.set(Calendar.SECOND, 0);
        showLog("Get Year: " + mCalendar.getTimeInMillis());
        new AlarmReceiver().createExactAlarm(context, mCalendar, reminderId);
    }


    private boolean checkValidation() {
        getTitle = etTitle.getText().toString();
        getDueDate = tvDueDate.getText().toString();
        getAmount = etAmount.getText().toString();
        getAlarmTime = tvAlarmTime.getText().toString();
        getNotification = tvNotification.getText().toString();

        if (getTitle.isEmpty()) {
            // etTitle.setError(getResources().getString(R.string.title_can_not_be_empty));
            showErrorMsg(tvTitleError);
            tvTitleError.setText(getResources().getString(R.string.title_can_not_be_empty));
            tvDueDateError.setVisibility(View.GONE);
            tvAlarmTimeError.setVisibility(View.GONE);
            return false;
        }
        if (getTitle.length() < 1 || getTitle.length() > 25) {
            // etTitle.setError(getResources().getString(R.string.title_length_should_be_three_character));
            showErrorMsg(tvTitleError);
            tvTitleError.setText(getResources().getString(R.string.title_length_should_be_three_character));
            return false;
        }


        if (!getAmount.isEmpty() && getAmount.equals("0")) {
            etAmount.setError(getResources().getString(R.string.zero_anount));
            return false;
        }

        if (getDueDate.isEmpty()) {
            //showAlert(getResources().getString(R.string.due_date_error), getResources().getString(R.string.please_select_due_date), false);
            showErrorMsg(tvDueDateError);
            tvDueDateError.setText(getResources().getString(R.string.please_select_due_date));
            tvTitleError.setVisibility(View.GONE);
            tvAlarmTimeError.setVisibility(View.GONE);
            return false;

        }

        if (getAlarmTime.isEmpty()) {
            // tvAlarmTime.setError(getResources().getString(R.string.please_select_alarm_time));
            showErrorMsg(tvAlarmTimeError);
            tvAlarmTimeError.setText(getResources().getString(R.string.please_select_alarm_time));
            tvDueDateError.setVisibility(View.GONE);
            tvTitleError.setVisibility(View.GONE);
            return false;
        }

        if (!checkTimeBefore()) {
            showAlert("Alarm time Error", "Can't select past time", false);
            tvAlarmTime.requestFocus();
            return false;
        }

        return true;
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }

    private void showErrorMsg(TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        showLog("Test: Reminder Id: " + reminderId);
        dbAdapter.addReminder(reminder, reminderId);
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

}