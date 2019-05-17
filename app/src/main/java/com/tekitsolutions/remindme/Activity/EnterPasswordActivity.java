package com.tekitsolutions.remindme.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tekitsolutions.remindme.R;

import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tekitsolutions.remindme.Utils.CommonUtils.CREATE_PASSCODE_ACTIVITY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PASSWORD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.SPLASH_SCREEN;

public class EnterPasswordActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = EnterPasswordActivity.class.getSimpleName();

    @BindView(R.id.etEnterPassword)
    EditText etEnterPassword;
    @BindView(R.id.tvForgetPassword)
    TextView tvForgetPassword;
    @BindView(R.id.card_lock)
    CardView cardLock;
    @BindView(R.id.doctsContainerLayout)
    View doctsContainerLayout;
    @BindView(R.id.card_success)
    CardView cardSuccess;
    @BindView(R.id.view_one)
    View viewOne;
    @BindView(R.id.view_two)
    View viewTwo;
    @BindView(R.id.view_three)
    View viewThree;
    @BindView(R.id.view_four)
    View viewFour;

    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CREATE_PASSCODE_ACTIVITY = 3;
        setThemeAndLoadLocale(EnterPasswordActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);
        ButterKnife.bind(this);
        doctsContainerLayout.setOnClickListener(this);
        // etEnterPassword.setVisibility(View.INVISIBLE);

        SharedPreferences settings = getSharedPreferences(SPLASH_SCREEN, 0);
        password = settings.getString(PASSWORD, "");

        etEnterPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    viewOne.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));
                    viewTwo.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));
                    viewThree.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));
                    viewFour.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));

                } else if (s.length() == 1) {
                    viewOne.setBackground(getResources().getDrawable(R.drawable.bg_circle_fill));
                    viewTwo.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));
                    viewThree.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));
                    viewFour.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));

                } else if (s.length() == 2) {
                    viewOne.setBackground(getResources().getDrawable(R.drawable.bg_circle_fill));
                    viewTwo.setBackground(getResources().getDrawable(R.drawable.bg_circle_fill));
                    viewThree.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));
                    viewFour.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));
                } else if (s.length() == 3) {
                    viewOne.setBackground(getResources().getDrawable(R.drawable.bg_circle_fill));
                    viewTwo.setBackground(getResources().getDrawable(R.drawable.bg_circle_fill));
                    viewThree.setBackground(getResources().getDrawable(R.drawable.bg_circle_fill));
                    viewFour.setBackground(getResources().getDrawable(R.drawable.bg_circle_transparent));

                } else if (s.length() == 4) {
                    viewFour.setBackground(getResources().getDrawable(R.drawable.bg_circle_fill));

                    String passcode = s.toString();
                    if (passcode.equals(password)) {
                        cardLock.setVisibility(View.GONE);
                        cardSuccess.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        etEnterPassword.getText().clear();
                        finish();
                    } else {
                        Toast.makeText(EnterPasswordActivity.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                        etEnterPassword.getText().clear();
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EnterPasswordActivity.this, CreatePasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        System.gc();
        System.exit(0);
    }

    @Override
    public void onClick(View view) {
        showSoftKeyboard(etEnterPassword);
    }

    public void showSoftKeyboard(View view) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }

}
