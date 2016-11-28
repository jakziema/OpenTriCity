package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.beata_macbook.opentricity.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
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

        searchButton = (Button) findViewById(R.id.searchButton);



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefCategories = database.getReference("Kategorie");

        myRefCategories.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> categories = new ArrayList<String>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    for (DataSnapshot ds2 : ds.getChildren()) {
                        Log.v("Categories", ds2.getValue().toString());
                        categories.add(ds2.getValue().toString());
                    }
                }

                categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
                ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(ListCategoriesScreenActivity.this,
                        android.R.layout.simple_spinner_item, categories);
                categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                categorySpinner.setAdapter(categoryAdapter);

                categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        choice = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        DatabaseReference myRefDisabilities = database.getReference("Niepelnosprawnosci");
        myRefDisabilities.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                final List<String> disabilities = new ArrayList<String>();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {

                    for (DataSnapshot ds2 : ds.getChildren()) {
                        Log.v("Categories", ds2.getValue().toString());
                        disabilities.add(ds2.getValue().toString());
                    }
                }

                disabilitySpinner = (Spinner) findViewById(R.id.disabilitySpinner);
                ArrayAdapter<String> disabilityAdapter = new ArrayAdapter<String>(ListCategoriesScreenActivity.this,
                        android.R.layout.simple_spinner_item, disabilities);
                disabilityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                disabilitySpinner.setAdapter(disabilityAdapter);

                disabilitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        wyborNiepelnosprawnosci = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("wybor",choice);
                Log.v("wybor",wyborNiepelnosprawnosci);


                Toast.makeText(ListCategoriesScreenActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : " + String.valueOf(categorySpinner.getSelectedItem()) +
                                "\nSpinner 2 : " + String.valueOf(disabilitySpinner.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();



                Intent intent = new Intent(ListCategoriesScreenActivity.this, PlacesListActivity.class);
                intent.putExtra("chosenCategory", choice);
                intent.putExtra("toggleButtonChoice", wyborNiepelnosprawnosci);
                startActivity(intent);
            }
        });

    }




}
