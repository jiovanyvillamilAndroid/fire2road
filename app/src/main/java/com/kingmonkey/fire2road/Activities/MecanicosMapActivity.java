package com.kingmonkey.fire2road.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.kingmonkey.fire2road.R;
import com.kingmonkey.fire2road.Utilities.Utilities;

public class MecanicosMapActivity extends AppCompatActivity {


    private static final String llaveGoogleMaps = "AIzaSyBTPCXzyHbtTIaGXl7VOZY_fVZddolpq70";
    private Utilities utilities;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mecanicos_map);
        getSupportActionBar().setTitle("Mecánicos");
        getSupportActionBar().setSubtitle("Mapa");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.fabMap);
        floatingActionButton.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_compass).colorRes(R.color.white));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        gson = new Gson();
    }

}