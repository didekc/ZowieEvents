package com.zowie.zowieevents.firebasemanager;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.zowie.zowieevents.MainActivity;
import com.zowie.zowieevents.items.Item;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Didek on 02/05/2017.
 */

public class FirebaseListener {
    private static final String TAG = FirebaseListener.class.getSimpleName();
    private DatabaseReference db;
    private MainActivity mainActivity;

    public FirebaseListener(MainActivity mainActivity, DatabaseReference db) {
        this.mainActivity = mainActivity;
        this.db = db;
        this.db.keepSynced(true);
        Log.w(TAG, db.getDatabase().toString());
    }

    public void addListenerForSingleValueEvent() {
        final ArrayList<Item> items = new ArrayList<>();
//            Log.w(TAG, "Retrieve items for: " + db);
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                items.clear();
                while (iterator.hasNext()) {
                    DataSnapshot data = iterator.next();
                    items.add(data.getValue(Item.class));
                }
                mainActivity.setAdapter(items);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "Database error: " + databaseError.getMessage());
            }
        });
    }
}