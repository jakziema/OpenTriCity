package com.example.beata_macbook.opentricity.UI.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.example.beata_macbook.opentricity.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;


import org.parceler.Parcels;

import java.util.Random;

import static android.R.attr.category;

public class AddPlace extends AppCompatActivity {

    EditText mTextName;
    EditText mTextAddress;
    EditText mTextPhone;
    EditText mTextElevator;

    Button mAddBtn;
    private String category;

    //DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    //id = Parcels.unwrap(getIntent().getParcelableExtra("id"));
   // category = Parcels.unwrap(getIntent().getParcelableExtra("category"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);


        mTextName = (EditText)findViewById(R.id.name);
        mTextAddress = (EditText)findViewById(R.id.address);
        mTextPhone = (EditText)findViewById(R.id.phone);
        mTextElevator = (EditText)findViewById(R.id.elevator);


        mAddBtn = (Button)findViewById(R.id.addBtn);
        category = Parcels.unwrap(getIntent().getParcelableExtra("category"));


    }



    private void addNewPlace() {
        Random rand = new Random();

        int  n = rand.nextInt(1000) + 1;
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Lokale_uslugowe").child(String.valueOf(n)).child("name");
        DatabaseReference refAdr = FirebaseDatabase.getInstance().getReference().child("Lokale_uslugowe").child(String.valueOf(n)).child("address");
        DatabaseReference refPho = FirebaseDatabase.getInstance().getReference().child("Lokale_uslugowe").child(String.valueOf(n)).child("phoneNumber");
        DatabaseReference refEle = FirebaseDatabase.getInstance().getReference().child("Lokale_uslugowe").child(String.valueOf(n)).child("elevator");
        //DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Lokale_uslugowe").child(String.valueOf(n)).child("name");

        final String name = mTextName.getText().toString();
        final String address = mTextAddress.getText().toString();
        final String phone = mTextPhone.getText().toString();
        final String elevator = mTextElevator.getText().toString();

        if (!name.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !elevator.isEmpty()) {
                  ref.setValue(name);
                    refAdr.setValue(address);
                    refPho.setValue(phone);
                    refEle.setValue(elevator);
                    finish();
                    startActivity(getIntent());
                }
       }

    @Override
    protected void onStart() {
        super.onStart();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                addNewPlace();
            }
        });
    }
    }




