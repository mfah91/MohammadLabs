package algonquin.cst2335.mohammadl041008763;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "MainActivity";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.loginid);
        Log.w(TAG, "In onCreate() - Loading Widgets" );
        loginButton.setOnClickListener(  clk -> {

            EditText emailEditText = findViewById(R.id.edit);
            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
            nextPage.putExtra( "EmailAddress", emailEditText.getText().toString() );
            startActivity(nextPage);
         });
        SharedPreferences prefs = getSharedPreferences("data", Context.MODE_PRIVATE);
        loginButton.setOnClickListener(clk ->{
            EditText emailEditText = findViewById(R.id.edit);
            Intent nextPage = new Intent(MainActivity.this, SecondActivity.class);
            nextPage.putExtra( "EmailAddress", emailEditText.getText().toString());
            SharedPreferences.Editor  editor = prefs.edit();
            editor.putString("email", emailEditText.getText().toString());
            editor.apply();
            startActivity( nextPage );
        });



    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent fromNextPage = data;
         if(requestCode == 3456)
        {
             if(resultCode == RESULT_OK) {
                Bitmap thumbnail = data.getParcelableExtra("data");
                 ImageView profilefileImage = findViewById(R.id.imageView);
                 profilefileImage.setImageBitmap(thumbnail);


            }
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.w(TAG, "In onStart() - The application is now visible on screen" );


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "In onDestroy() - The application is now responding to user input" );


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(TAG, "onStop() - The application is no longer visible" );


    }

    @Override
    public File getFilesDir() {
        return super.getFilesDir();

    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.w(TAG, "onPause() - The application no longer responds to user input");


    }
}