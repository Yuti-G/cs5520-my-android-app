package edu.neu.madcourse.numad21s_yutinggan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView statusText = findViewById(R.id.statusText);
                statusText.setText("Name: Yuting Gan \nEmail: gan.yut@northeastern.edu");
            }
        });
    }


}