package com.example.beata_macbook.opentricity.UI.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Klasa odpowiadająca za widok pokazujący szczegóły danego miejsca
 */

public class PlaceDetailActivity extends AppCompatActivity {


    // deklaracja wszystkich labeli itd
    TextView detailPlaceNameTextView;
    TextView addressTextView;
    TextView descriptionTextView;
    TextView phoneTextView;
    ImageView detailPlaceImageView;
    //deklarujemy obiekt typu Place z ktorego bedziemy pobierali opodwiednie pola
    Place place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_detail);

        //rozpakowujemy przeslane miejsce
         place = Parcels.unwrap(getIntent().getParcelableExtra("place"));

        //szukamy widokow
        detailPlaceNameTextView = (TextView) findViewById(R.id.detailPlaceNameTextView);
        addressTextView = (TextView) findViewById(R.id.addressTextView);
        descriptionTextView = (TextView)findViewById(R.id.descriptionTextView);
        phoneTextView = (TextView)findViewById(R.id.phoneTextView);
        detailPlaceImageView = (ImageView)findViewById(R.id.detailPlaceImageView);


        //wrzucamy do labeli pola kliknietego miejsca
        detailPlaceNameTextView.setText(place.getName());
        addressTextView.setText(place.getAddress());
        descriptionTextView.setText(place.getDescription());
        phoneTextView.setText(place.getPhoneNumber());
        //ustawiamy zdjecie po URL
        Picasso.with(this).load(place.getImageURL()).resize(400, 300).centerCrop().into(detailPlaceImageView);


    }
}