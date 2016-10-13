package com.example.beata_macbook.opentricity.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class FirebasePlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;
    View mView;
    Context mContext;

    public FirebasePlaceViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPlace(Place place) {
        ImageView placeImageView = (ImageView) mView.findViewById(R.id.placeImageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.placeNameTextView);
        TextView addressTextView = (TextView) mView.findViewById(R.id.addressTextView);

        nameTextView.setText(place.getName());
        addressTextView.setText(place.getAddress());

    }

    public void onClick(View view) {
        final ArrayList<Place> places = new ArrayList<>();
        DatabaseReference dbReference = FirebaseDatabase.getInstance().getReference("Lokale_gastronomiczne");

        dbReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    places.add(snapshot.getValue(Place.class));
                }

                int itemPosition = getLayoutPosition();

//                Intent intent = new Intent(mContext, RestaurantDetailActivity.class);
//                intent.putExtra("position", itemPosition + "");
//                intent.putExtra("restaurants", Parcels.wrap(restaurants));
//
//                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
