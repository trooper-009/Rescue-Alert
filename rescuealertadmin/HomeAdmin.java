package com.example.rescuealertadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeAdmin extends AppCompatActivity {

    ImageButton userMap;
    ImageButton userReport;
    ImageButton nearbyUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_admin);

        userMap = findViewById(R.id.Map);
        userReport = findViewById(R.id.reportUser);
        nearbyUser = findViewById(R.id.nearby);

        userMap.setOnClickListener(view -> {
            goToMap();

        } );

        userReport.setOnClickListener(view -> {
            goToReport();
        });

        nearbyUser.setOnClickListener(view -> {
            goToNearby();
        });
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference locationRef = rootRef.child("User Location");

        locationRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                        notification();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void goToNearby() {

        startActivity(new Intent(HomeAdmin.this, NearbyMaps.class));
    }

    private void goToReport() {
        startActivity(new Intent(HomeAdmin.this, Report.class));

    }

    private void goToMap() {
        startActivity(new Intent(HomeAdmin.this, RetrieveMapsActivity.class));
    }
    private void notification(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_HIGH);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                .setContentText("Rescue Alert")
                .setSmallIcon(R.drawable.fui_ic_phone_white_24dp)
                .setAutoCancel(true)
                .setContentText("New Location has been Sent!");

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999, builder.build());

    }
}