package com.example.robotclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestingActivity extends AppCompatActivity {
    int numCurrent = 12;
    int status = 0;
    int statusDance = 0;
    TextView textViewCurrentNumber;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        getSupportActionBar().setTitle("Quick Session");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Button btnClock1 = (Button) findViewById(R.id.btnClock1);
        Button btnClock2 = (Button) findViewById(R.id.btnClock2);
        Button btnClock3 = (Button) findViewById(R.id.btnClock3);
        Button btnClock4 = (Button) findViewById(R.id.btnClock4);
        Button btnClock5 = (Button) findViewById(R.id.btnClock5);
        Button btnClock6 = (Button) findViewById(R.id.btnClock6);
        Button btnClock7 = (Button) findViewById(R.id.btnClock7);
        Button btnClock8 = (Button) findViewById(R.id.btnClock8);
        Button btnClock9 = (Button) findViewById(R.id.btnClock9);
        Button btnClock10 = (Button) findViewById(R.id.btnClock10);
        Button btnClock11 = (Button) findViewById(R.id.btnClock11);
        Button btnClock12 = (Button) findViewById(R.id.btnClock12);

        Button btnSubmit = (Button) findViewById(R.id.btnSubmitTestClock);
        Button btnKawad = (Button) findViewById(R.id.btnMove);
        Button btnChat = (Button) findViewById(R.id.btnChat);
        textViewCurrentNumber = (TextView) findViewById(R.id.textViewCurrentNumber);
        reff = FirebaseDatabase.getInstance().getReference().child("clockHand");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueString = dataSnapshot.child("statusSpeak").getValue().toString();
                status = Integer.parseInt(valueString) + 1;
                valueString = dataSnapshot.child("statusDance").getValue().toString();
                statusDance = Integer.parseInt(valueString) + 1;

                if (status > 9)
                    status = 2;
                if (statusDance > 9)
                    statusDance = 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh.mm dd/MM/yyyy");
                String format = simpleDateFormat.format(new Date());

                reff.child("clockSpeakNumber").setValue(numCurrent);
                reff.child("statusSpeak").setValue(status++);
                reff.child("Timestamp").setValue(format);

                Toast.makeText(TestingActivity.this, "Clock number submitted", Toast.LENGTH_SHORT).show();
            }
        });

        btnKawad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff.child("statusDance").setValue(statusDance++);
                Toast.makeText(TestingActivity.this, "Robot is Moving", Toast.LENGTH_SHORT).show();
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //reff.child("statusChat").setValue(++statusDance);
                Intent startIntent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(startIntent);
            }
        });


        btnClock1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 1;
                textViewCurrentNumber.setText("1");
                //btnClock1.setBackgroundColor(Color.RED);
            }
        });

        btnClock2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 2;
                textViewCurrentNumber.setText("2");
                btnClock1.setBackgroundColor(5);
            }
        });

        btnClock3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 3;
                textViewCurrentNumber.setText("3");
            }
        });

        btnClock4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 4;
                textViewCurrentNumber.setText("4");
            }
        });

        btnClock5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 5;
                textViewCurrentNumber.setText("5");
            }
        });

        btnClock6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 6;
                textViewCurrentNumber.setText("6");
            }
        });

        btnClock7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 7;
                textViewCurrentNumber.setText("7");
            }
        });

        btnClock8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 8;
                textViewCurrentNumber.setText("8");
            }
        });

        btnClock9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 9;
                textViewCurrentNumber.setText("9");
            }
        });

        btnClock10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 10;
                textViewCurrentNumber.setText("10");
            }
        });

        btnClock11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 11;
                textViewCurrentNumber.setText("11");
            }
        });

        btnClock12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numCurrent = 12;
                textViewCurrentNumber.setText("12");
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
