package algonquin.cst2335.mohammadl041008763;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView mytextview = findViewById(R.id.mytextview);
        Button mybutton = findViewById(R.id.button);
        EditText myedit = findViewById(R.id.myedit);
        CheckBox mycheck = findViewById(R.id.cbox);
        Switch myswitch = findViewById(R.id.myswitch);
        Switch myswitch2 = findViewById(R.id.myswitch2);
        RadioButton option1 = findViewById(R.id.option1);
        RadioButton option2 = findViewById(R.id.option2);


        mybutton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String editString = myedit.getText().toString();
                mybutton.setOnClickListener((vw) -> {
                    mybutton.setText(" your edit text has " + editString);
                });
            }
        });

        mycheck.setOnCheckedChangeListener((btn, isChecked) -> {
            Context mycheckbox = getApplicationContext();
            CharSequence text = "You clicked on the " + "Checkbox" + " and it is now: " + isChecked;
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(mycheckbox, text, duration);
            toast.show();
        });
        myswitch.setOnCheckedChangeListener((btn, isChecked) -> {
            Context myswitchtext = getApplicationContext();
            CharSequence text = "You clicked on the " + "Checkbox" + " and it is now: " + isChecked;
            int duration_2 = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(myswitchtext, text, duration_2);
            toast.show();
        });
        myswitch2.setOnCheckedChangeListener((btn, isChecked) -> {
            Context myswitchtext2 = getApplicationContext();
            CharSequence text = "You clicked on the " + "Checkbox" + " and it is now: " + isChecked;
            int duration_3 = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(myswitchtext2, text, duration_3);
            toast.show();
        });
        option1.setOnCheckedChangeListener((btn, isChecked) -> {
            Context myoptiontext = getApplicationContext();
            CharSequence text = "You clicked on the " + "Checkbox" + " and it is now: " + isChecked;
            int duration_4 = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(myoptiontext, text, duration_4);
            toast.show();
        });
        option2.setOnCheckedChangeListener((btn, isChecked) -> {
            Context myoptiontext2 = getApplicationContext();
            CharSequence text = "You clicked on the " + "Checkbox" + " and it is now: " + isChecked;
            int duration_5 = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(myoptiontext2, text, duration_5);
            toast.show();
        });
        ImageView myimage = findViewById(R.id.picture);
        ImageButton imhbtn = findViewById(R.id.imgbtn);

        imhbtn.setOnClickListener((btn) -> {
            Context imhbtn2 = getApplicationContext();
             CharSequence text = "The width = " + imhbtn.getWidth() + " and height = " + imhbtn.getHeight() ;
            int duration_6 = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(imhbtn2, text, duration_6);
            toast.show();
        });




    }

}