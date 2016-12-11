package com.example.beata_macbook.opentricity.UI.UI;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.beata_macbook.opentricity.R;
import com.example.beata_macbook.opentricity.UI.Adapter.CustomCommentsAdapter;
import com.example.beata_macbook.opentricity.UI.Model.Comment;
import com.example.beata_macbook.opentricity.UI.Model.CommentFields;
import com.example.beata_macbook.opentricity.UI.Model.Place;
import com.example.beata_macbook.opentricity.UI.Utils.LocationHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
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
    TextView parkingTextView;
    TextView komunikacjaTextView;


    EditText addCommentTxt;
    Button addCommentBtn;
    LayoutInflater inflater;
    View details;
    private ListView list ;
    private CustomCommentsAdapter adapter ;
    LocationManager mLocationManager;
    private FirebaseAuth mAuth;
    Button logujBtn;
    Button wylogujBtn;
    Button lokalizujBtn;
    Location location = null;
    ImageView detailPlaceImageView;
    //deklarujemy obiekt typu Place z ktorego bedziemy pobierali opodwiednie pola
    Place place;
    private String id;
    private String category;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places_detail);
        inflater = LayoutInflater.from(this);
        details = inflater.inflate(R.layout.detail_layout, null, false);
        //rozpakowujemy przeslane miejsce
        place = Parcels.unwrap(getIntent().getParcelableExtra("place"));
        id = Parcels.unwrap(getIntent().getParcelableExtra("id"));
        category = Parcels.unwrap(getIntent().getParcelableExtra("category"));

        /*if(mAuth.getCurrentUser()!=null){
            logujBtn.setText("Zaloguj");

        }else{
            logujBtn.setText("wyloguj");
        }*/
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
        parkingTextView = (TextView)details.findViewById(R.id.parking1TextView);
        komunikacjaTextView = (TextView)details.findViewById(R.id.komunikacja1TextView);



        noCommentsTxt = (TextView) details.findViewById(R.id.noCommentsTxt);
        lokalizujBtn = (Button) findViewById(R.id.lokalizuj_btn);
        logujBtn = (Button) findViewById(R.id.loguj_btn);
        wylogujBtn = (Button)findViewById(R.id.button2);
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
        parkingTextView.setText(place.getParking());
        komunikacjaTextView.setText(place.getKomunikacja());
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

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            public static final String TAG = "komunikat" ;

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Użytkownik zalogowany
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // Użytkownik wylogowany
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }

                updateUI(user);

            }
        };
    }


   /* @Override
    protected void onRestart() {
        super.onRestart();
        if(mAuth.getCurrentUser()!=null){
            logujBtn.setText("Zaloguj");

        }else{
            logujBtn.setText("wyloguj");
        }

    }*/

    private void createListView() {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        if (place.getComments() != null) {
            for (final Map.Entry<String, Object> entry : place.getComments().entrySet()) {
                comments.add(new Comment(entry.getKey(), (HashMap) entry.getValue()));
            }
        }
        Collections.sort(comments, new DateComparator());
        adapter = new CustomCommentsAdapter(list, comments, PlaceDetailActivity.this);
        list.addHeaderView(details);
        list.setAdapter(adapter);
        if (comments.isEmpty()) {
            noCommentsTxt.setVisibility(View.VISIBLE);
        }
    }

    private void dodajKomentarz() {
        if (mAuth.getCurrentUser() != null) {
            if (this.category != null && this.id != null) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference(this.category)
                        .child(this.id).child("comments").push();
                final String comment = addCommentTxt.getText().toString();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                Map<String, String> mapa = new HashMap<>();
                mapa.put(CommentFields.DATE.name(), dateFormat.format(date).toString());
                mapa.put(CommentFields.TEXT.name(), comment);
                if (!comment.isEmpty()) {
                    ref.setValue(mapa);
                    finish();
                    startActivity(getIntent());
                }
            } else {
                Toast.makeText(this, "Wystąpił problem przy dodawaniu komentarza.", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Toast.makeText(this, "Zaloguj się by dodać komentarz.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PopComments.class);
            startActivity(intent);
        }
    }

    public void logujClick(View view) {
        Intent intent = new Intent(PlaceDetailActivity.this, AddUserActivity.class);
        startActivity(intent);
    }

    public void wylogujClick(View view){
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
                if(location == null) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Poczekaj na pobranie Twojej lokalizacji",
                            Toast.LENGTH_SHORT
                    ).show();
                }
                else{
                    location = mLocationManager
                            .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    //W miejsce `52` i `23` wstawcie swoje wspolrzedne geograficzne
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                            Uri.parse("http://maps.google.com/maps?saddr="+location.getLatitude()+
                                    "," + location.getLongitude() + "&daddr="+ place.getLongitude() +
                                            ","+ place.getLatitude()));

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



    private void updateUI(FirebaseUser user) {
        if (user != null) {
           logujBtn.setText("Wyloguj");
        } else {
            //findViewById(R.id.loguj_btn).setVisibility(View.GONE);
            logujBtn.setText("Zaloguj");
        }
    }

    public class DateComparator implements Comparator<Comment> {
        @Override
        public int compare(Comment o1, Comment o2) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
            try {
                Date date1 =  df.parse(o1.getDate());
                final Date date2 = df.parse(o2.getDate());
                return date1.compareTo(date2);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        }
    }

}