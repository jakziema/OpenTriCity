package com.example.beata_macbook.opentricity.UI.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;


import java.util.ArrayList;


import static com.example.beata_macbook.opentricity.UI.UI.CategoriesScreenActivity.choice;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class PlaceDetailActivity extends AppCompatActivity {

    ArrayList<Place> mPlaces = new ArrayList<>();

    TextView detailPlaceNameTextView;
    TextView addressTextView;
    TextView descriptionTextView;
    TextView phoneTextView;
    ImageView detailPlaceImageView;

    Place place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_detail);

        mPlaces = Parcels.unwrap(getIntent().getParcelableExtra("places"));
        int position = Integer.parseInt(getIntent().getStringExtra("position"));

        detailPlaceNameTextView = (TextView) findViewById(R.id.detailPlaceNameTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);
        phoneTextView = (TextView)findViewById(R.id.phoneTextView);
        detailPlaceImageView = (ImageView)findViewById(R.id.detailPlaceImageView);

        place = mPlaces.get(position);

        detailPlaceNameTextView.setText(place.getName());
        addressTextView.setText(place.getAddress());
        descriptionTextView.setText(place.getDescription());
        phoneTextView.setText(place.getPhoneNumber());

        Picasso.with(this).load(place.getImageURL()).resize(400, 300).centerCrop().into(detailPlaceImageView);


    }
}