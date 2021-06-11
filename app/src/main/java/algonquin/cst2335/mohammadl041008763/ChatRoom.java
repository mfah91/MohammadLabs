package algonquin.cst2335.mohammadl041008763;

import android.os.Bundle;
import android.view.ViewGroup;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class ChatRoom extends AppCompatActivity {
    RecyclerView chatList;
    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView( R.layout.chatlayout );
        chatList= findViewById(R.id.myrecycler);
        chatList.setAdapter(new RecyclerView.Adapter() {

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(  ViewGroup parent, int viewType) {
                return null;
            }

            @Override
            public void onBindViewHolder(  RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public int getItemCount() {
                return 0;
            }
        });
    }
    private class ChatMessage
    {
        String message;
        int sendOrReceive;
        Date timeSent;
    }
}
