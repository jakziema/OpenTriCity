package com.example.beata_macbook.opentricity.UI.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Adapter.FirebasePlaceViewHolder;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

/**
 * Klasa odpowiadajca za wyswietlanie listy miejsc po przejsciu z kategorii
 */

public class PlacesListActivity extends AppCompatActivity {


    private DatabaseReference mFirebaseReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    Query mFirebaseReference2;

    RecyclerView mRecyclerView ;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_list);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        //ustawiamy zapytanie do bazy Firebase w zaleznosci od tego co wybral uzytkownik, pobieramy intentem z ekranu
        // CategoriesScreenActivity
        String wyborKategorii = getIntent().getStringExtra("chosenCategory");
        String wyborNiepelnosprawnosci = getIntent().getStringExtra("toggleButtonChoice");
        Log.v("WYBOR_KATEGORII",wyborKategorii );
        Log.v("WYBOR_NIEPELNO",wyborNiepelnosprawnosci);
         mFirebaseReference2 = FirebaseDatabase.getInstance().getReference(wyborKategorii).orderByChild(wyborNiepelnosprawnosci).equalTo("true");


        mFirebaseReference = FirebaseDatabase.getInstance().getReference(wyborKategorii);
        Log.d("Firebase", mFirebaseReference.toString());
        //ustawiamy Adapter Firebase
        setupFirebaseAdapter();
    }

    private void setupFirebaseAdapter() {
        //w konstruktorze ustawiamy model klasy - Place, jak maja wygladac komorki - place_list_item,
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Place, FirebasePlaceViewHolder>
                (Place.class, R.layout.place_list_item, FirebasePlaceViewHolder.class,
                        mFirebaseReference2) {

            @Override
            //zapelnij viewHolder danymi
            protected void populateViewHolder(final FirebasePlaceViewHolder viewHolder, Place model, int position) {

                //ustawiamy całe UI
                viewHolder.bindPlace(model);

            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }
}
