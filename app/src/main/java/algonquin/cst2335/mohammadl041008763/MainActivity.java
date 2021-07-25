package algonquin.cst2335.mohammadl041008763;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.text.CaseMap;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
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
    /** This is holds the text at center of screen*/
    private TextView text = null ;
    /** This is holds the twist message at the buttom of the screen*/
    private EditText edit = null ;
    /** This is holds the Button*/
    private    Button btn  = null;

    /**This is holds the URL**/
    private String stringUrl = null;

    /**
     * This fuction contains declartiion for variables
     * setOn click event to check password complexity
     *
     * @param savedInstanceState
     */
    Bitmap image = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       edit = findViewById(R.id.editText);

         btn = findViewById(R.id.button);

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
                        String text = (new BufferedReader(
                                new InputStreamReader(in, StandardCharsets.UTF_8)))
                                .lines()
                                .collect(Collectors.joining("\n"));
//                                .collect(Collectors.joining("\n"));
                        JSONObject theDocument = new JSONObject(text); //this converts the String to JSON Object.
                        JSONObject coord = theDocument.getJSONObject("coord");
                        JSONArray weatherArray = theDocument.getJSONArray("weather");
                        JSONObject position0 = weatherArray.getJSONObject(0);
                        String descripition = position0.getString("descripition");
                        String iconName = position0.getString("icon");
                        File file = new File(getFilesDir().getPath());

                        if (file.exists()) {
                            image = BitmapFactory.decodeFile(file.getPath());
                        } else {

                            URL imgUrl = new URL("https://openweathermap.org/img/w/" + iconName + ".png");
                            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                            connection.connect();
                            int responseCode = connection.getResponseCode();
                            if (responseCode == 200) {
                                image = BitmapFactory.decodeStream(connection.getInputStream());
                                image.compress(Bitmap.CompressFormat.PNG, 100, openFileOutput(iconName + ".png", Activity.MODE_PRIVATE));
                            }

                        }

                        JSONObject mainObject = theDocument.getJSONObject("main");
                        double current = mainObject.getDouble("temp");
                        double min = mainObject.getDouble("temp_min");
                        double max = mainObject.getDouble("temp_max");
                        int humitidy = mainObject.getInt("humidity");
                        String desc = mainObject.getString("Descripition");
                        runOnUiThread( (  )  ->{
                            TextView tv = findViewById(R.id.tempText);
                        tv.setText("The current temperature is " + current);
                        tv.setVisibility(View.VISIBLE);
                        tv = findViewById(R.id.MinText);
                        tv.setText("The current min temperature is " + min);
                        tv.setVisibility(View.VISIBLE);
                        tv = findViewById(R.id.MaxText);
                        tv.setText("The current max temperature is " + max);
                        tv.setVisibility(View.VISIBLE);
                        tv = findViewById(R.id.humText);
                        tv.setText("The current humidity is " + humitidy);
                        tv.setVisibility(View.VISIBLE);
                        ImageView tp = findViewById(R.id.icon);
                        tp.setImageBitmap(image);
                        tp.setVisibility(View.VISIBLE);
                        tv = findViewById(R.id.DesText);
                        tv.setText("The description " + descripition);
                        tv.setVisibility(View.VISIBLE);

                    });

                    } catch (IOException | JSONException e) {
                        Log.e("Connection Error:", e.getMessage());

                    }
                });

            });
    }
}









