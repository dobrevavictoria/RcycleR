package com.example.pc.rcycler;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMap extends AppCompatActivity
        implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    public void onProviderEnabled(String provider) {
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    public void onProviderDisabled(String provider) {
    }

    GoogleMap mGoogleMap;
    SupportMapFragment mapFrag;
    LocationRequest mLocationRequest;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_map);
        //getSupportActionBar().setTitle("RcycleR карта");
        mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFrag.getMapAsync(this);

    }

    @Override
    public void onPause() {
        super.onPause();

        //stop location updates when Activity is no longer active
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mGoogleMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        } else {
            buildGoogleApiClient();
            mGoogleMap.setMyLocationEnabled(true);
        }

        //MARKERS FOR MY CONTAINERS -STARA ZAGORA
        yDetelina = mGoogleMap.addMarker(new MarkerOptions()
                .position(yellowDetelina)
                .title("1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        yDetelina.setTag(0);

        gDetelina = mGoogleMap.addMarker(new MarkerOptions()
                .position(greenDetelina)
                .title("2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        gDetelina.setTag(0);

        yHome = mGoogleMap.addMarker(new MarkerOptions()
                .position(yellowHome)
                .title("3")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        yHome.setTag(0);

        gHome = mGoogleMap.addMarker(new MarkerOptions()
                .position(greenHome)
                .title("4")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        gHome.setTag(0);

        yHristov = mGoogleMap.addMarker(new MarkerOptions()
                .position(yellowHristov)
                .title("5")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        yHristov.setTag(0);

        gHristov = mGoogleMap.addMarker(new MarkerOptions()
                .position(greenHristov)
                .title("6")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        gHristov.setTag(0);
        yBiblioteka = mGoogleMap.addMarker(new MarkerOptions()
                .position(yellowBiblioteka)
                .title("7")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        yBiblioteka.setTag(0);
        gBiblioteka = mGoogleMap.addMarker(new MarkerOptions()
                .position(greenBiblioteka)
                .title("8")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        gBiblioteka.setTag(0);
        yEzikova = mGoogleMap.addMarker(new MarkerOptions()
                .position(yellowEzikova)
                .title("9")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        yEzikova.setTag(0);
        gEzikova = mGoogleMap.addMarker(new MarkerOptions()
                .position(greenEzikova)
                .title("10")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        gEzikova.setTag(0);
        yHOF = mGoogleMap.addMarker(new MarkerOptions()
                .position(yellowHOF)
                .title("11")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        yHOF.setTag(0);
        gHOF = mGoogleMap.addMarker(new MarkerOptions()
                .position(greenHOF)
                .title("12")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        gHOF.setTag(0);
        yOpera = mGoogleMap.addMarker(new MarkerOptions()
                .position(yellowOpera)
                .title("13")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        yOpera.setTag(0);
        gOpera = mGoogleMap.addMarker(new MarkerOptions()
                .position(greenOpera)
                .title("14")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        gOpera.setTag(0);
        yStroitelen = mGoogleMap.addMarker(new MarkerOptions()
                .position(yellowStroitelen)
                .title("15")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        yStroitelen.setTag(0);
        gToto = mGoogleMap.addMarker(new MarkerOptions()
                .position(greenToto)
                .title("16")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        gToto.setTag(0);
        gPiperka = mGoogleMap.addMarker(new MarkerOptions()
                .position(greenPiperka)
                .title("18")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        gPiperka.setTag(0);

    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Текущо местоположение");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        mCurrLocationMarker = mGoogleMap.addMarker(markerOptions);

        //move map camera
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        //optionally, stop location updates if only current location is needed
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Разрешение за използване на текущо местоположение")
                        .setMessage("Приложението се нуждае от разрешение за използване на местоположението. Моля приемете, за да имате достъп до всички функции.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(MyMap.this,
                                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                                        MY_PERMISSIONS_REQUEST_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mGoogleMap.setMyLocationEnabled(true);
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Разрешението е отхвърлено", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    //declaring markers for recycling containers
    private static final LatLng yellowDetelina = new LatLng(42.433312, 25.633603);
    private static final LatLng greenDetelina = new LatLng(42.433215, 25.633178);
    private static final LatLng yellowHristov = new LatLng(42.433392, 25.633978);
    private static final LatLng greenHristov = new LatLng(42.43338, 25.63389);
    private static final LatLng yellowHome = new LatLng(42.433495, 25.636486);
    private static final LatLng greenHome = new LatLng(42.433549, 25.636728);
    private static final LatLng yellowBiblioteka= new LatLng(42.426564, 25.628267);
    private static final LatLng greenBiblioteka = new LatLng(42.426564, 25.628267);
    //    not accurate greenBiblioteka
    private static final LatLng yellowEzikova = new LatLng(42.428042, 25.630971);
    private static final LatLng greenEzikova = new LatLng(42.427424, 25.630236);
    private static final LatLng yellowHOF= new LatLng(42.42703, 25.623976); //not accurate
    private static final LatLng greenHOF=new LatLng(42.427036, 25.623968);
    private static final LatLng yellowOpera=new LatLng(42.427711, 25.625532);
    private static final LatLng greenOpera = new LatLng(42.427284, 25.627253);
    private static final LatLng  yellowStroitelen= new LatLng(42.426104, 25.62932);
    private static final LatLng greenToto= new LatLng(42.427895, 25.633054);
    private static final LatLng greenPiperka = new LatLng(42.42826, 25.63468);

    private Marker yDetelina;
    private Marker gDetelina;
    private Marker yHristov;
    private Marker gHristov;
    private Marker yHome;
    private Marker gHome;
    private Marker yBiblioteka;
    private Marker gBiblioteka;
    private Marker yEzikova;
    private Marker gEzikova;
    private Marker yHOF, gHOF, yOpera, gOpera, yStroitelen, gToto, gPiperka;

//da iziskvam razreshenie za izpolzvane na GPS i Internet

}