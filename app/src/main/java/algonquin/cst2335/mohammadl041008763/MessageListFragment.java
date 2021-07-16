package algonquin.cst2335.mohammadl041008763;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MessageListFragment extends Fragment {
    RecyclerView chatList;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    MyChatAdaptor adt;
    SQLiteDatabase db;
    Button send;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View chatLayout = inflater.inflate(R.layout.chatlayout, container, false);
 send = chatLayout.findViewById(R.id.sendbtn);

        chatList = chatLayout.findViewById(R.id.myrecycler);
        chatList.setAdapter(new MyChatAdaptor());
        Button send = chatLayout.findViewById(R.id.sendbtn);
        Button receive = chatLayout.findViewById(R.id.recbtn);
        EditText edit = chatLayout.findViewById(R.id.editText);
        MyOpenHelper opener = new MyOpenHelper( getContext());
        db = opener.getWritableDatabase();
        Cursor results = db.rawQuery("Select * FROM " + MyOpenHelper.TABLE_NAME+ ";" , null);
        int _idCol = results.getColumnIndex("_id");
        int messageCol = results.getColumnIndex(MyOpenHelper.col_message);
        int sendCol = results.getColumnIndex(MyOpenHelper.col_send_receive);
        int timeCol = results.getColumnIndex(MyOpenHelper.col_time_sent);

       while(results.moveToNext()) {
           long id = results.getInt(_idCol);
           String message = results.getString(messageCol);
           String times = results.getString(timeCol);
           int sendOrRecive = results.getInt(sendCol);
           messages.add(new ChatMessage(message, sendOrRecive, times, id));
       }
        adt = new MyChatAdaptor();
        chatList.setAdapter(adt);
        LinearLayoutManager layoutManager =  new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        chatList.setLayoutManager(layoutManager);
        send.setOnClickListener(e ->{
            SimpleDateFormat sdf = new SimpleDateFormat("EE, dd-MMM-yyyy hh-mm-ss a", Locale.getDefault());
            String time = sdf.format(new Date());
            ChatMessage thisMessage = new ChatMessage(edit.getText().toString(),1, time);
            ContentValues newRow = new ContentValues();
            newRow.put(MyOpenHelper.col_message, thisMessage.getMessage());
            newRow.put(MyOpenHelper.col_send_receive, thisMessage.getSendOrReceive());
            newRow.put(MyOpenHelper.col_time_sent, thisMessage.getTimeSent());
            long newId = db.insert(MyOpenHelper.TABLE_NAME, MyOpenHelper.col_message, newRow);
            thisMessage.setId(newId);
            messages.add(thisMessage);
            edit.setText("");
            adt.notifyItemInserted(messages.size()-1);

        });

      receive.setOnClickListener(e ->{
            SimpleDateFormat sdf = new SimpleDateFormat("EE, dd-MMM-yyyy hh-mm-ss a", Locale.getDefault());
            String time = sdf.format(new Date());
            ChatMessage thisMessage = new ChatMessage(edit.getText().toString(),2, time);
            ContentValues newRow = new ContentValues();
            newRow.put(MyOpenHelper.col_message, thisMessage.getMessage());
            newRow.put(MyOpenHelper.col_send_receive, thisMessage.getSendOrReceive());
            newRow.put(MyOpenHelper.col_time_sent, thisMessage.getTimeSent());
           long newId = db.insert(MyOpenHelper.TABLE_NAME, MyOpenHelper.col_message, newRow);
            thisMessage.setId(newId);
            messages.add(thisMessage);
            edit.setText("");
            adt.notifyItemInserted(messages.size()-1);
      });
        return chatLayout;

    }

    public void notifyMessageDeleted(ChatMessage chosenMessage, int chosenPosition) {
        AlertDialog.Builder bu = new AlertDialog.Builder(getContext());
        bu.setMessage("Do you want Delete  " + chosenMessage.getMessage() +" ??")
                .setTitle("Question:")
                .setNegativeButton("no", (dlg, clic) -> {})
                .setPositiveButton("yes", (dlg, clic) -> {
                    ChatMessage delete = messages.get(chosenPosition);
                    messages.remove(chosenPosition);
                    adt.notifyItemRemoved(chosenPosition);
                    db.delete(MyOpenHelper.TABLE_NAME , "_id=?", new String[]{Long.toString(delete.getId()) });
                    Snackbar.make(send, "You deleted message  " + chosenPosition, Snackbar.LENGTH_LONG)
                            .setAction("Undo", clk -> {
                                messages.add(chosenPosition, delete);
                                              db.execSQL("Insert into " + MyOpenHelper.TABLE_NAME + " Values('"
                                          + delete.getId() +
                                                              "','" + delete.getMessage() +
                                                              "','" + delete.getSendOrReceive() +
                                                              "','" + delete.getTimeSent() + "')");
                                              adt.notifyItemInserted(chosenPosition);
                            }).show() ;

                }) .show();
    }


    private class MyRowViews extends RecyclerView.ViewHolder {
              TextView messageText;
              TextView timeText;
              int position = -1;

              public MyRowViews(View itemView) {

                  super(itemView);
                  itemView.setOnClickListener(e -> {
                      ChatRoom parentActivity = (ChatRoom)getContext();
                      int position = getAbsoluteAdapterPosition();
                      parentActivity.userClickedMessage(messages.get(position),position);
                  });
                  messageText = itemView.findViewById(R.id.message);
                  timeText = itemView.findViewById(R.id.time);

              }

              public void setPosition(int p) {
                  position = p;
              }
          }

          class MyChatAdaptor extends RecyclerView.Adapter{
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

                  thisRowLayout.setPosition(position);
              }

              @Override
              public int getItemCount() {
                  return messages.size();
              }
          }

    class ChatMessage
    {
        public String message;
        public int sendOrReceive;
        public String timeSent;
        long id;
        public ChatMessage(String message, int sendOrReceive, String timeSent){
            this.message = message;
            this.sendOrReceive = sendOrReceive;
            this.timeSent = timeSent;

        }

        public ChatMessage(String message, int sendOrReceive, String timeSent, long id){
            this.message = message;
            this.sendOrReceive = sendOrReceive;
            this.timeSent = timeSent;
            setId(id);

        }
        public String getMessage(){return message;}
        public int getSendOrReceive(){return sendOrReceive;}
        public String  getTimeSent(){return timeSent;}
        public void setId(long l){id=l;}
        public long getId(){return id;}
    }





    }

