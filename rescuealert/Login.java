package com.example.rescuealert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView UserName;
    TextView UserEmail;
    ImageView UserImage;
    Button btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        setContentView(R.layout.activity_login);
        UserName = findViewById(R.id.userName);
        UserEmail = findViewById(R.id.userEmail);
        UserImage = findViewById(R.id.userImage);
        btnRegister = findViewById(R.id.btnRegister);

        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String userName = preferences.getString("username", "");
        String userPhotoUrl = preferences.getString("useremail", "");
        String userEmail = preferences.getString("userphoto", "");

        UserName.setText(userName);
        UserEmail.setText(userEmail);
        Glide.with(Login.this).load(userPhotoUrl).into(UserImage);

        btnRegister.setOnClickListener(view -> {
            goToRegistration();
        });
    }

    private void goToRegistration() {

        startActivity(new Intent(Login.this, Registration.class));
    }

}

