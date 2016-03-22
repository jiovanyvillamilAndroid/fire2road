package com.kingmonkey.fire2road;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.johnpersano.supertoasts.SuperToast;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

public class MecanicosMapActivity extends AppCompatActivity {
    private MapView myOpenMapView;
    private MapController myMapController;
    private Utilities utilities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mecanicos_map);
        getSupportActionBar().setTitle("Mecánicos");
        getSupportActionBar().setSubtitle("Mapa");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        utilities = Utilities.getInstance(this);
        prepareMap();
    }

    private void prepareMap(){
        myOpenMapView = (MapView)findViewById(R.id.openmapview);
        myOpenMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setMinZoomLevel(10);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setZoom(150);
        myOpenMapView.setMultiTouchControls(true);
        myOpenMapView.setTilesScaledToDpi(true);
        myMapController.setCenter(new GeoPoint(4.598620,-74.109228));
        ArrayList<OverlayItem> overlays = new ArrayList<>();
        ArrayList<MecanicoObject> mecanicoObjects = new ArrayList<>();
        OverlayItem item = new OverlayItem("Here", "SampleDescription", new GeoPoint(4.598620,  -74.109228));
        item.setMarker(new IconDrawable(this, FontAwesomeIcons.fa_street_view).color(R.color.colorPrimary).sizeDp(50));
        overlays.add(item);
        mecanicoObjects.add(new MecanicoObject("","","","",""));
        MarkerItemizedOverlay markerItemizedOverlay = new MarkerItemizedOverlay(this,overlays,mecanicoObjects);
        myOpenMapView.getOverlays().add(markerItemizedOverlay);
    }

    private OverlayItem getItem(String title,String description,int type,double latitude,double longitude){
        //OverlayItem item = new OverlayItem(title, description, new GeoPoint(4.598620,  -74.109228));
        OverlayItem item = new OverlayItem(title, description, new GeoPoint(latitude,  longitude));
        item.setMarker(new IconDrawable(this, FontAwesomeIcons.fa_map_marker).color(R.color.colorPrimary).sizeDp(40));
        return item;
    }
}