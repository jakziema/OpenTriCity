package com.example.beata_macbook.opentricity.UI.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;

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
        detailPlaceNameTextView.setText(name);


    }
}