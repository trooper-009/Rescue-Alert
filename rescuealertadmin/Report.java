package com.example.rescuealertadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.data.model.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Report extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapter mainAdapter;
    Button blockBtn;
    Button unblockBtn;
    String uid = FirebaseAuth.getInstance().getUid();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        recyclerView = (RecyclerView) findViewById(R.id.userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainData> mainDataFirebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<MainData>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Users"), MainData.class)
                .build();



        mainAdapter = new MainAdapter(mainDataFirebaseRecyclerOptions);
        recyclerView.setAdapter(mainAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }

    private void blockUser(){

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Blocked Users");
        reference.child(uid).setValue(1);

    }
    private void unblockUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Blocked Users");
        reference.child(uid).setValue(0);
    }
}