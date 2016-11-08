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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.example.beata_macbook.opentricity.UI.Utils.LocationHelper;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa odpowiadająca za widok pokazujący szczegóły danego miejsca
 */

public class PlaceDetailActivity extends AppCompatActivity {


    // deklaracja wszystkich labeli itd
    TextView detailPlaceNameTextView;
    TextView addressTextView;
    TextView descriptionTextView;
    TextView phoneTextView;
    TextView elevatorTextView;
    TextView podjazdyTextView;
    TextView toiletsTextView;
    TextView staffTextView;
    TextView barTextView;
    TextView noCommentsTxt;
    EditText addCommentTxt;
    Button addCommentBtn;
    LayoutInflater inflater;
    View details;
    private ListView list ;
    private ArrayAdapter<String> adapter ;
    LocationManager mLocationManager;
    private FirebaseAuth mAuth;

    Button logujBtn;
    Button lokalizujBtn;

    Location location = null;
    ImageView detailPlaceImageView;
    //deklarujemy obiekt typu Place z ktorego bedziemy pobierali opodwiednie pola
    Place place;
    private String id;
    private String category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_detail);
        inflater = LayoutInflater.from(this);
        details = inflater.inflate(
                R.layout.detail_layout, null, false);
        //rozpakowujemy przeslane miejsce
        place = Parcels.unwrap(getIntent().getParcelableExtra("place"));
        id = Parcels.unwrap(getIntent().getParcelableExtra("id"));
        category = Parcels.unwrap(getIntent().getParcelableExtra("category"));

        //szukamy widokow
        detailPlaceNameTextView = (TextView) findViewById(R.id.detailPlaceNameTextView);
        addressTextView = (TextView) details.findViewById(R.id.addressTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        phoneTextView = (TextView) details.findViewById(R.id.phoneTextView);
        detailPlaceImageView = (ImageView) findViewById(R.id.detailPlaceImageView);
        elevatorTextView = (TextView) details.findViewById(R.id.elevatorTextView);
        barTextView = (TextView) details.findViewById(R.id.barTextView);
        toiletsTextView = (TextView) details.findViewById(R.id.toiletsTextView);
        staffTextView = (TextView) details.findViewById(R.id.staffTextView);
        podjazdyTextView = (TextView) details.findViewById(R.id.podjazdyTextView);
        noCommentsTxt = (TextView) details.findViewById(R.id.noCommentsTxt);
        lokalizujBtn = (Button) findViewById(R.id.lokalizuj_btn);
        logujBtn = (Button) findViewById(R.id.loguj_btn);
        addCommentTxt = (EditText) details.findViewById(R.id.addCommentTxt);
        addCommentBtn = (Button) details.findViewById(R.id.addCommentBtn);
        addCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dodajKomentarz();
            }
        });
        mAuth = FirebaseAuth.getInstance();

        //wrzucamy do labeli pola kliknietego miejsca
        detailPlaceNameTextView.setText(place.getName());
        addressTextView.setText(place.getAddress());
        descriptionTextView.setText(place.getDescription());
        phoneTextView.setText(place.getPhoneNumber());
        elevatorTextView.setText(place.getElevator());
        barTextView.setText(place.getBar());
        toiletsTextView.setText(place.getToilets());
        staffTextView.setText(place.getStaff());
        podjazdyTextView.setText(place.getPodjazdy());
        list = (ListView) findViewById(R.id.commentList);
        this.createListView();
        //ustawiamy zdjecie po URL
        Picasso.with(this).load(place.getImageURL()).resize(400, 300).centerCrop().into(detailPlaceImageView);

        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (LocationHelper.checkLocationPermission(getApplicationContext())) {
            Toast.makeText(getApplicationContext(), "Nie masz dostępu do usługi lokalizacji.", Toast.LENGTH_SHORT).show();
        } else {
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 2,
                    10, mLocationListener);
        }
        lokalizujBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nawigujStart();
            }
        });
    }

    private void createListView() {
        ArrayList<String> carL = new ArrayList<String>();
        if (place.getComments() != null) {
            for (final Object comment : place.getComments().values()) {
                carL.add(comment.toString());
            }
        }
        adapter = new ArrayAdapter<String>(this, R.layout.single_comment_row, carL);
        list.addHeaderView(details);
        list.setAdapter(adapter);
        if (carL.isEmpty()) {
            noCommentsTxt.setVisibility(View.VISIBLE);
        }
    }

    private void dodajKomentarz() {
        if (true) {
            if (this.category != null && this.id != null) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference(this.category)
                        .child(this.id).child("comments").push();
                final String comment = addCommentTxt.getText().toString();
                if (!comment.isEmpty()) {
                    ref.setValue(comment);
                    adapter.add(comment);
                }
            } else {
                Toast.makeText(this, "Wystąpił problem przy dodawaniu komentarza.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Zaloguj się by dodać komentarz.", Toast.LENGTH_SHORT).show();
        }
    }

    public void logujClick(View view) {
        Intent intent = new Intent(PlaceDetailActivity.this, AddUserActivity.class);
        startActivity(intent);
    }

    public void nawigujStart() {
        if (!LocationHelper.isLocationEnabled(getApplicationContext())) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Lokalizacja");
            dialog.setMessage("GPS jest wyłączony. Czy chcesz uruchomić ekran ustawień aby go włączyć?");
            dialog.setPositiveButton("Ustawienia", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                }
            });
            dialog.setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {

                }
            });
            dialog.show();
        } else {
            if(!LocationHelper.checkLocationPermission(getApplicationContext())){
                if(null == location) {
                    Toast.makeText(getApplicationContext(), "Poczekaj na pobranie Twojej lokalizacji", Toast.LENGTH_SHORT).show();
                }
                else{
                    location = mLocationManager
                            .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    //W miejsce `52` i `23` wstawcie swoje wspolrzedne geograficzne
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr="+location.getLatitude() +
                                    "," + location.getLongitude() + "&daddr="+ 18 +
                                    ","+ 57));

                    startActivity(intent);
                }

            }
            else{
                Toast.makeText(getApplicationContext(), "Coś poszło nie tak", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private final LocationListener mLocationListener = new LocationListener() {

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("LOCATION", "STATUS: " + Integer.toString(status));
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("LOCATION", "ENABLED");
        }

        @Override
        public void onProviderDisabled(String provider) {

        }

        @Override
        public void onLocationChanged(final Location mLocation) {
            location = mLocation;
            Log.d("LOCATION", Double.toString(location.getLatitude()));
        }
    };

}