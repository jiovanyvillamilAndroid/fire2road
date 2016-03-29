package com.kingmonkey.fire2road;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.kingmonkey.fire2road.Domain.MecanicoObject;

public class MecanicosActivity extends AppCompatActivity {
    ListView mecanicosList;
    Utilities utilities;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Iconify.with(new FontAwesomeModule());
        setContentView(R.layout.activity_mecanicos);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mecánicos");
        getSupportActionBar().setSubtitle("Lista");
        getSupportActionBar().setIcon(new IconDrawable(this, FontAwesomeIcons.fa_share).colorRes(R.color.white).actionBarSize());
        mecanicosList = (ListView)findViewById(R.id.listViewMecanicos);
        utilities = Utilities.getInstance(this);

        MecanicoObject[] mecanicos = new MecanicoObject[30];
        for (int i=0;i<30;i++){
            mecanicos[i] = new MecanicoObject("1","-45234","123213","Mecanico#"+i,"salkdsakljasdl");
        }
        MecanicosAdapter adapter = new MecanicosAdapter(this,R.layout.mecanicos_item,mecanicos);
        mecanicosList.setItemsCanFocus(true);
        mecanicosList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        FloatingActionButton floatingActionButton = (FloatingActionButton)findViewById(R.id.myFAB);
        floatingActionButton.setImageDrawable(new IconDrawable(this, FontAwesomeIcons.fa_map).colorRes(R.color.white));
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MecanicosActivity.this, MapsActivity.class);
                startActivity(i);
            }
        });
        utilities.makeSimpleToast("Recuerda activar la ubicación GPS",SuperToast.Duration.MEDIUM);
    }
}
