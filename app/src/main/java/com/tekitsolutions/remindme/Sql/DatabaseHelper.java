package com.tekitsolutions.remindme.Sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private String query;

    public DatabaseHelper(Context context) {
        super(context, DataBaseConstant.DATABASE_NAME
                , null, DataBaseConstant.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_REMINDER_SPINNER_TYPE);
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_REPEAT_ALARM_TYPE);
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_PAYMENT);
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_SUB_PROVIDER);
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_PROVIDER);
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_CATEGORY);
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_BANK);
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_REMINDER);
            sqLiteDatabase.execSQL(DataBaseConstant.CREATE_TABLE_PARTICULAR_PAYMENT_MODE);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        query = "DROP TABLE IF EXISTS " + DataBaseConstant.CREATE_TABLE_SUB_PROVIDER;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + DataBaseConstant.CREATE_TABLE_PROVIDER;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + DataBaseConstant.CREATE_TABLE_CATEGORY;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + DataBaseConstant.CREATE_TABLE_BANK;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + DataBaseConstant.CREATE_TABLE_REMINDER;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + DataBaseConstant.CREATE_TABLE_REMINDER_SPINNER_TYPE;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + DataBaseConstant.CREATE_TABLE_REPEAT_ALARM_TYPE;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + DataBaseConstant.CREATE_TABLE_PARTICULAR_PAYMENT_MODE;
        db.execSQL(query);
        query = "DROP TABLE IF EXISTS " + DataBaseConstant.CREATE_TABLE_PAYMENT;
        db.execSQL(query);

        /*TODO:-> Create tables again*/
        onCreate(db);
    }
}
