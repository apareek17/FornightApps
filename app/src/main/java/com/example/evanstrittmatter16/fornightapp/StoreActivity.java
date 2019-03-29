package com.example.evanstrittmatter16.fornightapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    private ListView storeitem;
    private ArrayList<Store> stores = new ArrayList<Store>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        stores = getIntent().getParcelableArrayListExtra("stores");
        wireWidgets();
        populateListView();


    }


    private void populateListView() {

        StoreAdapter adapter = new StoreAdapter(StoreActivity.this, R.layout.storeitemdisplay, stores);
        storeitem.setAdapter(adapter);


    }

    private void wireWidgets() {
        storeitem = findViewById(R.id.listview_storeactivity);
    }






}
