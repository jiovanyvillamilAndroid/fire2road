package com.kingmonkey.fire2road.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;

import com.github.johnpersano.supertoasts.SuperToast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.kingmonkey.fire2road.R;
import com.kingmonkey.fire2road.Utilities.Utilities;
import com.yayandroid.locationmanager.LocationBaseActivity;
import com.yayandroid.locationmanager.LocationConfiguration;
import com.yayandroid.locationmanager.LocationManager;
import com.yayandroid.locationmanager.constants.FailType;
import com.yayandroid.locationmanager.constants.LogType;
import com.yayandroid.locationmanager.constants.ProviderType;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class MapsActivity extends LocationBaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Utilities utilities;
    static final LatLng PERTH = new LatLng(4.5978837, -74.1075547);
    SweetAlertDialog progressDialog;

    @Override
    public LocationConfiguration getLocationConfiguration() {
        return new LocationConfiguration()
                .keepTracking(true)
                .askForGooglePlayServices(true)
                .setMinAccuracy(200.0f)
                .setWaitPeriod(ProviderType.GOOGLE_PLAY_SERVICES, 5 * 1000)
                .setWaitPeriod(ProviderType.GPS, 10 * 1000)
                .setWaitPeriod(ProviderType.NETWORK, 5 * 1000)
                .setGPSMessage("¿Quieres activar el GPS?")
                .setRationalMessage("Activar!");
    }

    @Override
    public void onLocationFailed(int failType) {
        switch (failType) {
            case FailType.PERMISSION_DENIED: {
                utilities.showSnackBar("No se puede obtener la ubicación, el usuario no otorgó permisos", findViewById(R.id.map), Snackbar.LENGTH_LONG);
                break;
            }
            case FailType.GP_SERVICES_NOT_AVAILABLE:
            case FailType.GP_SERVICES_CONNECTION_FAIL: {
                utilities.showSnackBar("No se puede obtener la ubicación, debido a que Google Play Services no esta disponible", findViewById(R.id.map), Snackbar.LENGTH_LONG);
                break;
            }
            case FailType.NETWORK_NOT_AVAILABLE: {
                utilities.showSnackBar("No se puede obtener la ubicación, debido a que la red no es accesible", findViewById(R.id.map), Snackbar.LENGTH_LONG);
                break;
            }
            case FailType.TIMEOUT: {
                utilities.showSnackBar("No se puede obtener la ubicación, tiempo de espera excedido", findViewById(R.id.map), Snackbar.LENGTH_LONG);
                break;
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        dismissProgress();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15));
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getLocationManager().isWaitingForLocation()
                && !getLocationManager().isAnyDialogShowing()) {
            displayProgress();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        dismissProgress();
    }

    private void displayProgress() {
        if (progressDialog == null) {
            progressDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
            progressDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
            progressDialog.setTitleText("Obteniendo ubicación...");
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void dismissProgress() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Iconify.with(new FontAwesomeModule());
        setContentView(R.layout.activity_maps);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Mecánicos");
        getSupportActionBar().setSubtitle("Mapa");
        getSupportActionBar().setIcon(new IconDrawable(this, FontAwesomeIcons.fa_share).colorRes(R.color.white).actionBarSize());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        utilities = Utilities.getInstance(this);
        utilities.showSnackBar("Recuerda activar la ubicación GPS", findViewById(R.id.map), Snackbar.LENGTH_LONG);
        LocationManager.setLogType(LogType.GENERAL);
        getLocation();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
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
        mMap.setMyLocationEnabled(true);

        //una marca de prueba
        final Marker perth = mMap.addMarker(new MarkerOptions()
                .position(PERTH)
                .title("Mecánico Octava")
                .snippet("Mecánico especializado en motociclistas desde baja hasta alta cilindrada")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mechanic_marker))
                .draggable(false));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                utilities.makeSimpleToast(marker.getId()+" "+marker.getPosition()+" "+marker.getTitle(), SuperToast.Duration.LONG);
                return false;
            }
        });
    }

}