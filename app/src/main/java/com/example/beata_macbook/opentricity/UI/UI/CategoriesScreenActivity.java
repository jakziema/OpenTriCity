package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import com.example.beata_macbook.opentricity.R;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */


/**
 * Klasa odpowiadajaca za ekran z kategoriami
 */
public class CategoriesScreenActivity extends AppCompatActivity {

    ToggleButton wheelchairToggleButton;
    ToggleButton deafToggleButton;
    ToggleButton crutchesToggleButton;
    ToggleButton midgetToggleButton;
    ToggleButton blindToggleButton;

    public  String toggleButtonChoice = "";


    // zmienna globalna wybor, która pozwala nam na ustawienie odpowiedniej kategorii do bazy Firebase
    public static String choice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_screen);

        wheelchairToggleButton = (ToggleButton)findViewById(R.id.wheelchairToggleButton);
        deafToggleButton = (ToggleButton)findViewById(R.id.deafToggleButton);
        crutchesToggleButton = (ToggleButton)findViewById(R.id.crunchesToggleButton);
        midgetToggleButton = (ToggleButton)findViewById(R.id.midgetToggleButton);
        blindToggleButton = (ToggleButton)findViewById(R.id.blindToggleButton);

        wheelchairToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wheelchairToggleButton.isChecked()) {

                    toggleButtonChoice = "wheelchair";
                    Log.v("Wcisnieto", toggleButtonChoice);
                }
            }
        });

        deafToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deafToggleButton.isChecked()) {

                    toggleButtonChoice = "deaf";
                    Log.v("Wcisnieto", toggleButtonChoice);
                }
            }
        });


        crutchesToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (crutchesToggleButton.isChecked()) {

                    toggleButtonChoice  = "crutch";

                }
            }
        });

        midgetToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (midgetToggleButton.isChecked()) {
                    toggleButtonChoice = "midget";
                }
            }
        });

        blindToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (blindToggleButton.isChecked()) {
                    toggleButtonChoice = "blind";
                }
            }
        });

    }

    public void onClick(View view) {

        Intent intent = new Intent(this, PlacesListActivity.class);
        //Pobieramy taga
        intent.putExtra("chosenCategory", view.getTag().toString());
        intent.putExtra("toggleButtonChoice", toggleButtonChoice);
        startActivity(intent);
    }



    public void onRestart() {
        super.onRestart();

        toggleButtonChoice = "";
        wheelchairToggleButton.setChecked(false);
        deafToggleButton.setChecked(false);
        blindToggleButton.setChecked(false);
        midgetToggleButton.setChecked(false);
        crutchesToggleButton.setChecked(false);

    }
}