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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.Model.ParticularPayment;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.RestApi.ApiClient;
import com.tekitsolutions.remindme.RestApi.ApiInterface;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ValidationUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.BANK_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.BANK_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_TYPE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PAYMENT_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.IS_EDIT;

public class AddNetBankingActivity extends BaseActivity implements View.OnClickListener, Callback<String> {
    private static final String TAG = AddNetBankingActivity.class.getSimpleName();
    private static final int SELECT_BANK = 1001;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_account_number)
    EditText etAccountNumber;
    @BindView(R.id.tv_select_bank)
    TextView tvSelectBank;
    @BindView(R.id.layout_select_bank)
    RelativeLayout layoutSelectBank;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.btn_add_account)
    Button btnAddAccount;
    @BindView(R.id.et_ifsc_code)
    EditText etIFSCCode;
    @BindView(R.id.alias_name)
    EditText aliasName;
    @BindView(R.id.mobile_number)
    EditText etMobileNumber;
    @BindView(R.id.url_link)
    EditText etUrlLink;
    @BindView(R.id.agent_name)
    EditText agentName;
    @BindView(R.id.agent_email)
    EditText agentEmail;

    @BindView(R.id.tv_bank_name_error)
    TextView tvBankNameError;
    @BindView(R.id.tv_bank_account_no_error)
    TextView tvBankAccountNoError;
    @BindView(R.id.tv_bank_user_name_error)
    TextView tvBankUserNameError;
    @BindView(R.id.tv_bank_ifsc_code_error)
    TextView tvBankIfscCodeError;
    @BindView(R.id.tv_bank_url_error)
    TextView tvBankURLError;
    @BindView(R.id.tv_bank_alias_error)
    TextView tvBankAliasError;

    private DatabaseAdapter dbAdapter;
    private long bankId, paymentId;
    private int bankIcon;
    private long particularPaymentId = 0, tempId = 0;
    private String getAccountNum, getUserName, getIFSC, getAlias, getMobileNum, getURL, paymentType;
    private AlertDialog.Builder builder;
    private General bank;
    private ParticularPayment particularPayment;
    private boolean isEditPayment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddNetBankingActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        dbAdapter = new DatabaseAdapter(this);
        setToolbar(toolbar, getString(R.string.add_account));
        sendAsAction();
        getIntentData();
        setClickListener();
        editAccount();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        paymentType = intent.getStringExtra(PARTICULAR_PAYMENT_TYPE);
        paymentId = intent.getLongExtra(PAYMENT_ID, 0);

        /*TODO:-> Edit case*/
        if (intent.hasExtra(PARTICULAR_PAYMENT_ID)) {
            isEditPayment = intent.getBooleanExtra(IS_EDIT, false);
            particularPaymentId = getIntent().getLongExtra(PARTICULAR_PAYMENT_ID, 0);
            if (particularPaymentId != 0) {
                showLog("ID: " + particularPaymentId);
                particularPayment = dbAdapter.getParticularPaymentById(PARTICULAR_PAYMENT_ID, particularPaymentId, null).get(0);
            }
        }
    }

    private void sendAsAction() {
        etUrlLink.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    btnAddAccount.performClick();
                }
                return false;
            }
        });
    }

    private void setClickListener() {
        layoutSelectBank.setOnClickListener(this);
        btnAddAccount.setOnClickListener(this);
    }

    private void editAccount() {
        if (particularPayment != null) {
            particularPaymentId = particularPayment.getParticularPaymentId();
            paymentId = particularPayment.getPaymentId();
            paymentType = particularPayment.getPaymentType();
            etAccountNumber.setText(particularPayment.getPaymentNumber());
            etIFSCCode.setText(particularPayment.getIfsc());
            aliasName.setText(particularPayment.getAliasName());
            etMobileNumber.setText(particularPayment.getMobile());
            etUrlLink.setText(particularPayment.getUrl());
            bankId = particularPayment.getBankId();
            bank = dbAdapter.getBankById(BANK_ID, bankId).get(0);
            tvSelectBank.setText(bank.getName());
            bankIcon = particularPayment.getPaymentIcon();
            etUsername.setText(particularPayment.getUsername());
            agentName.setText(particularPayment.getAgentName());
            agentEmail.setText(particularPayment.getEmail());
            getSupportActionBar().setTitle(getResources().getString(R.string.update_account));
            btnAddAccount.setText(getResources().getString(R.string.update_account));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_select_bank:
                startActivityForResult(new Intent(this, BankListActivity.class), SELECT_BANK);
                break;

            case R.id.btn_add_account:
                if (checkValidation()) {
                    addNetBankingData();
                }
                break;
        }
    }


    private void addNetBankingData() {
        bank = dbAdapter.getBankById(BANK_ID, bankId).get(0);
        bankIcon = bank.getIcon();


        if (isEditPayment == true) {
            tempId = particularPaymentId;
        } else {
            tempId = System.currentTimeMillis();
        }


        particularPayment = new ParticularPayment();
        particularPayment.setParticularPaymentId(tempId);
        particularPayment.setUserId("9714594200");
        particularPayment.setPaymentId(paymentId);
        particularPayment.setBankId(bankId);
        particularPayment.setPaymentNumber(etAccountNumber.getText().toString());
        particularPayment.setUsername(etUsername.getText().toString());
        particularPayment.setIfsc(etIFSCCode.getText().toString());
        particularPayment.setAliasName(aliasName.getText().toString());
        particularPayment.setMobile(etMobileNumber.getText().toString());
        particularPayment.setUrl(etUrlLink.getText().toString());
        particularPayment.setAgentName(agentEmail.getText().toString());
        particularPayment.setEmail(agentEmail.getText().toString());
        particularPayment.setPaymentType(paymentType);
        particularPayment.setPaymentIcon(bankIcon);

        if (checkConnection(AddNetBankingActivity.this)) {
            addToServer();
        }
    }

    private void addToServer() {
        saveOnServer();

        String message = "";
        if (!isEditPayment) {
            message = getResources().getString(R.string.account_added_successfully);
        } else {
            message = getResources().getString(R.string.account_update_successfully);

        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle(getResources().getString(R.string.success)).setMessage(message).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                intent.putExtra(PARTICULAR_PAYMENT_ID, particularPaymentId);
                setResult(RESULT_OK, intent);
                finish();
            }
        }).show();
    }

    private void saveOnServer() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<String> particularPaymentList = apiInterface.addPayment(particularPayment);
        particularPaymentList.enqueue(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_BANK) {
            bankId = data.getLongExtra(BANK_ID, 0);
            tvSelectBank.setText(data.getStringExtra(BANK_NAME));
        }
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

    private boolean checkValidation() {
        getAccountNum = etAccountNumber.getText().toString();
        getUserName = etUsername.getText().toString();
        getIFSC = etIFSCCode.getText().toString();
        getAlias = aliasName.getText().toString();
        getMobileNum = etMobileNumber.getText().toString();
        getURL = etUrlLink.getText().toString();

        if (bankId == 0) {
            // showAlert(getResources().getString(R.string.bank_error), getResources().getString(R.string.please_select_a_bank), false);
            showErrorMsg(tvBankNameError);
            tvBankNameError.setText(getResources().getString(R.string.please_select_a_bank));
            tvBankAccountNoError.setVisibility(View.GONE);
            tvBankUserNameError.setVisibility(View.GONE);
            tvBankIfscCodeError.setVisibility(View.GONE);
            tvBankURLError.setVisibility(View.GONE);
            tvBankAliasError.setVisibility(View.GONE);
            return false;
        }

        if (getAccountNum.isEmpty()) {
            // etAccountNumber.setError(getResources().getString(R.string.account_number_can_not_empty));
            showErrorMsg(tvBankAccountNoError);
            tvBankAccountNoError.setText(getResources().getString(R.string.account_number_can_not_empty));
            tvBankNameError.setVisibility(View.GONE);
            tvBankUserNameError.setVisibility(View.GONE);
            tvBankIfscCodeError.setVisibility(View.GONE);
            tvBankURLError.setVisibility(View.GONE);
            tvBankAliasError.setVisibility(View.GONE);
            return false;
        }

        if (getAccountNum.length() < 11 || getAccountNum.length() > 16) {
            // etAccountNumber.setError(getResources().getString(R.string.account_number_length_should_be_eleven));
            showErrorMsg(tvBankAccountNoError);
            tvBankAccountNoError.setText(getResources().getString(R.string.account_number_length_should_be_eleven));
            tvBankNameError.setVisibility(View.GONE);
            tvBankUserNameError.setVisibility(View.GONE);
            tvBankIfscCodeError.setVisibility(View.GONE);
            tvBankURLError.setVisibility(View.GONE);
            tvBankAliasError.setVisibility(View.GONE);
            return false;
        }


        if (!getUserName.isEmpty() && (getUserName.length() < 3 || getUserName.length() > 15)) {
            // etUsername.setError(getResources().getString(R.string.user_name_length_should_be_three_digits));
            showErrorMsg(tvBankUserNameError);
            tvBankUserNameError.setText(getResources().getString(R.string.user_name_length_should_be_three_digits));
            tvBankNameError.setVisibility(View.GONE);
            tvBankAccountNoError.setVisibility(View.GONE);
            tvBankIfscCodeError.setVisibility(View.GONE);
            tvBankURLError.setVisibility(View.GONE);
            tvBankAliasError.setVisibility(View.GONE);
            return false;
        }

        if (!getIFSC.isEmpty() && getIFSC.length() != 11) {
            // etIFSCCode.setError(getResources().getString(R.string.ifsc_code_length_should_eleven_digits));
            showErrorMsg(tvBankIfscCodeError);
            tvBankIfscCodeError.setText(getResources().getString(R.string.ifsc_code_length_should_eleven_digits));
            tvBankNameError.setVisibility(View.GONE);
            tvBankAccountNoError.setVisibility(View.GONE);
            tvBankUserNameError.setVisibility(View.GONE);
            tvBankURLError.setVisibility(View.GONE);
            tvBankAliasError.setVisibility(View.GONE);
            return false;
        }

        if (!getIFSC.isEmpty() && !ValidationUtils.validateIFSC(etIFSCCode.getText().toString())) {
            // etIFSCCode.setError(getResources().getString(R.string.invalid_ifsc_code));
            showErrorMsg(tvBankIfscCodeError);
            tvBankIfscCodeError.setText(getResources().getString(R.string.invalid_ifsc_code));
            tvBankNameError.setVisibility(View.GONE);
            tvBankAccountNoError.setVisibility(View.GONE);
            tvBankUserNameError.setVisibility(View.GONE);
            tvBankURLError.setVisibility(View.GONE);
            tvBankAliasError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.isEmpty()) {
            // aliasName.setError(getResources().getString(R.string.alias_name_can_not_be_empty));
            showErrorMsg(tvBankAliasError);
            tvBankAliasError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvBankNameError.setVisibility(View.GONE);
            tvBankAccountNoError.setVisibility(View.GONE);
            tvBankUserNameError.setVisibility(View.GONE);
            tvBankURLError.setVisibility(View.GONE);
            tvBankIfscCodeError.setVisibility(View.GONE);
            return false;
        }

        if (!getAlias.isEmpty() && getAlias.length() > 20) {
            // aliasName.setError(getResources().getString(R.string.alias_name_can_not_be_empty));
            showErrorMsg(tvBankAliasError);
            tvBankAliasError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvBankNameError.setVisibility(View.GONE);
            tvBankAccountNoError.setVisibility(View.GONE);
            tvBankUserNameError.setVisibility(View.GONE);
            tvBankURLError.setVisibility(View.GONE);
            tvBankIfscCodeError.setVisibility(View.GONE);
            return false;
        }


        if (!getURL.isEmpty() && !ValidationUtils.validateUrl(etUrlLink.getText().toString())) {
            // etUrlLink.setError(getResources().getString(R.string.invalid_url));
            showErrorMsg(tvBankURLError);
            tvBankURLError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvBankNameError.setVisibility(View.GONE);
            tvBankAccountNoError.setVisibility(View.GONE);
            tvBankUserNameError.setVisibility(View.GONE);
            tvBankAliasError.setVisibility(View.GONE);
            tvBankIfscCodeError.setVisibility(View.GONE);
            return false;
        }
        return true;
    }


    private void showErrorMsg(TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
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

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        dbAdapter.addParticularPaymentMode(particularPayment, particularPaymentId);
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
