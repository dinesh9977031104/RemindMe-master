package com.tekitsolutions.remindme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.tekitsolutions.remindme.Adapter.ProviderListAdapter;
import com.tekitsolutions.remindme.Adapter.SubProviderListAdapter;
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

import static com.tekitsolutions.remindme.Sql.DataBaseConstant.CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_CATEGORY_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_ICON;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_KEY;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.PROVIDER_NAME;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SUB_PROVIDER_ICON;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SUB_PROVIDER_ID;
import static com.tekitsolutions.remindme.Sql.DataBaseConstant.SUB_PROVIDER_NAME;
import static com.tekitsolutions.remindme.Utils.CommonUtils.INS_CATEGORY;

public class ProviderListActivity extends BaseActivity {
    private static final String TAG = ProviderListActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ProviderListAdapter providerListAdapter;
    private SubProviderListAdapter subProviderListAdapter;
    private List<General> providerList = new ArrayList<>();
    private List<General> subProviderList = new ArrayList<>();
    private DatabaseAdapter databaseAdapter;
    private General provider;
    private General subProvider;
    private long categoryId, insProviderId;
    private Intent intent;
    private Boolean insSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(ProviderListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_list_new);
        init();

    }

    private void init() {
        ButterKnife.bind(this);
        getIntentData();
        setToolbar(toolbar, getString(R.string.select_provider));
        databaseAdapter = new DatabaseAdapter(this);
        providerListAdapter = new ProviderListAdapter(this, providerList);
        subProviderListAdapter = new SubProviderListAdapter(this, subProviderList);
        isInsurance();
    }

    private void isInsurance() {
        if (insSelected) {
            subProviderList.clear();
            subProviderList.addAll(databaseAdapter.getSubProvidersById(PROVIDER_KEY, insProviderId));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(subProviderListAdapter);
            recyclerView.setHasFixedSize(true);
            subProviderListAdapter.notifyDataSetChanged();

            recyclerView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    subProvider = subProviderList.get(position);
                    Intent intent = new Intent();
                    intent.putExtra(SUB_PROVIDER_ID, subProvider.getId());
                    intent.putExtra(SUB_PROVIDER_ICON, subProvider.getIcon());
                    intent.putExtra(SUB_PROVIDER_NAME, subProvider.getName());
                    setResult(RESULT_OK, intent);
                    finish();
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));

        } else {
            providerList.clear();
            providerList.addAll(databaseAdapter.getProviderById(PROVIDER_CATEGORY_ID, categoryId));
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            recyclerView.setAdapter(providerListAdapter);
            recyclerView.setHasFixedSize(true);
            providerListAdapter.notifyDataSetChanged();


            recyclerView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {
                    provider = providerList.get(position);
                    Intent intent = new Intent();
                    intent.putExtra(PROVIDER_ID, provider.getId());
                    intent.putExtra(PROVIDER_ICON, provider.getIcon());
                    intent.putExtra(PROVIDER_NAME, provider.getName());
                    setResult(RESULT_OK, intent);
                    finish();
                }

                @Override
                public void onLongClick(View view, int position) {

                }
            }));
        }
    }

    private void getIntentData() {
        intent = getIntent();
        categoryId = intent.getLongExtra(CATEGORY_ID, 0);
        insProviderId = intent.getLongExtra(PROVIDER_ID, 0);
        insSelected = intent.getBooleanExtra(INS_CATEGORY, false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent intent = new Intent();
            intent.putExtra(PROVIDER_ID, provider.getId());
            intent.putExtra(PROVIDER_ICON, provider.getIcon());
            intent.putExtra(PROVIDER_NAME, provider.getName());
            intent.putExtra(SUB_PROVIDER_ID, subProvider.getId());
            intent.putExtra(SUB_PROVIDER_NAME, subProvider.getName());
            intent.putExtra(SUB_PROVIDER_ICON, subProvider.getIcon());
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        intent = getIntent();
        categoryId = intent.getLongExtra(CATEGORY_ID, 0);
        insProviderId = intent.getLongExtra(PROVIDER_ID, 0);
        insSelected = intent.getBooleanExtra(INS_CATEGORY, false);

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
        super.onBackPressed();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}