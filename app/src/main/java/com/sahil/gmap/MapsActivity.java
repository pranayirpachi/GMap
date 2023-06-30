package com.sahil.gmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.sahil.gmap.databinding.ActivityMapsBinding;

import java.io.IOException;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback{

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);



    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(26.2389, 73.0243);

        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("jodhpur");

        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));

        //CIRCLE

        mMap.addCircle(new CircleOptions().center(latLng)
                .radius(1000).fillColor(Color.YELLOW)
                .strokeColor(Color.DKGRAY));

        // Polygon

        mMap.addPolygon(new PolygonOptions().add(new LatLng(26.2389, 73.0243),
                new LatLng(26.2389, 73.0243),
                new LatLng(26.2389, 73.0243),
                new LatLng(26.2389, 73.0243),
                new LatLng(26.2389, 73.0243)).fillColor(Color.YELLOW).strokeColor(Color.BLUE));

        // mMap.addGroundOverlay(new GroundOverlayOptions().position(latLng,1000f,1000f).image(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background)));
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng).title("Clicked here"));
                Geocoder geocoder = new Geocoder(MapsActivity.this);


                try {
                    ArrayList<Address> arrAdr = (ArrayList<Address>) geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    Log.d("Adr", arrAdr.get(0).getAddressLine(0));

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        });
    }


}