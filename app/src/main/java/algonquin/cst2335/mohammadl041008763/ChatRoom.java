package algonquin.cst2335.mohammadl041008763;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class ChatRoom extends AppCompatActivity {
    RecyclerView chatList;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    MyChatAdaptor adt;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatlayout);
        chatList = findViewById(R.id.myrecycler);
        chatList.setAdapter(new MyChatAdaptor());
        Button send = findViewById(R.id.sendbtn);
        Button receive = findViewById(R.id.recbtn);
        EditText edit = findViewById(R.id.editText);
        adt = new MyChatAdaptor();
        chatList.setAdapter(adt);
        chatList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        send.setOnClickListener(clk ->{
            ChatMessage thisMessage = new ChatMessage(edit.getText().toString(),1,new Date());
            messages.add(thisMessage);
            adt.notifyItemInserted(messages.size()-1);
            edit.setText("");
        });
        receive.setOnClickListener(clk ->{
            ChatMessage thisMessage = new ChatMessage(edit.getText().toString(),2,new Date());
            messages.add(thisMessage);
            adt.notifyItemInserted(messages.size()-1);
            edit.setText("");
        });

    }
          private class MyRowViews extends RecyclerView.ViewHolder {
              TextView messageText;
              TextView timeText;
              int position = -1;

              public MyRowViews(View itemView) {
                  super(itemView);
                  itemView.setOnClickListener(e -> {
                      AlertDialog.Builder bu = new AlertDialog.Builder(ChatRoom.this);
                      bu.setMessage("" + messageText.getText())
                              .setTitle("")
                              .setNegativeButton("no", (dlg, clic) -> {
                              })
                              .setPositiveButton("yes", (dlg, clic) -> {
                                  messages.remove(position);
                                  adt.notifyItemRemoved(position);
                                  ChatMessage delete = messages.get(position);
                                  Snackbar.make(messageText, "" + position, Snackbar.LENGTH_LONG)
                                          .setAction("", clk -> {
                                              messages.add(position, delete);
                                              adt.notifyItemInserted(position);
                                          });

                              })
                                .create()
                                .show();
                  });
                  messageText = itemView.findViewById(R.id.message);
                  timeText = itemView.findViewById(R.id.time);

              }

              public void setPosition(int p) {
                  position = p;
              }
          }

          private class MyChatAdaptor extends RecyclerView.Adapter{
              @Override
              public int getItemViewType(int position) {
                  return messages.get(position).getSendOrReceive();
              }

              @Override
              public RecyclerView.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
                  LayoutInflater inflater = getLayoutInflater();
                  int layoutID;
                  if(viewType == 1 )
                      layoutID = R.layout.sent;
                  else
                      layoutID = R.layout.receive;
                  View loadedRow = inflater.inflate(layoutID,parent,false);
                  return new MyRowViews(loadedRow);
              }

              @Override
              public void onBindViewHolder( RecyclerView.ViewHolder holder, int position) {
                  MyRowViews thisRowLayout = (MyRowViews)holder;
                  thisRowLayout.messageText.setText(messages.get(position).getMessage());
                  SimpleDateFormat sdf = new SimpleDateFormat("EEEE,dd-MMM-yyy hh-mm-ss a", Locale.getDefault());
                  String currentDate = sdf.format(messages.get(position).getTimeSent());
                  thisRowLayout.timeText.setText(currentDate);
                  thisRowLayout.setPosition(position);
              }

              @Override
              public int getItemCount() {
                  return messages.size();
              }
          }

    private class ChatMessage
    {
        public String message;
        public int sendOrReceive;
        public Date timeSent;
        public ChatMessage(String message, int sendOrReceive,Date timeSent){
            this.message = message;
            this.sendOrReceive = sendOrReceive;
            this.timeSent = timeSent;
        }
        public CharSequence getMessage(){return message;}
        public int getSendOrReceive(){return sendOrReceive;}
        public Date getTimeSent(){return timeSent;}
    }
}

