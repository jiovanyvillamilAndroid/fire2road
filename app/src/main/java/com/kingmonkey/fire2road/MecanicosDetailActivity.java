package com.kingmonkey.fire2road;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MecanicosDetailActivity extends AppCompatActivity {
    String mecanicoJSON;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mecanicos_detail);
        View layout = findViewById(R.id.mecanicosDetailView);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        Intent intent = getIntent();
        mecanicoJSON = intent.getExtras().getString("mecanicoJSONExtra");
        Snackbar
                .make(layout,mecanicoJSON,Snackbar.LENGTH_LONG)
                .show();
    }
}
