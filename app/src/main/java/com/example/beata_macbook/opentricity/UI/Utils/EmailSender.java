package com.example.beata_macbook.opentricity.UI.Utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by alazelewska on 08.11.16.
 */

public class EmailSender {

    private final Activity activity;

    public EmailSender(final Activity activity) {
        this.activity = activity;
    }

    public void sendEmail(final String recipent, final String subject, final String message) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{recipent});
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, message);
        try {
            activity.startActivity(Intent.createChooser(i, "Wysyłanie..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(activity, "Zainstaluj klienta email.", Toast.LENGTH_SHORT).show();
        }
    }
}
