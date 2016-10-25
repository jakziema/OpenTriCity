package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.beata_macbook.opentricity.R;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */


/**
 * Klasa odpowiadajaca za ekran z kategoriami
 */
public class CategoriesScreenActivity extends AppCompatActivity {

    // zmienna globalna wybor, która pozwala nam na ustawienie odpowiedniej kategorii do bazy Firebase
    public static String choice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_screen);
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
        }

        Intent intent = new Intent(this, PlacesListActivity.class);
        //przesylamy wybor uzytkownika
        intent.putExtra("chosenCategory", wybrano);
        startActivity(intent);

    }
}