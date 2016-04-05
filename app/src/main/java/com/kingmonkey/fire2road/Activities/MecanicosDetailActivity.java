package com.kingmonkey.fire2road.Activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.kingmonkey.fire2road.R;

public class MecanicosDetailActivity extends AppCompatActivity {
    String mecanicoJSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Iconify.with(new FontAwesomeModule());
        setContentView(R.layout.activity_mecanicos_detail);
        View layout = findViewById(R.id.mecanicosDetailView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setIcon(new IconDrawable(this, FontAwesomeIcons.fa_share).colorRes(R.color.white).actionBarSize());
        Intent intent = getIntent();
        mecanicoJSON = intent.getExtras().getString("mecanicoJSONExtra");
        Snackbar
                .make(layout,mecanicoJSON,Snackbar.LENGTH_LONG)
                .show();
    }
}
