package com.example.robotclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {
    EditText editTextChatBox;
    TextView textViewOutput1, textViewOutput2, textViewOutput3, textViewOutput4;
    String text1, text2, text3, text4;
    Button btnSend;
    DatabaseReference reff;
    int numCurrent = 0;
    int countText = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        getSupportActionBar().setTitle("Chat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reff = FirebaseDatabase.getInstance().getReference().child("clockHand");

        btnSend = (Button)findViewById(R.id.btnSend);
        editTextChatBox = (EditText)findViewById(R.id.editTextChatBox);

        textViewOutput1 = (TextView)findViewById(R.id.textViewOutput1);
        textViewOutput2 = (TextView)findViewById(R.id.textViewOutput2);
        textViewOutput3 = (TextView)findViewById(R.id.textViewOutput3);
        textViewOutput4 = (TextView)findViewById(R.id.textViewOutput4);

        textViewOutput1.setVisibility(View.GONE);
        textViewOutput2.setVisibility(View.GONE);
        textViewOutput3.setVisibility(View.GONE);
        textViewOutput4.setVisibility(View.GONE);

        // suggestion button
        Button btnHiThere = (Button) findViewById(R.id.btnHiThere);
        Button btnRepeatAfterMe = (Button) findViewById(R.id.btnRepeatAfterMe);
        Button btnGreatJob = (Button) findViewById(R.id.btnGreatJob);
        Button btnWhatIsYourName = (Button) findViewById(R.id.btnWhatIsYourName);
        Button btnNiceToMeetYou = (Button) findViewById(R.id.btnNiceToMeetYou);

        // auto keyboard
        editTextChatBox.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.showSoftInput(editTextChatBox, InputMethodManager.SHOW_IMPLICIT);

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueString = dataSnapshot.child("statusChat").getValue().toString();
                numCurrent = Integer.parseInt(valueString) + 1;

                if (numCurrent > 9)
                    numCurrent = 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                //trying to get data
                //String dtbs = reff.child("statusCalibrate").getDatabase().toString();

                String text = editTextChatBox.getText().toString().trim();
                editTextChatBox.setText("");
                countText++;

                if (text.equals(""))
                    countText = countText;
                    //textViewOutput1.setVisibility(View.GONE);
                else{
                    switch (countText) {
                        case 1:
                            text1 = text;
                            textViewOutput1.setVisibility(View.VISIBLE);
                            textViewOutput1.setText(text1);
                            break;
                        case 2:
                            text2 = text1;
                            text1 = text;
                            textViewOutput2.setVisibility(View.VISIBLE);
                            textViewOutput2.setText(text2);
                            textViewOutput1.setText(text1);
                            break;
                        case 3:
                            text3 = text2;
                            text2 = text1;
                            text1 = text;
                            textViewOutput3.setVisibility(View.VISIBLE);
                            textViewOutput3.setText(text3);
                            textViewOutput2.setText(text2);
                            textViewOutput1.setText(text1);
                            break;
                        case 4:
                            text4 = text3;
                            text3 = text2;
                            text2 = text1;
                            text1 = text;
                            textViewOutput4.setVisibility(View.VISIBLE);
                            textViewOutput4.setText(text4);
                            textViewOutput3.setText(text3);
                            textViewOutput2.setText(text2);
                            textViewOutput1.setText(text1);
                            break;
                        default:
                            text4 = text3;
                            text3 = text2;
                            text2 = text1;
                            text1 = text;
                            textViewOutput4.setText(text4);
                            textViewOutput3.setText(text3);
                            textViewOutput2.setText(text2);
                            textViewOutput1.setText(text1);
                            break;
                    }
                }

                reff.child("statusChat").setValue(numCurrent++);
                reff.child("chatText").setValue(text);
            }
        });

        btnHiThere.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String text = editTextChatBox.getText().toString().trim();

                editTextChatBox.setText("Hi there");
            }
        });

        btnRepeatAfterMe.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String text = editTextChatBox.getText().toString().trim();

                editTextChatBox.setText("Repeat after me");
            }
        });

        btnGreatJob.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String text = editTextChatBox.getText().toString().trim();

                editTextChatBox.setText("Great job");
            }
        });

        btnWhatIsYourName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String text = editTextChatBox.getText().toString().trim();

                editTextChatBox.setText("What is your name?");
            }
        });

        btnNiceToMeetYou.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                String text = editTextChatBox.getText().toString().trim();

                editTextChatBox.setText("Nice to meet you");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
