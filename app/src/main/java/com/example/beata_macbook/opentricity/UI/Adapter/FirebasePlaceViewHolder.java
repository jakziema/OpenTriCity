package com.example.beata_macbook.opentricity.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.example.beata_macbook.opentricity.UI.UI.CategoriesScreenActivity;
import com.example.beata_macbook.opentricity.UI.UI.PlaceDetailActivity;
import com.example.beata_macbook.opentricity.UI.Utils.LocationHelper;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static android.content.Context.LOCATION_SERVICE;
import static com.example.beata_macbook.opentricity.UI.UI.CategoriesScreenActivity.choice;
import static com.example.beata_macbook.opentricity.UI.UI.CategoriesScreenActivity.wyborNiepelnosprawnosci;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

/**
 * ViewHolder ktory trzyma nasze info o UI i dane komorki
 */

public class FirebasePlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    View mView;
    Context mContext;
    ImageView placeImageView;
    TextView nameTextView;
    TextView addressTextView;
    TextView descriptionTextView;

    Query mFirebaseReference2;




    public FirebasePlaceViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    //ustawiamy cale UI i dane miejsca
    public void bindPlace(Place place) {
        placeImageView = (ImageView) mView.findViewById(R.id.placeImageView);
        nameTextView = (TextView) mView.findViewById(R.id.placeNameTextView);
        addressTextView = (TextView) mView.findViewById(R.id.addressTextView);
        descriptionTextView = (TextView) mView.findViewById(R.id.descriptionTextView);
        nameTextView.setText(place.getName());
        addressTextView.setText(place.getAddress());
        descriptionTextView.setText(place.getDescription());
        Picasso.with(mContext).load(place.getImageURL()).resize(200, 200).centerCrop().into(placeImageView);

    }

    public void onClick(View view) {
        //tablica do ktorej wrzucimy sciagniete z firebase miejsca
        final LinkedHashMap<String, Place> places = new LinkedHashMap<>();

        //choice wybor uzytkownika
        if (wyborNiepelnosprawnosci.isEmpty()) {
            mFirebaseReference2 = FirebaseDatabase.getInstance().getReference(choice);
        } else {
            mFirebaseReference2 = FirebaseDatabase.getInstance().getReference(choice)
                    .orderByChild(wyborNiepelnosprawnosci).equalTo("true");

        }

        mFirebaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //wrzucamy do tablicy miejsca
                    places.put(snapshot.getKey(), snapshot.getValue(Place.class));
                    Log.d("Places", snapshot.toString());
                }
                //pobieramy pozycje kliknietej komorki
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, PlaceDetailActivity.class);
                //przesylamy spakowane miejsce
                Place place = (new ArrayList<Place>(places.values())).get(itemPosition);
                String id = (new ArrayList<String>(places.keySet())).get(itemPosition);
                intent.putExtra("place", Parcels.wrap(place));

                intent.putExtra("id", Parcels.wrap(id));
                intent.putExtra("category", Parcels.wrap(choice));

                Log.v("itemPosition", String.valueOf(place));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }








}
