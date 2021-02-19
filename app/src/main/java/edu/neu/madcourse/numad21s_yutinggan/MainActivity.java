package edu.neu.madcourse.numad21s_yutinggan;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;

public class MainActivity extends AppCompatActivity {

    public Button aboutButton;
    public Button clickyButton;
    public Button locatorButton;
    public Button linkCollectorButton;
    private final int locationPermissionRequestCode = 1;
    protected LocationManager locationManager;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;

    protected void requestPermission(String permissionType,
                                     int requestCode) {
        ActivityCompat.requestPermissions(this, new String[]{permissionType}, requestCode );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent aboutIntent = new Intent(MainActivity.this, AboutMeActivity.class);
               startActivity(aboutIntent);
            }
        });

        clickyButton = findViewById(R.id.button_Clicky);
        clickyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent clickyIntent = new Intent(MainActivity.this, ClickyActivity.class);
                startActivity(clickyIntent);
            }
        });

        linkCollectorButton = findViewById(R.id.button_linkCollector);
        linkCollectorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent linkCollectorIntent = new Intent(MainActivity.this, LinkCollector.class);
                startActivity(linkCollectorIntent);
            }
        });

        Intent locatorIntent = new Intent(MainActivity.this, Locator.class);
        locatorButton = findViewById(R.id.button_locator);
        locatorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
//                Intent locatorIntent = new Intent(MainActivity.this, Locator.class);
//                startActivity(locatorIntent);
                this.getLocationPermission();

            }


            private void getLocationPermission() {
                requestPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, locationPermissionRequestCode);
                if (ContextCompat.checkSelfPermission(locatorButton.getContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {

                    startActivity(locatorIntent);
                    

                } else {
                    requestPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, locationPermissionRequestCode);

                }
            }
        });



    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        switch (requestCode) {
            case locationPermissionRequestCode:
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {



                } else {

                    Toast.makeText(this,
                            "Unable to show location - permission required", Toast.LENGTH_LONG).show();
                }
                return;
        }
    }


}