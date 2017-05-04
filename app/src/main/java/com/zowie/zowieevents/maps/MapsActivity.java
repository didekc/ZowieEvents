package com.zowie.zowieevents.maps;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.zowie.zowieevents.ItemDetailActivity;
import com.zowie.zowieevents.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final String TAG = ItemDetailActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        TypedValue outValue = new TypedValue();
        getResources().getValue(R.dimen.firstCord, outValue, true);
        float firstCord = outValue.getFloat();
        getResources().getValue(R.dimen.secondCord, outValue, true);
        float secondCord = outValue.getFloat();

        LatLng event = new LatLng(firstCord, secondCord);
        googleMap.addMarker(new MarkerOptions().position(event).title("ZovieEvent"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(event));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(event, 17.0f));
    }
}