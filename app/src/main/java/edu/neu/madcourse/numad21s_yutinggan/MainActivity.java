package edu.neu.madcourse.numad21s_yutinggan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public Button aboutButton;
    public Button clickyButton;
    public Button locatorButton;
    public Button linkCollectorButton;

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
                Intent locatorIntent = new Intent(MainActivity.this, Locator.class);
                startActivity(locatorIntent);
            }
        });

    }


}