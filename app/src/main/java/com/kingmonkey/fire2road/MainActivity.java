package com.kingmonkey.fire2road;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.johnpersano.supertoasts.SuperToast;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.orhanobut.logger.Logger;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Fire 2 Road");

        if (Build.VERSION.SDK_INT >= 21) {

            // Set the status bar to dark-semi-transparentish
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

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
        ImageView profile = (ImageView)navigationView.getHeaderView(0).findViewById(R.id.imageView);
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(profile);
        //Evita que al terminar los fragmentos quede activo
        fragmentManager = getFragmentManager();
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if(getFragmentManager().getBackStackEntryCount() == 0) finish();
            }
        });
        //Para que inicialice en home siempre
        HomeFragment homeFragment = new HomeFragment();
        switchFragment(homeFragment,"home");

    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            toolbar.setSubtitle("Fire2Road");
            HomeFragment homeFragment = new HomeFragment();
            switchFragment(homeFragment,"home");
        } else if (id == R.id.nav_gallery) {
            toolbar.setSubtitle("Para Ti");

        } else if (id == R.id.nav_slideshow) {
            toolbar.setSubtitle("Para Tu Motocicleta");

        } else if (id == R.id.nav_manage) {
            toolbar.setTitle("Eventos");

        } else if (id == R.id.nav_share) {
            Intent i = new Intent(MainActivity.this,MecanicosActivity.class);
            startActivity(i);
            /*MecanicosFragment mecanicosFragment = new MecanicosFragment();
            switchFragment(mecanicosFragment,"Mecanicos");*/
        } else if (id == R.id.nav_send) {
            toolbar.setSubtitle("Lavaderos");
        } else if (id == R.id.nav_personalizacion) {
            toolbar.setSubtitle("Personalización");
        } else if (id == R.id.nav_cerrarsesion) {
            this.finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void switchFragment(Fragment fragmento,String tag){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragmento,tag);
        transaction.commit();
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
