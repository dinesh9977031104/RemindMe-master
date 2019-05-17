package com.tekitsolutions.remindme.Utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtils {

    public static final int PICK_PAYMENT = 1001;
    public static final int PICK_CATEGORY = 1003;
    public static final int PICK_REPEAT_TYPE = 1004;
    public static final int PICK_BILL_PROVIDER = 1005;
    public static final int PICK_INSURANCE_PROVIDER = 1008;
    public static final int PICK_LANDLINE_PROVIDER = 1013;
    public static final int PICK_REMINDER = 1014;
    public static final int PICK_HOME = 1013;
    public static final int PICK_OFFICE = 1014;
    public static final int PICK_OTHER = 1015;
    public static final int PICK_GAS_PROVIDER = 1016;
    public static final int PICK_WATER_PROVIDER = 1016;

    public static final long HOME = 1;
    public static final long OFFICE = 2;
    public static final long OTHER = 3;

    public static final String REPEAT_INTERVAL_TYPE = "repeat_interval_type";
    public static final String REPEAT_TIMES = "repeat_times";
    public static final String REPEAT = "repeat";
    public static final String REPEAT_TYPE = "repeat_type";
    public static final String REPEAT_INTERVAL_DAYS = "REPEAT_INTERVAL_DAYS";
    public static final String GET_DAYS = "days";
    public static final String END_DATE = "End Date";
    public static final String FOREVER = "Forever";
    public static final String NUMBER_OF_TIMES = "Number of times";

    //Category name
    public static final String ELECTRICITY_BILL = "Electricity Bill";
    public static final String INSURANCE = "Insurance";
    public static final String LANDLINE = "Land Line";
    public static final String GAS = "Gas";
    public static final String WATER_BILL = "Water Bill";
    public static final String CUSTOM = "Custom";

    //Category Id
    public static final int ELECTRICITY_BILL_ID = 1;
    public static final int INSURANCE_ID = 2;
    public static final int LANDLINE_ID = 3;
    public static final int GAS_ID = 4;
    public static final int WATER_BILL_ID = 5;
    public static final int CUSTOM_ID = 6;

    public static final String MONDAY = "MO";
    public static final String TUESDAY = "TU";
    public static final String WEDNESDAY = "WE";
    public static final String THURSDAY = "TH";
    public static final String FRIDAY = "FR";
    public static final String SATURDAY = "SA";
    public static final String SUNDAY = "SU";

    public static final String DAY = "day";
    public static final String WEEK = "week";
    public static final String MONTH = "month";
    public static final String YEAR = "year";

    public static final String VISA = "Visa";
    public static final String MASTERCARD = "MasterCard";
    public static final String AMEX = "Amex";
    public static final String DISCOVER = "Discover";
    public static final String RUPAY = "RuPay";

    //Payment name
    public static final String DEBIT_CARD = "Debit Card";
    public static final String CREDIT_CARD = "Credit Card";
    public static final String NET_BANKING = "Net Banking";
    public static final String CASH_ACCOUNT = "Cash";

    //Payment Id
    public static final int CREDIT_CARD_ID = 1;
    public static final int DEBIT_CARD_ID = 2;
    public static final int NET_BANKING_ID = 3;
    public static final int CASH_ACCOUNT_ID = 4;

    public static final String PAYMENT = "payment";
    public static final String ACCOUNT_VALUE = "ACCOUNT_PAYMENT";

    //ID's
    public static final String INS_CATEGORY = "INS_CATEGORY";
    //Intent
    public static final String PAYMENT_NAME = "PAYMENT_NAME";
    public static final String EDIT_CATEGORY_INTENT = "EDIT_CATEGORY_INTENT";
    //Shared Preference
    public static final String SPLASH_SCREEN = "Splash Screen";
    public static final String PASSWORD = "PASSWORD";
    public static final String FIRST_TIME = "First Time";
    public static final String IS_EDIT = "IS_EDIT";


    //onCreate
    public static int CREATE_PASSCODE_ACTIVITY = 0;
    //DIALOGUE BUILDER
    public static int GETDAY = 0;
    public static int CHECKED_ITEM = 0;
    public static int CURRENCY_ITEM = 0;

    public CommonUtils() {
    }

    public static long milisecondsFromDate(String dateStr) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dateStr);
            return date.getTime();
        } catch (Exception e) {
            Log.e("Tag", "Wrong date Format");
        }
        return -1;
    }

}
