package com.tekitsolutions.remindme.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tekitsolutions.remindme.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tekitsolutions.remindme.Utils.CommonUtils.PASSWORD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SPLASH_SCREEN;

public class AlertDialogActivity extends BaseActivity {
    private static final String TAG = AlertDialogActivity.class.getSimpleName();

    @BindView(R.id.forgot)
    TextView forgot;
    @BindView(R.id.passcode)
    EditText passcode;

    private String getUserPwd;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        ButterKnife.bind(this);
        SharedPreferences shared = getSharedPreferences(SPLASH_SCREEN, 0);
        getUserPwd = shared.getString(PASSWORD, "");
        showAlertDialog(AlertDialogActivity.this);
    }

    public void showAlertDialog(final Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setCancelable(false);

        setTextWatcher();
        setClickListener();

        dialog = builder.create();
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
