package com.tekitsolutions.remindme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.tekitsolutions.remindme.Adapter.PaymentAdapter;
import com.tekitsolutions.remindme.Model.General;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PARTICULAR_PAYMENT_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PAYMENT_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.CASH_ACCOUNT_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.CREDIT_CARD_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.DEBIT_CARD_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.NET_BANKING_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PAYMENT_NAME;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_PAYMENT;

public class PaymentListActivity extends BaseActivity {
    private static final String TAG = PaymentListActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private long particularPaymentId;
    private PaymentAdapter paymentAdapter;
    private List<General> payments = new ArrayList<>();
    private DatabaseAdapter databaseAdapter;
    private General general;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(PaymentListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_list);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.select_payment_mode));
        databaseAdapter = new DatabaseAdapter(this);
        recyclerView();
        getIntentData();
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent.hasExtra(PARTICULAR_PAYMENT_ID)) {
            particularPaymentId = intent.getLongExtra(PARTICULAR_PAYMENT_ID, 0);
        }
    }

    private void recyclerView() {
        payments.addAll(databaseAdapter.getPaymentById(null, 0));
        paymentAdapter = new PaymentAdapter(this, payments);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(paymentAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                general = payments.get(position);
                switch ((int) general.getId()) {
                    case CREDIT_CARD_ID:
                        sendToParticularPaymentModeActivity();
                        break;

                    case DEBIT_CARD_ID:
                        sendToParticularPaymentModeActivity();
                        break;

                    case NET_BANKING_ID:
                        sendToParticularPaymentModeActivity();
                        break;

                    case CASH_ACCOUNT_ID:
                        sendToParticularPaymentModeActivity();
                        break;
                }

            }

            private void sendToParticularPaymentModeActivity() {
                Intent intent = new Intent(PaymentListActivity.this, ParticularPaymentModeActivity.class);
                intent.putExtra(PAYMENT_ID, general.getId());
                intent.putExtra(PAYMENT_NAME, general.getName());
                if (particularPaymentId != 0) {
                    intent.putExtra(PARTICULAR_PAYMENT_ID, particularPaymentId);
                }
                startActivityForResult(intent, PICK_PAYMENT);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK:
                data.getLongExtra(PARTICULAR_PAYMENT_ID, 0);
                setResult(RESULT_OK, data);
                finish();
                break;

            case RESULT_CANCELED:
                if (data != null) {
                    data.getLongExtra(PARTICULAR_PAYMENT_ID, 0);
                    setResult(RESULT_CANCELED, data);
                }

                break;
        }
    }

    @Override
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}
