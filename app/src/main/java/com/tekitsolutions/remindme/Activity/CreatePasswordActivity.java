package com.tekitsolutions.remindme.Activity;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tekitsolutions.remindme.R;

import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tekitsolutions.remindme.Utils.CommonUtils.CREATE_PASSCODE_ACTIVITY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PASSWORD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SPLASH_SCREEN;

public class CreatePasswordActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = CreatePasswordActivity.class.getSimpleName();

    @BindView(R.id.card_lock)
    CardView cardLock;
    @BindView(R.id.iv_lock)
    ImageView ivLock;
    @BindView(R.id.card_success)
    CardView cardSuccess;
    @BindView(R.id.iv_success)
    ImageView ivSuccess;
    @BindView(R.id.etCreatePassword)
    EditText etCreatePassword;
    @BindView(R.id.etConfirmPassword)
    EditText etConfirmPassword;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CREATE_PASSCODE_ACTIVITY = 2;
        setThemeAndLoadLocale(CreatePasswordActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);
        ButterKnife.bind(this);

        etConfirmPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    btnConfirm.performClick();
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

    private void setOnClickListener() {
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String text1 = etCreatePassword.getText().toString();
        etCreatePassword.getText().clear();
        String text2 = etConfirmPassword.getText().toString();
        etConfirmPassword.getText().clear();

        if (text1.equals("") || text2.equals("")) {
            Toast.makeText(CreatePasswordActivity.this, "Password should not be blank", Toast.LENGTH_SHORT).show();
        } else {
            if (text1.equals(text2)) {
                SharedPreferences settings = getSharedPreferences(SPLASH_SCREEN, 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString(PASSWORD, text1);
                editor.apply();

                cardLock.setVisibility(View.GONE);
                cardSuccess.setVisibility(View.VISIBLE);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(CreatePasswordActivity.this, "Password does not match", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }

}
