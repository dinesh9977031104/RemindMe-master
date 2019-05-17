package com.tekitsolutions.remindme.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tekitsolutions.remindme.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tekitsolutions.remindme.Utils.CommonUtils.PASSWORD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SPLASH_SCREEN;

public class ChangePasscodeActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = ChangePasscodeActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.alias_name)
    EditText currentPassword;
    @BindView(R.id.mobile_number)
    EditText newPassword;
    @BindView(R.id.url_link)
    EditText confirmPassword;
    @BindView(R.id.confirm)
    Button confirm;

    @BindView(R.id.tv_current_passcode_error)
    TextView tvCurrentPasscodeError;
    @BindView(R.id.tv_new_passcode_error)
    TextView tvNewPasscodeError;
    @BindView(R.id.tv_confirm_passcode_error)
    TextView tvConfirmPasscodeError;

    private String getCurrentPwd, getNewPwd, getConfirmPwd, getUserPwd;
    private AlertDialog.Builder alertDialogBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(ChangePasscodeActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_passcode);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.change_pwd));

        SharedPreferences shared = getSharedPreferences(SPLASH_SCREEN, 0);
        getUserPwd = shared.getString(PASSWORD, "");


        confirmPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    confirm.performClick();
                }
                return false;
            }
        });
        setOnClickListener();
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

    public void showAlert(String title, String msg, final Boolean close) {
        if (alertDialogBuilder == null) {
            alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        }
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle(title).setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (close)
                    finish();
            }
        }).show();
    }

    private void setOnClickListener() {
        confirm.setOnClickListener(this);
    }

    private boolean checkValidation() {
        getCurrentPwd = currentPassword.getText().toString();
        getNewPwd = newPassword.getText().toString();
        getConfirmPwd = confirmPassword.getText().toString();

        if (getCurrentPwd.isEmpty()) {
            //     showAlert("Current password error","Current password can't be empty", false);
            //  currentPassword.setError(getResources().getString(R.string.current_password_can_not_empty));
            showErrorMsg(tvCurrentPasscodeError);
            tvCurrentPasscodeError.setText(getResources().getString(R.string.current_password_can_not_empty));
            tvNewPasscodeError.setVisibility(View.GONE);
            tvConfirmPasscodeError.setVisibility(View.GONE);
            return false;
        }

        if (!getCurrentPwd.equals(getUserPwd)) {
            //  showAlert("", getResources().getString(R.string.current_password_error), false);
            // currentPassword.setError(getResources().getString(R.string.current_password_error));
            showErrorMsg(tvCurrentPasscodeError);
            tvCurrentPasscodeError.setText(getResources().getString(R.string.current_password_error));
            tvNewPasscodeError.setVisibility(View.GONE);
            tvConfirmPasscodeError.setVisibility(View.GONE);
            //  currentPassword.getText().clear();
            return false;
        }

        if (getNewPwd.isEmpty()) {
            //     showAlert("", getResources().getString(R.string.new_password_error), false);
            // newPassword.setError(getResources().getString(R.string.new_password_error));
            showErrorMsg(tvNewPasscodeError);
            tvNewPasscodeError.setText(getResources().getString(R.string.new_password_error));
            tvCurrentPasscodeError.setVisibility(View.GONE);
            tvConfirmPasscodeError.setVisibility(View.GONE);
            return false;
        }

        if (getNewPwd.length() != 4) {
            //     showAlert("", getResources().getString(R.string.new_password_error), false);
            // newPassword.setError(getResources().getString(R.string.four_digit));
            showErrorMsg(tvNewPasscodeError);
            tvNewPasscodeError.setText(getResources().getString(R.string.four_digit));
            tvCurrentPasscodeError.setVisibility(View.GONE);
            tvConfirmPasscodeError.setVisibility(View.GONE);
            return false;
        }

        if (getConfirmPwd.isEmpty()) {
            // showAlert("", getResources().getString(R.string.confirm_password_error), false);
            //confirmPassword.setError(getResources().getString(R.string.confirm_password_error));
            showErrorMsg(tvConfirmPasscodeError);
            tvConfirmPasscodeError.setText(getResources().getString(R.string.confirm_password_error));
            tvCurrentPasscodeError.setVisibility(View.GONE);
            tvNewPasscodeError.setVisibility(View.GONE);
            return false;
        }

        if (!getConfirmPwd.equals(getNewPwd)) {
            showAlert(getResources().getString(R.string.alert), getResources().getString(R.string.password_not_match), false);
            newPassword.getText().clear();
            confirmPassword.getText().clear();
        }

        return true;
    }

    private void showErrorMsg(TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                if (checkValidation()) {
                    if (getConfirmPwd.equals(getNewPwd)) {
                        final android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this, R.style.dialogBoxStyle);
                        alertDialogBuilder.setTitle(getResources().getString(R.string.success)).setMessage(getResources().getString(R.string.password_has_bin_change)).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences settings = getSharedPreferences(SPLASH_SCREEN, 0);
                                SharedPreferences.Editor editor = settings.edit();
                                editor.putString(PASSWORD, getConfirmPwd);
                                editor.apply();


                                Intent intent = new Intent(ChangePasscodeActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
                    }
                }
                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}
