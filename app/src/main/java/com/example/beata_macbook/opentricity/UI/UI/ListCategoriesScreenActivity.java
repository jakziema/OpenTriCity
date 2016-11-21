package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.beata_macbook.opentricity.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beata-MacBook on 21.11.2016.
 */

public class ListCategoriesScreenActivity extends AppCompatActivity {

    Spinner categorySpinner;
    Spinner disabilitySpinner;
    Button searchButton;

    public static String choice = "";
    public static String wyborNiepelnosprawnosci = "";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorylist_screen);

        categorySpinner = (Spinner)findViewById(R.id.categorySpinner);
        disabilitySpinner = (Spinner)findViewById(R.id.disabilitySpinner);
        searchButton = (Button)findViewById(R.id.searchButton);

        categorySpinner.setPrompt("Wybierz rodzaj lokalu");
        disabilitySpinner.setPrompt("Wybierz rodzaj niepełnosprawności");


        final List<String> categories = new ArrayList<String>();
        final List<String> disabilities = new ArrayList<String>();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefCategories = database.getReference("Kategorie");

        myRefCategories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    for (DataSnapshot ds2 : ds.getChildren()) {
                        Log.v("Categories", ds2.getValue().toString());
                        categories.add(ds2.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference myRefDisabilities = database.getReference("Niepelnosprawnosci");
        myRefDisabilities.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    for (DataSnapshot ds2 : ds.getChildren()) {
                        Log.v("Categories", ds2.getValue().toString());
                        disabilities.add(ds2.getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        categorySpinner.setAdapter(spinnerArrayAdapter);

        ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, disabilities);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        disabilitySpinner.setAdapter(spinnerArrayAdapter2);
        spinnerArrayAdapter.notifyDataSetChanged();
        spinnerArrayAdapter2.notifyDataSetChanged();

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                choice = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        disabilitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                wyborNiepelnosprawnosci = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ListCategoriesScreenActivity.this, PlacesListActivity.class);
                intent.putExtra("chosenCategory", choice);
                intent.putExtra("toggleButtonChoice", wyborNiepelnosprawnosci);
                startActivity(intent);
            }
        });


    }
}
