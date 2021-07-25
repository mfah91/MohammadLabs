package algonquin.cst2335.mohammadl041008763;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.text.CaseMap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Mohammad Abou Haibeh
 * @since  Jun 20
 * @version 1.0
 * */
public class MainActivity extends AppCompatActivity {



    private String stringUrl = null;

    /**
     * This fuction contains declartiion for variables
     * setOn click event to check password complexity
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edit = findViewById(R.id.editText);

         Button btn = findViewById(R.id.button);

            btn.setOnClickListener((clc)-> {

                Executor newThread = Executors.newSingleThreadExecutor();
                newThread.execute(() -> {


                    try {
                        String cityname = edit.getText().toString();
                        stringUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + URLEncoder.encode(cityname, "UTF-8") +
                                "&appid=7e943c97096a9784391a981c4d878b22&Units=Metric";
                        URL url = new URL(stringUrl);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    } catch (IOException e) {
                        Log.e("Connection Error:", e.getMessage());

                    }
                });

            });
    }
}









