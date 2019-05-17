package com.tekitsolutions.remindme.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.facebook.stetho.Stetho;
import com.tekitsolutions.remindme.Activity.CreatePasswordActivity;
import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;

import static com.tekitsolutions.remindme.Utils.CommonUtils.CHECKED_ITEM;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PASSWORD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SPLASH_SCREEN;

public class ReminderApp extends Application implements Application.ActivityLifecycleCallbacks {
    public static final String NIGHT_MODE = "NIGHT_MODE";
    private static final String TAG = ReminderApp.class.getSimpleName();
    private static Context mContext;
    private static ReminderApp mInstance;

    private static boolean isChanged = false;
    public DatabaseAdapter adapter;
    public boolean isNightModeEnabled = false;
    HashMap<String, List<General>> providerHashmap;
    HashMap<String, List<General>> categoryHashmap;
    HashMap<String, List<General>> subProviderMap;
    private General allGeneral;
    private ArrayList<General> subInsurance = new ArrayList<>();
    private String[] lifeIns, carIns, bikeIns, termIns, healthIns, mainCategory, waterProvider, mainProviders, billProvider, landlineProvider, gasProvider, insuranceProvider, categoryName;
    private TypedArray lifeIcon, carIcon, bikeIcon, termIcon, healthIcon, billIcon, waterIcon, landlineIcon, gasIcon, categoryIcon, insuranceIcon;
    private int activityReferences = 0;
    private String getUserPwd;
    private boolean isActivityChangingConfigurations = false;
    private EditText passcode;
    private TextView forgot;
    private Dialog dialog;
    private SharedPreferences.Editor editor;

    public static Context getContext() {
        return mContext;
    }

    public static ReminderApp getInstance() {
        return mInstance;
    }

    public static boolean isChanged() {
        return isChanged;
    }

    public static void setChanged(boolean change) {
        isChanged = change;
    }

    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        mInstance = this;
        Stetho.initializeWithDefaults(this);
        registerActivityLifecycleCallbacks(this);
        Fabric.with(this, new Crashlytics());
        adapter = new DatabaseAdapter(this);
        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        this.isNightModeEnabled = mPrefs.getBoolean(NIGHT_MODE, false);
        updateUI(false);

        SharedPreferences shared = getSharedPreferences(SPLASH_SCREEN, 0);
        getUserPwd = shared.getString(PASSWORD, "");

    }

    public boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }

    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;

        SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = mPrefs.edit();
        editor.putBoolean(NIGHT_MODE, isNightModeEnabled);
        editor.apply();
    }

    public void updateUI(Boolean changeLanguage) {
        if (changeLanguage == true) {
            adapter.dropTables();
        }

        providerHashmap = new LinkedHashMap<String, List<General>>();
        categoryHashmap = new LinkedHashMap<String, List<General>>();
        subProviderMap = new LinkedHashMap<String, List<General>>();

        allGeneral = new General();

        lifeIns = getResources().getStringArray(R.array.life_insurance_name);
        carIns = getResources().getStringArray(R.array.car_insurance_name);
        bikeIns = getResources().getStringArray(R.array.bike_insurance_name);
        termIns = getResources().getStringArray(R.array.term_insurance_name);
        healthIns = getResources().getStringArray(R.array.health_insurance_name);
        billProvider = getResources().getStringArray(R.array.provider_list);
        landlineProvider = getResources().getStringArray(R.array.landline_provider_name);
        gasProvider = getResources().getStringArray(R.array.gas_provider_name);
        waterProvider = getResources().getStringArray(R.array.water_provider_name);
        categoryName = getResources().getStringArray(R.array.category_list);

        lifeIcon = getResources().obtainTypedArray(R.array.life_insurance_icon);
        carIcon = getResources().obtainTypedArray(R.array.car_insurance_icon);
        bikeIcon = getResources().obtainTypedArray(R.array.bike_insurance_icon);
        termIcon = getResources().obtainTypedArray(R.array.term_insurance_icon);
        healthIcon = getResources().obtainTypedArray(R.array.health_insurance_icon);
        billIcon = getResources().obtainTypedArray(R.array.provider_icon_list);
        landlineIcon = getResources().obtainTypedArray(R.array.landline_provider_icon);
        gasIcon = getResources().obtainTypedArray(R.array.gas_provider_icon);
        waterIcon = getResources().obtainTypedArray(R.array.water_provider_icon);
        categoryIcon = getResources().obtainTypedArray(R.array.category_icon_list);

        mainCategory = getResources().getStringArray(R.array.main_provider_list);
        mainProviders = getResources().getStringArray(R.array.main_provider_list);
    }

    private void storeInSharedPreference(String newLanguage) {
        editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_lang", newLanguage);
        editor.apply();
    }

    public void setLanguageForLowerVersion(String newLanguage) {
        Locale locale = new Locale(newLanguage);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        storeInSharedPreference(newLanguage);
    }

    public void setLanguageForHigherVersion(String newLanguage, Context mContext) {
        Configuration config = mContext.getResources().getConfiguration();
        Locale locale = new Locale(newLanguage);
        config.setLocale(locale);
        mContext.getResources().updateConfiguration(config, mContext.getResources().getDisplayMetrics());
        storeInSharedPreference(newLanguage);
    }

    public void loadLocale(Context mContext) {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_lang", "");

        if (language.equals("") || language.equals("en")) {
            CHECKED_ITEM = 0;
        } else {
            CHECKED_ITEM = 1;
        }

        switch (Build.VERSION.SDK_INT) {
            case 19:
                break;

            case 21:
                break;

            case 22:
                /*TODO:-> Tested on Arpan Mathur's OPPO A37f*/
                setLanguageForHigherVersion(language, mContext);
                break;

            case 23:
                /*TODO:-> Tested on Anurag Sir's KYOCERA E6810*/
                setLanguageForLowerVersion(language);
                break;

            case 24:
                /*TODO:-> Tested on Dinesh Patidar's Moto C Plus*/
                setLanguageForLowerVersion(language);
                break;

            case 25:
                break;

            case 26:
                break;

            case 27:
                /*TODO:-> Tested on Jimmy Trivedi's Redmi Note 5 Pro*/
                setLanguageForHigherVersion(language, mContext);
                break;

            case 28:
                /*TODO:-> Tested on Pratiksha Deshmukh's MI A2*/
                setLanguageForHigherVersion(language, mContext);
                break;
        }
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        if (++activityReferences == 1 && !isActivityChangingConfigurations) {
            /*showAlertDialog(getApplicationContext());
            if (CREATE_PASSCODE_ACTIVITY == 0) {
                dialog.show();
            }*/
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {
      /*  if (CREATE_PASSCODE_ACTIVITY == 1) {
            dialog.cancel();
        }
        if (CREATE_PASSCODE_ACTIVITY == 2) {
            dialog.cancel();
        }
        if (CREATE_PASSCODE_ACTIVITY == 3) {
            dialog.cancel();
        }*/
    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        isActivityChangingConfigurations = activity.isChangingConfigurations();
        if (--activityReferences == 0 && !isActivityChangingConfigurations) {
            //     dialog.cancel();
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    private void showAlertDialog(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.myPasscodeAlertDialog)
                .setCancelable(false);

        View view = inflater
                .inflate(R.layout.row_passcode_dialog, null);
        passcode = view.findViewById(R.id.passcode);

        forgot = view.findViewById(R.id.forgot);
        setTextWatcher();
        setClickListener();
        builder.setView(view);


        dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (Build.VERSION.SDK_INT >= 27) {
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        } else {
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        }

        dialog.show();


    }

    private void setClickListener() {
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CreatePasswordActivity.class));
            }
        });
    }

    private void setTextWatcher() {
        passcode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 4) {
                    if (passcode.getText().toString().equals(getUserPwd)) {
                        dialog.cancel();
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.validate_passcode), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}