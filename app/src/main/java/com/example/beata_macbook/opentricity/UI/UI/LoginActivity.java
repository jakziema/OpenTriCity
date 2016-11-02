package com.example.beata_macbook.opentricity.UI.UI;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.beata_macbook.opentricity.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {

    private EditText mLoginEmail;
    private EditText mLoginPassword;
    private Button mLoginButton;

    private DatabaseReference dbReference;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
        @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
            if(firebaseAuth.getCurrentUser()==null){
                Intent loginIntent = new Intent(LoginActivity.this, AddUserActivity.class);
                loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent);
            }
        }

        };

        mLoginEmail = (EditText)findViewById(R.id.emailTextView);
        mLoginPassword = (EditText)findViewById(R.id.passTextView);

        mLoginButton = (Button)findViewById(R.id.loginButton);

        mLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                checkLogin();
            }

        });
    }

    

    private void checkLogin(){

        String email = mLoginEmail.getText().toString().trim();
        String password = mLoginPassword.getText().toString().trim();
        Intent intent = new Intent(LoginActivity.this, AddUserActivity.class);
        startActivity(intent);
        Log.v(email, "klik klik");

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){



        }
    }
}
