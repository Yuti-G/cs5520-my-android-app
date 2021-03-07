package edu.neu.madcourse.numad21s_yutinggan;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {

    public Button aboutButton;
    public Button clickyButton;
    public Button locatorButton;
    public Button linkCollectorButton;
    public Button sendButton;
    private final int locationPermissionRequestCode = 1;
    private static String zipCodePlaceResult = "";


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


        locatorButton = findViewById(R.id.button_locator);
        locatorButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                this.getLocationPermission();

            }
            private void getLocationPermission() {
                requestPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, locationPermissionRequestCode);
                if (ContextCompat.checkSelfPermission(locatorButton.getContext(),
                        android.Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    Intent locatorIntent = new Intent(MainActivity.this, Locator.class);
                    startActivity(locatorIntent);

                } else {
                    requestPermission(android.Manifest.permission.ACCESS_FINE_LOCATION, locationPermissionRequestCode);
                }
            }
        });

        sendButton = findViewById(R.id.btn_send);
        sendButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent webService = new Intent(MainActivity.this, WebService.class);
                startActivity(webService);
            }
        });


    }

    public static String getZipCodePlaceResult() {
        return zipCodePlaceResult;
    }

    public static void setZipCodePlaceResult(String textToSet) {
        zipCodePlaceResult = textToSet;
    }


}