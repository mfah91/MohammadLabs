package algonquin.cst2335.mohammadl041008763;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

/*
* @author Mohammad Abou Haibeh
* @version 1.0.0
*  */
public class MainActivity extends AppCompatActivity {
     TextView text  ;
     EditText edit ;
      Button btn  ;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.textView);
        edit = findViewById(R.id.editText);
        btn = findViewById(R.id.button);

        btn.setOnClickListener(clck -> {
            String password = edit.getText().toString();
            checkPasswordComplexity(password);
        });
    }

    /**
     * this function checks the string
     *
     * @param pw The String object that we are checking
     * @return Returns true if ....
     */

    boolean checkPasswordComplexity(String pw ) {
         char c;
         c = pw.charAt(0) ;


        boolean upperCase = Character.isUpperCase(c);
        boolean lowerCase = Character.isLowerCase(c);
        boolean dijitcase = Character.isDigit(c);
        boolean specialcase = isSpecialCharacter(c);

        Context context = getApplicationContext();
        CharSequence text1 = "Missing upper case";
        CharSequence text2 = "Missing lower case";
        CharSequence text3 = "Missing Numbers";
        CharSequence text4 = "Missing special char";

        int duration = Toast.LENGTH_SHORT;


        for (int i = 0; i < pw.length(); i++) {



            if (!upperCase   ) {
                Toast.makeText(context, text1, duration).show();
                return false;
            } else if (!lowerCase) {
                Toast.makeText(context, text2, duration).show();

                return false;

            } else if (!dijitcase) {
                Toast.makeText(context, text3, duration).show();

                return false;
            } else if (!specialcase) {
                Toast.makeText(context, text4, duration).show();
                return false;
            }
           

         }

        return true;


    }

    /**
     * this fuction will check symbols and return special character
     *
     * @param c
     * @return
     */
    boolean isSpecialCharacter(char c) {
        switch (c) {
            case '#':
            case '?':
            case '*':
            case '$':
            case '%':
            case '^':
            case '&':
            case '!':
            case '@':
                return true;
            default:
                return false;
        }
    }

}