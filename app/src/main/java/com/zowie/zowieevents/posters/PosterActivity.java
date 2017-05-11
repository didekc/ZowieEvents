package com.zowie.zowieevents.posters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.zowie.zowieevents.R;

public class PosterActivity extends FragmentActivity {
    private static final String TAG = PosterActivity.class.getSimpleName();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        connectionState();
        setContentView(R.layout.activity_posters);
    }

    public void connectionState() {
        DatabaseReference connectedRef = FirebaseDatabase.getInstance().getReference(".info/connected");
        connectedRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                boolean conn = snapshot.getValue(Boolean.class);
                if (conn) {
                    StorageReference poster = FirebaseStorage.getInstance().getReference("posters").child("zowie_pwr.png");
                    ImageView imageView = (ImageView) findViewById(R.id.posterImageView);
                    Glide.with(context)
                            .using(new FirebaseImageLoader())
                            .load(poster)
                            .into(imageView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
