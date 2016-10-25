package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.beata_macbook.opentricity.R;

/**
 * Klasa odpowiadajca za ekran glowny , wybor odpowiedniej opcji
 */
public class MainScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
    }

    //Przejscie do ekranu informacji
    public void onAboutButtonClick(View view) {
        Intent intent = new Intent(MainScreenActivity.this, AboutScreenActivity.class);
        startActivity(intent);
    }

    //Przejscie do keranu z kategoriami
    public void onGuestButtonClick(View view) {
        Intent intent = new Intent(MainScreenActivity.this, CategoriesScreenActivity.class);
        startActivity(intent);
    }

//    public void onLoginButtonClicked(View view) {
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }

    //Przejscie do ekranu z instrukcjami
    public void onInstructionsButtonClick(View view) {
        Intent intent = new Intent(MainScreenActivity.this, Instruction.class);
        startActivity(intent);
    }
}