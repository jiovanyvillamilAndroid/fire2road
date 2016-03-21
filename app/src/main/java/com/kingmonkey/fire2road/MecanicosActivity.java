package com.kingmonkey.fire2road;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.johnpersano.supertoasts.SuperToast;

import okhttp3.OkHttpClient;

public class MecanicosActivity extends AppCompatActivity {
    ListView mecanicosList;
    Utilities utilities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mecanicos);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mecánicos");
        mecanicosList = (ListView)findViewById(R.id.listViewMecanicos);
        utilities = Utilities.getInstance(this);

        MecanicoObject[] mecanicos = new MecanicoObject[3];
        for (int i=0;i<3;i++){
            mecanicos[i] = new MecanicoObject("1","-45234","123213","Mecanico#"+i,"salkdsakljasdl");
        }
        MecanicosAdapter adapter = new MecanicosAdapter(this,R.layout.mecanicos_item,mecanicos);
        mecanicosList.setItemsCanFocus(true);
        mecanicosList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.myFAB);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MecanicosActivity.this,MecanicosMapActivity.class);
                startActivity(i);
            }
        });
    }
}
