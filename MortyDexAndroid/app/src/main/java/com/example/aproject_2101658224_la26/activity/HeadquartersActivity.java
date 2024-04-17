package com.example.aproject_2101658224_la26.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.aproject_2101658224_la26.R;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;

import java.util.ArrayList;

public class HeadquartersActivity extends AppCompatActivity {
    MapView mapView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.headquarters_option_item_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.itemCharacters) {
            Intent intent = new Intent(HeadquartersActivity.this, CharacterActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.itemLogout) {
            Intent intent = new Intent(HeadquartersActivity.this, MainActivity.class);
            startActivity(intent);
            finishAffinity();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headquarters);
        Configuration.getInstance().load(this,
                PreferenceManager.getDefaultSharedPreferences(this));
        mapView = findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);

        IMapController controller = mapView.getController();
        controller.setZoom(19);
        GeoPoint iGeoPoint = new GeoPoint(34.1486, -118.3386);
        controller.setCenter(iGeoPoint);

        CompassOverlay compassOverlay = new CompassOverlay(this,
                new InternalCompassOrientationProvider(this), mapView);
        mapView.getOverlays().add(compassOverlay);

        Marker marker = new Marker(mapView);
        marker.setTitle("Morty Headquarters");
        marker.setPosition(iGeoPoint);
        mapView.getOverlays().add(marker);

        ArrayList<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.INTERNET);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        requestMapPermission(permissions);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        ArrayList<String> notAllowedPermission = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i])
                    != PackageManager.PERMISSION_GRANTED) {
                notAllowedPermission.add(permissions[i]);
            }
        }

        if (notAllowedPermission.size() > 0) {
            ActivityCompat.requestPermissions(this, notAllowedPermission.toArray(new String[0]), 1);
        }
    }

    void requestMapPermission(ArrayList<String> permissions) {
        ArrayList<String> notAllowedPermission = new ArrayList<>();
        for (int i = 0; i < permissions.size(); i++) {
            if (ContextCompat.checkSelfPermission(this, permissions.get(i))
                    != PackageManager.PERMISSION_GRANTED) {
                notAllowedPermission.add(permissions.get(i));
            }
        }

        if (notAllowedPermission.size() > 0) {
            ActivityCompat.requestPermissions(this, notAllowedPermission.toArray(new String[0]), 1);
        }
    }
}