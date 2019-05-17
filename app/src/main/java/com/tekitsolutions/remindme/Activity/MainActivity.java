package com.tekitsolutions.remindme.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.ajts.androidmads.library.SQLiteToExcel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.tekitsolutions.remindme.Adapter.ReminderListAdapter;
import com.tekitsolutions.remindme.Adapter.ViewPagerAdapter;
import com.tekitsolutions.remindme.Fragment.HomeFragment;
import com.tekitsolutions.remindme.Fragment.OfficeFragment;
import com.tekitsolutions.remindme.Fragment.OtherFragment;
import com.tekitsolutions.remindme.Interface.AdapterInterface;
import com.tekitsolutions.remindme.Interface.HamburgerMenuInterface;
import com.tekitsolutions.remindme.Model.Reminder;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Sql.DataBaseConstant;
import com.tekitsolutions.remindme.Sql.DatabaseAdapter;
import com.tekitsolutions.remindme.Utils.ReminderApp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tekitsolutions.remindme.Utils.CommonUtils.CREATE_PASSCODE_ACTIVITY;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterInterface, HamburgerMenuInterface {
    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.list_view)
    RecyclerView listView;
    AdapterInterface listener;
    private ActionBar actionBar;
    private DatabaseAdapter dbAdapter;
    private ReminderListAdapter listAdapter;
    private List<Reminder> listItems = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private HamburgerMenuInterface menuInterface;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CREATE_PASSCODE_ACTIVITY = 0;
        setThemeAndLoadLocale(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        ButterKnife.bind(this);
        setToolbar(toolbar, getString(R.string.app_name));

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        Menu menu = navigationView.getMenu();
        MenuItem tools = menu.findItem(R.id.nav_general_setting);
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.nav_header_color), 0, s.length(), 0);
        tools.setTitle(s);

       /* Menu menu1 = navigationView.getMenu();
        MenuItem tools1 = menu1.findItem(R.id.nav_recycle);
        SpannableString s1 = new SpannableString(tools1.getTitle());
        s1.setSpan(new TextAppearanceSpan(this, R.style.nav_header_color), 0, s1.length(), 0);
        tools1.setTitle(s1);*/

        Menu menu2 = navigationView.getMenu();
        MenuItem tools2 = menu2.findItem(R.id.nav_communicate);
        SpannableString s2 = new SpannableString(tools2.getTitle());
        s2.setSpan(new TextAppearanceSpan(this, R.style.nav_header_color), 0, s2.length(), 0);
        tools2.setTitle(s2);

      /*  Menu menu3 = navigationView.getMenu();
        MenuItem tools3 = menu3.findItem(R.id.nav_other);
        SpannableString s3 = new SpannableString(tools3.getTitle());
        s3.setSpan(new TextAppearanceSpan(this, R.style.nav_header_color), 0, s3.length(), 0);
        tools3.setTitle(s3);*/

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new HomeFragment(), getResources().getString(R.string.fragment_home));
        adapter.AddFragment(new OfficeFragment(), getResources().getString(R.string.fragment_office));
        adapter.AddFragment(new OtherFragment(), getResources().getString(R.string.fragment_other));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        listAdapter = new ReminderListAdapter(this, listItems, MainActivity.this, this);
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


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(MainActivity.this, AddReminderActivity.class));
                startActivity(new Intent(MainActivity.this, DashBoardActivity.class));
            }
        });


        dbAdapter = new DatabaseAdapter(this);
    }

    public void convertDatabaseToExcel() {
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/RemindMe/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        SQLiteToExcel sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DataBaseConstant.DATABASE_NAME, directory_path);
        sqliteToExcel.exportAllTables("RemindMe.xls", new SQLiteToExcel.ExportListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onCompleted(String filePath) {
                // Utils.showSnackBar(view, "Successfully Exported");
                Log.e(TAG, "Successfully Exported");

            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "Exception: " + e);
            }
        });
    }




  /*  public void shareText(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        String shareBodyText = "Your shearing message goes here";
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject/Title");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(intent, "Choose sharing method"));
    }*/

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_favorite:
                intent = new Intent(MainActivity.this, AddFavoriteReminder.class);
                startActivity(intent);
                break;
            case R.id.nav_setting:
                convertDatabaseToExcel();
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
           /* case R.id.nav_language:
                Toast.makeText(this, "Language", Toast.LENGTH_SHORT).show();
                break;*/
          /*  case R.id.nav_support:
                break;*/
           /* case R.id.nav_bin:

                break;
            case R.id.nav_backup:

                break;
            case R.id.nav_restore:

                break;
            case R.id.nav_send:

                break;*/
            case R.id.nav_share:
                String link = "https://drive.google.com/drive/folders/1sDS_hGb5s5SjyDIK3me3oY8cS1Oo1I4F?usp=sharing";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                SpannableString ss = new SpannableString("Android is a Software stack");

                String shareBodyText = link;
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
                startActivity(Intent.createChooser(sharingIntent, "Shearing Option"));
                return true;
            //break;
            case R.id.nav_help:
                intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_feedback:


                intent = new Intent(Intent.ACTION_SENDTO);
                intent.setType("message/rfc822");
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"jimmytrivedi@outlook.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Remind Me Feedback");
                intent.putExtra(Intent.EXTRA_TEXT, "Developer Team,");
                try {
                    startActivity(Intent.createChooser(intent, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

                break;
           /* case R.id.nav_about:

                break;
            case R.id.nav_logout:

                break;*/

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClickFavorite(int isFavorite, long position) {

    }

    @Override
    public void onClickReminderList(int position) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        CREATE_PASSCODE_ACTIVITY = 0;
        if (ReminderApp.isChanged()) {
            ReminderApp.setChanged(false);
            recreate();
        }
        //changeLanguage();
        //    loadAllReminders();
    }

    /*private void loadAllReminders() {
        listItems.clear();
        List<Reminder> list = dbAdapter.getAllReminders();
        if (list.size() > 0) {
            tvNoData.setVisibility(View.GONE);
            listItems.addAll(list);
            listAdapter.notifyDataSetChanged();
        } else {
            tvNoData.setVisibility(View.VISIBLE);
        }
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK) {
            switch (requestCode) {

            }
        }
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        *//*SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));*//*
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
           /* case R.id.search:
                break;*/
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClickHamburger(int position) {

    }

    @Override
    public void onClickListItem(int position) {

    }

    @Override
    public void setToolbar(Toolbar toolBar, String title) {
        super.setToolbar(toolBar, title);
    }

    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}