package com.tekitsolutions.remindme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.tekitsolutions.remindme.Model.ParticularPayment;
import com.tekitsolutions.remindme.Model.Reminder;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.REMINDER_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_REMINDER;

public class ReminderDetailActivity extends BaseActivity {
    private static final String TAG = ReminderDetailActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.iv_reminder)
    ImageView ivReminder;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_category)
    TextView tvCategory;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.iv_payment_type)
    ImageView ivPaymentType;
    @BindView(R.id.tv_payment_type)
    TextView tvPaymentType;
    @BindView(R.id.tv_payment_detail)
    TextView tvPaymentDetail;
    @BindView(R.id.scroll_view)
    ScrollView scrollView;
    @BindView(R.id.tv_due_date)
    TextView tvDueDate;
    @BindView(R.id.layout_date)
    LinearLayout layoutDate;
    @BindView(R.id.layout_dismis)
    LinearLayout layoutDismis;
    @BindView(R.id.layout_pay_now)
    LinearLayout layoutPayNow;
    @BindView(R.id.layout_actions)
    RelativeLayout layoutActions;
    @BindView(R.id.layout_edit)
    LinearLayout layoutEdit;
    @BindView(R.id.tv_notes)
    TextView tvNotes;
    @BindView(R.id.iv_provider)
    ImageView ivProvider;
    @BindView(R.id.tv_provider)
    TextView tvProvider;
    @BindView(R.id.tv_bill_detail)
    TextView tvBillDetail;
    @BindView(R.id.tv_bill_alias)
    TextView tvBillAlias;
    @BindView(R.id.pay_now)
    TextView payNow;
    @BindView(R.id.layoutElectricity)
    LinearLayout layoutElectricity;

    private int reminderId;
    private DatabaseAdapter dbAdapter;
    private Reminder reminder;
    private String bankUrl, ifsc, aliasName, mobileNum;
    private ParticularPayment particularPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(ReminderDetailActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_detail);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.title_reminder_info));

        dbAdapter = ReminderApp.getInstance().adapter;
        layoutPayNow.setEnabled(false);

        if (getIntent().hasExtra(REMINDER_ID)) {
            reminderId = getIntent().getIntExtra(REMINDER_ID, 0);
            Log.d("mTesting", "reminderId: " + reminderId);
            setReminderDetails(reminderId);
        }
    }

    private void setReminderDetails(int reminderId) {
        reminder = dbAdapter.getReminderById(REMINDER_ID, reminderId).get(0);
        if (reminder != null) {
            tvTitle.setText(reminder.getTitle());
            /*if (reminder.getParticularCategoryId() > 0) {
                tvCategory.setText(reminder.getGeneral().getName());
                ivReminder.setImageResource(reminder.getGeneral().getIcon());
               *//* if (reminder.getParticularCategory() != null) {
                    if (reminder.getGeneral().getName().equals(getResources().getString(R.string.insurance))) {
                        tvProvider.setText(reminder.getParticularCategory().getGeneralSubProvider().getName());
                        ivProvider.setImageResource(reminder.getParticularCategory().getGeneralSubProvider().getIcon());
                    } else if (reminder.getId() == 6) {
                        tvProvider.setText(reminder.getParticularCategory().getCustomProviderName());
                        ivProvider.setImageResource(R.drawable.ic_add_circle_black_24dp);
                    } else {
                        tvProvider.setText(reminder.getParticularCategory().getGeneralProvider().getName());
                        ivProvider.setImageResource(reminder.getParticularCategory().getGeneralProvider().getIcon());
                    }

                    tvBillDetail.setText(reminder.getParticularCategory().getProviderNumber());
                    tvBillAlias.setText(reminder.getParticularCategory().getAliasName());
                    layoutElectricity.setVisibility(View.VISIBLE);
                }*//*

            } else {
                layoutElectricity.setVisibility(View.GONE);
            }*/


            tvAmount.setText(reminder.getAmount());

            //For Card
          /*  if (reminder.getPaymentType().equals(CARD)) {
                particularPayment = dbAdapter.getParticularPaymentById(reminder.getParticularPaymentId());
                if (particularPayment != null) {
                    ivPaymentType.setImageResource(CardUtils.getCardIcon(particularPayment.getPaymentNumber()));
                    tvPaymentType.setText(particularPayment.getPaymentType().substring(0, 1).toUpperCase() + particularPayment.getPaymentType().substring(1) + " Card");


                    String cardDetail =
                            getResources().getString(R.string.alias_name) + ": " + particularPayment.getAliasName() +
                                    "\n" + getResources().getString(R.string.card_no) + "" + " : " + particularPayment.getPaymentNumber() +
                                    "\n" + getResources().getString(R.string.card_holder) + "" + " : " + particularPayment.getOwnerName() +
                                    "\n" + getResources().getString(R.string.validity) + "" + " : " + particularPayment.getCardValidity();

                    tvPaymentDetail.setText(cardDetail);
                    payNow.setText(getResources().getString(R.string.pay_now));
                } else {
                    tvPaymentType.setText("Payment");
                    tvPaymentDetail.setText("Payment information not available.");
                    payNow.setText("Select Payment");
                }
            }*/

            if (reminder.getDueDate() == "")
                layoutDate.setVisibility(View.GONE);
            else
                tvDueDate.setText(reminder.getDueDate());

            tvNotes.setText("Note: " + reminder.getNotes());

        }

    }


    @OnClick({R.id.layout_edit, R.id.layout_dismis, R.id.layout_pay_now})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.layout_dismis:
                if (this.getParent() == null) {
                    intent = new Intent(this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
                this.finish();
                break;
            case R.id.layout_edit:
                intent = new Intent(this, AddReminderActivity.class);
                intent.putExtra(REMINDER_ID, reminderId);
                startActivityForResult(intent, PICK_REMINDER);
                break;
            case R.id.layout_pay_now:
               /* intent = new Intent(this, WebPageActivity.class);
                intent.putExtra("page_url", bankUrl);
                intent.putExtra("page_title", "ParticularPayment");
                startActivity(intent);
                finish();*/
                break;
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PICK_REMINDER:
                    reminderId = getIntent().getIntExtra(REMINDER_ID, 0);
                    setReminderDetails(reminderId);
            }
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