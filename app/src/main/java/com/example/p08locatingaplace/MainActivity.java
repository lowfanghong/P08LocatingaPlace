package com.example.p08locatingaplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {
    Button btnN, btnC, btnE;
    private GoogleMap map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);
        btnN = findViewById(R.id.btnN);
        btnC = findViewById(R.id.btnC);
        btnE = findViewById(R.id.btnE);

        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LatLng poi_North = new LatLng(1.461708, 103.813500);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_North,
                        15));

            }
        });
         btnC.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 LatLng poi_Cen = new LatLng(1.300542, 103.841226);
                 map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Cen,
                         15));

             }
         });

         btnE.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 LatLng poi_East = new LatLng(1.350087, 103.934452);
                 map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_East,
                         15));
             }
         });
        mapFragment.getMapAsync(new OnMapReadyCallback(){
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;
                LatLng poi_North = new LatLng(1.461708, 103.813500);
                Marker NHQ = map.addMarker(new
                        MarkerOptions()
                        .position(poi_North)
                        .title("North - HQ")
                        .snippet("Block 333, Admiralty Ave 3, 765654 Operating hours: 10am-5pm\n" +
                                "Tel:65433456\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));

                LatLng poi_Cen = new LatLng(1.300542, 103.841226);
                Marker CHQ = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Cen)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542 \n" +
                                "Operating hours: 11am-8pm\n" +
                                "Tel:67788652\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                LatLng poi_East = new LatLng(1.350087, 103.934452);
                Marker EHQ = map.addMarker(new
                        MarkerOptions()
                        .position(poi_East)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788 \n" +
                                "Operating hours: 9am-5pm\n" +
                                "Tel:66776677\"\n")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

                LatLng poi_SG = new LatLng(1.3521, 103.8198);
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_SG,
                        13));

                UiSettings ui = map.getUiSettings();

                ui.setCompassEnabled(true);

                UiSettings ui1 = map.getUiSettings();

                ui1.setZoomControlsEnabled(true);


                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }



            }
        });


    }
}