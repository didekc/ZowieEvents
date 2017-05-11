package com.zowie.zowieevents;

/**
 * Created by Didek on 02/05/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.zowie.zowieevents.firebasemanager.FirebaseListener;
import com.zowie.zowieevents.items.Item;
import com.zowie.zowieevents.items.ItemAdapter;
import com.zowie.zowieevents.items.ItemDetailActivity;
import com.zowie.zowieevents.maps.MapsActivity;
import com.zowie.zowieevents.posters.PosterActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    final String filename = "items.json";
    final ArrayList<Item> items = new ArrayList<>();
    private ItemAdapter adapter;
    private Context context;
    DatabaseReference db;
    FirebaseListener monitorsListener;
    FirebaseListener mousepadsListener;
    FirebaseListener mousesListener;
    Button monitorsB;
    Button mousepadsB;
    Button mousesB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        Log.w(TAG, "Test");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        monitorsB = (Button) findViewById(R.id.button0);
        mousepadsB = (Button) findViewById(R.id.button1);
        mousesB = (Button) findViewById(R.id.button2);
        monitorsB.setBackgroundColor(Color.WHITE);
        mousepadsB.setBackgroundColor(Color.GRAY);
        mousesB.setBackgroundColor(Color.GRAY);

        context = this;
        adapter = new ItemAdapter(context);
        connectToDatabase();

        monitorsListener.addListenerForSingleValueEvent();

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
        monitorsB.setBackgroundColor(Color.WHITE);
        mousepadsB.setBackgroundColor(Color.GRAY);
        mousesB.setBackgroundColor(Color.GRAY);
        monitorsListener.addListenerForSingleValueEvent();
    }

    public void onMousepads(View view) {
        monitorsB.setBackgroundColor(Color.GRAY);
        mousepadsB.setBackgroundColor(Color.WHITE);
        mousesB.setBackgroundColor(Color.GRAY);
        mousepadsListener.addListenerForSingleValueEvent();
    }

    public void onMouses(View view) {
        monitorsB.setBackgroundColor(Color.GRAY);
        mousepadsB.setBackgroundColor(Color.GRAY);
        mousesB.setBackgroundColor(Color.WHITE);
        mousesListener.addListenerForSingleValueEvent();
    }

    public void setAdapter(ArrayList<Item> it) {
        items.clear();
        items.addAll(it);
        adapter.getList().clear();
        adapter.getList().addAll(items);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void connectToDatabase() {
        db = FirebaseDatabase.getInstance().getReference("items");
        db.keepSynced(true);
        monitorsListener = new FirebaseListener(MainActivity.this, db.child("monitors"));
        mousepadsListener = new FirebaseListener(MainActivity.this, db.child("mousepads"));
        mousesListener = new FirebaseListener(MainActivity.this, db.child("mouses"));
    }

    public void onShowMap(MenuItem mi) {
        Intent intent = new Intent(context, MapsActivity.class);
        startActivity(intent);
    }

    public void onShowPoster(MenuItem mi) {
        Intent intent = new Intent(context, PosterActivity.class);
        startActivity(intent);
    }
}