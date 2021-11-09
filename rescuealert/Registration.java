package com.example.rescuealert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    EditText personalAddress;
    EditText emergencyContact;
    EditText personalNumber;
    EditText emergencyNumber;
    EditText personalName;
    Button createAccount;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    DatabaseReference locationData;
    String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);

           personalAddress = findViewById(R.id.personalAddress);
           emergencyContact = findViewById(R.id.emergencyContact);
           personalNumber = findViewById(R.id.personalNumber);
           emergencyNumber = findViewById(R.id.emergencyNumber);
           createAccount = findViewById(R.id.createAccount);
           personalName = findViewById(R.id.contactEmergency);

           createAccount.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   rootNode = FirebaseDatabase.getInstance();
                   reference = rootNode.getReference("Users").child(uid);


                   String address = personalAddress.getText().toString();
                   String name = emergencyContact.getText().toString();
                   String phoneNum = personalNumber.getText().toString();
                   String emergencyNum = emergencyNumber.getText().toString();
                   String lName = personalName.getText().toString();

                   Data userData = new Data (address, name, phoneNum, emergencyNum, lName);
                   reference.setValue(userData);


                   goToAlertType();


               }
           });
    }

    private void goToAlertType() {
        startActivity(new Intent(Registration.this, EmergencyType.class));
    }
}