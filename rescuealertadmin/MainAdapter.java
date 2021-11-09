package com.example.rescuealertadmin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainAdapter extends FirebaseRecyclerAdapter<MainData,MainAdapter.myViewHolder> {

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull MainData model) {
        holder.lName.setText(model.getlName());
        holder.address.setText(model.getAddress());
        holder.phoneNum.setText(model.getPhoneNum());


        Glide.with(holder.image.getContext())
                .load(model.getlName())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .circleCrop()
                .error(R.drawable.btn_google_signin_dark_normal)
                .into(holder.image);

        holder.block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase.getInstance().getReference().child("Blocked Users")
                        .child(Objects.requireNonNull(getRef(position).getKey())).setValue(true);

            }
        });
        holder.unblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                FirebaseDatabase.getInstance().getReference().child("Blocked Users")
                        .child(Objects.requireNonNull(getRef(position).getKey())).setValue(false);

            }
        });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new myViewHolder(view);
    }

    public MainAdapter(@NonNull FirebaseRecyclerOptions<MainData> mainDataFirebaseRecyclerOptions) {
        super(mainDataFirebaseRecyclerOptions);
    }

    class myViewHolder extends RecyclerView.ViewHolder {

            CircleImageView image;
            TextView lName, address, phoneNum;
            Button block, unblock;



        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            image = (CircleImageView) itemView.findViewById(R.id.img1);
            lName = (TextView) itemView.findViewById(R.id.nameText);
            address = (TextView) itemView.findViewById(R.id.addressText);
            phoneNum = (TextView) itemView.findViewById(R.id.numberText);

            block = (Button) itemView.findViewById(R.id.button3);
            unblock = (Button) itemView.findViewById(R.id.button4);


        }
    }




}