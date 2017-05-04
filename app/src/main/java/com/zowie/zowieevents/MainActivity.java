package com.zowie.zowieevents;

/**
 * Created by Didek on 02/05/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zowie.zowieevents.firebasemanager.FirebaseListener;
import com.zowie.zowieevents.items.Item;
import com.zowie.zowieevents.items.ItemAdapter;
import com.zowie.zowieevents.maps.MapsActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    final ArrayList<Item> items = new ArrayList<>();

    DatabaseReference db = FirebaseDatabase.getInstance().getReference("items");
    FirebaseListener monitorsListener = new FirebaseListener(db.child("monitors"));
    FirebaseListener mousepadsListener = new FirebaseListener(db.child("mouses"));
    FirebaseListener mousesListener = new FirebaseListener(db.child("mousepads"));
    ItemAdapter adapter;
    String filename = "items.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Context context = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        items.addAll(Item.getMonitorsItemsFromFile(this, filename));

        adapter = new ItemAdapter(this);
        adapter.getList().addAll(items);

        ListView mListView = (ListView) findViewById(R.id.item_list_view);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item selectedItem = items.get(position);

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

    public void onMonitors(View view) {
        ArrayList<Item> monitors = new ArrayList<>();

        monitorsListener.itemsChildEventListener(monitors);

        items.clear();
        items.addAll(monitors);

        adapter.getList().clear();
        if(items.size() == 0) {
            items.addAll(Item.getMonitorsItemsFromFile(this, filename));
        }

        adapter.getList().addAll(items);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void onMousepads(View view) {
        ArrayList<Item> mousepads = new ArrayList<>();

        mousepadsListener.itemsChildEventListener(mousepads);

        items.clear();
        items.addAll(mousepads);

        adapter.getList().clear();
        if(items.size() == 0) {
            items.addAll(Item.getMousepadsItemsFromFile(this, filename));
        }

        adapter.getList().addAll(items);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void onMouses(View view) {
        ArrayList<Item> mouses = new ArrayList<>();

        mousesListener.itemsChildEventListener(mouses);

        items.clear();
        items.addAll(mouses);

        adapter.getList().clear();
        if(items.size() == 0) {
            items.addAll(Item.getMousesItemsFromFile(this, filename));
        }

        adapter.getList().addAll(items);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void onShowMap(MenuItem mi) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}