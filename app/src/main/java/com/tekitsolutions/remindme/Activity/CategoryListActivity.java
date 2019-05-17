package com.tekitsolutions.remindme.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.tekitsolutions.remindme.Adapter.CategoryAdapter;
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
import static com.tekitsolutions.remindme.Utils.CommonUtils.CUSTOM_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.ELECTRICITY_BILL_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.GAS_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.INSURANCE_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.LANDLINE_ID;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PAYMENT_NAME;
import static com.tekitsolutions.remindme.Utils.CommonUtils.PICK_CATEGORY;
import static com.tekitsolutions.remindme.Utils.CommonUtils.WATER_BILL_ID;

public class CategoryListActivity extends BaseActivity {
    private static final String TAG = CategoryListActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private CategoryAdapter categoryAdapter;
    private List<General> categories = new ArrayList<>();
    private DatabaseAdapter databaseAdapter;
    private General general;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(CategoryListActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_list);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.title_select_category));
        databaseAdapter = new DatabaseAdapter(this);
        categories.addAll(databaseAdapter.getCategoryById(null, 0));
        categoryAdapter = new CategoryAdapter(this, categories);
        recyclerView();
    }

    private void recyclerView() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener.RecyclerTouchListener(this, recyclerView, new RecyclerViewClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                general = categories.get(position);
                switch ((int) general.getId()) {
                    case ELECTRICITY_BILL_ID:
                        showLog("TypeCast: " + (int) general.getId());
                        sendData(AddElectricityBillActivity.class);
                        break;

                    case INSURANCE_ID:
                        sendData(AddInsuranceActivity.class);
                        break;

                    case LANDLINE_ID:
                        sendData(AddLandlineActivity.class);
                        break;

                    case GAS_ID:
                        sendData(AddGasActivity.class);
                        break;

                    case WATER_BILL_ID:
                        sendData(AddWaterBillActivity.class);
                        break;

                    case CUSTOM_ID:
                        sendData(AddCustomCategory.class);
                        break;
                }

            }

            private void sendData(Class<?> cls) {
                Intent intent = new Intent(CategoryListActivity.this, cls);
                intent.putExtra(CATEGORY_ID, general.getId());
                intent.putExtra(PAYMENT_NAME, general.getName());
                startActivityForResult(intent, PICK_CATEGORY);
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
                setResult(RESULT_OK, data);
                finish();
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
