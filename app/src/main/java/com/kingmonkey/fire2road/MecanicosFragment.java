package com.kingmonkey.fire2road;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import org.osmdroid.events.MapAdapter;
import org.osmdroid.events.ScrollEvent;
import org.osmdroid.events.ZoomEvent;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;

public class MecanicosFragment extends Fragment implements LocationListener {
    private MapView myOpenMapView;
    private MapController myMapController;
    private Button centerMeButton;
    private LocationManager locationManager;
    private Activity activityMain;

    public MecanicosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mecanicos, container, false);
        activityMain = getActivity();
        centerMeButton = (Button) view.findViewById(R.id.centerMe);
        myOpenMapView = (MapView) view.findViewById(R.id.openmapview);
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
        ItemizedOverlayWithFocus<OverlayItem> anotherItemizedIconOverlay = new ItemizedOverlayWithFocus<OverlayItem>(getActivity().getApplicationContext(), anotherOverlayItemArray, myOnItemGestureListener);
        myOpenMapView.getOverlays().add(anotherItemizedIconOverlay);
        anotherItemizedIconOverlay.setFocusItemsOnTap(true);

        centerMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).makeSimpleToast("Centrando...");
            }
        });


        return view;
    }



    ItemizedIconOverlay.OnItemGestureListener<OverlayItem> myOnItemGestureListener = new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {

        @Override
        public boolean onItemLongPress(int arg0, OverlayItem arg1) {
            // TODO Auto-generated method stub
            return false;
        }

        @Override
        public boolean onItemSingleTapUp(int index, OverlayItem item) {
            return true;
        }

    };

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
