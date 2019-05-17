package com.tekitsolutions.remindme.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tekitsolutions.remindme.Utils.CommonUtils.CHECKED_ITEM;
import static com.tekitsolutions.remindme.Utils.CommonUtils.CURRENCY_ITEM;

public class SettingsActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    private static final String TAG = SettingsActivity.class.getSimpleName();

    public Switch switchTheme;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_general_setting)
    TextView textViewGeneralSetting;
    @BindView(R.id.passcode)
    TextView passcode;
    @BindView(R.id.layout_currency)
    LinearLayout layoutCurrency;
    @BindView(R.id.linearLayoutCurrency)
    LinearLayout currency;
    @BindView(R.id.tv_currency)
    TextView textViewCurrency;
    @BindView(R.id.layout_language)
    LinearLayout layoutLanguage;
    @BindView(R.id.linearLayoutLanguage)
    LinearLayout linearLayoutLanguage;
    @BindView(R.id.tv_language)
    TextView tvLanguage;
    @BindView(R.id.language)
    TextView language;
    @BindView(R.id.tv_theme)
    TextView textViewTheme;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.tv_security_setting)
    TextView textViewSecuritySetting;
    Integer selection = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(SettingsActivity.this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);
        ButterKnife.bind(SettingsActivity.this);
        switchTheme = findViewById(R.id.switch_theme);
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            switchTheme.setChecked(true);
        }
        if (ReminderApp.getInstance().isNightModeEnabled()) {
            switchTheme.setChecked(true);
            textViewTheme.setText(getResources().getString(R.string.enable));
        }

        setToolbar(toolbar, getString(R.string.nav_setting));

        switchTheme.setOnCheckedChangeListener(this);


        currency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCurrencyDialog();

            }
        });


        /*if (Build.VERSION.SDK_INT >= 27) {
            layoutLanguage.setVisibility(View.GONE);
            view.setVisibility(View.GONE);
        } else {
            layoutLanguage.setVisibility(View.VISIBLE);
            view.setVisibility(View.VISIBLE);
        }*/

        // multi language
        linearLayoutLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangLanguageDialogue();
            }
        });


        passcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, ChangePasscodeActivity.class);
                startActivity(intent);
            }
        });


    }

    private void showChangLanguageDialogue() {
        final String[] listItem = getResources().getStringArray(R.array.multi_language);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this, R.style.dialogBoxStyle);
        mBuilder.setTitle("Choose Language");
        mBuilder.setSingleChoiceItems(listItem, CHECKED_ITEM, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if (which == 0) {
                    selection = which;
                    switch (Build.VERSION.SDK_INT) {
                        case 19:
                            break;

                        case 21:
                            break;

                        case 22:
                            /*TODO:-> Tested on Arpan Mathur's OPPO A37f*/
                            ReminderApp.getInstance().setLanguageForHigherVersion("en", SettingsActivity.this);
                            break;

                        case 23:
                            /*TODO:-> Tested on Anurag Sir's KYOCERA E6810*/
                            ReminderApp.getInstance().setLanguageForLowerVersion("en");
                            break;

                        case 24:
                            /*TODO:-> Tested on Dinesh Patidar's Moto C Plus*/
                            ReminderApp.getInstance().setLanguageForLowerVersion("en");
                            break;

                        case 25:
                            break;

                        case 26:
                            break;

                        case 27:
                            /*TODO:-> Tested on Jimmy Trivedi's Redmi Note 5 Pro*/
                            ReminderApp.getInstance().setLanguageForHigherVersion("en", SettingsActivity.this);
                            break;

                        case 28:
                            /*TODO:-> Tested on Pratiksha Deshmukh's MI A2*/
                            ReminderApp.getInstance().setLanguageForHigherVersion("en", SettingsActivity.this);
                            break;
                    }


                    tvLanguage.setText(listItem[which]);
                    recreate();
                    ReminderApp.getInstance().updateUI(true);
                    ReminderApp.setChanged(true);

                }
                if (which == 1) {
                    selection = which;
                    switch (Build.VERSION.SDK_INT) {
                        case 19:
                            break;

                        case 21:
                            break;

                        case 22:
                            /*TODO:-> Tested on Arpan Mathur's OPPO A37f*/
                            ReminderApp.getInstance().setLanguageForHigherVersion("hi", SettingsActivity.this);
                            break;

                        case 23:
                            /*TODO:-> Tested on Anurag Sir's KYOCERA E6810*/
                            ReminderApp.getInstance().setLanguageForLowerVersion("hi");
                            break;

                        case 24:
                            /*TODO:-> Tested on Dinesh Patidar's Moto C Plus*/
                            ReminderApp.getInstance().setLanguageForLowerVersion("hi");
                            break;

                        case 25:
                            break;

                        case 26:
                            break;

                        case 27:
                            /*TODO:-> Tested on Jimmy Trivedi's Redmi Note 5 Pro*/
                            ReminderApp.getInstance().setLanguageForHigherVersion("hi", SettingsActivity.this);
                            break;

                        case 28:
                            /*TODO:-> Tested on Pratiksha Deshmukh's MI A2*/
                            ReminderApp.getInstance().setLanguageForHigherVersion("hi", SettingsActivity.this);
                            break;
                    }
                    tvLanguage.setText(listItem[which]);
                    recreate();
                    ReminderApp.getInstance().updateUI(true);
                    ReminderApp.setChanged(true);

                }
                dialogInterface.dismiss();
            }
        });
        mBuilder.setNegativeButton(R.string.cancel_btn_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

   /* @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // refresh your views here
        super.onConfigurationChanged(newConfig);
    }*/


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        switch (id) {
            case R.id.switch_theme:
                if (isChecked) {
                    ReminderApp.getInstance().setIsNightModeEnabled(true);
                    ReminderApp.setChanged(true);
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);
                    // AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    ReminderApp.getInstance().setIsNightModeEnabled(false);
                    ReminderApp.setChanged(true);
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    startActivity(intent);
                    //  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                break;
        }
    }

    private void showCurrencyDialog() {
        final String[] listItem = getResources().getStringArray(R.array.currency_dialog);
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SettingsActivity.this, R.style.dialogBoxStyle);
        mBuilder.setTitle("Select Currency");
        mBuilder.setSingleChoiceItems(listItem, CURRENCY_ITEM, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                CURRENCY_ITEM = which;
                dialogInterface.dismiss();
            }
        });
        mBuilder.setNegativeButton(R.string.cancel_btn_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}