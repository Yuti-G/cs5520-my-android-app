package edu.neu.madcourse.numad21s_yutinggan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EditActivity extends AppCompatActivity {

    private Button saveURLButton;
    private EditText title;
    private EditText urlText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saveURLButton = findViewById(R.id.saveURLButton);
        urlText = findViewById(R.id.inputText);
        title = findViewById(R.id.titleText);

//        urlText.setText(getIntent().getStringExtra(LinkCollector.KEY_URL_TEXT));
//        title.setText(getIntent().getStringExtra(LinkCollector.KEY_Title_TEXT));


        saveURLButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = urlText.getText().toString();

                Intent intent = new Intent();

                intent.putExtra(LinkCollector.KEY_Title_TEXT, title.getText().toString());
                intent.putExtra(LinkCollector.KEY_URL_TEXT, urlText.getText().toString());
                intent.putExtra(LinkCollector.KEY_ITEM_POSITION, getIntent().getExtras().getInt(LinkCollector.KEY_ITEM_POSITION));

                setResult(RESULT_OK, intent);



                finish();


            }
        });
    }
}