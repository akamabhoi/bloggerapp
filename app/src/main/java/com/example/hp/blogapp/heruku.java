package com.example.hp.blogapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class heruku extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppt_generator);
        WebView w=(WebView)findViewById(R.id.pptweb);
        w.setWebViewClient(new WebViewClient());
        WebSettings webSettings =w.getSettings();
        webSettings.setJavaScriptEnabled(true);
        w.loadUrl("https://gfqgg.herokuapp.com/?username=aayush12");
    }
}
