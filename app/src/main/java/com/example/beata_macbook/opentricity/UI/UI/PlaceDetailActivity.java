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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;

import static com.example.beata_macbook.opentricity.UI.UI.CategoriesScreenActivity.choice;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class PlaceDetailActivity extends AppCompatActivity {

    ArrayList<Place> mPlaces = new ArrayList<>();

    TextView detailPlaceNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_detail);
        String name = getIntent().getStringExtra("name");
//        Log.d("PRZESLANO", name);

        detailPlaceNameTextView = (TextView) findViewById(R.id.detailPlaceNameTextView);

        mPlaces = Parcels.unwrap(getIntent().getParcelableExtra("places"));
        int startingPosition = Integer.parseInt(getIntent().getStringExtra("position"));

        Log.d("PLACES", mPlaces.get(startingPosition).getName());
        detailPlaceNameTextView.setText(mPlaces.get(startingPosition).getName());

    }
}