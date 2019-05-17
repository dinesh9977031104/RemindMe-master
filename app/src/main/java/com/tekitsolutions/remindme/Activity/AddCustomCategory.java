package com.tekitsolutions.remindme.Activity;

import android.app.AlertDialog;
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

import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ALIAS_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_OWNER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_CATEGORY_PROVIDER_NUMBER;
import static com.tekitsolutions.remindme.Utils.CommonUtils.EDIT_CATEGORY_INTENT;

public class AddCustomCategory extends BaseActivity {
    private static final String TAG = AddCustomCategory.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.inputConsumerNumber)
    EditText inputConsumerNumber;
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.btnConfirm)
    Button btnConfirm;
    @BindView(R.id.inputAliasName)
    EditText inputAliasName;
    @BindView(R.id.provider)
    EditText provider;

    @BindView(R.id.tv_custom_provider_name_error)
    TextView tvCustomProviderNameError;
    @BindView(R.id.tv_custom_consumer_no_error)
    TextView tvCustomConsumerNoError;
    @BindView(R.id.tv_custom_owner_error)
    TextView tvCustomOwnerError;
    @BindView(R.id.tv_custom_alias_error)
    TextView tvCustomAliasError;

    private String getProviderName, getAlias, providerName, providerNum, ownerName, aliasName;
    private DatabaseAdapter dbAdapter;
    private long particularCategoryId, categoryId;
    private Boolean onActivity = false;
    private General general;
    private Intent intent, categoryIntent;
    private androidx.appcompat.app.AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddCustomCategory.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_category);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.add_custom_bill));


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


        editBill();
    }

    private void editBill() {
        intent = getIntent();
        if (intent.hasExtra(EDIT_CATEGORY_INTENT)) {
            onActivity = intent.getBooleanExtra(EDIT_CATEGORY_INTENT, false);
        }

        if (onActivity) {
            categoryId = intent.getLongExtra(REMINDER_CATEGORY_ID, 0);
            providerNum = intent.getStringExtra(REMINDER_CATEGORY_PROVIDER_NUMBER);
            providerName = intent.getStringExtra(REMINDER_CATEGORY_PROVIDER_NAME);
            ownerName = intent.getStringExtra(REMINDER_CATEGORY_OWNER_NAME);
            aliasName = intent.getStringExtra(REMINDER_CATEGORY_ALIAS_NAME);


            general = dbAdapter.getCategoryById(CATEGORY_ID, categoryId).get(0);

            provider.setText(providerName);

            inputConsumerNumber.setText(providerNum);
            inputAliasName.setText(aliasName);
            name.setText(ownerName);

            getSupportActionBar().setTitle(getResources().getString(R.string.update_bill));
            btnConfirm.setText(getResources().getString(R.string.update_bill));
        }
    }

    private void passIntent() {
        String msg = "";

        if (onActivity) {
            msg = getResources().getString(R.string.bill_update_successfully);
        } else {
            msg = getResources().getString(R.string.bill_added_successfully);
        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("").setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                intent = new Intent();
                intent.putExtra(REMINDER_CATEGORY_ID, categoryId);
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_NAME, provider.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_PROVIDER_NUMBER, inputConsumerNumber.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_OWNER_NAME, name.getText().toString());
                intent.putExtra(REMINDER_CATEGORY_ALIAS_NAME, inputAliasName.getText().toString());
                setResult(RESULT_OK, intent);
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

    private boolean checkValidation() {
        getProviderName = provider.getText().toString();
        getAlias = inputAliasName.getText().toString();

        if (getProviderName.isEmpty()) {
            // provider.setError(getResources().getString(R.string.provider_name_can_not_be_empty));
            showErrorMsg(tvCustomProviderNameError);
            tvCustomProviderNameError.setText(getResources().getString(R.string.provider_name_can_not_be_empty));
            return false;
        }

        if (getAlias.isEmpty()) {
            //inputAliasName.setError(getResources().getString(R.string.alias_name_can_not_be_empty));
            showErrorMsg(tvCustomAliasError);
            tvCustomAliasError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvCustomProviderNameError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.length() > 20) {
            // inputAliasName.setError(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            showErrorMsg(tvCustomAliasError);
            tvCustomAliasError.setText(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            tvCustomProviderNameError.setVisibility(View.GONE);
            return false;
        }
        return true;
    }

    private void showErrorMsg(TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}