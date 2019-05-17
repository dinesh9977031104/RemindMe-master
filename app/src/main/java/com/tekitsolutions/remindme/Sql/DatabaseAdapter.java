package com.tekitsolutions.remindme.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tekitsolutions.remindme.Model.BankResponse;
import com.tekitsolutions.remindme.Model.CategoryResponse;
import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.Model.ParticularPayment;
import com.tekitsolutions.remindme.Model.PaymentResponse;
import com.tekitsolutions.remindme.Model.ProviderResponse;
import com.tekitsolutions.remindme.Model.Reminder;
import com.tekitsolutions.remindme.Model.ReminderSpinnerListResponse;
import com.tekitsolutions.remindme.Model.RepeatAlarmResponse;
import com.tekitsolutions.remindme.Model.SubProviderResponse;

import java.util.ArrayList;
import java.util.List;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.ACCOUNT_BANK_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.ACCOUNT_IFSC;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.ACCOUNT_URL;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.ACCOUNT_USERNAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.BANK_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.BANK_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.CATEGORY_ITEM;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.COLUMN_CARD_CVV;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.COLUMN_CARD_VALIDITY;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_AGENT_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_ALIAS;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_EMAIL;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_ICON;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_MOBILE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_NUMBER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_OWNER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_TYPE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PAYMENT_ITEM;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PAYMENT_OPTION_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_KEY;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_ALARM_TIME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_AMOUNT;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ACCOUNT_NUMBER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ALIAS_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_OWNER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_NUMBER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_SUB_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_COMMON_INTERVAL_COUNT;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CREATED_AT;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_DAY_WEEK_TYPE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_DUE_DATE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_FAVORITE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_NOTES;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_NOTIFY_DAY;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_PARTICULAR_PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_REPEAT;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_REPEAT_DAYS_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_REPEAT_END_DATE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_REPEAT_SPINNER_ITEMS;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_TITLE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_TYPE_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REPEAT_ALARM_TYPE_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REPEAT_ALARM_TYPE_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SELECT_CATEGORY;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SELECT_PAYMENT;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SELECT_REPEAT_ALARM_TYPE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SELECT_SUB_PROVIDER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SPINNER_TYPE_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SPINNER_TYPE_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SUB_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SUB_PROVIDER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.TABLE_BANK;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.TABLE_CATEGORY;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.TABLE_PARTICULAR_PAYMENT_MODE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.TABLE_PAYMENT;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.TABLE_PROVIDER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.TABLE_REMINDER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.TABLE_REMINDER_SPINNER_TYPE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.TABLE_REPEAT_ALARM_TYPE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.TABLE_SUB_PROVIDER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.USER_ID_PAYMENT;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.USER_ID_REMINDER;

public class DatabaseAdapter {
    private static final String TAG = DatabaseAdapter.class.getSimpleName();

    Context context;
    DatabaseHelper dataBaseHelper;
    SQLiteDatabase sqLiteDatabase;

    public DatabaseAdapter(Context context) {
        this.context = context;
        dataBaseHelper = new DatabaseHelper(context);
    }

    /*TODO:-> Open DataBase*/
    public void openDataBase() {
        try {
            sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /*TODO:-> Drop Tables*/
    public void dropTables() {
        openDataBase();
        String query1 = "DELETE FROM " + DataBaseConstant.TABLE_CATEGORY;
        sqLiteDatabase.execSQL(query1);
        String query2 = "DELETE FROM " + DataBaseConstant.TABLE_BANK;
        sqLiteDatabase.execSQL(query2);
        String query3 = "DELETE FROM " + DataBaseConstant.TABLE_PROVIDER;
        sqLiteDatabase.execSQL(query3);
        String query4 = "DELETE FROM " + DataBaseConstant.TABLE_SUB_PROVIDER;
        sqLiteDatabase.execSQL(query4);
        String query5 = "DELETE FROM " + DataBaseConstant.TABLE_REMINDER_SPINNER_TYPE;
        sqLiteDatabase.execSQL(query5);
        String query6 = "DELETE FROM " + DataBaseConstant.TABLE_PAYMENT;
        sqLiteDatabase.execSQL(query6);
        String query7 = "DELETE FROM " + TABLE_REPEAT_ALARM_TYPE;
        sqLiteDatabase.execSQL(query7);
    }

    /*TODO:-> Show Logs*/
    private void showLog(String msg) {
        Log.d(TAG, msg);
    }


    /*TODO:-> Add Sub Providers*/
    public void addAllSubProviders(List<SubProviderResponse> responseList) {
        sqLiteDatabase.beginTransaction();
        ContentValues values;
        try {
            for (SubProviderResponse subProviderResponse : responseList) {
                values = new ContentValues();
                values.put(SUB_PROVIDER_ID, subProviderResponse.getSubProviderId());
                values.put(SUB_PROVIDER_NAME, subProviderResponse.getSubProviderName());
                values.put(PROVIDER_KEY, subProviderResponse.getProviderid());
                sqLiteDatabase.insert(TABLE_SUB_PROVIDER, null, values);
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    /*TODO:-> Get Sub Providers*/
    public List<General> getSubProvidersById(String columnName, long id) {
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        List<General> list = new ArrayList<>();
        General subProvider = null;
        Cursor cursor;
        if (columnName == null && id == 0) {
            cursor = sqLiteDatabase.rawQuery(SELECT_SUB_PROVIDER, null);
        } else {
            cursor = sqLiteDatabase.rawQuery(SELECT_SUB_PROVIDER + " WHERE " + columnName + " = " + id, null);
        }

        if (cursor.moveToFirst()) {
            do {
                subProvider = new General();
                subProvider.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.SUB_PROVIDER_ID)));
                subProvider.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.SUB_PROVIDER_NAME)));
                subProvider.setIcon(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.SUB_PROVIDER_ICON)));
                list.add(subProvider);
            } while (cursor.moveToNext());
        }

        return list;
    }

    /*TODO:-> Add Payment Option*/
    public void addAllPayment(List<PaymentResponse> responseList) {
        sqLiteDatabase.beginTransaction();
        ContentValues values;
        try {
            for (PaymentResponse paymentResponse : responseList) {
                values = new ContentValues();
                values.put(PAYMENT_ID, paymentResponse.getPaymentId());
                values.put(PAYMENT_ITEM, paymentResponse.getPaymentOption());
                sqLiteDatabase.insert(TABLE_PAYMENT, null, values);
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    /*TODO:-> Get Payment Option*/
    public List<General> getPaymentById(String columnName, long id) {
        List<General> paymentList = new ArrayList<>();
        General particularPayment = null;
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor;
        if (columnName == null && id == 0) {
            cursor = sqLiteDatabase.rawQuery(SELECT_PAYMENT, null);
        } else {
            cursor = sqLiteDatabase.rawQuery(SELECT_PAYMENT + " WHERE " + columnName + " = " + id, null);
        }
        if (cursor.moveToFirst()) {
            do {
                particularPayment = new General();
                particularPayment.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.PAYMENT_ID)));
                particularPayment.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.PAYMENT_ITEM)));
                particularPayment.setIcon(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.PAYMENT_ICON)));
                paymentList.add(particularPayment);
            } while (cursor.moveToNext());
        }
        return paymentList;
    }

    /*TODO:-> Add Category Response*/
    public void addAllCategory(List<CategoryResponse> responseList) {
        sqLiteDatabase.beginTransaction();
        ContentValues values;
        try {
            for (CategoryResponse categoryResponse : responseList) {
                values = new ContentValues();
                values.put(CATEGORY_ID, categoryResponse.getId());
                values.put(CATEGORY_ITEM, categoryResponse.getName());
                sqLiteDatabase.insert(TABLE_CATEGORY, null, values);
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    /*TODO:-> Get Category*/
    public List<General> getCategoryById(String columnName, long id) {
        List<General> generalList = new ArrayList<>();
        General general = null;
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor;
        if (columnName == null && id == 0) {
            cursor = sqLiteDatabase.rawQuery(SELECT_CATEGORY, null);
        } else {
            cursor = sqLiteDatabase.rawQuery(SELECT_CATEGORY + " WHERE " + columnName + "=" + id, null);
        }
        if (cursor.moveToFirst()) {
            do {
                general = new General();
                general.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.CATEGORY_ID)));
                general.setName(cursor.getString(cursor.getColumnIndex(CATEGORY_ITEM)));
                general.setIcon(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.CATEGORY_ICON)));
                generalList.add(general);
            } while (cursor.moveToNext());
        }
        return generalList;
    }

    /*TODO:-> Add/Edit Reminder*/
    public long addReminder(Reminder reminder, long reminderId) {
        long createId = 0;
        long editId = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(REMINDER_ID, reminder.getReminderId());
        values.put(USER_ID_REMINDER, reminder.getUserId());
        values.put(REMINDER_TITLE, reminder.getTitle());
        values.put(REMINDER_FAVORITE, reminder.getFavoriteId());
        values.put(REMINDER_CATEGORY_ID, reminder.getCategoryId());
        values.put(REMINDER_PAYMENT_ID, reminder.getPaymentId());
        values.put(REMINDER_AMOUNT, reminder.getAmount());
        values.put(REMINDER_DUE_DATE, reminder.getDueDate());
        values.put(REMINDER_ALARM_TIME, reminder.getAlarmTime());
        values.put(REMINDER_NOTIFY_DAY, reminder.getNotifyDay());
        values.put(REMINDER_TYPE_ID, reminder.getReminderTypeId());
        values.put(REMINDER_PARTICULAR_PAYMENT_ID, reminder.getParticularPaymentId());
        values.put(REMINDER_REPEAT, reminder.getRepeat());
        values.put(REMINDER_DAY_WEEK_TYPE, reminder.getRepeatIntervalType());
        values.put(REMINDER_COMMON_INTERVAL_COUNT, reminder.getRepeatCount());
        values.put(REMINDER_REPEAT_SPINNER_ITEMS, reminder.getRepeatType());
        values.put(REMINDER_REPEAT_END_DATE, reminder.getRepeatEndDate());
        values.put(REMINDER_REPEAT_DAYS_NAME, reminder.getRepeatDays());
        values.put(REMINDER_NOTES, reminder.getNotes());
        values.put(REMINDER_CREATED_AT, reminder.getCategoryId());
        values.put(REMINDER_CATEGORY_PROVIDER_ID, reminder.getProviderId());
        values.put(REMINDER_CATEGORY_SUB_PROVIDER_ID, reminder.getSubProviderId());
        values.put(REMINDER_CATEGORY_PROVIDER_NUMBER, reminder.getProviderNo());
        values.put(REMINDER_CATEGORY_ALIAS_NAME, reminder.getAliasName());
        values.put(REMINDER_CATEGORY_OWNER_NAME, reminder.getOwnerName());
        values.put(REMINDER_CATEGORY_PROVIDER_NAME, reminder.getProviderName());
        values.put(REMINDER_CATEGORY_ACCOUNT_NUMBER, reminder.getAccountNo());

        if (reminderId == 0) {
            createId = sqLiteDatabase.insert(TABLE_REMINDER, null, values);
            return createId;
        } else {
            editId = sqLiteDatabase.update(TABLE_REMINDER, values, "" + REMINDER_ID + " = '" + reminderId + "'", null);
            return editId;
        }
    }


    /*TODO:-> Get Reminder*/
    public List<Reminder> getReminderById(String columnName, long id) {
        List<Reminder> list = new ArrayList<>();
        Reminder reminder = null;
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor;
        if (columnName == null && id == 0) {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_REMINDER + " ORDER BY " + REMINDER_ID + " DESC ", null);
        } else {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_REMINDER + " WHERE " + columnName + " = " + id, null);
        }
        if (cursor.moveToFirst()) {
            do {
                reminder = new Reminder();
                reminder.setReminderId(cursor.getLong(cursor.getColumnIndex(REMINDER_ID)));
                reminder.setUserId(cursor.getString(cursor.getColumnIndex(USER_ID_REMINDER)));
                reminder.setReminderTypeId(cursor.getLong(cursor.getColumnIndex(REMINDER_TYPE_ID)));
                reminder.setParticularPaymentId(cursor.getLong(cursor.getColumnIndex(REMINDER_PARTICULAR_PAYMENT_ID)));
                reminder.setCategoryId(cursor.getLong(cursor.getColumnIndex(REMINDER_CATEGORY_ID)));
                reminder.setPaymentId(cursor.getLong(cursor.getColumnIndex(REMINDER_PAYMENT_ID)));
                reminder.setRepeat(cursor.getInt(cursor.getColumnIndex(REMINDER_REPEAT)));
                reminder.setNotifyDay(cursor.getInt(cursor.getColumnIndex(REMINDER_NOTIFY_DAY)));
                reminder.setRepeatInterval(cursor.getInt(cursor.getColumnIndex(REMINDER_COMMON_INTERVAL_COUNT)));
                reminder.setTitle(cursor.getString(cursor.getColumnIndex(REMINDER_TITLE)));
                reminder.setFavoriteId(cursor.getInt(cursor.getColumnIndex(REMINDER_FAVORITE)));
                reminder.setAmount(cursor.getString(cursor.getColumnIndex(REMINDER_AMOUNT)));
                reminder.setDueDate(cursor.getString(cursor.getColumnIndex(REMINDER_DUE_DATE)));
                reminder.setAlarmTime(cursor.getString(cursor.getColumnIndex(REMINDER_ALARM_TIME)));
                reminder.setRepeatIntervalType(cursor.getString(cursor.getColumnIndex(REMINDER_DAY_WEEK_TYPE)));
                reminder.setRepeatType(cursor.getString(cursor.getColumnIndex(REMINDER_REPEAT_SPINNER_ITEMS)));
                reminder.setRepeatEndDate(cursor.getString(cursor.getColumnIndex(REMINDER_REPEAT_END_DATE)));
                reminder.setRepeatDays(cursor.getString(cursor.getColumnIndex(REMINDER_REPEAT_DAYS_NAME)));
                reminder.setNotes(cursor.getString(cursor.getColumnIndex(REMINDER_NOTES)));
                reminder.setCreatedAt(cursor.getString(cursor.getColumnIndex(REMINDER_CREATED_AT)));
                reminder.setProviderId(cursor.getLong(cursor.getColumnIndex(REMINDER_CATEGORY_PROVIDER_ID)));
                reminder.setSubProviderId(cursor.getLong(cursor.getColumnIndex(REMINDER_CATEGORY_SUB_PROVIDER_ID)));
                reminder.setProviderNo(cursor.getString(cursor.getColumnIndex(REMINDER_CATEGORY_PROVIDER_NUMBER)));
                reminder.setOwnerName(cursor.getString(cursor.getColumnIndex(REMINDER_CATEGORY_OWNER_NAME)));
                reminder.setAliasName(cursor.getString(cursor.getColumnIndex(REMINDER_CATEGORY_ALIAS_NAME)));
                reminder.setProviderName(cursor.getString(cursor.getColumnIndex(REMINDER_CATEGORY_PROVIDER_NAME)));
                reminder.setAccountNo(cursor.getString(cursor.getColumnIndex(REMINDER_CATEGORY_ACCOUNT_NUMBER)));

                if (cursor.getLong(cursor.getColumnIndex(REMINDER_CATEGORY_ID)) != 0) {
                    General general = getCategoryById(CATEGORY_ID, cursor.getLong(cursor.getColumnIndex(REMINDER_CATEGORY_ID))).get(0);
                    reminder.setGeneral(general);
                }

                General reminderType = new General();
                Cursor mCursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_REMINDER_SPINNER_TYPE + " WHERE " + SPINNER_TYPE_ID + " = " + cursor.getInt(cursor.getColumnIndex(REMINDER_TYPE_ID)), null);
                if (mCursor.moveToFirst()) {
                    reminderType.setId(mCursor.getLong(mCursor.getColumnIndex(DataBaseConstant.SPINNER_TYPE_ID)));
                    reminderType.setName(mCursor.getString(mCursor.getColumnIndex(DataBaseConstant.SPINNER_TYPE_NAME)));
                    reminder.setGeneral(reminderType);
                }

                list.add(reminder);
            } while (cursor.moveToNext());
        }

        return list;
    }

    /*TODO:-> Delete Reminder*/
    public Boolean deleteReminder(long id) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_REMINDER, REMINDER_ID + "=" + id, null) > 0;
    }

    /*TODO:-> Add Bank*/
    public void addAllBanks(List<BankResponse> responseList) {
        sqLiteDatabase.beginTransaction();
        ContentValues values;
        try {
            for (BankResponse bankResponse : responseList) {
                values = new ContentValues();
                values.put(BANK_ID, bankResponse.getId());
                values.put(BANK_NAME, bankResponse.getName());
                sqLiteDatabase.insert(TABLE_BANK, null, values);
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    /*TODO:-> Get Bank*/
    public List<General> getBankById(String columnName, long id) {
        List<General> list = new ArrayList<>();
        General bank = null;
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor;
        if (columnName == null && id == 0) {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_BANK, null);
        } else {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_BANK + " WHERE " + columnName + "=" + id, null);
        }
        if (cursor.moveToFirst()) {
            do {
                bank = new General();
                bank.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.BANK_ID)));
                bank.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.BANK_NAME)));
                bank.setIcon(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.BANK_ICON)));

                list.add(bank);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /*TODO:-> Add Repeat Spinner Alarm Type*/
    public void addAllRepeatAlarm(List<RepeatAlarmResponse> responseList) {
        sqLiteDatabase.beginTransaction();
        ContentValues values;
        try {
            for (RepeatAlarmResponse repeatAlarmResponse : responseList) {
                values = new ContentValues();
                values.put(REPEAT_ALARM_TYPE_ID, repeatAlarmResponse.getId());
                values.put(REPEAT_ALARM_TYPE_NAME, repeatAlarmResponse.getName());
                sqLiteDatabase.insert(TABLE_REPEAT_ALARM_TYPE, null, values);
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    /*TODO:-> Get Repeat Spinner Alarm Type*/
    public List<General> getAllAlarmType() {
        List<General> alarmTypeList = new ArrayList<>();
        General alarmType = null;
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_REPEAT_ALARM_TYPE, null);
        if (cursor.moveToFirst()) {
            do {
                alarmType = new General();
                alarmType.setId(cursor.getLong(cursor.getColumnIndex(REPEAT_ALARM_TYPE_ID)));
                alarmType.setName(cursor.getString(cursor.getColumnIndex(REPEAT_ALARM_TYPE_NAME)));
                alarmTypeList.add(alarmType);
            } while (cursor.moveToNext());
        }
        return alarmTypeList;
    }

    /*TODO:-> Add/Edit Particular Payment Mode*/
    public long addParticularPaymentMode(ParticularPayment particularPayment, long particularPaymentId) {
        long createId = 0;
        long editId = 0;
        openDataBase();
        ContentValues values = new ContentValues();
        values.put(PARTICULAR_PAYMENT_ID, particularPayment.getParticularPaymentId());
        values.put(USER_ID_PAYMENT, particularPayment.getUserId());
        values.put(PAYMENT_OPTION_ID, particularPayment.getPaymentId());
        values.put(ACCOUNT_BANK_ID, particularPayment.getBankId());
        values.put(ACCOUNT_USERNAME, particularPayment.getUsername());
        values.put(ACCOUNT_IFSC, particularPayment.getIfsc());
        values.put(ACCOUNT_URL, particularPayment.getUrl());
        values.put(PARTICULAR_PAYMENT_ALIAS, particularPayment.getAliasName());
        values.put(PARTICULAR_PAYMENT_MOBILE, particularPayment.getMobile());
        values.put(PARTICULAR_PAYMENT_AGENT_NAME, particularPayment.getAgentName());
        values.put(PARTICULAR_PAYMENT_EMAIL, particularPayment.getEmail());
        values.put(PARTICULAR_PAYMENT_OWNER, particularPayment.getOwnerName());
        values.put(PARTICULAR_PAYMENT_NUMBER, particularPayment.getPaymentNumber());
        values.put(COLUMN_CARD_VALIDITY, particularPayment.getCardValidity());
        values.put(COLUMN_CARD_CVV, particularPayment.getCvv());
        values.put(PARTICULAR_PAYMENT_TYPE, particularPayment.getPaymentType());
        values.put(PARTICULAR_PAYMENT_ICON, particularPayment.getPaymentIcon());


        if (particularPaymentId == 0) {
            createId = sqLiteDatabase.insert(TABLE_PARTICULAR_PAYMENT_MODE, null, values);
            return createId;
        } else {
            editId = sqLiteDatabase.update(TABLE_PARTICULAR_PAYMENT_MODE, values, "" + PARTICULAR_PAYMENT_ID + " = '" + particularPaymentId + "'", null);
            return editId;
        }
    }

    /*TODO:-> Get Particular Payment Mode*/
    public List<ParticularPayment> getParticularPaymentById(String columnName, long id, String paymentType) {
        showLog("DB ID: " + id);
        List<ParticularPayment> list = new ArrayList<>();
        ParticularPayment particularPayment = null;
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor;
        if (columnName == null && id == 0 && paymentType == null) {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_PARTICULAR_PAYMENT_MODE, null);
        } else if (paymentType != null) {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_PARTICULAR_PAYMENT_MODE + " WHERE " + PARTICULAR_PAYMENT_TYPE + " = '" + paymentType + "'", null);
        } else {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_PARTICULAR_PAYMENT_MODE + " WHERE " + columnName + "=" + id, null);
        }
        if (cursor.moveToFirst()) {
            do {
                particularPayment = new ParticularPayment();
                particularPayment.setParticularPaymentId(cursor.getLong(cursor.getColumnIndex(PARTICULAR_PAYMENT_ID)));
                particularPayment.setPaymentId(cursor.getLong(cursor.getColumnIndex(PAYMENT_OPTION_ID)));
                particularPayment.setUsername(cursor.getString(cursor.getColumnIndex(ACCOUNT_USERNAME)));
                particularPayment.setBankId(cursor.getLong(cursor.getColumnIndex(ACCOUNT_BANK_ID)));
                particularPayment.setIfsc(cursor.getString(cursor.getColumnIndex(ACCOUNT_IFSC)));
                particularPayment.setAliasName(cursor.getString(cursor.getColumnIndex(PARTICULAR_PAYMENT_ALIAS)));
                particularPayment.setUrl(cursor.getString(cursor.getColumnIndex(ACCOUNT_URL)));
                particularPayment.setAgentName(cursor.getString(cursor.getColumnIndex(PARTICULAR_PAYMENT_AGENT_NAME)));
                particularPayment.setEmail(cursor.getString(cursor.getColumnIndex(PARTICULAR_PAYMENT_EMAIL)));
                particularPayment.setMobile(cursor.getString(cursor.getColumnIndex(PARTICULAR_PAYMENT_MOBILE)));
                particularPayment.setPaymentNumber(cursor.getString(cursor.getColumnIndex(PARTICULAR_PAYMENT_NUMBER)));
                particularPayment.setCardValidity(cursor.getString(cursor.getColumnIndex(COLUMN_CARD_VALIDITY)));
                particularPayment.setCvv(cursor.getString(cursor.getColumnIndex(COLUMN_CARD_CVV)));
                particularPayment.setPaymentType(cursor.getString(cursor.getColumnIndex(PARTICULAR_PAYMENT_TYPE)));
                particularPayment.setPaymentIcon(cursor.getInt(cursor.getColumnIndex(PARTICULAR_PAYMENT_ICON)));
                particularPayment.setOwnerName(cursor.getString(cursor.getColumnIndex(PARTICULAR_PAYMENT_OWNER)));

                General bank = new General();
                Cursor mCursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_BANK + " WHERE " + DataBaseConstant.BANK_ID + " = " + cursor.getInt(cursor.getColumnIndex(ACCOUNT_BANK_ID)), null);
                if (mCursor.moveToFirst()) {
                    bank.setId(mCursor.getLong(mCursor.getColumnIndex(DataBaseConstant.BANK_ID)));
                    bank.setName(mCursor.getString(mCursor.getColumnIndex(DataBaseConstant.BANK_NAME)));
                    bank.setIcon(mCursor.getInt(mCursor.getColumnIndex(DataBaseConstant.BANK_ICON)));
                    bank.setBankLink(mCursor.getString(mCursor.getColumnIndex(DataBaseConstant.BANK_LINK)));
                    particularPayment.setBank(bank);
                }

                list.add(particularPayment);
            } while (cursor.moveToNext());
        }
        return list;
    }

    /*TODO:-> Delete Particular Payment Mode*/
    public Boolean deleteParticularPaymentMode(long id) {
        sqLiteDatabase = dataBaseHelper.getWritableDatabase();
        return sqLiteDatabase.delete(DataBaseConstant.TABLE_PARTICULAR_PAYMENT_MODE, PARTICULAR_PAYMENT_ID + "=" + id, null) > 0;
    }

    /*TODO:-> Add Reminder Spinner Type*/
    public void addAllReminderSpinner(List<ReminderSpinnerListResponse> responseList) {
        sqLiteDatabase.beginTransaction();
        ContentValues values;
        try {
            for (ReminderSpinnerListResponse response : responseList) {
                values = new ContentValues();
                values.put(SPINNER_TYPE_ID, response.getId());
                values.put(SPINNER_TYPE_NAME, response.getName());
                sqLiteDatabase.insert(TABLE_REMINDER_SPINNER_TYPE, null, values);
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }


    /*TODO:-> Get Reminder Spinner Type*/
    public List<General> getAllReminderType() {
        List<General> reminderTypeList = new ArrayList<>();
        General reminderType = null;
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_REMINDER_SPINNER_TYPE, null);
        if (cursor.moveToFirst()) {
            do {
                reminderType = new General();
                reminderType.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.SPINNER_TYPE_ID)));
                reminderType.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.SPINNER_TYPE_NAME)));
                reminderType.setIcon(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.SPINNER_TYPE_ICON)));
                reminderTypeList.add(reminderType);
            } while (cursor.moveToNext());
        }
        return reminderTypeList;
    }

    /*TODO:-> Add Providers*/
    public void addAllProviders(List<ProviderResponse> responseList) {
        sqLiteDatabase.beginTransaction();
        ContentValues values;
        try {
            for (ProviderResponse providerResponse : responseList) {
                values = new ContentValues();
                values.put(PROVIDER_ID, providerResponse.getProviderId());
                values.put(PROVIDER_NAME, providerResponse.getProviderName());
                values.put(PROVIDER_CATEGORY_ID, providerResponse.getCategoryId());
                sqLiteDatabase.insert(TABLE_PROVIDER, null, values);
            }
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }


    /*TODO:-> Get Providers*/
    public List<General> getProviderById(String columnName, long id) {
        List<General> list = new ArrayList<>();
        General provider = null;
        Cursor cursor;
        sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        if (columnName == null && id == 0) {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_PROVIDER, null);
        } else {
            cursor = sqLiteDatabase.rawQuery(DataBaseConstant.SELECT_PROVIDER + " WHERE " + columnName + " = " + id, null);
        }

        if (cursor.moveToFirst()) {
            do {
                provider = new General();
                provider.setId(cursor.getLong(cursor.getColumnIndex(DataBaseConstant.PROVIDER_ID)));
                provider.setName(cursor.getString(cursor.getColumnIndex(DataBaseConstant.PROVIDER_NAME)));
                provider.setIcon(cursor.getInt(cursor.getColumnIndex(DataBaseConstant.PROVIDER_ICON)));
                list.add(provider);
            } while (cursor.moveToNext());
        }
        return list;
    }
}