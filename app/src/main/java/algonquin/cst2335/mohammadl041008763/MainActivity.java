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


    /**
     * This fuction contains declartiion for variables
     * setOn click event to check password complexity
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
            if(checkPasswordComplexity(password) == true){
                text.setText("Your password meets the requirements");
                return;
            }else
                text.setText("You shall not pass!");
            return;
        });
    }

    /**
     * this function checks the string
     *
     * @param pw The String object that we are checking
     * @return Returns true if ....
     */

    boolean checkPasswordComplexity(String pw ) {

        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;
        for (int i = 0; i < pw.length(); i++) {
            char c;
            c= pw.charAt(i);
            if (Character.isUpperCase(c)){
                foundUpperCase = true;
            }else if (Character.isLowerCase(c)){
                foundLowerCase =true;
            }else if (Character.isDigit(c)){
                foundNumber = true;
            }else if (isSpecialCharacter(c)){
                foundSpecial = true;
            }
        }
        Context context = getApplicationContext();
        CharSequence text1 = "Missing upper case";
        CharSequence text2 = "Missing lower case";
        CharSequence text3 = "Missing Numbers";
        CharSequence text4 = "Missing special char";

        int duration = Toast.LENGTH_SHORT;

            if (!foundUpperCase) {
                Toast.makeText(context, text1, duration).show();
                return false;
            } else if (!foundLowerCase) {
                Toast.makeText(context, text2, duration).show();

                return false;

            } else if (!foundNumber) {
                Toast.makeText(context, text3, duration).show();

                return false;
            } else if (!foundSpecial) {
                Toast.makeText(context, text4, duration).show();
                return false;
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