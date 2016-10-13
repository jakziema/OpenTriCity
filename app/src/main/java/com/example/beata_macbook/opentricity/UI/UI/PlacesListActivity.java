package com.example.beata_macbook.opentricity.UI.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Adapter.FirebasePlaceViewHolder;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class PlacesListActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    RecyclerView mRecyclerView ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mFirebaseReference = FirebaseDatabase.getInstance().getReference("Lokale_gastronomiczne");
        setupFirebaseAdapter();
    }

    private void setupFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Place, FirebasePlaceViewHolder>
                (Place.class, R.layout.place_list_item, FirebasePlaceViewHolder.class,
                        mFirebaseReference) {

            @Override
            protected void populateViewHolder(FirebasePlaceViewHolder viewHolder,
                                              Place model, int position) {
                viewHolder.bindPlace(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
}
