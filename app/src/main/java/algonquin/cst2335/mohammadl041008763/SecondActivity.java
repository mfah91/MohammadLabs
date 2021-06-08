package algonquin.cst2335.mohammadl041008763;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent fromPrevious = getIntent();
        String emailAddress = fromPrevious.getStringExtra("EmailAddress");
        TextView text = findViewById(R.id.textView);
          text.setText("Welcome back " + emailAddress);

        Button btn1 = findViewById(R.id.button);
        btn1.setOnClickListener( clk -> {
            Intent call = new Intent(Intent.ACTION_DIAL);
            EditText phoneNumber = findViewById(R.id.editTextPhone);
            call.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
             startActivityForResult( call,5432);
        });
        Button btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(clk -> {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult( cameraIntent, 3456);

        });







    }
}