package com.tekitsolutions.remindme.Sql;

public class DataBaseConstant {

    /*TODO:-> Database Version*/
    public static final int DATABASE_VERSION = 1;

    /*TODO:-> Database Name*/
    public static final String DATABASE_NAME = "db_remind_me";

    /*TODO:-> Category Table*/
    public static final String TABLE_CATEGORY = "table_category";
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_ITEM = "category_item";
    public static final String CATEGORY_ICON = "category_icon";

    public static final String CREATE_TABLE_CATEGORY = " CREATE TABLE " + TABLE_CATEGORY + " ( " +
            CATEGORY_ID + " INTEGER PRIMARY KEY, " +
            CATEGORY_ITEM + " TEXT, " +
            CATEGORY_ICON + "  TEXT " +
            ")";

    public static final String SELECT_CATEGORY = " SELECT * FROM " + TABLE_CATEGORY;


    /*TODO:-> Particular Payment Table*/
    public static final String TABLE_PAYMENT = "table_payment";
    public static final String PAYMENT_ID = "payment_id";
    public static final String PAYMENT_ITEM = "payment_item";
    public static final String PAYMENT_ICON = "payment_icon";

    public static final String CREATE_TABLE_PAYMENT = " CREATE TABLE " + TABLE_PAYMENT + " ( " +
            PAYMENT_ID + " INTEGER PRIMARY KEY, " +
            PAYMENT_ITEM + " TEXT, " +
            PAYMENT_ICON + " TEXT " +
            ")";

    public static final String SELECT_PAYMENT = " SELECT * FROM " + TABLE_PAYMENT;

    /*TODO:-> Providers Table*/
    public static final String TABLE_PROVIDER = "table_provider";
    public static final String PROVIDER_ID = "provider_id";
    public static final String PROVIDER_CATEGORY_ID = "provider_category_id";
    public static final String PROVIDER_NAME = "provider_name";
    public static final String PROVIDER_ICON = "provider_icon";

    public static final String CREATE_TABLE_PROVIDER = " CREATE TABLE " + TABLE_PROVIDER + " ( " +
            PROVIDER_ID + " INTEGER PRIMARY KEY, " +
            PROVIDER_CATEGORY_ID + " INTEGER, " +
            PROVIDER_NAME + " TEXT, " +
            PROVIDER_ICON + "  TEXT " +
            ")";

    public static final String SELECT_PROVIDER = " SELECT * FROM " + TABLE_PROVIDER;

    /*TODO:-> Sub Providers Table*/
    public static final String TABLE_SUB_PROVIDER = "table_sub_provider";
    public static final String SUB_PROVIDER_ID = "sub_provider_id";
    public static final String SUB_PROVIDER_NAME = "sub_provider_name";
    public static final String SUB_PROVIDER_ICON = "sub_provider_icon";
    public static final String PROVIDER_KEY = "provider_key";

    public static final String CREATE_TABLE_SUB_PROVIDER = " CREATE TABLE " + TABLE_SUB_PROVIDER + " ( " +
            SUB_PROVIDER_ID + " INTEGER PRIMARY KEY, " +
            SUB_PROVIDER_NAME + " TEXT, " +
            SUB_PROVIDER_ICON + " TEXT, " +
            PROVIDER_KEY + " INTEGER " +
            ")";

    public static final String SELECT_SUB_PROVIDER = "SELECT * FROM " + TABLE_SUB_PROVIDER;

    /*TODO:-> Bank Table*/
    public static final String TABLE_BANK = "table_bank";
    public static final String BANK_ID = "bank_id";
    public static final String BANK_NAME = "bank_name";
    public static final String BANK_ICON = "bank_icon";
    public static final String BANK_LINK = "bank_link";

    public static final String CREATE_TABLE_BANK = " CREATE TABLE " + TABLE_BANK + " ( " +
            BANK_ID + " INTEGER PRIMARY KEY, " +
            BANK_NAME + " TEXT, " +
            BANK_LINK + " TEXT, " +
            BANK_ICON + "  TEXT " +
            ")";

    public static final String SELECT_BANK = " SELECT * FROM " + TABLE_BANK;

    /*TODO:-> Repeat Alarm Type Table*/
    public static final String TABLE_REPEAT_ALARM_TYPE = "table_repeat_alarm_spinner_type";
    public static final String REPEAT_ALARM_TYPE_ID = "repeat_alarm_id";
    public static final String REPEAT_ALARM_TYPE_NAME = "repeat_alarm_name";

    public static final String CREATE_TABLE_REPEAT_ALARM_TYPE = " CREATE TABLE " + TABLE_REPEAT_ALARM_TYPE + " ( " +
            REPEAT_ALARM_TYPE_ID + " INTEGER PRIMARY KEY, " +
            REPEAT_ALARM_TYPE_NAME + " TEXT " +
            ")";
    public static final String SELECT_REPEAT_ALARM_TYPE = " SELECT * FROM " + TABLE_REPEAT_ALARM_TYPE;

    /*TODO:-> Particular Payment Table*/
    public static final String TABLE_PARTICULAR_PAYMENT_MODE = "table_particular_payment_mode";
    public static final String PARTICULAR_PAYMENT_ID = "particular_payment_id";
    public static final String USER_ID_PAYMENT = "user_id";
    public static final String PAYMENT_OPTION_ID = "payment_option_id";
    public static final String ACCOUNT_BANK_ID = "bank_id";
    public static final String ACCOUNT_USERNAME = "account_username";
    public static final String ACCOUNT_IFSC = "account_ifsc";
    public static final String ACCOUNT_URL = "account_url";
    public static final String PARTICULAR_PAYMENT_ALIAS = "payment_alias";
    public static final String PARTICULAR_PAYMENT_MOBILE = "payment_mobile";
    public static final String PARTICULAR_PAYMENT_AGENT_NAME = "payment_agent_name";
    public static final String PARTICULAR_PAYMENT_EMAIL = "payment_email";
    public static final String PARTICULAR_PAYMENT_OWNER = "payment_owner";
    public static final String PARTICULAR_PAYMENT_NUMBER = "payment_number";
    public static final String COLUMN_CARD_VALIDITY = "card_validity";
    public static final String COLUMN_CARD_CVV = "card_cvv";
    public static final String PARTICULAR_PAYMENT_TYPE = "payment_type";
    public static final String PARTICULAR_PAYMENT_ICON = "payment_icon";

    public static final String CREATE_TABLE_PARTICULAR_PAYMENT_MODE = " CREATE TABLE " + TABLE_PARTICULAR_PAYMENT_MODE + " ( " +
            PARTICULAR_PAYMENT_ID + " NUMERIC , " +
            USER_ID_PAYMENT + "  TEXT, " +
            PAYMENT_OPTION_ID + " INTEGER," +
            PARTICULAR_PAYMENT_AGENT_NAME + " TEXT, " +
            PARTICULAR_PAYMENT_EMAIL + "  TEXT, " +
            PARTICULAR_PAYMENT_OWNER + " TEXT, " +
            PARTICULAR_PAYMENT_ALIAS + "  TEXT, " +
            PARTICULAR_PAYMENT_MOBILE + " TEXT, " +
            ACCOUNT_BANK_ID + " INTEGER, " +
            ACCOUNT_IFSC + " TEXT, " +
            ACCOUNT_URL + " TEXT, " +
            ACCOUNT_USERNAME + "  TEXT, " +
            PARTICULAR_PAYMENT_NUMBER + " TEXT," +
            PARTICULAR_PAYMENT_ICON + " INTEGER," +
            COLUMN_CARD_VALIDITY + " TEXT," +
            COLUMN_CARD_CVV + " TEXT," +
            PARTICULAR_PAYMENT_TYPE + " TEXT" +
            ")";

    public static final String SELECT_PARTICULAR_PAYMENT_MODE = " SELECT * FROM " + TABLE_PARTICULAR_PAYMENT_MODE;

    /*TODO:-> Reminder Type Spinner Table*/
    public static final String TABLE_REMINDER_SPINNER_TYPE = "table_reminder_spinner_type";
    public static final String SPINNER_TYPE_ID = "spinner_type_id";
    public static final String SPINNER_TYPE_NAME = "spinner_type_name";
    public static final String SPINNER_TYPE_ICON = "spinner_type_icon";

    public static final String CREATE_TABLE_REMINDER_SPINNER_TYPE = " CREATE TABLE " + TABLE_REMINDER_SPINNER_TYPE + " ( " +
            SPINNER_TYPE_ID + " INTEGER PRIMARY KEY, " +
            SPINNER_TYPE_NAME + " TEXT, " +
            SPINNER_TYPE_ICON + " TEXT " +
            ")";

    public static final String SELECT_REMINDER_SPINNER_TYPE = " SELECT * FROM " + TABLE_REMINDER_SPINNER_TYPE;

    /*TODO:-> Reminder Table*/
    public static final String TABLE_REMINDER = "table_reminder";
    public static final String REMINDER_ID = "reminder_id";
    public static final String USER_ID_REMINDER = "user_id";
    public static final String REMINDER_TITLE = "title";
    public static final String REMINDER_FAVORITE = "favorite_id";
    public static final String REMINDER_CATEGORY_ID = "category_id";
    public static final String REMINDER_PAYMENT_ID = "payment_id";
    public static final String REMINDER_AMOUNT = "amount";
    public static final String REMINDER_DUE_DATE = "due_date";
    public static final String REMINDER_ALARM_TIME = "alarm_time";
    public static final String REMINDER_NOTIFY_DAY = "notify_day"; //0 for same day
    public static final String REMINDER_TYPE_ID = "reminder_type_id";
    public static final String REMINDER_PARTICULAR_PAYMENT_ID = "reminder_particular_payment_id";
    public static final String REMINDER_REPEAT = "repeat";//0 for false, 1 for true
    public static final String REMINDER_DAY_WEEK_TYPE = "day_week_type";
    public static final String REMINDER_COMMON_INTERVAL_COUNT = "interval";
    public static final String REMINDER_REPEAT_SPINNER_ITEMS = "repeat_spinner_items";
    public static final String REMINDER_REPEAT_END_DATE = "repeat_end_date";
    public static final String REMINDER_REPEAT_DAYS_NAME = "repeat_days";
    public static final String REMINDER_NOTES = "notes";
    public static final String REMINDER_CREATED_AT = "created_at";
    public static final String REMINDER_CATEGORY_PROVIDER_ID = "category_provider_id";
    public static final String REMINDER_CATEGORY_SUB_PROVIDER_ID = "category_sub_provider_id";
    public static final String REMINDER_CATEGORY_PROVIDER_NUMBER = "category_provider_number";
    public static final String REMINDER_CATEGORY_ALIAS_NAME = "category_alias_name";
    public static final String REMINDER_CATEGORY_OWNER_NAME = "category_owner_name";
    public static final String REMINDER_CATEGORY_PROVIDER_NAME = "category_provider_name";
    public static final String REMINDER_CATEGORY_ACCOUNT_NUMBER = "category_account_number";

    public static final String CREATE_TABLE_REMINDER = "CREATE TABLE " + TABLE_REMINDER + " ("
            + REMINDER_ID + " NUMERIC,"
            + USER_ID_REMINDER + " TEXT,"
            + REMINDER_PARTICULAR_PAYMENT_ID + " INTEGER,"
            + REMINDER_CATEGORY_ID + " INTEGER,"
            + REMINDER_PAYMENT_ID + " INTEGER,"
            + REMINDER_CATEGORY_PROVIDER_ID + " INTEGER,"
            + REMINDER_CATEGORY_SUB_PROVIDER_ID + " INTEGER,"
            + REMINDER_TYPE_ID + " INTEGER,"
            + REMINDER_FAVORITE + " INTEGER,"
            + REMINDER_TITLE + " TEXT,"
            + REMINDER_AMOUNT + " TEXT,"
            + REMINDER_DUE_DATE + " TEXT,"
            + REMINDER_CATEGORY_PROVIDER_NUMBER + " TEXT,"
            + REMINDER_CATEGORY_ALIAS_NAME + " TEXT,"
            + REMINDER_CATEGORY_OWNER_NAME + " TEXT,"
            + REMINDER_CATEGORY_ACCOUNT_NUMBER + " TEXT,"
            + REMINDER_CATEGORY_PROVIDER_NAME + " TEXT,"
            + REMINDER_ALARM_TIME + " TEXT,"
            + REMINDER_NOTIFY_DAY + " INTEGER,"
            + REMINDER_REPEAT + " INTEGER,"
            + REMINDER_DAY_WEEK_TYPE + " TEXT,"
            + REMINDER_COMMON_INTERVAL_COUNT + " INTEGER,"
            + REMINDER_REPEAT_SPINNER_ITEMS + " TEXT,"
            + REMINDER_REPEAT_END_DATE + " TEXT,"
            + REMINDER_REPEAT_DAYS_NAME + " TEXT,"
            + REMINDER_NOTES + " TEXT,"
            + REMINDER_CREATED_AT + " TEXT "
            + ")";

    public static final String SELECT_REMINDER = " SELECT * FROM " + TABLE_REMINDER;

}