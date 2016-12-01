package com.example.beata_macbook.opentricity.UI.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.beata_macbook.opentricity.R;

public class Instruction extends AppCompatActivity {
    TextView instruction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        instruction = (TextView)findViewById(R.id.instructionTextView);

    }
}
