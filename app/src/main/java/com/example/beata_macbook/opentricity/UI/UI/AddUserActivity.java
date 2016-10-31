package com.example.beata_macbook.opentricity.UI.UI;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.beata_macbook.opentricity.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUserActivity extends AppCompatActivity {

    private EditText mUserEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;

    private Button mRegisterButton;

    private FirebaseAuth mAuth;
    private DatabaseReference dbReferenece;

    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mAuth = FirebaseAuth.getInstance();
        dbReferenece = FirebaseDatabase.getInstance().getReference().child("Users");

        mProgress = new ProgressDialog(this);

        mUserEditText = (EditText) findViewById(R.id.userEditText);
        mEmailEditText = (EditText) findViewById(R.id.emailEditText);
        mPasswordEditText = (EditText) findViewById(R.id.passTextView);

        mRegisterButton = (Button) findViewById(R.id.registerButton);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
                                               public void onClick(View view) {
                                                   addUser();
                                               }
                                           });
    }
        private void addUser(){

            final String user = mUserEditText.getText().toString().trim();
            final String email = mEmailEditText.getText().toString().trim();
            String password = mPasswordEditText.getText().toString().trim();

            if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

                mProgress.setMessage("Trwa rejestracja");
                mProgress.show();;

                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {

                            String user_id = mAuth.getCurrentUser().getUid();

                            DatabaseReference current_user_db = dbReferenece.child(user_id);
                            current_user_db.child("user").setValue(user);
                            current_user_db.child("email").setValue(email);
                        }}
                });
            }

    }

    }



