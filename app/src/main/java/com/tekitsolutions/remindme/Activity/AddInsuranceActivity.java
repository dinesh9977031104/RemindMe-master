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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tekitsolutions.remindme.Adapter.CustomSpinnerAdapter;
import com.tekitsolutions.remindme.Adapter.CustomSpinnerClass;
import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.Model.ProvidersInfo;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ProviderStatic;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ALIAS_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_OWNER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_NUMBER;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_SUB_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SUB_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SUB_PROVIDER_NAME;
import static com.tekitsolutions.remindme.Utils.CommonUtils.EDIT_CATEGORY_INTENT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.INS_CATEGORY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_INSURANCE_PROVIDER;

public class AddInsuranceActivity extends BaseActivity {
    private static final String TAG = AddInsuranceActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.inputInsuranceProvider)
    TextView inputInsuranceProvider;
    @BindView(R.id.inputPolicyNumber)
    EditText inputPolicyNumber;
    @BindView(R.id.inputAgentName)
    EditText inputAgentName;
    @BindView(R.id.inputAgentNumber)
    EditText inputAgentNumber;
    @BindView(R.id.inputAliasName)
    EditText inputAliasName;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.relative_layout)
    RelativeLayout relativeLayout;
    @BindView(R.id.spinInsuranceList)
    CustomSpinnerClass spinInsuranceList;
    CustomSpinnerAdapter spinnerAdapter;

    @BindView(R.id.tv_insurance_provider_error)
    TextView tvInsuranceProviderError;
    @BindView(R.id.tv_insurance_owner_error)
    TextView tvInsuranceOwnerError;
    @BindView(R.id.tv_insurance_alias_error)
    TextView tvInsuranceAliasError;
    @BindView(R.id.tv_insurance_policy_no_error)
    TextView tvInsurancePolicyNoError;

    private String getPolicyNum, getAgentName, getMobileNum, getAlias, selected = "", defaultName = "";
    private long providerId, categoryId, particularCategoryId, subProviderId;
    private boolean insurance, onActivity, editInsurance;
    private DatabaseAdapter dbAdapter;
    private List<General> mainInsuranceList = new ArrayList<>();
    private androidx.appcompat.app.AlertDialog.Builder builder;
    private ProvidersInfo providersInfo;
    private General provider;
    private General subProvider;
    private General general;
    private Intent intent, categoryIntent;
    private String providerNum;
    private String insuranceName;
    private String aliasName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddInsuranceActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_insurance);
        ButterKnife.bind(this);
        CustomSpinnerClass spinInsuranceList;
        setToolbar(toolbar, getString(R.string.add_insurance));
        dbAdapter = ReminderApp.getInstance().adapter;

        categoryIntent = getIntent();
        categoryId = categoryIntent.getLongExtra(CATEGORY_ID, 0);

        inputAliasName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    btnConfirm.performClick();
                }
                return false;
            }
        });

        editInsurance();
        setSpinner();
    }

    private void editInsurance() {
        intent = getIntent();
        if (intent.hasExtra(EDIT_CATEGORY_INTENT)) {
            onActivity = intent.getBooleanExtra(EDIT_CATEGORY_INTENT, false);
        }

        if (onActivity) {
            subProviderId = intent.getLongExtra(REMINDER_CATEGORY_SUB_PROVIDER_ID, 0);
            providerId = intent.getLongExtra(REMINDER_CATEGORY_PROVIDER_ID, 0);
            categoryId = intent.getLongExtra(REMINDER_CATEGORY_ID, 0);
            providerNum = intent.getStringExtra(REMINDER_CATEGORY_PROVIDER_NUMBER);
            insuranceName = intent.getStringExtra(REMINDER_CATEGORY_OWNER_NAME);
            aliasName = intent.getStringExtra(REMINDER_CATEGORY_ALIAS_NAME);

            provider = dbAdapter.getProviderById(PROVIDER_ID, providerId).get(0);
            subProvider = dbAdapter.getSubProvidersById(SUB_PROVIDER_ID, subProviderId).get(0);
            general = dbAdapter.getCategoryById(CATEGORY_ID, categoryId).get(0);

            if (subProvider != null) {
                selected = subProvider.getName();
                if (selected.equals("")) {
                    relativeLayout.setVisibility(View.GONE);
                } else {
                    relativeLayout.setVisibility(View.VISIBLE);
                }
                inputInsuranceProvider.setText(subProvider.getName());
            }

            inputPolicyNumber.setText(providerNum);
            inputAliasName.setText(aliasName);
            inputAgentName.setText(insuranceName);

            getSupportActionBar().setTitle(getResources().getString(R.string.update_insurance));
            btnConfirm.setText(getResources().getString(R.string.update_insurance));
        }
    }

    private void passIntent() {
        String msg = "";

        if (onActivity) {
            msg = getResources().getString(R.string.insurance_update_successfully);
        } else {
            msg = getResources().getString(R.string.insurance_added_succesfully);
        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("").setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                intent = new Intent();
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_ID, providerId);
                intent.putExtra(REMINDER_CATEGORY_SUB_PROVIDER_ID, subProviderId);
                intent.putExtra(REMINDER_CATEGORY_ID, categoryId);
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_NUMBER, inputPolicyNumber.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_OWNER_NAME, inputAgentName.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_ALIAS_NAME, inputAliasName.getText().toString());
                setResult(RESULT_OK, intent);
                finish();


            }
        }).show();
    }


    private void setSpinner() {
        mainInsuranceList.addAll(dbAdapter.getProviderById(PROVIDER_CATEGORY_ID, categoryId));

        if (onActivity) {
            defaultName = provider.getName();
            mainInsuranceList.add(0, new General(0, 0, 0, 0, defaultName + " (selected)", ""));
        } else {
            mainInsuranceList.add(0, new General(0, 0, 0, 0, getResources().getString(R.string.select_insurance_provider), ""));
        }

        spinnerAdapter = new CustomSpinnerAdapter(AddInsuranceActivity.this,
                R.layout.row_spinner_provider, mainInsuranceList);
        spinInsuranceList.setAdapter(spinnerAdapter);


        spinInsuranceList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    insurance = true;
                    General provider = mainInsuranceList.get(position);
                    Intent intent = new Intent(AddInsuranceActivity.this, ProviderListActivity.class);
                    intent.putExtra(PROVIDER_ID, provider.getId());
                    intent.putExtra(SUB_PROVIDER_ID, provider.getSubProviderId());
                    intent.putExtra(CATEGORY_ID, categoryId);
                    intent.putExtra(INS_CATEGORY, insurance);
                    providerId = provider.getId();
                    startActivityForResult(intent, PICK_INSURANCE_PROVIDER);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_INSURANCE_PROVIDER:
                    subProviderId = data.getLongExtra(SUB_PROVIDER_ID, 0);
                    inputInsuranceProvider.setText(data.getStringExtra(SUB_PROVIDER_NAME));
                    selected = data.getStringExtra(SUB_PROVIDER_NAME);
                    setProviderInfo(selected);
                    if (selected.equals("")) {
                        relativeLayout.setVisibility(View.GONE);
                    } else {
                        relativeLayout.setVisibility(View.VISIBLE);
                    }
                    break;
            }

        } else {
            inputInsuranceProvider.setText("");
        }
    }

    private void setProviderInfo(String selected) {
        if (ProviderStatic.providersInfoHashMap.containsKey(selected)) {
            providersInfo = new ProviderStatic().providersInfoHashMap.get(selected);
            inputPolicyNumber.setText("");
            inputPolicyNumber.setInputType(providersInfo.getInputType());
            inputPolicyNumber.setHint(providersInfo.getProviderEditTextPlaceHolder());
            inputPolicyNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(providersInfo.getProviderIDMaxLength())});
        }
    }


    private boolean checkValidation() {
        getPolicyNum = inputPolicyNumber.getText().toString();
        getAgentName = inputAgentName.getText().toString();
        getMobileNum = inputAgentNumber.getText().toString();
        getAlias = inputAliasName.getText().toString();

        if (subProviderId == 0) {
            //  showAlert(getResources().getString(R.string.provider_error), getResources().getString(R.string.please_select_provider), false);
            showErrorMsg(tvInsuranceProviderError);
            tvInsuranceProviderError.setText(getResources().getString(R.string.please_select_provider));
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

        if (inputInsuranceProvider.getText().toString().isEmpty()) {
            inputInsuranceProvider.setError(getString(R.string.select_sub_provider));
            return false;
        }

        if (!getPolicyNum.isEmpty()) {
            if (checkProviderIDValidation()) {
                return false;
            }
        }

        if (!getAgentName.isEmpty() && getAgentName.length() < 3 || getAgentName.length() > 20) {
            // inputAgentName.setError(getResources().getString(R.string.owner_name_length_should_be_three_character));
            showErrorMsg(tvInsuranceOwnerError);
            tvInsuranceOwnerError.setText(getResources().getString(R.string.owner_name_length_should_be_three_character));
            tvInsuranceProviderError.setVisibility(View.GONE);
            tvInsurancePolicyNoError.setVisibility(View.GONE);
            tvInsuranceAliasError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.isEmpty()) {
            // inputAliasName.setError(getResources().getString(R.string.alias_name_can_not_be_empty));
            showErrorMsg(tvInsuranceAliasError);
            tvInsuranceAliasError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvInsuranceProviderError.setVisibility(View.GONE);
            tvInsuranceOwnerError.setVisibility(View.GONE);
            tvInsurancePolicyNoError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.length() > 20) {
            // inputAliasName.setError(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            showErrorMsg(tvInsuranceAliasError);
            tvInsuranceAliasError.setText(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            tvInsuranceProviderError.setVisibility(View.GONE);
            tvInsuranceOwnerError.setVisibility(View.GONE);
            tvInsurancePolicyNoError.setVisibility(View.GONE);
            return false;
        }

        return true;
    }

    private void showErrorMsg(TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
    }


    @OnClick({R.id.btnConfirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnConfirm:
                if (checkValidation()) {
                    passIntent();
                }
                break;
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

    public boolean checkProviderIDValidation() {
        String selected = inputInsuranceProvider.getText().toString().trim();
        boolean result = false;
        int minlenth = 0;
        int idStartWith = 0;
        if (new ProviderStatic().providersInfoHashMap.containsKey(selected)) {
            providersInfo = new ProviderStatic().providersInfoHashMap.get(selected);
            minlenth = providersInfo.getProviderIDMinLength();
            idStartWith = providersInfo.getProviderIDStatringDegit();
        }


        if (idStartWith != -1) {
            if (Character.isDigit(getPolicyNum.charAt(0)) == false) {
                // inputPolicyNumber.setError("First number should be start with " + idStartWith);
                showErrorMsg(tvInsurancePolicyNoError);
                tvInsurancePolicyNoError.setText("First number should be start with " + idStartWith);
                tvInsuranceOwnerError.setVisibility(View.GONE);
                tvInsuranceProviderError.setVisibility(View.GONE);
                tvInsuranceAliasError.setVisibility(View.GONE);
                return true;
            }
        }


        if (minlenth != -1 && inputPolicyNumber.getText().toString().trim().length() < minlenth) {
            // showAlert("Policy number error", "Policy number length should be " + minlenth + " digits", false);
            // inputPolicyNumber.setError(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_can_not_empty) + minlenth + getResources().getString(R.string.digits));
            showErrorMsg(tvInsurancePolicyNoError);
            tvInsurancePolicyNoError.setText(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_can_not_empty) + minlenth + getResources().getString(R.string.digits));
            tvInsuranceOwnerError.setVisibility(View.GONE);
            tvInsuranceProviderError.setVisibility(View.GONE);
            tvInsuranceAliasError.setVisibility(View.GONE);
            result = true;
        } else if (idStartWith != -1 && Integer.parseInt(inputPolicyNumber.getText().toString().trim().substring(0, 1)) != (char) idStartWith) {
            // showAlert("Policy number error", "Policy number should be start with " + idStartWith, false);
            // inputPolicyNumber.setError(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_length_should_be) + idStartWith);
            showErrorMsg(tvInsurancePolicyNoError);
            tvInsurancePolicyNoError.setText(providersInfo.mProviderEditTextPlaceHolder + getResources().getString(R.string.consumer_number_length_should_be) + idStartWith);
            tvInsuranceOwnerError.setVisibility(View.GONE);
            tvInsuranceProviderError.setVisibility(View.GONE);
            tvInsuranceAliasError.setVisibility(View.GONE);
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