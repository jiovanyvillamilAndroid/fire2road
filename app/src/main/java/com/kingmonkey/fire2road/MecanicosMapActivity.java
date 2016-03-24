package com.kingmonkey.fire2road;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.johnpersano.supertoasts.SuperToast;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.orhanobut.logger.Logger;

import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.OverlayItem;

import java.util.ArrayList;
import java.util.List;

public class MecanicosMapActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {


    private static final String llaveGoogleMaps = "AIzaSyBTPCXzyHbtTIaGXl7VOZY_fVZddolpq70";
    private MapView myOpenMapView;
    private MapController myMapController;
    private Utilities utilities;
    private Gson gson;
    private GoogleApiClient mGoogleApiClient = null;
    private Location mLastLocation;
    private Activity currentActivity;
    private Handler handler;
    private Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActivity = this;
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
                utilities.makeSimpleToast("Latitud: " + String.valueOf(mLastLocation.getLatitude()) + " Longitud: " + String.valueOf(mLastLocation.getLongitude()), SuperToast.Duration.LONG);
            }
        });
        gson = new Gson();
        prepareMap();
        prepareGoogleApi();
        locationAccurancy();
    }

    private void locationAccurancy() {
        handler = new Handler();
        r = new Runnable() {
            public void run() {
                mLastLocation = getLocation();
                Logger.e(mLastLocation+"");
                if (mLastLocation != null && mLastLocation.getAccuracy() <= 50) {
                    handler.removeCallbacksAndMessages(null);
                    handler.removeCallbacks(r);
                    utilities.makeSimpleToast("Latitud: " + String.valueOf(mLastLocation.getLatitude()) + " Longitud: " + String.valueOf(mLastLocation.getLongitude()), SuperToast.Duration.LONG);
                    return;
                }
                handler.postDelayed(this, 5000);
            }
        };
        handler.postDelayed(r, 5000);
    }

    private Location getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        return LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
    }

    private void prepareGoogleApi() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            //**************************
            builder.setAlwaysShow(true); //this is the key ingredient
            //**************************

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    final LocationSettingsStates state = result.getLocationSettingsStates();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            // All location settings are satisfied. The client can initialize location
                            // requests here.
                            utilities.makeSimpleToast("Latitud: " + String.valueOf(mLastLocation.getLatitude()) + " Longitud: " + String.valueOf(mLastLocation.getLongitude()), SuperToast.Duration.LONG);
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied. But could be fixed by showing the user
                            // a dialog.
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(currentActivity, 1000);
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way to fix the
                            // settings so we won't show the dialog.
                            break;
                    }
                }
            });
        }
    }

    private void prepareMap() {
        myOpenMapView = (MapView) findViewById(R.id.openmapview);
        myOpenMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setMinZoomLevel(10);
        myMapController = (MapController) myOpenMapView.getController();
        myMapController.setZoom(150);
        myOpenMapView.setMultiTouchControls(true);
        myOpenMapView.setTilesScaledToDpi(true);
        myMapController.setCenter(new GeoPoint(4.598620, -74.109228));
        ArrayList<OverlayItem> overlays = new ArrayList<>();
        MecanicoObject m = new MecanicoObject("1", "4.598620", "-74.109228", "Mecánico de prueba", "http://www.gilera150.com.ar/wp-content/uploads/2008/11/mecanico-motos-gilera.gif");
        OverlayItem item = new OverlayItem("Here", "SampleDescription/" + gson.toJson(m), new GeoPoint(4.598620, -74.109228));
        item.setMarker(new IconDrawable(this, FontAwesomeIcons.fa_street_view).color(R.color.colorPrimary).sizeDp(50));
        overlays.add(item);
        MarkerItemizedOverlay markerItemizedOverlay = new MarkerItemizedOverlay(this, overlays);
        myOpenMapView.getOverlays().add(markerItemizedOverlay);
    }

    @Override
    public void onConnected(Bundle connectionHint) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLastLocation = getLocation();
        if (mLastLocation != null) {
            Logger.e(mLastLocation + "");
            utilities.makeSimpleToast("Latitud: " + String.valueOf(mLastLocation.getLatitude()) + " Longitud: " + String.valueOf(mLastLocation.getLongitude()), SuperToast.Duration.LONG);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    private OverlayItem getItem(String title,String description,int type,double latitude,double longitude){
        //OverlayItem item = new OverlayItem(title, description, new GeoPoint(4.598620,  -74.109228));
        OverlayItem item = new OverlayItem(title, description, new GeoPoint(latitude,  longitude));
        item.setMarker(new IconDrawable(this, FontAwesomeIcons.fa_map_marker).color(R.color.colorPrimary).sizeDp(40));
        return item;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}