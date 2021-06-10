package algonquin.cst2335.mohammadl041008763;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    SharedPreferences prefs = getSharedPreferences("MyData", Context.MODE_PRIVATE);
    EditText phoneNumber = findViewById(R.id.editTextPhone);
    String emailAddress = prefs.getString("Phone", "");
    phoneNumber.setText(emailAddress);
    TextView topOfScreen = findViewById(R.id.textView);
    Intent fromPrevious = getIntent();
    String text = fromPrevious.getStringExtra("SomeInfo");
    topOfScreen.setText("Welcome: " + text);
    Button btn1 = findViewById(R.id.button);

    btn1.setOnClickListener( clk -> {
        Intent call = new Intent(Intent.ACTION_DIAL);
         SharedPreferences.Editor  editor = prefs.edit();
        editor.putString("Phone", phoneNumber.getText().toString());
        editor.apply();
        call.setData(Uri.parse("tel:" + phoneNumber.getText().toString()));
        startActivity(call);
    });

    Button btn2 = findViewById(R.id.button2);
    btn2.setOnClickListener( clk -> {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult( cameraIntent, 5432);
        String path = getFilesDir().getPath();
        File PicFile = new File(path +  "/Picture.png");
        ImageView Pic = findViewById(R.id.imageView);
        if(PicFile.exists())
        {
            Bitmap Img = BitmapFactory.decodeFile(path+"/Picture.png");
             Pic.setImageBitmap( Img );
        }
    });
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView Pic = findViewById(R.id.imageView);

        if(requestCode == 5432) {
            if(resultCode == RESULT_OK) {
                Bitmap thumbnail =data.getParcelableExtra("data");
                Pic.setImageBitmap(thumbnail);
                FileOutputStream fOut = null;
                try {
                    fOut = openFileOutput( "Picture.png", Context.MODE_PRIVATE);
                    thumbnail.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


