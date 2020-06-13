package com.example.robotclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    DatabaseReference reffSession, reff;
    long maxid = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().setTitle("");

        Button btnSetting = (Button) findViewById(R.id.btnSetting);
        Button btnTesting = (Button) findViewById(R.id.btnTesting);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        Button btnChat = (Button) findViewById(R.id.btnChat);
        Button btnReport = (Button) findViewById(R.id.btnReport);

        /*
        reff = FirebaseDatabase.getInstance().getReference().child("clockHand");
        reffSession = FirebaseDatabase.getInstance().getReference().child("Session");

        reffSession.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount()) + 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
         */

        /*secondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
                startIntent.putExtra("com.example.quqicklauncher.apa2", "Hello awak");
                startActivity(startIntent);
            }
        });*/

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(startIntent);
            }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), StartingActivity.class);
                startIntent.putExtra("com.example.quqicklauncher.maxid", maxid);
                startActivity(startIntent);
            }
        });

        btnTesting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), TestingActivity.class);
                startActivity(startIntent);
            }
        });

        btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ChatActivity.class);
                startActivity(startIntent);
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), ReportActivity.class);
                startActivity(startIntent);
                //Toast.makeText(MainActivity.this, "Report in development", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
