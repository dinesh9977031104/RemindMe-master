package com.tekitsolutions.remindme.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tekitsolutions.remindme.Model.ParticularPayment;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ValidationUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_TYPE;

public class AddCashActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = AddCashActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.mobile_number)
    EditText etMobileNo;
    @BindView(R.id.email_id)
    EditText etEmailId;
    @BindView(R.id.agent_name)
    EditText agentName;
    @BindView(R.id.alias_name)
    EditText aliasName;
    @BindView(R.id.btn_add_cash)
    Button btnAddCash;

    @BindView(R.id.tv_cash_holder_name_error)
    TextView tvCashHolderNameError;
    @BindView(R.id.tv_cash_agent_name_error)
    TextView tvCashAgentNameError;
    @BindView(R.id.tv_cash_agent_mobile_no_error)
    TextView tvCashAgentMobileNoError;
    @BindView(R.id.tv_cash_agent_email_error)
    TextView tvCashAgentEmailError;
    @BindView(R.id.tv_cash_alias_error)
    TextView tvCashAliasError;

    private String getName, getMobileNum, getEmail, getAlias, paymentType;
    private int paymentId = 0, paymentIcon;
    private AlertDialog.Builder builder;
    private DatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddCashActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cash);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        dbAdapter = new DatabaseAdapter(this);
        setToolbar(toolbar, getString(R.string.your_cash_record));
        paymentIcon = R.drawable.icon_cash;
        getIntentData();
        setClickListener();
        sendAsAction();
        editCash();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        paymentType = intent.getStringExtra(PARTICULAR_PAYMENT_TYPE);
    }

    private void sendAsAction() {
        etEmailId.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    btnAddCash.performClick();
                }
                return false;
            }
        });
    }

    private void setClickListener() {
        btnAddCash.setOnClickListener(this);
    }


    private void editCash() {
        if (getIntent().hasExtra(PARTICULAR_PAYMENT_ID)) {
            paymentId = getIntent().getIntExtra(PARTICULAR_PAYMENT_ID, 0);
            ParticularPayment particularPayment = dbAdapter.getParticularPaymentById(PARTICULAR_PAYMENT_ID, paymentId, null).get(0);
            if (particularPayment != null) {
                paymentIcon = R.drawable.icon_cash;
                paymentType = particularPayment.getPaymentType();
                etName.setText(particularPayment.getOwnerName());
                etMobileNo.setText(particularPayment.getMobile());
                etEmailId.setText(particularPayment.getEmail());
                agentName.setText(particularPayment.getAgentName());
                aliasName.setText(particularPayment.getAliasName());
            }

            getSupportActionBar().setTitle(getResources().getString(R.string.update_cash_record));
            btnAddCash.setText(getResources().getString(R.string.update_cash_record));
        }
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

    private boolean checkValidation() {
        getName = etName.getText().toString();
        getMobileNum = etMobileNo.getText().toString();
        getEmail = etEmailId.getText().toString();
        getAlias = aliasName.getText().toString();

        if (!getName.isEmpty() && getName.length() < 3 || getName.length() > 20) {
            // etName.setError(getResources().getString(R.string.name_length_should_three_character));
            showErrorMsg(tvCashHolderNameError);
            tvCashHolderNameError.setText(getResources().getString(R.string.name_length_should_three_character));
            tvCashAgentNameError.setVisibility(View.GONE);
            tvCashAgentMobileNoError.setVisibility(View.GONE);
            tvCashAgentEmailError.setVisibility(View.GONE);
            tvCashAliasError.setVisibility(View.GONE);
            return false;
        }

        if (!getMobileNum.isEmpty() && getMobileNum.length() != 10) {
            // etMobileNo.setError(getResources().getString(R.string.mobile_number_length_should_ten_digits));
            showErrorMsg(tvCashAgentMobileNoError);
            tvCashAgentMobileNoError.setText(getResources().getString(R.string.mobile_number_length_should_ten_digits));
            tvCashAgentNameError.setVisibility(View.GONE);
            tvCashHolderNameError.setVisibility(View.GONE);
            tvCashAgentEmailError.setVisibility(View.GONE);
            tvCashAliasError.setVisibility(View.GONE);
            return false;
        }

        if (!getEmail.isEmpty() && !ValidationUtils.validateEmail(etEmailId.getText().toString())) {
            //etEmailId.setError(getResources().getString(R.string.invalid_email_id));
            showErrorMsg(tvCashAgentEmailError);
            tvCashAgentEmailError.setText(getResources().getString(R.string.invalid_email_id));
            tvCashAgentNameError.setVisibility(View.GONE);
            tvCashAgentMobileNoError.setVisibility(View.GONE);
            tvCashHolderNameError.setVisibility(View.GONE);
            tvCashAliasError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.isEmpty()) {
            // aliasName.setError(getResources().getString(R.string.alias_name_can_not_be_empty));
            showErrorMsg(tvCashAliasError);
            tvCashAliasError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvCashAgentNameError.setVisibility(View.GONE);
            tvCashAgentMobileNoError.setVisibility(View.GONE);
            tvCashAgentEmailError.setVisibility(View.GONE);
            tvCashHolderNameError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.length() > 20) {
            //aliasName.setError(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            showErrorMsg(tvCashAliasError);
            tvCashAliasError.setText(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            tvCashAgentNameError.setVisibility(View.GONE);
            tvCashAgentMobileNoError.setVisibility(View.GONE);
            tvCashAgentEmailError.setVisibility(View.GONE);
            tvCashHolderNameError.setVisibility(View.GONE);
            return false;
        }
        return true;
    }

    private void showErrorMsg(TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
    }

   /* private void addToDatabase() {
        ContentValues values = new ContentValues();
        values.put(PARTICULAR_PAYMENT_OWNER, etName.getText().toString());
        values.put(PARTICULAR_PAYMENT_MOBILE, etMobileNo.getText().toString());
        values.put(PARTICULAR_PAYMENT_EMAIL, etEmailId.getText().toString());
        values.put(PARTICULAR_PAYMENT_AGENT_NAME, agentName.getText().toString());
        values.put(PARTICULAR_PAYMENT_ALIAS, aliasName.getText().toString());
        values.put(PARTICULAR_PAYMENT_TYPE, paymentType);
        values.put(PAYMENT_ICON, paymentIcon);
        String msg = "";
        if (paymentId == 0) {
            paymentId = (int) dbAdapter.addParticularPaymentMode(values);
            msg = getResources().getString(R.string.cash_added_successfully);
        } else {
            dbAdapter.updateParticularPaymentMode(paymentId, values);

            msg = getResources().getString(R.string.cash_update_successfully);
        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle(getResources().getString(R.string.success)).setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                intent.putExtra(PARTICULAR_PAYMENT_ID, paymentId);
                setResult(RESULT_OK, intent);
                finish();
            }
        }).show();
    }*/

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_add_cash:
                if (checkValidation()) {
                    //     addToDatabase();
                    addCashData();
                }
                break;
        }
    }

    private void addCashData() {

    }

    public void showAlert(String title, String msg, final Boolean close) {
        if (builder == null) {
            builder = new androidx.appcompat.app.AlertDialog.Builder(this, R.style.dialogBoxStyle);
        }
        builder.setCancelable(false);
        builder.setTitle(title).setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (close)
                    finish();
            }
        }).show();
    }

    @Override
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}
