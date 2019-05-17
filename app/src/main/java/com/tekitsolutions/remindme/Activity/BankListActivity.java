package com.tekitsolutions.remindme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.tekitsolutions.remindme.Adapter.BankListAdapter;
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

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.BANK_ICON;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.BANK_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.BANK_NAME;
import static com.tekitsolutions.remindme.Utils.CommonUtils.ACCOUNT_VALUE;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PAYMENT;

public class BankListActivity extends BaseActivity {
    private static final String TAG = BankListActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private BankListAdapter bankAdapter;
    private List<General> banks = new ArrayList<>();
    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(BankListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_list);
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.select_bank));

        databaseAdapter = new DatabaseAdapter(this);

        banks.addAll(databaseAdapter.getBankById(null, 0));

        bankAdapter = new BankListAdapter(this, banks);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(bankAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                General bank = banks.get(position);
                Intent intent = new Intent();
                intent.putExtra(BANK_ID, bank.getId());
                intent.putExtra(BANK_ICON, bank.getIcon());
                intent.putExtra(BANK_NAME, bank.getName());
                intent.putExtra(PAYMENT, ACCOUNT_VALUE);

                setResult(RESULT_OK, intent);
                finish();
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
}
