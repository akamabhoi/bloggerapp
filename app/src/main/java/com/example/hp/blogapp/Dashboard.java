package com.example.hp.blogapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.miguelcatalan.materialsearchview.MaterialSearchView;


public class Dashboard extends AppCompatActivity {

    MaterialSearchView materialSearchView;
    public FrameLayout frameLayout;
    String[] list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main222);
        LinearLayout ppt= (LinearLayout)findViewById(R.id.pptlayout);
        LinearLayout heruku1= (LinearLayout)findViewById(R.id.herukulayout);
        LinearLayout summary1= (LinearLayout)findViewById(R.id.summary);
        LinearLayout discovery1= (LinearLayout)findViewById(R.id.discovery);
        RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.chat);
        frameLayout = (FrameLayout)findViewById(R.id.toolbar_container);





        ppt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,pptGenerator.class);
                startActivity(intent);

            }
        });
        heruku1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,heruku.class);
                startActivity(intent);

            }
        });
       discovery1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,questiongenerator.class);
                startActivity(intent);

            }
        });
        summary1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,summary11.class);
                startActivity(intent);

            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,watchat.class);
                startActivity(intent);

            }
        });





        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar this);

        //Hello everybody, i'll show you create SearchView like Google Play
        //Watch Until the end :D Learn

        list = new String[]{"Hello","Aayush","Kartavya","Clipcodes",
                "abacus",
                "abandon",
                "abandoned",
                "abandonment",
                "abashed",
                "abate",
                "abbey",
                "abbr",
                        "abbreviate",
                "abbreviation",
                "ABC",
                "ABC","Android Tutorials", "Youtube Clipcodes Tutorials", "SearchView Clicodes", "Android Clipcodes", "Tutorials Clipcodes"};

        materialSearchView = (MaterialSearchView)findViewById(R.id.mysearch);
        materialSearchView.clearFocus();
        materialSearchView.setSuggestions(list);
        materialSearchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("sssdd", "onQueryTextS: "+query );

                Intent intent = new Intent(Dashboard.this,Query.class);
                intent.putExtra("Msg",query);
               startActivity(intent);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                frameLayout.setVisibility(View.VISIBLE);
                Log.e("aayu", "onQueryTextSubmit: "+newText);
                //You can make change realtime if you typing here
                //See my tutorials for filtering with ListView
                return false;
            }
        });

        //Follow this video for fix and other happend, Comment and Like this video . THANKS
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_home, menu);

        MenuItem item = menu.findItem(R.id.search);
        materialSearchView.setMenuItem(item);

        return true;
    }




}

