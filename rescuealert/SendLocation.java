package com.example.rescuealert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.util.Linkify;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.ui.auth.data.model.User;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import static android.content.ContentValues.TAG;

public class SendLocation extends AppCompatActivity {

    TextView hotlineNumber1;
    TextView hotlineNumber2;
    TextView hotlineNumber3;
    Button sendLocation;
    Button signOut;
    Linkify linkify;
    private Context context;
    private SettingsClient mSettingsClient;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationRequest locationRequest;
    public static final int REQUEST_CHECK_SETTINGS = 1001;
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User Location").child(currentUser.getUid());
    DatabaseReference timestamp = reference.child("timestamp");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_location);

        sendLocation = findViewById(R.id.sendButton);
        sendLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendLocationToDB();
                userBlock();
                timestamp.setValue(ServerValue.TIMESTAMP);
                locationRequest = LocationRequest.create();
                locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                locationRequest.setInterval(5000);



                LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                        .addLocationRequest(locationRequest);
                builder.setAlwaysShow(true);

                Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(getApplicationContext())
                        .checkLocationSettings(builder.build());

                result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<LocationSettingsResponse> task) {

                        try {
                            LocationSettingsResponse response = task.getResult(ApiException.class);
                            Toast.makeText(SendLocation.this, "GPS is turned on", Toast.LENGTH_SHORT).show();

                        } catch (ApiException e) {

                            switch (e.getStatusCode()) {
                                case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                                    try {
                                        ResolvableApiException resolvableApiException = (ResolvableApiException)e;
                                        resolvableApiException.startResolutionForResult(SendLocation.this,REQUEST_CHECK_SETTINGS);
                                    } catch (IntentSender.SendIntentException ex) {
                                        ex.printStackTrace();
                                    }
                                    break;

                                case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                    //Device does not have location
                                    break;
                            }
                        }
                    }
                });


            }
        });

        signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            goToMainActivity();

        });

    }

    private void sendLocationToDB() {
        startActivity(new Intent(SendLocation.this, MapsActivity.class));
    }

    private void goToMainActivity() {
        startActivity(new Intent(SendLocation.this, MainActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CHECK_SETTINGS) {

            switch (resultCode) {
                case Activity.RESULT_OK:
                    Toast.makeText(this, "GPS is turned on", Toast.LENGTH_SHORT).show();
                case Activity.RESULT_CANCELED:
                    Toast.makeText(this, "GPS is required to be turned on", Toast.LENGTH_SHORT).show();
            }
        }



    }

    private int blockUser;
    private void userBlock() {

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){

            final DatabaseReference blockRef = FirebaseDatabase.getInstance().getReference().child("Blocked Users").child(currentUser.getUid());
            blockRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    blockUser = Integer.parseInt(snapshot.getValue().toString());
                    if (blockUser == 0){

                    }
                    else if (blockUser == 1){
                        Intent mainIntent = new Intent().setClass(SendLocation.this, BlockedNotice.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }


}