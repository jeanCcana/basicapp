package com.example.myapplication.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Marker> markersList = new ArrayList<>();
    private LinkedHashMap<String, LatLng> markerHashMap = new LinkedHashMap<>();

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            String stringData = getArguments().getString("data");
            Gson gson = new Gson();
            Type entityType = new TypeToken<LinkedHashMap<String, LatLng>>() {
            }.getType();
            markerHashMap = gson.fromJson(stringData, entityType);
        }

        MapView mapView = view.findViewById(R.id.map);
        mapView.onCreate(null);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getContext());
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        addMarkers();
    }

    private void addMarkers() {
        markersList.clear();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for (LinkedHashMap.Entry<String, LatLng> entry : markerHashMap.entrySet()) {
            Marker marker = mMap.addMarker(new MarkerOptions().position(entry.getValue()).title(entry.getKey()));
            builder.include(marker.getPosition());
            markersList.add(marker);
        }

        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.20); // offset from edges of the map 20% of screen
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding);
        mMap.moveCamera(cu);
    }

    public void deleteLocations() {
        int[] positions = {10, 5, 2, 1, 7};

        for (int index : positions) {
            markersList.get(index - 1).remove();
        }
    }

    public void restoreLocations() {
        mMap.clear();
        addMarkers();
    }


}
