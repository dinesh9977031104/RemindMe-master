package com.tekitsolutions.remindme.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
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
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Receiver.ConnectivityReceiver;
import com.tekitsolutions.remindme.RestApi.ApiClient;
import com.tekitsolutions.remindme.RestApi.ApiInterface;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tekitsolutions.remindme.Utils.CommonUtils.CREATE_PASSCODE_ACTIVITY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.FIRST_TIME;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PASSWORD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SPLASH_SCREEN;

public class SplashActivity extends BaseActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    private int count, delay;
    private String password;
    private ProgressDialog progressDialog;
    private Handler handler;
    private DatabaseAdapter dbAdapter;
    private List<General> categories = new ArrayList<>();
    private List<General> providers = new ArrayList<>();
    private List<General> subProviders = new ArrayList<>();
    private List<General> banks = new ArrayList<>();
    private List<General> payments = new ArrayList<>();
    private List<General> reminderSpinnerType = new ArrayList<>();
    private List<General> repeatAlarmSpinner = new ArrayList<>();
    private List<Reminder> reminders = new ArrayList<>();
    private List<ParticularPayment> particularPayments = new ArrayList<>();
    private SharedPreferences settings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CREATE_PASSCODE_ACTIVITY = 1;
        setThemeAndLoadLocale(SplashActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
        nextActivity();
        //    installFirstTime();

        //   callPermission();
    }

    private void installFirstTime() {
        boolean isFirstRun = settings.getBoolean(FIRST_TIME, true);
        if (isFirstRun && checkConnection()) {
            showLog("1st case");
            isFirstRun = false;
            nextActivity();
        }

        if (!isFirstRun) {
            showLog("2nd case");
            nextActivity();
        }
    }

    public void init() {
        settings = getSharedPreferences(SPLASH_SCREEN, 0);
        count = 0;
        delay = 1000;
        progressDialog = new ProgressDialog(this);
        handler = new Handler();
    }

    private void database() {
        getCategoryList();
        getProviderList();
        getSubProviderList();
        getBankList();
        getPaymentList();
        getReminderSpinnerList();
        getRepeatAlarmList();
        getReminderList();
        getParticularPaymentList();

    }

    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected(SplashActivity.this);
        showNetworkStatus(isConnected);
        return true;
    }

    private void showNetworkStatus(boolean isConnected) {
        if (isConnected) {
            progressDialog.dismiss();
            showSnackbar(setStringResource(R.string.connected));
        } else {
            progressDialog.setTitle(setStringResource(R.string.check_connection));
            progressDialog.setMessage(setStringResource(R.string.wait));
            progressDialog.setCancelable(false);
            progressDialog.show();
            ProgressBar progressBar = progressDialog.findViewById(android.R.id.progress);
            runTimer(progressBar);
        }
    }

    private void runTimer(ProgressBar mProgressBar) {
        handler.postDelayed(new Runnable() {
            public void run() {
                count++;
                switch (count % 4) {
                    case 0:
                        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
                        break;
                    case 1:
                        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);
                        break;
                    case 2:
                        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN);
                        break;
                    case 3:
                        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.MAGENTA, android.graphics.PorterDuff.Mode.SRC_IN);
                        break;
                }
                handler.postDelayed(this, delay);
            }
        }, delay);
    }


    public void showSnackbar(CharSequence text) {
        View view = findViewById(android.R.id.content);
        InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_SHORT);
        view = snackbar.getView();
        TextView textView = view.findViewById(R.id.snackbar_text);
        textView.setTextColor(Color.GREEN);
        snackbar.show();
    }

    private void nextActivity() {
        password = settings.getString(PASSWORD, "");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if (password.equals("")) {
                    database();
                    Intent intent = new Intent(getApplicationContext(), WalkThroughActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), EnterPasswordActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 3000);
    }


    private void callPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                      /*  Manifest.permission.GET_TASKS,
                        Manifest.permission.RECEIVE_BOOT_COMPLETED,
                        Manifest.permission.SET_ALARM,*/
                        /* Manifest.permission.WRITE_EXTERNAL_STORAGE,*/
                        Manifest.permission.GET_ACCOUNTS)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted", Toast.LENGTH_SHORT).show();
                            nextActivity();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    private void getCategoryList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<CategoryResponse>> categoryResponse = apiInterface.getCategoryList();

        categoryResponse.enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    List<CategoryResponse> responseList = response.body();
                    dbAdapter = new DatabaseAdapter(SplashActivity.this);
                    dbAdapter.openDataBase();
                    categories.addAll(dbAdapter.getCategoryById(null, 0));
                    if (categories.size() == 0) {
                        dbAdapter.addAllCategory(responseList);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                showLog("Failed: " + t.getMessage());
            }
        });
    }

    private void getProviderList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ProviderResponse>> providerResponse = apiInterface.getProviderList();
        providerResponse.enqueue(new Callback<List<ProviderResponse>>() {
            @Override
            public void onResponse(Call<List<ProviderResponse>> call, Response<List<ProviderResponse>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    List<ProviderResponse> responseList = response.body();
                    dbAdapter = new DatabaseAdapter(SplashActivity.this);
                    dbAdapter.openDataBase();
                    providers.addAll(dbAdapter.getProviderById(null, 0));
                    if (providers.size() == 0) {
                        dbAdapter.addAllProviders(responseList);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<ProviderResponse>> call, Throwable t) {
                showLog("Failed: " + t.getMessage());
            }
        });
    }

    private void getSubProviderList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<SubProviderResponse>> subProviderResponse = apiInterface.getSubProviderList();
        subProviderResponse.enqueue(new Callback<List<SubProviderResponse>>() {
            @Override
            public void onResponse(Call<List<SubProviderResponse>> call, Response<List<SubProviderResponse>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    List<SubProviderResponse> responseList = response.body();
                    dbAdapter = new DatabaseAdapter(SplashActivity.this);
                    dbAdapter.openDataBase();
                    subProviders.addAll(dbAdapter.getSubProvidersById(null, 0));
                    if (subProviders.size() == 0) {
                        dbAdapter.addAllSubProviders(responseList);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<SubProviderResponse>> call, Throwable t) {
                showLog("Failed: " + t.getMessage());
            }
        });
    }

    private void getBankList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<BankResponse>> bankResponse = apiInterface.getBankList();
        bankResponse.enqueue(new Callback<List<BankResponse>>() {
            @Override
            public void onResponse(Call<List<BankResponse>> call, Response<List<BankResponse>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    List<BankResponse> responseList = response.body();
                    dbAdapter = new DatabaseAdapter(SplashActivity.this);
                    dbAdapter.openDataBase();
                    banks.addAll(dbAdapter.getBankById(null, 0));
                    if (banks.size() == 0) {
                        dbAdapter.addAllBanks(responseList);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<BankResponse>> call, Throwable t) {
                showLog("Failed: " + t.getMessage());
            }
        });
    }

    private void getPaymentList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<PaymentResponse>> paymentResponse = apiInterface.getPaymentList();
        paymentResponse.enqueue(new Callback<List<PaymentResponse>>() {
            @Override
            public void onResponse(Call<List<PaymentResponse>> call, Response<List<PaymentResponse>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    List<PaymentResponse> responseList = response.body();
                    dbAdapter = new DatabaseAdapter(SplashActivity.this);
                    dbAdapter.openDataBase();
                    payments.addAll(dbAdapter.getPaymentById(null, 0));
                    if (payments.size() == 0) {
                        dbAdapter.addAllPayment(responseList);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<PaymentResponse>> call, Throwable t) {
                showLog("Failed: " + t.getMessage());
            }
        });
    }

    private void getReminderSpinnerList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ReminderSpinnerListResponse>> reminderSpinnerResponse = apiInterface.getReminderSpinnerList();
        reminderSpinnerResponse.enqueue(new Callback<List<ReminderSpinnerListResponse>>() {
            @Override
            public void onResponse(Call<List<ReminderSpinnerListResponse>> call, Response<List<ReminderSpinnerListResponse>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    List<ReminderSpinnerListResponse> responseList = response.body();
                    dbAdapter = new DatabaseAdapter(SplashActivity.this);
                    dbAdapter.openDataBase();
                    reminderSpinnerType.addAll(dbAdapter.getAllReminderType());
                    if (reminderSpinnerType.size() == 0) {
                        dbAdapter.addAllReminderSpinner(responseList);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<ReminderSpinnerListResponse>> call, Throwable t) {
                showLog("Failed: " + t.getMessage());
            }
        });
    }

    private void getRepeatAlarmList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<RepeatAlarmResponse>> repeatAlarmResponse = apiInterface.getRepeatAlarmList();
        repeatAlarmResponse.enqueue(new Callback<List<RepeatAlarmResponse>>() {
            @Override
            public void onResponse(Call<List<RepeatAlarmResponse>> call, Response<List<RepeatAlarmResponse>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    List<RepeatAlarmResponse> responseList = response.body();
                    dbAdapter = new DatabaseAdapter(SplashActivity.this);
                    dbAdapter.openDataBase();
                    repeatAlarmSpinner.addAll(dbAdapter.getAllAlarmType());
                    if (repeatAlarmSpinner.size() == 0) {
                        dbAdapter.addAllRepeatAlarm(responseList);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<RepeatAlarmResponse>> call, Throwable t) {
                showLog("Failed: " + t.getMessage());
            }
        });
    }

    private void getReminderList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Reminder>> reminderList = apiInterface.getReminderList("9714594200");
        reminderList.enqueue(new Callback<List<Reminder>>() {
            @Override
            public void onResponse(Call<List<Reminder>> call, Response<List<Reminder>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    List<Reminder> responseList = response.body();
                    dbAdapter = new DatabaseAdapter(SplashActivity.this);
                    dbAdapter.openDataBase();
                    reminders.addAll(dbAdapter.getReminderById(null, 0));
                    if (reminders.size() == 0) {
                        Reminder reminder = responseList.get(0);
                        dbAdapter.addReminder(reminder, 0);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Reminder>> call, Throwable t) {
                showLog("Failed: " + t.getMessage());
            }
        });
    }

    private void getParticularPaymentList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ParticularPayment>> particularPaymentList = apiInterface.getParticularPaymentList("9714594200");
        particularPaymentList.enqueue(new Callback<List<ParticularPayment>>() {
            @Override
            public void onResponse(Call<List<ParticularPayment>> call, Response<List<ParticularPayment>> response) {
                int statusCode = response.code();

                if (statusCode == 200) {
                    List<ParticularPayment> responseList = response.body();
                    dbAdapter = new DatabaseAdapter(SplashActivity.this);
                    dbAdapter.openDataBase();
                    particularPayments.addAll(dbAdapter.getParticularPaymentById(null, 0, null));
                    if (particularPayments.size() == 0) {
                        ParticularPayment particularPayment = responseList.get(0);
                        dbAdapter.addParticularPaymentMode(particularPayment, 0);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<ParticularPayment>> call, Throwable t) {
                showLog("Failed: " + t.getMessage());
            }
        });
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }

    private String setStringResource(int resourceId) {
        return getString(resourceId);
    }
}
