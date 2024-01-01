package com.dragon.multigame.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.dragon.multigame.R;

public class Terms_Conditions extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);

        webView = findViewById(R.id.webview);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl("https://goldwin.fun/terms&conditions.html");
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}