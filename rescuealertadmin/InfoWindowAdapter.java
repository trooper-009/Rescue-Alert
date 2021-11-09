package com.example.rescuealertadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class InfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

    Context context;
    public InfoWindowAdapter(Context context){
        this.context = context;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        View infoView = LayoutInflater.from(context).inflate(R.layout.infowindow, null);
        TextView title = infoView.findViewById(R.id.title);
        TextView alert = infoView.findViewById(R.id.alert);

        title.setText(marker.getTitle());
        alert.setText(marker.getSnippet());


        return infoView;
    }

    @Nullable
    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }
}
