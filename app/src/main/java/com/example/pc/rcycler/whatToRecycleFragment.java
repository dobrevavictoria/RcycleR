package com.example.pc.rcycler;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class whatToRecycleFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private FragmentActivity currentFragment;
    private ImageView yellow_circle;
    private ImageView green_circle;
    private ImageView blue_circle;
    private Button qr;
    private Button getCoordinates;
    private Button goToMap;
    private AlertDialog dialog;
    private LocationManager locationMangaer = null;
    private LocationListener locationListener = null;
    private AppCompatButton gmail;
    private Button btnGetLocation = null;
    private TextView et_latitude, et_longitude, tv_message;
    private TextView editLocation = null;
    private ProgressBar pb = null;

    private static final String TAG = "Debug";
    private Boolean flag = false;
    private GoogleApiClient client;
    private OnFragmentInteractionListener mListener;
private ImageView info;

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public whatToRecycleFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_what_to_recycle, container, false);
        initViews(view);
        return view;

    }


    private void initViews(View view) {
        yellow_circle = (ImageView) view.findViewById(R.id.yellowCircle);
        green_circle = (ImageView) view.findViewById(R.id.greenCircle);
        blue_circle = (ImageView) view.findViewById(R.id.blueCircle);
        info=(ImageView)view.findViewById(R.id.info);
        getCoordinates = (Button) view.findViewById(R.id.getCoordinates);
        getCoordinates.setOnClickListener(this);
        qr = (Button) view.findViewById(R.id.qr);
        qr.setOnClickListener(this);
        yellow_circle.setOnClickListener(this);
        green_circle.setOnClickListener(this);
        blue_circle.setOnClickListener(this);
        info.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.yellowCircle:
                Intent intentMap1 = new Intent(getActivity(), MyMap.class);
                startActivity(intentMap1);
                break;

            case R.id.greenCircle:
                Intent intentMap2 = new Intent(getActivity(), MyMap.class);
                startActivity(intentMap2);
                break;
            case R.id.blueCircle:
                Intent intentMap3 = new Intent(getActivity(), MyMap.class);
                startActivity(intentMap3);
                break;
            case R.id.getCoordinates:
                showDialog();
                break;
            case R.id.qr:
//                goToQRScanner();
//                onQRclicked();
                Intent intent = new Intent(getActivity(), QRscanner.class);
                startActivity(intent);
                break;
            case R.id.info:
                Intent intent_info= new Intent(getActivity(), info_list.class);
                startActivity(intent_info);
        }
    }

    private void showDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_get_coordinates, null);
        et_latitude = (TextView) view.findViewById(R.id.et_latitude);
        et_longitude = (TextView) view.findViewById(R.id.et_longitude);
        pb = (ProgressBar) view.findViewById(R.id.progress);
        pb.setVisibility(View.INVISIBLE);
        editLocation = (TextView) view.findViewById(R.id.tv_message);
        locationMangaer = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        client = new GoogleApiClient.Builder(getActivity()).addApi(AppIndex.API).build();

        builder.setView(view);
        builder.setTitle("Вземи текущи координати");
        builder.setPositiveButton("Покажи", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Отмени", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setNeutralButton("Предложи", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Boolean wantToCloseDialog = false;
                if (wantToCloseDialog)
                    dialog.dismiss();
                if (et_latitude.getText().toString().equals("") ||
                        et_longitude.getText().toString().equals(""))

                {
                    et_latitude.setText("Моля първо вземете текущите си координати!");
                } else {
                    String email = "rcyler.app@gmail.com";
                    String subject = "Добавяне на координати към RcycleR картата";
                    String body = et_latitude.getText().toString() + "\n" + et_longitude.getText().toString();
                    String chooserTitle = "Изпрати координатите със:";
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                    emailIntent.putExtra(Intent.EXTRA_TEXT, body);
                    startActivity(Intent.createChooser(emailIntent, chooserTitle));
//emailIntent.putExtra(Intent.EXTRA_HTML_TEXT, body); //If you are using HTML in your body text
                }
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = displayGpsStatus();
                if (flag) {

                    Log.v(TAG, "onClick");

                    editLocation.setText("Моля, променете текущото си местоположение, за да отчетем новите координати"
                            + "\nМомент...");


                    pb.setVisibility(View.VISIBLE);
                    locationListener = new whatToRecycleFragment.MyLocationListener();

                    if (ActivityCompat.checkSelfPermission(getActivity(),
                            android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(getActivity(),
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    locationMangaer.requestLocationUpdates(LocationManager
                            .GPS_PROVIDER, 5000, 1, locationListener);

                } else {
                    alertbox("GPS състояние!", "GPS: изключен");
                }

            }
        });
    }

    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getActivity().getBaseContext()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }

    protected void alertbox(String title, String mymessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("GPS: изключен")
                .setCancelable(false)
                .setTitle("** GPS състояние**")
                .setPositiveButton("Включи GPS",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // finish the current activity
                                // AlertBoxAdvance.this.finish();
                                Intent myIntent = new Intent(
                                        Settings.ACTION_SECURITY_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Отмени",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {

            editLocation.setText("");
            pb.setVisibility(View.INVISIBLE);
            String s_latitude = "Географска ширина: " + loc.getLatitude();
            et_latitude.setText(s_latitude);
            String s_longitude = "Географска дължина: " + loc.getLongitude();
            et_longitude.setText(s_longitude);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider,
                                    int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }


}