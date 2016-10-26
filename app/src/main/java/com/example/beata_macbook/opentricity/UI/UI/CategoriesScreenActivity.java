package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
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
    ToggleButton electronicWheelchairToggleButton;
    ToggleButton crutchesToggleButton;

    public static String toggleButtonChoice = "";


    // zmienna globalna wybor, która pozwala nam na ustawienie odpowiedniej kategorii do bazy Firebase
    public static String choice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_screen);

        wheelchairToggleButton = (ToggleButton)findViewById(R.id.wheelchairToggleButton);
        electronicWheelchairToggleButton = (ToggleButton)findViewById(R.id.electronicWheelchairToggleButton);
        crutchesToggleButton = (ToggleButton)findViewById(R.id.crunchesToggleButton);

        wheelchairToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wheelchairToggleButton.isChecked()) {
                    wheelchairToggleButton.setBackgroundColor(Color.RED);
                    toggleButtonChoice = "wheelchair";
                    Log.v("Wcisnieto", toggleButtonChoice);
                }else {
                    wheelchairToggleButton.setBackgroundColor(Color.GRAY);
                }
            }
        });



        electronicWheelchairToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(electronicWheelchairToggleButton.isChecked()) {
                    electronicWheelchairToggleButton.setBackgroundColor(Color.RED);
                    toggleButtonChoice = "el_wheelchair";
                    Log.v("Wcisnieto", toggleButtonChoice);
                }else {
                    electronicWheelchairToggleButton.setBackgroundColor(Color.GRAY);
                }
            }
        });


        crutchesToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (crutchesToggleButton.isChecked()) {
                    crutchesToggleButton.setBackgroundColor(Color.RED);
                    toggleButtonChoice  = "crutch";
                    Log.v("Wcisnieto", toggleButtonChoice);
                } else {
                    crutchesToggleButton.setBackgroundColor(Color.GRAY);
                }
            }
        });
    }

    public void onClick(View view) {
        String wybrano = "";
        switch (view.getId()){
            case R.id.barberButton:
                wybrano = "Lokale_uslugowe";
                choice = "Lokale_uslugowe";

                break;
            case R.id.restaurantsButton:
                wybrano = "Lokale_gastronomiczne";
                choice = "Lokale_gastronomiczne";
                break;
            case R.id.eduButton:
                wybrano = "Obiekty_edukacyjne";
                choice = "Obiekty_edukacyjne";
                break;
            case R.id.sportsButton:
                wybrano = "Obiekty_sportowe";
                choice = "Obiekty_sportowe";
                break;
            case R.id.govButton:
                wybrano = "Urzedy";
                choice= "Urzedy";
                break;
            default:
                break;
        }

        Intent intent = new Intent(this, PlacesListActivity.class);
        //przesylamy wybor uzytkownika
        intent.putExtra("chosenCategory", wybrano);
        intent.putExtra("toggleButtonChoice", toggleButtonChoice);
        startActivity(intent);
    }
}