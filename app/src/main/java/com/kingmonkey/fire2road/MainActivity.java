package com.kingmonkey.fire2road;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;
import jp.wasabeef.picasso.transformations.BlurTransformation;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    SliderLayout sliderShowProductosDestacados;
    SliderLayout sliderShowEventos;
    Utilities utilities;
    View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Iconify.with(new FontAwesomeModule());
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        utilities = Utilities.getInstance(this);
        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(this.getResources().getColor(R.color.dark_gray));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //Inicializamos Iconify
        Iconify.with(new FontAwesomeModule());
        //Inicializamos Logger
        Logger.init();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        headerView = navigationView.getHeaderView(0);

        ImageView profile = (ImageView) headerView.findViewById(R.id.imageView);
        ImageView backgroundProfile = (ImageView) headerView.findViewById(R.id.imageViewBackgroundView);
        TextView usernameTextView = (TextView)headerView.findViewById(R.id.usernameTextView);
        TextView refMotoTextView = (TextView)headerView.findViewById(R.id.refMotoTextView);
        usernameTextView.setText("Cristian Jiovany Villamil");
        refMotoTextView.setText("YZF-R3");
        Picasso.with(this).load("http://cdn.wonderfulengineering.com/wp-content/uploads/2013/12/yamaha-logo-wallpaper.jpg").transform(new BlurTransformation(this)).into(backgroundProfile);
        Picasso.with(this).load("http://www.yamaha-motor.com.au/sites/yamaha-motor/files/imagecache/lightbox/Product_Feature_Image_R3_3.jpg").into(profile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ProfileActivity.class);
                i.putExtra("linkPhoto","http://www.yamaha-motor.com.au/sites/yamaha-motor/files/imagecache/lightbox/Product_Feature_Image_R3_3.jpg");
                startActivity(i);
            }
        });
        sliderShowProductosDestacados = (SliderLayout) findViewById(R.id.productosDestacadosSlider);
        sliderShowProductosDestacados.setBackgroundColor(Color.parseColor("#050505"));
        TextSliderView textSliderView = new TextSliderView(this);
        textSliderView
                .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                .description("AGV CORSA")
                .image("http://images2.revzilla.com/segment_homepage_callout/image/749/rectangle/20160203-FL-AGV-Corsa-Helmet-520-Update-FINAL.jpg");
        sliderShowProductosDestacados.addSlider(textSliderView);
        sliderShowProductosDestacados.addSlider(textSliderView);
        sliderShowProductosDestacados.addSlider(textSliderView);
        sliderShowProductosDestacados.stopAutoCycle();
        sliderShowProductosDestacados.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));

        sliderShowEventos = (SliderLayout) findViewById(R.id.eventosSlider);
        sliderShowEventos.setBackgroundColor(Color.parseColor("#050505"));
        TextSliderView textSliderView2 = new TextSliderView(this);
        textSliderView2
                .setScaleType(BaseSliderView.ScaleType.FitCenterCrop)
                .description("Expo Moto")
                .image("http://mexicoenlared.tv/wp-content/uploads/2015/11/Expomoto.-M%C3%A9xico-en-la-Red.jpg");
        sliderShowEventos.addSlider(textSliderView2);
        sliderShowEventos.addSlider(textSliderView2);
        sliderShowEventos.addSlider(textSliderView2);
        sliderShowEventos.stopAutoCycle();
        sliderShowEventos.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicatorEventos));
    }

    @Override
    protected void onStop() {
        sliderShowEventos.stopAutoCycle();
        sliderShowProductosDestacados.stopAutoCycle();
        super.onStop();
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {
            Intent i = new Intent(MainActivity.this,ParaTiActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_slideshow) {
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(MainActivity.this,MecanicosActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_send) {
        } else if (id == R.id.nav_personalizacion) {
        } else if (id == R.id.nav_cerrarsesion) {
            this.finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public void onBackPressed(){

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else{
            finish();
        }

    }

}
