package algonquin.cst2335.mohammadl041008763;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

public class MessageDetailsFragment extends Fragment {

    MessageListFragment.ChatMessage chosenMessage;
    int  chosenPosition;
    public MessageDetailsFragment (MessageListFragment.ChatMessage message , int position){
        chosenMessage = message;
        chosenPosition =position;
    }

    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container,   Bundle savedInstanceState) {

        View detailsView = inflater.inflate(R.layout.details_layout, container, false);
        TextView messageView = detailsView.findViewById(R.id.textView2);
        TextView sendView = detailsView.findViewById(R.id.textView3);
        TextView timeView = detailsView.findViewById(R.id.textView4);
        TextView idView = detailsView.findViewById(R.id.textView5);

        messageView.setText("message is :" + chosenMessage.getMessage());
        sendView.setText("Send or Receive" + chosenMessage.getMessage());
        timeView.setText("Time Send :" + chosenMessage.getMessage());
        idView.setText("Database id is :" + chosenMessage.getMessage());

        Button closeButton = detailsView.findViewById(R.id.button3);
        closeButton.setOnClickListener(e->{
            getParentFragmentManager().beginTransaction().remove(this).commit();

        });

        Button deleteButton = detailsView.findViewById(R.id.button4);
        deleteButton.setOnClickListener(e->{
             ChatRoom parentActivity = (ChatRoom)getContext();
             parentActivity.notifyMessageDeleted(chosenMessage,chosenPosition);
            getParentFragmentManager().beginTransaction().remove(this).commit();


        });





        return detailsView;
    }
}

