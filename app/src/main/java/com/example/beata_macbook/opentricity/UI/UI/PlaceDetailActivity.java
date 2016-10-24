package com.example.beata_macbook.opentricity.UI.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.beata_macbook.opentricity.UI.UI.CategoriesScreenActivity.choice;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class PlaceDetailActivity extends AppCompatActivity {

    TextView detailPlaceNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_detail);
        String name = getIntent().getStringExtra("name");
        Log.d("PRZESLANO", name);

        detailPlaceNameTextView = (TextView) findViewById(R.id.detailPlaceNameTextView);


        DatabaseReference dbReference  = FirebaseDatabase.getInstance().getReference(choice);
        dbReference.orderByChild("name").equalTo(name).limitToFirst(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("TAG", dataSnapshot.getValue().toString());



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}