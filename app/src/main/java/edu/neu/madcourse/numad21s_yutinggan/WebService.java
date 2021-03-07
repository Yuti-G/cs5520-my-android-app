package edu.neu.madcourse.numad21s_yutinggan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import static edu.neu.madcourse.numad21s_yutinggan.MainActivity.getZipCodePlaceResult;
import static edu.neu.madcourse.numad21s_yutinggan.MainActivity.setZipCodePlaceResult;

public class WebService extends AppCompatActivity {

    private EditText userInput;
    private TextView resultText;
    private Button sendRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

        userInput = (EditText)findViewById(R.id.URL_editText);
        resultText = (TextView)findViewById(R.id.result_textview);
        sendRequest = findViewById(R.id.callWebserviceButtonHandler);

        resultText.setText((CharSequence) getZipCodePlaceResult());


        sendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultText.setText((CharSequence) getZipCodePlaceResult());
                PingWebServiceTask webServiceFetchThread = new PingWebServiceTask();
                new Thread(webServiceFetchThread).start();

            }
        });


    }

    private class PingWebServiceTask  implements Runnable{

        @Override
        public void run() {

            URL url = null;
            try {
                // append the city
                url = new URL("https://api.zippopotam.us/us/ma/" + userInput.getText());

                // establish the connection
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setDoInput(true);

                connection.connect();

                // Parse response from API.
                InputStream inStream = connection.getInputStream();
                final String response = convertStreamToString(inStream);

                JSONObject jsonObj = new JSONObject(response);

                // get array of places
                JSONArray places = jsonObj.getJSONArray("places");

                // get the first zipc ode
                String resultPlace = places.getJSONObject(0).getString("post code");

                // set result to the city's first zip code
                setZipCodePlaceResult(resultPlace);
                resultText.setText(getZipCodePlaceResult());


            } catch (FileNotFoundException e) {
                // This exception is thrown when the city does not exist
                System.out.println("FileNotFoundException");
                e.printStackTrace();
                setZipCodePlaceResult("Please enter a valid city in Massachusetts");
                resultText.setText(getZipCodePlaceResult());
            } catch (MalformedURLException e) {
                System.out.println("MalformedURLException");
                e.printStackTrace();
                setZipCodePlaceResult("Encountered an error while fetching");
                resultText.setText(getZipCodePlaceResult());
            } catch (ProtocolException e) {
                System.out.println("ProtocolException");
                e.printStackTrace();
                setZipCodePlaceResult("Encountered an error while fetching");
                resultText.setText(getZipCodePlaceResult());
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
                setZipCodePlaceResult("Encountered an error while fetching");
                resultText.setText(getZipCodePlaceResult());
            } catch (JSONException e) {
                System.out.println("JSONException");
                e.printStackTrace();
                setZipCodePlaceResult("Encountered an error while fetching");
                resultText.setText(getZipCodePlaceResult());
            }

        }

    }


    /**
     * Helper function
     * @param is
     * @return
     */
    private String convertStreamToString(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next().replace(",", ",\n") : "";
    }
}