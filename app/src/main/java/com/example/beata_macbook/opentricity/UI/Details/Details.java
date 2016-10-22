package com.example.beata_macbook.opentricity.UI.Details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Details extends AppCompatActivity {

    private String mPlace_key = null;
    private DatabaseReference mFirebaseReference;

    private TextView mTitleTextView;
    private TextView mAddressTextView;
    private TextView mElevatorTextView;
    private TextView mSinglePlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mFirebaseReference = FirebaseDatabase.getInstance().getReference().child("Obiekty_edukacyjne");

        mPlace_key = getIntent().getExtras().getString("name");
        mSinglePlace = (TextView)findViewById(R.id.textTitle);

        mFirebaseReference.child(mPlace_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String place_title = (String) dataSnapshot.child("name").getValue();
                String place_address = (String) dataSnapshot.child("address").getValue();
                String place_elevator = (String) dataSnapshot.child("elevator").getValue();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}


