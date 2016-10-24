package com.example.beata_macbook.opentricity.UI.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.example.beata_macbook.opentricity.UI.UI.CategoriesScreenActivity;
import com.example.beata_macbook.opentricity.UI.UI.PlaceDetailActivity;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.beata_macbook.opentricity.UI.UI.CategoriesScreenActivity.choice;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class FirebasePlaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    ImageView placeImageView;
    TextView nameTextView;
    TextView addressTextView;
    TextView descriptionTextView;

    public FirebasePlaceViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindPlace(Place place) {
         placeImageView = (ImageView) mView.findViewById(R.id.placeImageView);
         nameTextView = (TextView) mView.findViewById(R.id.placeNameTextView);
        addressTextView = (TextView) mView.findViewById(R.id.addressTextView);
         descriptionTextView = (TextView) mView.findViewById(R.id.descriptionTextView);

        nameTextView.setText(place.getName());
        addressTextView.setText(place.getAddress());
        descriptionTextView.setText(place.getDescription());
//        Picasso.with(mContext).load(place.getImageURL()).resize(MAX_WIDTH, MAX_HEIGHT).centerCrop().into(placeImageView);

    }

    public void onClick(View view) {

        Intent intent = new Intent(mContext, PlaceDetailActivity.class);
        intent.putExtra("name", nameTextView.getText().toString());
        mContext.startActivity(intent);
    }
}
