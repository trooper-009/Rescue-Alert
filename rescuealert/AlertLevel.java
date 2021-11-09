package com.example.rescuealert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;


public class AlertLevel extends AppCompatActivity {

     Button high;
     Button medium;
     Button low;
     DatabaseReference reference;
     DatabaseReference blockReference;
     String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_level);

        high = (Button) findViewById(R.id.buttonRed);
        medium = (Button) findViewById(R.id.buttonYellow);
        low = (Button) findViewById(R.id.buttonGreen);
        reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        blockReference = FirebaseDatabase.getInstance().getReference("Blocked Users");

        high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Alert Level").setValue("High");
                blockReference.child(uid).setValue(0);
                goToSendLocation();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Alert Level").setValue("Medium");
                blockReference.child(uid).setValue(0);
                goToSendLocation();
            }
        });

        low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Alert Level").setValue("Low");
                blockReference.child(uid).setValue(0);
                goToSendLocation();
            }
        });

    }

    private void goToSendLocation() {
        startActivity(new Intent(AlertLevel.this, SendLocation.class));
    }
}