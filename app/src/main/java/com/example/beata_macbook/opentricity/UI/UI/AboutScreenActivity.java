package com.example.beata_macbook.opentricity.UI.UI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;
import com.google.android.gms.vision.text.Text;

/**
 * Created by Beata-MacBook on 13.10.2016.
 */

public class AboutScreenActivity extends AppCompatActivity {
    TextView about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_screen);
        about = (TextView)findViewById(R.id.aboutTextView);
    }
}
