package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.beata_macbook.opentricity.R;

/**
 * Klasa odpowiadajca za ekran glowny , wybor odpowiedniej opcji
 */
public class MainScreenActivity extends AppCompatActivity {
<<<<<<< HEAD
=======


>>>>>>> origin/Kasia_Branch
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);
        if(!isOnline()) {
            this.connectionAlert().show();
        }
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

<<<<<<< HEAD
//    public void onInstructionsButtonClicked(View view) {
//        Intent intent = new Intent(this, InstructionsActivity.class);
//        startActivity(intent);
//    }

    /**
     * Metoda sprawdza, czy urządzenie jest połączone do internetu.
     * @return True jeśli jest połączenie ineternetowe, false w przeciwnym razie
     */
    public boolean isOnline() {
        ConnectivityManager cm;
        cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /**
     * Metoda zwraca alert dialog pozwalający na wyświetlenie stosownego monitu gdy brak połączenia
     * internetowego. W metodzie musimy nadpisać (poprzez 'Override') domyślną akcję dla przycisku,
     * aby alert dialog nie zamykał się automatycznie przy kliknięciu - ma zamknąć się dopiero
     * gdy połączenie internetowe będzie dostępne.
     * @return Nowa instancja alert dialogu
     */
    private AlertDialog connectionAlert() {
        final AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle(R.string.error)
                .setMessage(R.string.connection_error)
                .setPositiveButton(R.string.try_again, null)
                .create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button b = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isOnline()) {
                            dialog.dismiss();
                        }
                    }
                });
            }
        });
        return alert;
    }
=======
>>>>>>> origin/Kasia_Branch
    //Przejscie do ekranu z instrukcjami
    public void onInstructionsButtonClick(View view) {
        Intent intent = new Intent(MainScreenActivity.this, Instruction.class);
        startActivity(intent);
    }
}