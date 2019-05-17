package com.tekitsolutions.remindme.Activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.material.snackbar.Snackbar;
import com.tekitsolutions.remindme.R;
import com.tekitsolutions.remindme.Receiver.ConnectivityReceiver;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;


public class WebPageActivity extends BaseActivity {
    private static final String TAG = WebPageActivity.class.getSimpleName();

    WebView webView;
    WebSettings webSettings;
    String postUrl = "";
    String pageTitle = "";
    FragmentManager manager;
    Intent mainIntent;
    private BroadcastReceiver mIntentReceiver;
    private ProgressDialog dialog;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private float m_downX;
    private Button btnRetry;
    private LinearLayout viewNoInternet;
    private LinearLayout relativeId;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setThemeAndLoadLocale(WebPageActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_page);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        mainIntent = getIntent();
        postUrl = mainIntent.getStringExtra("page_url");
        pageTitle = mainIntent.getStringExtra("page_title");
        showToast(getApplicationContext(), postUrl);
        actionBar.setTitle(pageTitle);
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        this.viewNoInternet = findViewById(R.id.viewNoInternet);
        this.btnRetry = findViewById(R.id.btnRetry);

        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ConnectivityReceiver.isConnected(WebPageActivity.this)) {
                    viewNoInternet.setVisibility(View.GONE);
                    renderPost();
                } else {
                    viewNoInternet.setVisibility(View.VISIBLE);
                }
            }
        });

        initWebView();
        if (ConnectivityReceiver.isConnected(WebPageActivity.this)) {
            renderPost();
        } else {
            viewNoInternet.setVisibility(View.VISIBLE);
        }
    }

    public void showSnackBar(String string, View view) {
        snackbar = Snackbar
                .make(view, string, Snackbar.LENGTH_INDEFINITE).
                        setAction("Ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                snackbar.dismiss();
                            }
                        });
        snackbar.show();
    }

    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    private void initWebView() {
        //webView.setWebChromeClient(new MyWebChromeClient(this));
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
                invalidateOptionsMenu();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
                if (url.contains("http://exitpage")) {
                    showToast(getApplicationContext(), "Thank you.");
                    finish();
                }
                invalidateOptionsMenu();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                progressBar.setVisibility(View.GONE);
                invalidateOptionsMenu();
                viewNoInternet.setVisibility(View.VISIBLE);
            }
        });

        webView.clearCache(true);
        webView.clearHistory();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getPointerCount() > 1) {
                    //Multi touch detected
                    return true;
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        // save the x
                        m_downX = event.getX();
                    }
                    break;
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP: {
                        // set x so that it doesn't move
                        event.setLocation(m_downX, event.getY());
                    }
                    break;
                }
                return false;
            }
        });
    }

    private void renderPost() {
        webView.loadUrl(postUrl);
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


    private void showLog(String msg) {
        Log.d(TAG, msg);
    }
}