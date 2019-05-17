package com.tekitsolutions.remindme.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.Model.ProvidersInfo;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ProviderStatic;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ALIAS_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_OWNER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_NUMBER;
import static com.tekitsolutions.remindme.Utils.CommonUtils.EDIT_CATEGORY_INTENT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_GAS_PROVIDER;

public class AddGasActivity extends BaseActivity {
    private static final String TAG = AddGasActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.inputCustomerNumber)
    EditText inputCustomerNumber;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.inputAliasName)
    EditText inputAliasName;
    @BindView(R.id.inputGasProvider)
    TextView inputGasProvider;

    @BindView(R.id.tv_gas_provider_error)
    TextView tvGasProviderError;
    @BindView(R.id.tv_gas_owner_error)
    TextView tvGasOwnerError;
    @BindView(R.id.tv_gas_alias_error)
    TextView tvGasAliasError;
    @BindView(R.id.tv_gas_customer_number_error)
    TextView tvGasCustomerNumberError;

    private DatabaseAdapter dbAdapter;
    private long providerId, particularCategoryId, categoryId;
    private String getConsumerNum, getName, getAlias, selected = "", providerNum, gasName, aliasName;
    private androidx.appcompat.app.AlertDialog.Builder builder;
    private ProvidersInfo providersInfo;
    private Boolean onActivity = false;
    private General provider;
    private General general;
    private Intent intent, categoryIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddGasActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_gas);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.add_gas));
        dbAdapter = ReminderApp.getInstance().adapter;

        inputAliasName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    btnConfirm.performClick();
                }
                return false;
            }
        });

        categoryIntent = getIntent();
        categoryId = categoryIntent.getLongExtra(CATEGORY_ID, 0);
        inputGasProvider.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    openProviderList();
                }
            }
        });


        editGas();
    }

    private void editGas() {
        intent = getIntent();
        if (intent.hasExtra(EDIT_CATEGORY_INTENT)) {
            onActivity = intent.getBooleanExtra(EDIT_CATEGORY_INTENT, false);
        }

        if (onActivity) {
            providerId = intent.getLongExtra(REMINDER_CATEGORY_PROVIDER_ID, 0);
            categoryId = intent.getLongExtra(REMINDER_CATEGORY_ID, 0);
            providerNum = intent.getStringExtra(REMINDER_CATEGORY_PROVIDER_NUMBER);
            gasName = intent.getStringExtra(REMINDER_CATEGORY_OWNER_NAME);
            aliasName = intent.getStringExtra(REMINDER_CATEGORY_ALIAS_NAME);

            provider = dbAdapter.getProviderById(PROVIDER_ID, providerId).get(0);
            general = dbAdapter.getCategoryById(CATEGORY_ID, categoryId).get(0);

            if (provider != null) {
                selected = provider.getName();
                inputGasProvider.setText(provider.getName());
                setProviderValidation(provider.getName());
            }

            inputCustomerNumber.setText(providerNum);
            inputAliasName.setText(aliasName);
            name.setText(gasName);

            getSupportActionBar().setTitle(getResources().getString(R.string.update_gas_bill));
            btnConfirm.setText(getResources().getString(R.string.update_gas_bill));
        }
    }

    private void passIntent() {
        String msg = "";

        if (onActivity) {
            msg = getResources().getString(R.string.gas_update_successfully);
        } else {
            msg = getResources().getString(R.string.gas_added_successfully);
        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("").setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                intent = new Intent();
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_ID, providerId);
                intent.putExtra(REMINDER_CATEGORY_ID, categoryId);
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_NUMBER, inputCustomerNumber.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_OWNER_NAME, name.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_ALIAS_NAME, inputAliasName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        }).show();
    }


    private void openProviderList() {
        Intent intent = new Intent(AddGasActivity.this, ProviderListActivity.class);
        intent.putExtra(CATEGORY_ID, categoryId);
        startActivityForResult(intent, PICK_GAS_PROVIDER);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_GAS_PROVIDER:
                    providerId = data.getLongExtra(PROVIDER_ID, 0);
                    inputGasProvider.setText(data.getStringExtra(PROVIDER_NAME));
                    selected = data.getStringExtra(PROVIDER_NAME);
                    setProviderValidation(selected);
                    break;
            }
        }
    }

    private void setProviderValidation(String selected) {
        if (ProviderStatic.providersInfoHashMap.containsKey(selected)) {
            ProvidersInfo providersInfo = new ProviderStatic().providersInfoHashMap.get(selected);
            inputCustomerNumber.setText("");
            inputCustomerNumber.setInputType(providersInfo.getInputType());
            inputCustomerNumber.setHint(providersInfo.getProviderEditTextPlaceHolder());
            inputCustomerNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(providersInfo.getProviderIDMaxLength())});
        }

    }


    private boolean checkValidation() {
        getConsumerNum = inputCustomerNumber.getText().toString();
        getName = name.getText().toString();
        getAlias = inputAliasName.getText().toString();

        if (providerId == 0) {
            // showAlert(getResources().getString(R.string.provider_error), getResources().getString(R.string.please_select_provider), false);
            showErrorMsg(tvGasProviderError);
            tvGasProviderError.setText(getResources().getString(R.string.please_select_provider));
            return false;
        }
        if (selected.equals("")) {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setTitle(getResources().getString(R.string.failed)).setMessage(getResources().getString(R.string.something_went_wrong)).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            }).show();
            return false;
        }

        if (!getConsumerNum.isEmpty()) {
            if (checkProviderIDValidation()) {
                return false;
            }
        }

        if (!getName.isEmpty() && getName.length() < 3 || getName.length() > 20) {
            // name.setError(getResources().getString(R.string.name_length_should_three_character));
            showErrorMsg(tvGasOwnerError);
            tvGasOwnerError.setText(getResources().getString(R.string.name_length_should_three_character));
            tvGasProviderError.setVisibility(View.GONE);
            tvGasCustomerNumberError.setVisibility(View.GONE);
            tvGasAliasError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.isEmpty()) {
            // inputAliasName.setError(getResources().getString(R.string.alias_name_can_not_be_empty));
            showErrorMsg(tvGasAliasError);
            tvGasAliasError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvGasProviderError.setVisibility(View.GONE);
            tvGasOwnerError.setVisibility(View.GONE);
            tvGasCustomerNumberError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.length() > 20) {
            //inputAliasName.setError(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            showErrorMsg(tvGasAliasError);
            tvGasAliasError.setText(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            tvGasProviderError.setVisibility(View.GONE);
            tvGasOwnerError.setVisibility(View.GONE);
            tvGasCustomerNumberError.setVisibility(View.GONE);
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


    @OnClick({R.id.inputGasProvider, R.id.btnConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inputGasProvider:
                openProviderList();
                break;
            case R.id.btnConfirm:
                if (checkValidation()) {
                    passIntent();
                }
                break;
        }
    }


    public boolean checkProviderIDValidation() {
        String selected = inputGasProvider.getText().toString().trim();
        boolean result = false;
        int minlenth = 0;
        int idStartWith = 0;
        if (new ProviderStatic().providersInfoHashMap.containsKey(selected)) {
            providersInfo = new ProviderStatic().providersInfoHashMap.get(selected);
            minlenth = providersInfo.getProviderIDMinLength();
            idStartWith = providersInfo.getProviderIDStatringDegit();
        }


        if (idStartWith != -1) {
            if (Character.isDigit(getConsumerNum.charAt(0)) == false) {
                // inputCustomerNumber.setError("First number should be start with " + idStartWith);
                showErrorMsg(tvGasCustomerNumberError);
                tvGasCustomerNumberError.setText("First number should be start with " + idStartWith);
                tvGasProviderError.setVisibility(View.GONE);
                tvGasOwnerError.setVisibility(View.GONE);
                tvGasAliasError.setVisibility(View.GONE);
                return true;
            }
        }

        if (minlenth != -1 && inputCustomerNumber.getText().toString().trim().length() < minlenth) {
            //      showAlert("Consumer number error", "Consumer number length should be " + minlenth + " digits", false);
            // inputCustomerNumber.setError(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_length_should_be) + minlenth + getResources().getString(R.string.digits));
            showErrorMsg(tvGasCustomerNumberError);
            tvGasCustomerNumberError.setText(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_length_should_be) + minlenth + getResources().getString(R.string.digits));
            tvGasProviderError.setVisibility(View.GONE);
            tvGasOwnerError.setVisibility(View.GONE);
            tvGasAliasError.setVisibility(View.GONE);
            result = true;
        } else if (idStartWith != -1 && Integer.parseInt(inputCustomerNumber.getText().toString().trim().substring(0, 1)) != (char) idStartWith) {
            //   showAlert("Consumer number error", "Consumer number should be start with " + idStartWith, false);
            // inputCustomerNumber.setError(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_should_be_start_with) + idStartWith);
            showErrorMsg(tvGasCustomerNumberError);
            tvGasCustomerNumberError.setText(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_should_be_start_with) + idStartWith);
            tvGasProviderError.setVisibility(View.GONE);
            tvGasOwnerError.setVisibility(View.GONE);
            tvGasAliasError.setVisibility(View.GONE);
            result = true;
        } else {
            result = false;
        }

        return result;
    }

    @Override
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}