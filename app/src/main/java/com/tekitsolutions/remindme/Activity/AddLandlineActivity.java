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
import android.widget.RelativeLayout;
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
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ACCOUNT_NUMBER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ALIAS_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_OWNER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_NUMBER;
import static com.tekitsolutions.remindme.Utils.CommonUtils.EDIT_CATEGORY_INTENT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_LANDLINE_PROVIDER;

public class AddLandlineActivity extends BaseActivity {
    private static final String TAG = AddLandlineActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.inputLandlineProvider)
    TextView inputLandlineProvider;
    @BindView(R.id.inputLandlineNumber)
    EditText inputLandlineNumber;
    @BindView(R.id.inputAliasName)
    EditText inputAliasName;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.inputAccountNumber)
    EditText inputAccountNumber;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.layoutAccountNumber)
    RelativeLayout layoutAccountNumber;

    @BindView(R.id.tv_landline_provider_error)
    TextView tvLandlineProviderError;
    @BindView(R.id.tv_landline_account_no_error)
    TextView tvLandlineAccountNoError;
    @BindView(R.id.tv_landline_owner_error)
    TextView tvLandlineOwnerError;
    @BindView(R.id.tv_landline_alias_error)
    TextView tvLandlineAliasError;
    @BindView(R.id.tv_landline_number_error)
    TextView tvLandlineNumberError;


    private DatabaseAdapter dbAdapter;
    private long providerId, particularCategoryId, categoryId;
    private String accountNumber, providerNum, landlineName, aliasName, getLandlineNum, getName, getAlias, getAccountNum, selected = "";
    private Boolean onActivity = false;
    private androidx.appcompat.app.AlertDialog.Builder builder;
    private ProvidersInfo providersInfo;
    private General general, provider;
    private Intent intent, categoryIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddLandlineActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_landline);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.enter_landline_details));
        dbAdapter = ReminderApp.getInstance().adapter;


        categoryIntent = getIntent();
        categoryId = categoryIntent.getLongExtra(CATEGORY_ID, 0);

        inputLandlineProvider.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    openProviderList();
                }
            }
        });


        inputAliasName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    btnConfirm.performClick();
                }
                return false;
            }
        });


        editLandline();

    }

    private void editLandline() {
        intent = getIntent();
        if (intent.hasExtra(EDIT_CATEGORY_INTENT)) {
            onActivity = intent.getBooleanExtra(EDIT_CATEGORY_INTENT, false);
        }

        if (onActivity) {
            providerId = intent.getLongExtra(REMINDER_CATEGORY_PROVIDER_ID, 0);
            categoryId = intent.getLongExtra(REMINDER_CATEGORY_ID, 0);
            providerNum = intent.getStringExtra(REMINDER_CATEGORY_PROVIDER_NUMBER);
            landlineName = intent.getStringExtra(REMINDER_CATEGORY_OWNER_NAME);
            accountNumber = intent.getStringExtra(REMINDER_CATEGORY_ACCOUNT_NUMBER);
            aliasName = intent.getStringExtra(REMINDER_CATEGORY_ALIAS_NAME);

            provider = dbAdapter.getProviderById(PROVIDER_ID, providerId).get(0);
            general = dbAdapter.getCategoryById(CATEGORY_ID, categoryId).get(0);

            if (provider != null) {
                selected = provider.getName();
                inputLandlineProvider.setText(provider.getName());
                setProviderValidation(provider.getName());
            }

            inputLandlineNumber.setText(providerNum);
            inputAccountNumber.setText(accountNumber);
            inputAliasName.setText(aliasName);
            name.setText(landlineName);

            getSupportActionBar().setTitle(getResources().getString(R.string.update_landline));
            btnConfirm.setText(getResources().getString(R.string.update_landline));
        }
    }


    private void openProviderList() {
        Intent intent = new Intent(AddLandlineActivity.this, ProviderListActivity.class);
        intent.putExtra(CATEGORY_ID, categoryId);
        startActivityForResult(intent, PICK_LANDLINE_PROVIDER);
    }


    private void passIntent() {
        String msg = "";

        if (onActivity) {
            msg = getResources().getString(R.string.landline_update_successfully);
        } else {
            msg = getResources().getString(R.string.landline_added_successfully);
        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("").setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                intent = new Intent();
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_ID, providerId);
                intent.putExtra(REMINDER_CATEGORY_ID, categoryId);
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_NUMBER, inputLandlineNumber.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_OWNER_NAME, name.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_ALIAS_NAME, inputAliasName.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_ACCOUNT_NUMBER, inputAccountNumber.getText().toString());
                setResult(RESULT_OK, intent);
                finish();

                providerId = intent.getLongExtra(REMINDER_CATEGORY_PROVIDER_ID, 0);
                categoryId = intent.getLongExtra(REMINDER_CATEGORY_ID, 0);
                providerNum = intent.getStringExtra(REMINDER_CATEGORY_PROVIDER_NUMBER);
                landlineName = intent.getStringExtra(REMINDER_CATEGORY_OWNER_NAME);
                accountNumber = intent.getStringExtra(REMINDER_CATEGORY_ACCOUNT_NUMBER);
                aliasName = intent.getStringExtra(REMINDER_CATEGORY_ALIAS_NAME);

            }
        }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_LANDLINE_PROVIDER:
                    providerId = data.getLongExtra(PROVIDER_ID, 0);
                    inputLandlineProvider.setText(data.getStringExtra(PROVIDER_NAME));
                    selected = data.getStringExtra(PROVIDER_NAME);
                    setProviderValidation(selected);
                    break;
            }
        }
    }

    private void setProviderValidation(String selected) {
        if (ProviderStatic.providersInfoHashMap.containsKey(selected)) {
            providersInfo = new ProviderStatic().providersInfoHashMap.get(selected);
            inputLandlineNumber.setText("");
            inputLandlineNumber.setInputType(providersInfo.getInputType());
            inputLandlineNumber.setHint(providersInfo.getProviderEditTextPlaceHolder());
            inputLandlineNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(providersInfo.getProviderIDMaxLength())});
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

    @OnClick({R.id.inputLandlineProvider, R.id.btnConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inputLandlineProvider:
                openProviderList();
                break;
            case R.id.btnConfirm:
                if (checkValidation()) {
                    passIntent();
                }
                break;
        }
    }

    private boolean checkValidation() {
        getLandlineNum = inputLandlineNumber.getText().toString();
        getName = name.getText().toString();
        getAlias = inputAliasName.getText().toString();
        getAccountNum = inputAccountNumber.getText().toString();

        if (providerId == 0) {
            // showAlert(getResources().getString(R.string.provider_error), getResources().getString(R.string.please_select_provider), false);
            showErrorMsg(tvLandlineProviderError);
            tvLandlineProviderError.setText(getResources().getString(R.string.please_select_provider));
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

        if (!getLandlineNum.isEmpty()) {
            if (checkProviderIDValidation()) {
                return false;
            }
        }

        if (!getAccountNum.isEmpty() && selected.equals(getResources().getString(R.string.landline_bsnl)) && getAccountNum.length() != 10) {
            // inputAccountNumber.setError(getResources().getString(R.string.account_number_length_should_be_ten));
            showErrorMsg(tvLandlineAccountNoError);
            tvLandlineAccountNoError.setText(getResources().getString(R.string.account_number_length_should_be_ten));
            tvLandlineProviderError.setVisibility(View.GONE);
            tvLandlineAliasError.setVisibility(View.GONE);
            return false;
        } else if (!getAccountNum.isEmpty() && selected.equals(getResources().getString(R.string.landline_mtnl_delhi)) && getAccountNum.length() != 8) {
            // inputAccountNumber.setError(getResources().getString(R.string.account_number_length_should_be_eight));
            showErrorMsg(tvLandlineAccountNoError);
            tvLandlineAccountNoError.setText(getResources().getString(R.string.account_number_length_should_be_eight));
            tvLandlineProviderError.setVisibility(View.GONE);
            tvLandlineAliasError.setVisibility(View.GONE);
            return false;
        } else if (!getAccountNum.isEmpty() && selected.equals(getResources().getString(R.string.landline_mtnl_mumbai)) && getAccountNum.length() != 8) {
            // inputAccountNumber.setError(getResources().getString(R.string.account_number_length_should_be_eight));
            showErrorMsg(tvLandlineAccountNoError);
            tvLandlineAccountNoError.setText(getResources().getString(R.string.account_number_length_should_be_eight));
            tvLandlineProviderError.setVisibility(View.GONE);
            tvLandlineAliasError.setVisibility(View.GONE);
            return false;
        }

        if (!getName.isEmpty() && getName.length() < 3 || getName.length() > 20) {
            // name.setError(getResources().getString(R.string.name_length_should_three_character));
            showErrorMsg(tvLandlineOwnerError);
            tvLandlineOwnerError.setText(getResources().getString(R.string.name_length_should_three_character));
            tvLandlineProviderError.setVisibility(View.GONE);
            tvLandlineAccountNoError.setVisibility(View.GONE);
            tvLandlineAliasError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.isEmpty()) {
            // inputAliasName.setError(getResources().getString(R.string.alias_name_can_not_be_empty));
            showErrorMsg(tvLandlineAliasError);
            tvLandlineAliasError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvLandlineProviderError.setVisibility(View.GONE);
            tvLandlineAccountNoError.setVisibility(View.GONE);
            tvLandlineOwnerError.setVisibility(View.GONE);
            tvLandlineNumberError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.length() > 20) {
            // inputAliasName.setError(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            showErrorMsg(tvLandlineAliasError);
            tvLandlineAliasError.setText(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            tvLandlineProviderError.setVisibility(View.GONE);
            tvLandlineAccountNoError.setVisibility(View.GONE);
            tvLandlineOwnerError.setVisibility(View.GONE);
            tvLandlineNumberError.setVisibility(View.GONE);
            return false;
        }

        return true;
    }

    private void showErrorMsg(TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
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

    public boolean checkProviderIDValidation() {
        String selected = inputLandlineProvider.getText().toString().trim();
        boolean result = false;
        int minlenth = 0;
        int idStartWith = 0;
        if (new ProviderStatic().providersInfoHashMap.containsKey(selected)) {
            providersInfo = new ProviderStatic().providersInfoHashMap.get(selected);
            minlenth = providersInfo.getProviderIDMinLength();
            idStartWith = providersInfo.getProviderIDStatringDegit();
        }


        if (idStartWith != -1) {
            if (Character.isDigit(getLandlineNum.charAt(0)) == false) {
                // inputLandlineNumber.setError("First number should be start with " + idStartWith);
                showErrorMsg(tvLandlineNumberError);
                tvLandlineNumberError.setText("First number should be start with " + idStartWith);
                tvLandlineOwnerError.setVisibility(View.GONE);
                tvLandlineAliasError.setVisibility(View.GONE);
                tvLandlineAccountNoError.setVisibility(View.GONE);
                tvLandlineProviderError.setVisibility(View.GONE);
                return true;
            }
        }


        if (minlenth != -1 && inputLandlineNumber.getText().toString().trim().length() < minlenth) {
            // showAlert(getResources().getString(R.string.landline_number_error), getResources().getString(R.string.landline_number_length_should_be) + minlenth + getResources().getString(R.string.digits), false);
            // inputLandlineNumber.setError(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_can_not_empty) + minlenth + getResources().getString(R.string.digits));
            showErrorMsg(tvLandlineNumberError);
            tvLandlineNumberError.setText(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_can_not_empty) + minlenth + getResources().getString(R.string.digits));
            tvLandlineOwnerError.setVisibility(View.GONE);
            tvLandlineAliasError.setVisibility(View.GONE);
            tvLandlineAccountNoError.setVisibility(View.GONE);
            tvLandlineProviderError.setVisibility(View.GONE);
            result = true;
        } else if (idStartWith != -1 && Integer.parseInt(inputLandlineNumber.getText().toString().trim().substring(0, 1)) != (char) idStartWith) {
            // showAlert("Landline number error", "Landline number should be start with " + idStartWith, false);
            //inputLandlineNumber.setError(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_length_should_be) + idStartWith);
            showErrorMsg(tvLandlineNumberError);
            tvLandlineNumberError.setText(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_length_should_be) + idStartWith);
            tvLandlineOwnerError.setVisibility(View.GONE);
            tvLandlineAliasError.setVisibility(View.GONE);
            tvLandlineAccountNoError.setVisibility(View.GONE);
            tvLandlineProviderError.setVisibility(View.GONE);
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