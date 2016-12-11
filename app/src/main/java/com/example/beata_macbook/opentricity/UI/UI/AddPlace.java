package com.example.beata_macbook.opentricity.UI.UI;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;


import com.example.beata_macbook.opentricity.R;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import java.util.Random;
import static android.R.attr.category;
import static com.example.beata_macbook.opentricity.UI.UI.ListCategoriesScreenActivity.wyborNiepelnosprawnosci;
import static android.R.attr.text;



public class AddPlace extends AppCompatActivity {

    String text;
    EditText mTextName;
    EditText mTextAddress;
    EditText mTextPhone;
    EditText mTextElevator;
    EditText mTextStaff;
    EditText mTextToilets;
    EditText mTextRamp;
    Spinner spinnerCategory;
    Spinner spinnerDisabilities;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;


    ToggleButton elevatorBtn;

    private static final String TAG = AddPlace.class.getName();

    Button mAddBtn;
    private String category;
    private String disabilities;
    private FirebaseAuth mAuth;
    private StorageReference mStorage;

    private Button mSelectImage;


    private GoogleApiClient client;

    private static final int GALLERY_INTENT = 2;
    //DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    //id = Parcels.unwrap(getIntent().getParcelableExtra("id"));
    // category = Parcels.unwrap(getIntent().getParcelableExtra("category"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mStorage = FirebaseStorage.getInstance().getReference();
        mSelectImage = (Button) findViewById(R.id.selectImage);

        mTextName = (EditText) findViewById(R.id.name);
        mTextAddress = (EditText) findViewById(R.id.address);
        mTextPhone = (EditText) findViewById(R.id.phone);
        mTextElevator = (EditText) findViewById(R.id.elevator);
        mTextStaff = (EditText) findViewById(R.id.staff);
        mTextToilets = (EditText) findViewById(R.id.toilets);
        mTextRamp = (EditText) findViewById(R.id.ramp);

        mAddBtn = (Button) findViewById(R.id.addBtn);

        spinnerCategory = (Spinner) findViewById(R.id.spinner);
        spinnerDisabilities = (Spinner)findViewById(R.id.spinner2);
        adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.disabilities, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);
        spinnerDisabilities.setAdapter(adapter2);

        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });


        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                parent.getItemAtPosition(position);
                text = parent.getSelectedItem().toString();


            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        spinnerDisabilities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                parent.getItemAtPosition(position);
                text = parent.getSelectedItem().toString();


            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    //category = Parcels.unwrap(getIntent().getParcelableExtra("category"));




  private void addNewPlace() {

        Random rand = new Random();

        int n = rand.nextInt(1000) + 1;


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("name");
        DatabaseReference refAdr = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("address");
        DatabaseReference refPho = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("phoneNumber");
        DatabaseReference refEle = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("elevator");
        DatabaseReference refStaff = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("staff");
        DatabaseReference refToil = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("toilets");
       DatabaseReference refKul = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("O kulach");
       DatabaseReference refWoz = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("Wozek");
       DatabaseReference refNies = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("Niesłyszący");
       DatabaseReference refPies = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("Karłowatość");
        DatabaseReference refRamp = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("podjazdy");
      DatabaseReference imageURL = FirebaseDatabase.getInstance().getReference().child(text).child(String.valueOf(n)).child("imageURL");
        Log.d("Wybrałam", text);


        final String name = mTextName.getText().toString();
        final String address = mTextAddress.getText().toString();
        final String phone = mTextPhone.getText().toString();
        String phonePatter = "[0-9]+[0-9]+[0-9]+[0-9]";
        final String elevator = mTextElevator.getText().toString();
        final String staff = mTextStaff.getText().toString();
        final String ramp = mTextRamp.getText().toString();
        final String toilets = mTextToilets.getText().toString();
       //final Uri imageUrl =

        if (!name.isEmpty() && !address.isEmpty() && !phone.isEmpty() && !elevator.isEmpty() && phone.matches(phonePatter)) {
            ref.setValue(name);
            refAdr.setValue(address);
            refPho.setValue(phone);
            refEle.setValue(elevator);
            refStaff.setValue(staff);
            refToil.setValue(toilets);
            refRamp.setValue(ramp);
            finish();
            startActivity(getIntent());

            Toast.makeText(this, "Miejsce zostało dodane", Toast.LENGTH_SHORT).show();
        } else {
                Toast.makeText(AddPlace.this,
                        "Uzupełnij wszystkie pola", Toast.LENGTH_LONG).show();
            }
        }




    @Override
    protected void onStart() {
        super.onStart();// ATTENTION: This was auto-generated to implement the App Indexing API.
// See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                    addNewPlace();
                    Log.d("Wybrano", text);


            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("AddPlace Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GALLERY_INTENT && resultCode==RESULT_OK){
            Uri uri = data.getData();

            StorageReference filepath = mStorage.child("Nowe_zdjecia").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri downloadUrl = taskSnapshot.getDownloadUrl();

                    Log.d(TAG, "url to" +downloadUrl);
                    Toast.makeText(AddPlace.this,"Plik dostarczono do bazy. W aplikacji będzie widoczny po akceptacji administratora" ,Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(AddPlace.this,"Operacja zakończona niepowodzeniem" ,Toast.LENGTH_LONG).show();
                }
            });

            }
        }

    }
