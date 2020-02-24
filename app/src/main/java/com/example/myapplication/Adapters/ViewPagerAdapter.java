package com.example.myapplication.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.Fragments.ButtonsFragment;
import com.example.myapplication.Fragments.MapFragment;
import com.example.myapplication.Fragments.RecyclerFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;

import java.util.LinkedHashMap;


public class ViewPagerAdapter extends FragmentStateAdapter {

    private MapFragment mapFragment;
    private LinkedHashMap<String, LatLng> markerHashMap = new LinkedHashMap<>();

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        putData();
        Gson gson = new Gson();
        String data = gson.toJson(markerHashMap);
        Bundle bundle = new Bundle();
        bundle.putString("data", data);

        switch (position) {
            case (0):
                mapFragment = new MapFragment();
                mapFragment.setArguments(bundle);
                return mapFragment;
            case (1):
                Fragment recyclerFragment = new RecyclerFragment();
                recyclerFragment.setArguments(bundle);
                return recyclerFragment;
            default:
                return new ButtonsFragment();
        }
    }

    private void putData() {
        markerHashMap.put("Parque de la Amistad", new LatLng(-12.131740, -76.981732));
        markerHashMap.put("Larcomar", new LatLng(-12.131916, -77.030514));
        markerHashMap.put("Universidad De San Martin De Porres- Ingenieria", new LatLng(-12.072727, -76.942225));
        markerHashMap.put("Aeropuerto Internacional Jorge Chávez", new LatLng(-12.023769, -77.112047));
        markerHashMap.put("Parque de las leyendas", new LatLng(-12.069255, -77.086782));
        markerHashMap.put("Centro Civico", new LatLng(-12.055955, -77.037075));
        markerHashMap.put("Barranco Puente Suspiros", new LatLng(-12.149030, -77.022544));
        markerHashMap.put("Universidad Privada Del Norte", new LatLng(-11.934190, -77.058574));
        markerHashMap.put("Skatepark Miraflores", new LatLng(-12.117264, -77.046229));
        markerHashMap.put("Jockey Plaza", new LatLng(-12.085051, -76.977354));
    }

    public MapFragment getMapFragment() {
        return mapFragment;
    }
}
