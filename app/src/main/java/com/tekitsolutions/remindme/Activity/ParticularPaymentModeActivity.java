package com.tekitsolutions.remindme.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tekitsolutions.remindme.Adapter.ParticularPaymentListAdapter;
import com.tekitsolutions.remindme.Interface.HamburgerMenuInterface;
import com.tekitsolutions.remindme.Model.ParticularPayment;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.RestApi.ApiClient;
import com.tekitsolutions.remindme.RestApi.ApiInterface;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_TYPE;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PAYMENT_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.CASH_ACCOUNT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.CREDIT_CARD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.DEBIT_CARD;
import static com.tekitsolutions.remindme.Utils.CommonUtils.IS_EDIT;
import static com.tekitsolutions.remindme.Utils.CommonUtils.NET_BANKING;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PAYMENT_NAME;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_PAYMENT;


public class ParticularPaymentModeActivity extends BaseActivity implements HamburgerMenuInterface, Callback<String> {
    private static final String TAG = ParticularPaymentModeActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private long paymentId;
    private long particularPaymentId;
    private long getParticularPaymentId;
    private String paymentName, cvv = "", paymentType;
    private ParticularPaymentListAdapter listAdapter;
    private List<ParticularPayment> listItems;
    private DatabaseAdapter dbAdapter;
    private boolean isDeleted = false;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(ParticularPaymentModeActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_with_plus_button);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        listItems = new ArrayList<>();
        dbAdapter = new DatabaseAdapter(this);
        getIntentData();
        setToolbar(toolbar, paymentName);
        recyclerView();
        floatingButton();
    }

    private void clickOnPlusButton(Class<?> cls, String paymentName) {
        Intent intent = new Intent(ParticularPaymentModeActivity.this, cls);
        intent.putExtra(PARTICULAR_PAYMENT_TYPE, paymentName);
        intent.putExtra(PAYMENT_ID, paymentId);
        startActivityForResult(intent, PICK_PAYMENT);
    }

    private void floatingButton() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (paymentName) {
                    case CREDIT_CARD:
                        clickOnPlusButton(AddCardActivity.class, CREDIT_CARD);
                        break;

                    case DEBIT_CARD:
                        clickOnPlusButton(AddCardActivity.class, DEBIT_CARD);
                        break;

                    case NET_BANKING:
                        clickOnPlusButton(AddNetBankingActivity.class, NET_BANKING);
                        break;

                    case CASH_ACCOUNT:
                        clickOnPlusButton(AddCashActivity.class, CASH_ACCOUNT);
                        break;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                data.getLongExtra(PARTICULAR_PAYMENT_ID, 0);
                break;
        }
    }

    private void recyclerView() {
        listAdapter = new ParticularPaymentListAdapter(this, listItems, this);
        listView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager;
        mLayoutManager = new LinearLayoutManager(this);
        listView.setLayoutManager(mLayoutManager);
        listView.setItemAnimator(new DefaultItemAnimator());
        try {
            listView.setAdapter(listAdapter);
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }

    private void getIntentData() {
        intent = getIntent();
        paymentId = intent.getLongExtra(PAYMENT_ID, 0);
        paymentName = intent.getStringExtra(PAYMENT_NAME);
        if (intent.hasExtra(PARTICULAR_PAYMENT_ID)) {
            getParticularPaymentId = intent.getLongExtra(PARTICULAR_PAYMENT_ID, 0);
        }
    }

    private void loadAllPayment() {
        listItems.clear();
        List<ParticularPayment> particularPaymentList = dbAdapter.getParticularPaymentById(null, 0, paymentName);

        if (particularPaymentList.size() > 0) {
            tvNoData.setVisibility(View.GONE);
            listItems.addAll(particularPaymentList);
            listAdapter.notifyDataSetChanged();
        } else {
            tvNoData.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadAllPayment();
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
        Intent intent = getIntent();
        if (isDeleted) {
            intent.putExtra(PARTICULAR_PAYMENT_ID, 0);
        }
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    public void onClickHamburger(int position) {
        final ParticularPayment particularPayment = listItems.get(position);

        if (particularPayment != null) {
            showLog("Not null");
        } else {
            showLog("null");
        }

        particularPaymentId = particularPayment.getParticularPaymentId();
        final CharSequence[] items = {getResources().getString(R.string.view), getResources().getString(R.string.edit),
                getResources().getString(R.string.delete), getResources().getString(R.string.cancel)};
        AlertDialog.Builder builder = new AlertDialog.Builder(ParticularPaymentModeActivity.this, R.style.dialogBoxStyle);

        builder.setItems(items, new DialogInterface.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(DialogInterface dialog, int item) {
                Intent intent;
                if (items[item].equals(getResources().getString(R.string.view))) {
                    if (!particularPayment.getCvv().isEmpty()) {
                        cvv = particularPayment.getCvv();
                    }

                    String info =
                            "<b>" + getResources().getString(R.string.card_no) + "</b> : " + particularPayment.getPaymentNumber() +
                                    "<br><b>" + getResources().getString(R.string.card_holder) + "</b> : " + particularPayment.getOwnerName() +
                                    "<br><b>" + getResources().getString(R.string.validity) + "</b> : " + particularPayment.getCardValidity() +
                                    "<br><b>" + getResources().getString(R.string.alias_name) + "</b> : " + particularPayment.getAliasName() +
                                    "<br><b>" + getResources().getString(R.string.cvv) + "</b> : " + cvv;
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ParticularPaymentModeActivity.this, R.style.dialogBoxStyle);

                    alertDialogBuilder.setTitle(Html.fromHtml(getResources().getString(R.string.card_info))).setMessage(Html.fromHtml(info)).setPositiveButton(getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    }).show();
                } else if (items[item].equals(getResources().getString(R.string.edit))) {
                    switch (particularPayment.getPaymentType()) {
                        case DEBIT_CARD:
                            sendToActivity(AddCardActivity.class);
                            break;

                        case CREDIT_CARD:
                            sendToActivity(AddCardActivity.class);
                            break;

                        case NET_BANKING:
                            sendToActivity(AddNetBankingActivity.class);
                            break;

                        case CASH_ACCOUNT:
                            sendToActivity(AddCashActivity.class);
                            break;
                    }

                } else if (items[item].equals(getResources().getString(R.string.delete))) {
                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ParticularPaymentModeActivity.this, R.style.dialogBoxStyle);
                    alertDialogBuilder.setCancelable(false);
                    alertDialogBuilder.setTitle(getResources().getString(R.string.are_you_sure)).setMessage(getResources().getString(R.string.want_to_delete_card)).setPositiveButton(getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (getParticularPaymentId == particularPaymentId) {
                                isDeleted = true;
                            }
                            deleteFromServer();
                        }
                    }).setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();
                } else if (items[item].equals(getResources().getString(R.string.cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void deleteFromServer() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        final Call<String> response = apiService.deletePayment(particularPaymentId);
        response.enqueue(this);
    }

    private void sendToActivity(Class<?> cls) {
        Intent intent = new Intent(ParticularPaymentModeActivity.this, cls);
        intent.putExtra(PARTICULAR_PAYMENT_ID, particularPaymentId);
        intent.putExtra(IS_EDIT, true);
        startActivity(intent);
    }

    @Override
    public void onClickListItem(int position) {
        ParticularPayment particularPayment = listItems.get(position);
        Intent intent = new Intent();
        intent.putExtra(PARTICULAR_PAYMENT_ID, particularPayment.getParticularPaymentId());
        setResult(RESULT_OK, intent);
        finish();
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
        int statusCode = response.code();

        if (statusCode == 200) {
            if (dbAdapter.deleteParticularPaymentMode(particularPaymentId)) {
                Toast.makeText(ParticularPaymentModeActivity.this, getResources().getString(R.string.card_info_deleted), Toast.LENGTH_SHORT).show();
                loadAllPayment();
            }
        }
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {

    }
}