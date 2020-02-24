package com.example.myapplication.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.Adapters.RecyclerAdapter;
import com.example.myapplication.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class RecyclerFragment extends Fragment {

    private List<String> markerList = new ArrayList<>();

    public RecyclerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        if (getArguments() != null) {
            String stringData = getArguments().getString("data");
            Gson gson = new Gson();
            Type entityType = new TypeToken<LinkedHashMap<String, LatLng>>() {
            }.getType();
            LinkedHashMap<String, LatLng> markerHashMap = gson.fromJson(stringData, entityType);

            for (LinkedHashMap.Entry<String, LatLng> entry : markerHashMap.entrySet()) {
                markerList.add(entry.getKey());
            }
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerAdapter adapter = new RecyclerAdapter(getContext(), markerList);
        recyclerView.setAdapter(adapter);
    }
}
