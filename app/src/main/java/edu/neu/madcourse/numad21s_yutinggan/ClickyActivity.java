package edu.neu.madcourse.numad21s_yutinggan;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ClickyActivity extends AppCompatActivity {
    private TextView pressTextView;
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonD;
    private Button buttonE;
    private Button buttonF;
    private static String clickyPressedText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pressTextView = findViewById(R.id.PressText);

        buttonA = findViewById(R.id.button_A);
        buttonB = findViewById(R.id.button_B);
        buttonC = findViewById(R.id.button_C);
        buttonD = findViewById(R.id.button_D);
        buttonE = findViewById(R.id.button_E);
        buttonF = findViewById(R.id.button_F);


        buttonA.setOnClickListener(clickyOnClickListener);
        buttonB.setOnClickListener(clickyOnClickListener);
        buttonC.setOnClickListener(clickyOnClickListener);
        buttonD.setOnClickListener(clickyOnClickListener);
        buttonE.setOnClickListener(clickyOnClickListener);
        buttonF.setOnClickListener(clickyOnClickListener);
    }




    private View.OnClickListener clickyOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.button_A:
                    setClickyPressedText("Pressed: A");
                    pressTextView.setText(getClickyPressedText());
                    break;
                case R.id.button_B:
                    setClickyPressedText("Pressed: B");
                    pressTextView.setText(getClickyPressedText());
                    break;
                case R.id.button_C:
                    setClickyPressedText("Pressed: C");
                    pressTextView.setText(getClickyPressedText());
                    break;
                case R.id.button_D:
                    setClickyPressedText("Pressed: D");
                    pressTextView.setText(getClickyPressedText());
                    break;
                case R.id.button_E:
                    setClickyPressedText("Pressed: E");
                    pressTextView.setText(getClickyPressedText());
                    break;
                case R.id.button_F:
                    setClickyPressedText("Pressed: F");
                    pressTextView.setText(getClickyPressedText());
                    break;
            }
        }

    };

    public String getClickyPressedText() {
        return clickyPressedText;
    }

    public void setClickyPressedText(String textToSet) {
        clickyPressedText = textToSet;
    }
}