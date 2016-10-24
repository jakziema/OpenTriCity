package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.View;
import android.widget.Toast;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Adapter.Details;
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
        mFirebaseReference = FirebaseDatabase.getInstance().getReference(getIntent().getStringExtra("chosenCategory").toString());
        setupFirebaseAdapter();
    }

    private void setupFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Place, FirebasePlaceViewHolder>
                (Place.class, R.layout.place_list_item, FirebasePlaceViewHolder.class,
                        mFirebaseReference) {

            @Override
            protected void populateViewHolder(final FirebasePlaceViewHolder viewHolder, Place model, int position) {

                final String place_key = getRef(position).getKey();

                viewHolder.bindPlace(model);
                viewHolder.mView.setOnClickListener((){
                    Intent detailsIntent = new Intent(PlacesListActivity.this, Details.class);
                    detailsIntent.putExtra("place_id", place_key);
                    startActivity(detailsIntent);
                });

            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
}
