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
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_WATER_PROVIDER;

public class AddWaterBillActivity extends BaseActivity {
    private static final String TAG = AddWaterBillActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.inputConnectionId)
    EditText inputConnectionId;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.inputAliasName)
    EditText inputAliasName;
    @BindView(R.id.inputWaterProvider)
    TextView inputWaterProvider;

    @BindView(R.id.tv_water_provider_error)
    TextView tvWaterProviderError;
    @BindView(R.id.tv_water_connection_id_error)
    TextView tvWaterConnectionIdError;
    @BindView(R.id.tv_water_owner_error)
    TextView tvWaterOwnerError;
    @BindView(R.id.tv_water_alias_error)
    TextView tvWaterAliasError;

    private DatabaseAdapter dbAdapter;
    private long providerId, particularCategoryId, categoryId;
    private String getConsumerNum, getName, getAlias, selected = "", providerNum, waterName, aliasName;
    private Boolean onActivity = false;
    private androidx.appcompat.app.AlertDialog.Builder builder;
    private ProvidersInfo providersInfo;
    private General general, provider;
    private Intent intent, categoryIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddWaterBillActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water_bill);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.add_water));

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
        inputWaterProvider.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    openProviderList();
                }
            }
        });


        editWaterBill();
    }

    private void editWaterBill() {
        intent = getIntent();
        if (intent.hasExtra(EDIT_CATEGORY_INTENT)) {
            onActivity = intent.getBooleanExtra(EDIT_CATEGORY_INTENT, false);
        }

        if (onActivity) {
            providerId = intent.getLongExtra(REMINDER_CATEGORY_PROVIDER_ID, 0);
            categoryId = intent.getLongExtra(REMINDER_CATEGORY_ID, 0);
            providerNum = intent.getStringExtra(REMINDER_CATEGORY_PROVIDER_NUMBER);
            waterName = intent.getStringExtra(REMINDER_CATEGORY_OWNER_NAME);
            aliasName = intent.getStringExtra(REMINDER_CATEGORY_ALIAS_NAME);


            provider = dbAdapter.getProviderById(PROVIDER_ID, providerId).get(0);
            general = dbAdapter.getCategoryById(CATEGORY_ID, categoryId).get(0);

            if (provider != null) {
                selected = provider.getName();
                inputWaterProvider.setText(provider.getName());
                setProviderValidation(provider.getName());
            }

            inputConnectionId.setText(providerNum);
            inputAliasName.setText(aliasName);
            name.setText(waterName);

            getSupportActionBar().setTitle(getResources().getString(R.string.update_water));
            btnConfirm.setText(getResources().getString(R.string.update_water));
        }
    }

    private void passIntent() {
        String msg = "";

        if (onActivity) {
            msg = getResources().getString(R.string.water_update_successfully);
        } else {
            msg = getResources().getString(R.string.water_added_successfully);
        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("").setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                intent = new Intent();
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_ID, providerId);
                intent.putExtra(REMINDER_CATEGORY_ID, categoryId);
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_NUMBER, inputConnectionId.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_OWNER_NAME, name.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_ALIAS_NAME, inputAliasName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();

            }
        }).show();
    }

    private void openProviderList() {
        Intent intent = new Intent(AddWaterBillActivity.this, ProviderListActivity.class);
        intent.putExtra(CATEGORY_ID, categoryId);
        startActivityForResult(intent, PICK_WATER_PROVIDER);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_WATER_PROVIDER:
                    providerId = data.getLongExtra(PROVIDER_ID, 0);
                    inputWaterProvider.setText(data.getStringExtra(PROVIDER_NAME));
                    selected = data.getStringExtra(PROVIDER_NAME);
                    setProviderValidation(selected);
                    break;
            }
        }
    }

    private void setProviderValidation(String selected) {
        if (ProviderStatic.providersInfoHashMap.containsKey(selected)) {
            providersInfo = new ProviderStatic().providersInfoHashMap.get(selected);
            inputConnectionId.setText("");
            inputConnectionId.setInputType(providersInfo.getInputType());
            inputConnectionId.setHint(providersInfo.getProviderEditTextPlaceHolder());
            inputConnectionId.setFilters(new InputFilter[]{new InputFilter.LengthFilter(providersInfo.getProviderIDMaxLength())});
        }

    }


    private boolean checkValidation() {
        getConsumerNum = inputConnectionId.getText().toString();
        getName = name.getText().toString();
        getAlias = inputAliasName.getText().toString();

        if (providerId == 0) {
            // showAlert(getResources().getString(R.string.provider_error), getResources().getString(R.string.please_select_provider), false);
            showErrorMsg(tvWaterProviderError);
            tvWaterProviderError.setText(getResources().getString(R.string.please_select_provider));
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
            showErrorMsg(tvWaterOwnerError);
            tvWaterOwnerError.setText(getResources().getString(R.string.name_length_should_three_character));
            tvWaterProviderError.setVisibility(View.GONE);
            tvWaterConnectionIdError.setVisibility(View.GONE);
            tvWaterAliasError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.isEmpty()) {
            // inputAliasName.setError(getResources().getString(R.string.alias_name_can_not_be_empty));
            showErrorMsg(tvWaterAliasError);
            tvWaterAliasError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvWaterProviderError.setVisibility(View.GONE);
            tvWaterOwnerError.setVisibility(View.GONE);
            tvWaterConnectionIdError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.length() > 20) {
            // inputAliasName.setError(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            showErrorMsg(tvWaterAliasError);
            tvWaterAliasError.setText(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            tvWaterProviderError.setVisibility(View.GONE);
            tvWaterOwnerError.setVisibility(View.GONE);
            tvWaterConnectionIdError.setVisibility(View.GONE);
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


    @OnClick({R.id.inputWaterProvider, R.id.btnConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inputWaterProvider:
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
        String selected = inputWaterProvider.getText().toString().trim();
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
                // inputConnectionId.setError("First number should be start with " + idStartWith);
                showErrorMsg(tvWaterConnectionIdError);
                tvWaterConnectionIdError.setText("First number should be start with " + idStartWith);
                tvWaterProviderError.setVisibility(View.GONE);
                tvWaterOwnerError.setVisibility(View.GONE);
                tvWaterAliasError.setVisibility(View.GONE);
                return true;
            }
        }

        if (minlenth != -1 && inputConnectionId.getText().toString().trim().length() < minlenth) {
            //      showAlert("Consumer number error", "Consumer number length should be " + minlenth + " digits", false);
            // inputConnectionId.setError(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_length_should_be) + minlenth + getResources().getString(R.string.digits));
            showErrorMsg(tvWaterConnectionIdError);
            tvWaterConnectionIdError.setText(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_length_should_be) + minlenth + getResources().getString(R.string.digits));
            tvWaterProviderError.setVisibility(View.GONE);
            tvWaterOwnerError.setVisibility(View.GONE);
            tvWaterAliasError.setVisibility(View.GONE);
            result = true;
        } else if (idStartWith != -1 && Integer.parseInt(inputConnectionId.getText().toString().trim().substring(0, 1)) != (char) idStartWith) {
            //   showAlert("Consumer number error", "Consumer number should be start with " + idStartWith, false);
            //  inputConnectionId.setError(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_should_be_start_with) + idStartWith);
            showErrorMsg(tvWaterConnectionIdError);
            tvWaterConnectionIdError.setText(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_should_be_start_with) + idStartWith);
            tvWaterProviderError.setVisibility(View.GONE);
            tvWaterOwnerError.setVisibility(View.GONE);
            tvWaterAliasError.setVisibility(View.GONE);
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