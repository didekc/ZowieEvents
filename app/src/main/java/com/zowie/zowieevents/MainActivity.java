package com.zowie.zowieevents;

/**
 * Created by Didek on 02/05/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;
        String filename = "items.json";

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseListener firebaseListener = new FirebaseListener();

        ArrayList<Item> monitors = firebaseListener.retrieveItems(FirebaseDatabase.getInstance().getReference("items/monitors"));
        ArrayList<Item> mousepads = firebaseListener.retrieveItems(FirebaseDatabase.getInstance().getReference("items/mousepads"));
        ArrayList<Item> mouses = firebaseListener.retrieveItems(FirebaseDatabase.getInstance().getReference("items/mouses"));

        Log.w(TAG, "List size: " + monitors.size());
        Log.w(TAG, "List size: " + mousepads.size());
        Log.w(TAG, "List size: " + mouses.size());

        ArrayList<Item> items = new ArrayList<>();
        items.addAll(monitors);
        items.addAll(mousepads);
        items.addAll(mouses);

        Log.w(TAG, "List size: " + items.size());

        if (items.size() == 0) {
            items = Item.getItemsFromFile(this, filename);
        }

        final ArrayList<Item> itemList = items;
        ItemAdapter adapter = new ItemAdapter(this, itemList);

        ListView mListView = (ListView) findViewById(R.id.item_list_view);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item selectedItem = itemList.get(position);

                Intent detailIntent = new Intent(context, ItemDetailActivity.class);
                detailIntent.putExtra("title", selectedItem.getTitle());
                detailIntent.putExtra("url", selectedItem.getUrl());

                startActivity(detailIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onShowMap(MenuItem mi) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}