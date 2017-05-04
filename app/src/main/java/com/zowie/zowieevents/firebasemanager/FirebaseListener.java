package com.zowie.zowieevents.firebasemanager;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.zowie.zowieevents.MainActivity;
import com.zowie.zowieevents.items.Item;

import java.util.ArrayList;

/**
 * Created by Didek on 02/05/2017.
 */

public class FirebaseListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private DatabaseReference db;

    public FirebaseListener(DatabaseReference db) {
        this.db = db;
    }

    public void itemsChildEventListener(final ArrayList<Item> items) {
        //Log.w(TAG, "Retrieve items for: " + db);
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevChildKey) {
                items.clear();
                items.add(dataSnapshot.getValue(Item.class));
//                Log.w(TAG, "Added child: " + dataSnapshot.getValue(Item.class).getTitle());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String prevChildKey) {
                items.clear();
                items.add(dataSnapshot.getValue(Item.class));
//                Log.w(TAG, "Changed child: " + dataSnapshot.getValue(Item.class).getTitle());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String prevChildKey) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
//                Log.w(TAG, "Failed to read value.", databaseError.toException());
            }
        });
    }
}