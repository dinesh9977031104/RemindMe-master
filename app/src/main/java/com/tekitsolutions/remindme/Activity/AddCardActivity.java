package com.tekitsolutions.remindme.Activity;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tekitsolutions.remindme.CardUtils.CardUtils;
import com.tekitsolutions.remindme.CardUtils.CreditCardEditText;
import com.tekitsolutions.remindme.CardUtils.CreditCardFormattingTextWatcher;
import com.tekitsolutions.remindme.Model.ParticularPayment;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;

import java.util.Calendar;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_TYPE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PAYMENT_ID;

public class AddCardActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = AddCardActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.et_card_number)
    CreditCardEditText card_number;
    @BindView(R.id.et_card_holder)
    CreditCardEditText cardEtCardHolder;
    @BindView(R.id.layout_card_validity)
    RelativeLayout layoutCardValidity;
    @BindView(R.id.et_validity)
    TextView textViewValidity;
    @BindView(R.id.btn_add_card)
    Button buttonAddCard;
    @BindView(R.id.et_card_cvv)
    EditText etCardCvv;
    @BindView(R.id.et_card_holder_alias_name)
    EditText etCardHolderAliasName;
    @BindView(R.id.et_card_holder_mobile_number)
    EditText etCardHolderMobileNumber;
    @BindView(R.id.iv_card_number)
    ImageView ivCardNumber;
    @BindView(R.id.agent_name)
    EditText agentName;
    @BindView(R.id.agent_email)
    EditText agentEmail;

    @BindView(R.id.tv_card_number_error)
    TextView tvCardNumberError;
    @BindView(R.id.tv_card_holder_name_error)
    TextView tvCardHolderNameError;
    @BindView(R.id.tv_card_alias_error)
    TextView tvCardAliasError;

    private int mDay, mYear, mMonth;
    private String cardType, getCardNum, getCardHolder, getCardValidity, getCvv, getAlias, getMobileNum, checkCardNum;
    private DatabaseAdapter dbAdapter;
    private long particularPaymentId = 0, paymentId;
    private int cardIcon = 0, validCardIcon = 0;
    private boolean isCredit, isEditable;
    private List<ParticularPayment> cardList;
    private androidx.appcompat.app.AlertDialog.Builder builder;
    private ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(AddCardActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        init();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        cardType = intent.getStringExtra(PARTICULAR_PAYMENT_TYPE);
        paymentId = intent.getIntExtra(PAYMENT_ID, 0);
    }

    private void init() {
        ButterKnife.bind(this);
        dbAdapter = new DatabaseAdapter(this);
        cardList = dbAdapter.getParticularPaymentById(null, 0, null);
        values = new ContentValues();
        setToolbar(toolbar, getString(R.string.title_add_card_activity));
        sendAsAction();
        setClickListener();
        getIntentData();
        editCard();
        focusOnCardNumber();
        getCardLogo();
    }

    private void sendAsAction() {
        etCardHolderMobileNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    buttonAddCard.performClick();
                }
                return false;
            }
        });
    }


    private void focusOnCardNumber() {
        card_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (checkValidationForCardLogo()) {
                        ivCardNumber.setImageResource(CardUtils.getCardIcon(card_number.getText().toString()));
                        cardIcon = CardUtils.getCardIcon(card_number.getText().toString());
                    }
                }
            }
        });
    }


    private void editCard() {
        if (getIntent().hasExtra(PARTICULAR_PAYMENT_ID)) {
            particularPaymentId = getIntent().getIntExtra(PARTICULAR_PAYMENT_ID, 0);
            ParticularPayment particularPayment = dbAdapter.getParticularPaymentById(PARTICULAR_PAYMENT_ID, particularPaymentId, null).get(0);
            card_number.setText(particularPayment.getPaymentNumber());
            checkCardNum = particularPayment.getPaymentNumber();
            ivCardNumber.setImageResource(particularPayment.getPaymentIcon());
            validCardIcon = particularPayment.getPaymentIcon();
            cardEtCardHolder.setText(particularPayment.getOwnerName());
            etCardCvv.setText(particularPayment.getCvv());
            textViewValidity.setText(particularPayment.getCardValidity());
            etCardHolderAliasName.setText(particularPayment.getAliasName());
            cardType = particularPayment.getPaymentType();
            getSupportActionBar().setTitle(getResources().getString(R.string.update_card));
            isEditable = true;
            buttonAddCard.setText(getResources().getString(R.string.update_card));
        }
    }

    private void setClickListener() {
        card_number.addTextChangedListener(new CreditCardFormattingTextWatcher(card_number));
        layoutCardValidity.setOnClickListener(this);
        buttonAddCard.setOnClickListener(this);
    }

    private boolean checkValidation() {
        getCardHolder = cardEtCardHolder.getText().toString();
        getAlias = etCardHolderAliasName.getText().toString();
        getCardValidity = textViewValidity.getText().toString();
        getCvv = etCardCvv.getText().toString();

        if (!checkValidationForCardLogo()) {
            return false;
        }

        if (particularPaymentId == 0 && !checkCardAlreadyExist()) {
            // card_number.setError(getResources().getString(R.string.card_alredy_exist));
            showErrorMsg(tvCardNumberError);
            tvCardNumberError.setText(getResources().getString(R.string.card_alredy_exist));
            tvCardAliasError.setVisibility(View.GONE);
            tvCardHolderNameError.setVisibility(View.GONE);
            return false;
        }

        if (!getCardHolder.isEmpty() && (getCardHolder.length() < 7 || getCardHolder.length() > 25)) {
            // cardEtCardHolder.setError(getResources().getString(R.string.card_holder_name_length_should_be_seven));
            showErrorMsg(tvCardHolderNameError);
            tvCardHolderNameError.setText(getResources().getString(R.string.card_holder_name_length_should_be_seven));
            tvCardAliasError.setVisibility(View.GONE);
            tvCardNumberError.setVisibility(View.GONE);
            return false;
        }

        if (!getCardHolder.isEmpty() && !isValidName(cardEtCardHolder.getText().toString())) {
            cardEtCardHolder.requestFocus();
            // cardEtCardHolder.setError(getResources().getString(R.string.please_enter_valid_card_holder_name));
            showErrorMsg(tvCardHolderNameError);
            tvCardHolderNameError.setText(getResources().getString(R.string.please_enter_valid_card_holder_name));
            tvCardNumberError.setVisibility(View.GONE);
            tvCardAliasError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.isEmpty()) {
            // etCardHolderAliasName.setError(getResources().getString(R.string.alias_name_can_not_be_empty));
            showErrorMsg(tvCardAliasError);
            tvCardAliasError.setText(getResources().getString(R.string.alias_name_can_not_be_empty));
            tvCardNumberError.setVisibility(View.GONE);
            tvCardHolderNameError.setVisibility(View.GONE);
            return false;
        }

        if (getAlias.length() > 20) {
            //etCardHolderAliasName.setError(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            showErrorMsg(tvCardAliasError);
            tvCardAliasError.setText(getResources().getString(R.string.alias_name_length_should_be_twenty_character));
            tvCardNumberError.setVisibility(View.GONE);
            tvCardHolderNameError.setVisibility(View.GONE);
            return false;
        }
        return true;
    }

    private void showErrorMsg(TextView errorTextView) {
        errorTextView.setVisibility(View.VISIBLE);
    }

    private void getCardLogo() {
        card_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ivCardNumber.setImageResource(R.drawable.ic_payment_icon);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private boolean checkValidationForCardLogo() {
        getCardNum = card_number.getText().toString();
        if (getCardNum.isEmpty()) {
            ivCardNumber.setImageResource(R.drawable.ic_payment_icon);
            // card_number.setError(getResources().getString(R.string.card_number_can_not_be_empty));
            showErrorMsg(tvCardNumberError);
            tvCardNumberError.setText(getResources().getString(R.string.card_number_can_not_be_empty));
            tvCardHolderNameError.setVisibility(View.GONE);
            tvCardAliasError.setVisibility(View.GONE);
            return false;
        }

        if (!CardUtils.isValid(Long.parseLong(card_number.getText().toString().replace(" ", "")))) {
            ivCardNumber.setImageResource(R.drawable.ic_payment_icon);
            // card_number.setError(getResources().getString(R.string.please_enter_a_valid_card_number));
            showErrorMsg(tvCardNumberError);
            tvCardNumberError.setText(getResources().getString(R.string.please_enter_a_valid_card_number));
            tvCardHolderNameError.setVisibility(View.GONE);
            tvCardAliasError.setVisibility(View.GONE);
            //card_number.requestFocus();
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_card_validity:
                setCardValidity();
                break;
            case R.id.btn_add_card:
                if (checkValidation()) {
                    //     addToDatabase();
                    addCardData();
                }
                break;
        }
    }

    private void addCardData() {

    }

    private boolean checkCardAlreadyExist() {
        if (!getCardNum.isEmpty()) {
            for (ParticularPayment particularPayment : cardList)
                if (particularPayment != null && particularPayment.getPaymentNumber() != null) {
                    if (particularPayment.getPaymentNumber().equals(getCardNum)) {
                        return false;
                    }
                }
        }
        return true;
    }

    private void setCardValidity() {
        Calendar calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        showLog("mDay: " + mDay + "\n" + "mYear: " + mYear + "\n" + "mMonth: " + mMonth);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker dateView, int year, int monthOfYear, int dayOfMonth) {
                String yearDate = String.valueOf(year).substring(2);
                String dueDate = (monthOfYear + 1) + "/" + yearDate;
                if ((monthOfYear + 1) < 10)
                    dueDate = 0 + dueDate;
                textViewValidity.setText(dueDate);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        datePickerDialog.setTitle("");
        datePickerDialog.show();
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


    /*private void addToDatabase() {
        if (isEditable) {
            if (getCardNum.equals(checkCardNum)) {
                cardIcon = validCardIcon;
            }
        }
        values.put(PARTICULAR_PAYMENT_NUMBER, getCardNum);
        values.put(PARTICULAR_PAYMENT_ICON, cardIcon);
        values.put(PARTICULAR_PAYMENT_OWNER, getCardHolder);
        values.put(COLUMN_CARD_VALIDITY, getCardValidity);
        values.put(COLUMN_CARD_CVV, getCvv);
        values.put(PARTICULAR_PAYMENT_ALIAS, getAlias);
        values.put(PARTICULAR_PAYMENT_MOBILE, getMobileNum);
        values.put(PARTICULAR_PAYMENT_AGENT_NAME, agentName.getText().toString());
        values.put(PARTICULAR_PAYMENT_EMAIL, agentEmail.getText().toString());
        values.put(PARTICULAR_PAYMENT_TYPE, cardType);
        values.put(PAYMENT_OPTION_ID, paymentId);

        String msg = "";
        if (particularPaymentId == 0) {
            particularPaymentId = (int) dbAdapter.addParticularPaymentMode(values);
            msg = getResources().getString(R.string.card_added_successfully);
        } else {
            dbAdapter.updateParticularPaymentMode(particularPaymentId, values);
            msg = getResources().getString(R.string.card_update_successfully);
        }

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this, R.style.dialogBoxStyle);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setTitle("").setMessage(msg).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = getIntent();
                intent.putExtra(PARTICULAR_PAYMENT_ID, particularPaymentId);
                setResult(RESULT_OK, intent);
                finish();
            }
        }).show();
    }*/

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