package com.kingmonkey.fire2road;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.johnpersano.supertoasts.SuperToast;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

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
        ArrayList<OverlayItem> anotherOverlayItemArray;
        anotherOverlayItemArray = new ArrayList<>();
        GeoPoint casa = new GeoPoint(4.598620, -74.109228);
        myMapController.setCenter(casa);
        anotherOverlayItemArray.add(new OverlayItem("Mi casa", "Descripcion de mi casita linda, la mas bonita de todas, y probamos la descripción.", casa));
        ItemizedOverlayWithFocus<OverlayItem> anotherItemizedIconOverlay = new ItemizedOverlayWithFocus<OverlayItem>(this, anotherOverlayItemArray, new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
            @Override
            public boolean onItemSingleTapUp(int index, OverlayItem item) {
                utilities.makeSimpleToast(index+""+item.getSnippet(), SuperToast.Duration.SHORT);
                return true;
            }

            @Override
            public boolean onItemLongPress(int index, OverlayItem item) {
                return false;
            }
        });
        myOpenMapView.getOverlays().add(anotherItemizedIconOverlay);
        anotherItemizedIconOverlay.setFocusItemsOnTap(true);
    }
}
