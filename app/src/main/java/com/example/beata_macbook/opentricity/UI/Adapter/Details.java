package com.example.beata_macbook.opentricity.UI.Adapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beata_macbook.opentricity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Details extends AppCompatActivity {

    private String mPlace_key = null;
    private DatabaseReference dbReference;

    private TextView mNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        dbReference = FirebaseDatabase.getInstance().getReference().child("Obiekty_edukacyjne");

        mPlace_key = getIntent().getExtras().getString("Obiekty_edukacyjne_id");

        mNameTextView = (TextView)findViewById(R.id.nameTextView);


        dbReference.child(mPlace_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String place_name = (String)dataSnapshot.child("name").getValue();

                mNameTextView.setText(place_name);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
