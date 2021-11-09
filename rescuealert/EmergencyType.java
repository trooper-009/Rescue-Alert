package com.example.rescuealert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

public class EmergencyType extends AppCompatActivity {

    Button emergencyBtn;
    Button manMadeBtn;
    Button naturalBtn;
    DatabaseReference reference;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_type);

        emergencyBtn = (Button) findViewById(R.id.emergencyButton);
        manMadeBtn = (Button) findViewById(R.id.manmadeButton);
        naturalBtn = (Button) findViewById(R.id.naturalButton);
        reference = FirebaseDatabase.getInstance().getReference("Users").child(uid);

        emergencyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Emergency Type").setValue("Emergency");
                reference.child("userTimestamp").setValue(ServerValue.TIMESTAMP);
                gotoAlertLevel();
            }
        });

        manMadeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Emergency Type").setValue("Man-Made Disaster");
                reference.child("userTimestamp").setValue(ServerValue.TIMESTAMP);
                gotoAlertLevel();
            }
        });

        naturalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Emergency Type").setValue("Natural Disaster");
                reference.child("userTimestamp").setValue(ServerValue.TIMESTAMP);
                gotoAlertLevel();
            }
        });

    }

    private void gotoAlertLevel() {
        startActivity(new Intent(EmergencyType.this, AlertLevel.class));
    }
}