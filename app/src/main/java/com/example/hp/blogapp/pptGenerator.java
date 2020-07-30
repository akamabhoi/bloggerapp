package com.example.hp.blogapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class pptGenerator extends AppCompatActivity {

    WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppt_generator);

w=(WebView)findViewById(R.id.pptweb);
       w.setWebViewClient(new WebViewClient());
        WebSettings webSettings =w.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (savedInstanceState != null)
        { w.restoreState(savedInstanceState);
            Log.e("hhjj", "onCreate: helo" );
        }
        else {
            Log.e("hhjj", "onCreate: hejbjblo" );
            w.loadUrl("https://script.google.com/macros/s/AKfycbyDWHtY1Vz6_PgeJwOydx4IF3_65mtaWUJ3Hvqnthc/dev");
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        Bundle bundle = new Bundle();
        w.saveState(bundle);
        outState.putBundle("webViewState", bundle);


    }

}
