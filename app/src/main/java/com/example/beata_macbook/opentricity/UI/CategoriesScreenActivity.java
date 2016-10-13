package com.example.beata_macbook.opentricity.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.beata_macbook.opentricity.R;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class CategoriesScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryscreen);
    }

    public void onClick(View view) {
        String wybrano = "";
        switch (view.getId()){
            case R.id.barberButton:
                wybrano = "Lokale_uslugowe";
                break;
            case R.id.restaurantsButton:
                wybrano = "Lokale_gastronomiczne";
                break;
            case R.id.eduButton:
                wybrano = "Obiekty_edukacyjne";
                break;
            case R.id.sportsButton:
                wybrano = "Obiekty_sportowe";
                break;
            case R.id.govButton:
                wybrano = "Urzedy";
                break;
        }
        Intent intent = new Intent(this, PlacesListViewActivity.class);
        intent.putExtra("choosenCategory", wybrano);
        startActivity(intent);

    }
}