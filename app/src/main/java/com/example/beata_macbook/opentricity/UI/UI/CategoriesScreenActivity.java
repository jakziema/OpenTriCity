package com.example.beata_macbook.opentricity.UI.UI;

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
        setContentView(R.layout.activity_category_screen);
    }

    public void onClick(View view) {
        //TODO: Czy Kasi takie rozwiązanie pasuje - każdy przycisk ma swojego
        // taga i za jego pomocą przesyłamy ten tekst co wcześniej był przypisany do pola "wybrano"
        // (zajrzyjcie w activity_category_screen.xml i spójrzcie na android.tag przy
        // każdym przycisku, to magia stanie się jasna).
        // W ten sposób oszczędzamy trochę linii kodu (pamietajce, że może w apce dojść jeszcze
        // trochę rodzajów obiektów i wtedy taki switch-case może zrobić się mega nieczytelny.
        // Poza tym ogólnie switch-case używa się raczej w szczególnych przypadkach - lepiej
        // pomyśleć o innych rozwiązaniach
//        String wybrano = "";
//        switch (view.getId()) {
//            case R.id.barberButton:
//                wybrano = "Lokale_uslugowe";
//                break;
//            case R.id.restaurantsButton:
//                wybrano = "Lokale_gastronomiczne";
//                break;
//            case R.id.eduButton:
//                wybrano = "Obiekty_edukacyjne";
//                break;
//            case R.id.sportsButton:
//                wybrano = "Obiekty_sportowe";
//                break;
//            case R.id.govButton:
//                wybrano = "Urzedy";
//                break;
//        }
        Intent intent = new Intent(this, PlacesListActivity.class);
        //Pobieramy taga
        intent.putExtra("chosenCategory", view.getTag().toString());
        startActivity(intent);

    }
}