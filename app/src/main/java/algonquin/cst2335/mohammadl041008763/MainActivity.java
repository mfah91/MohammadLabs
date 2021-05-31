package algonquin.cst2335.mohammadl041008763;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
         Button loginButton = findViewById(R.id.loginid);
        loginButton.setOnClickListener(  clk -> { Intent nextPage = new Intent( MainActivity.this, SecondActivity.class);  } );

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
    }
}