package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.beata_macbook.opentricity.R;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
    }

    public void onAboutButtonClick(View view) {
        Intent intent = new Intent(MainScreenActivity.this, AboutScreenActivity.class);
        startActivity(intent);
    }

    public void onGuestButtonClick(View view) {
        Intent intent = new Intent(this, CategoriesScreenActivity.class);
        startActivity(intent);
    }

//    public void onLoginButtonClicked(View view) {
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }

//    public void onInstructionsButtonClicked(View view) {
//        Intent intent = new Intent(this, InstructionsActivity.class);
//        startActivity(intent);
//    }
}