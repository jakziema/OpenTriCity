package com.example.beata_macbook.opentricity.UI.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;

import org.w3c.dom.Text;

public class Instruction extends AppCompatActivity {
    TextView instruction1;
    TextView instruction2;
    TextView instruction3;
    TextView instruction4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);

        instruction1 = (TextView)findViewById(R.id.instruction1TextView);
        instruction2 = (TextView)findViewById(R.id.instruction2TextView);
        instruction3 = (TextView)findViewById(R.id.instruction3TextView);
        instruction4 = (TextView)findViewById(R.id.instruction4TextView);


    }
}
