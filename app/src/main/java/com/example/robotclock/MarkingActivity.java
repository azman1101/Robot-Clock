package com.example.robotclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MarkingActivity extends AppCompatActivity {
    DatabaseReference reffSession, reffReports;
    String statusSpeak;
    String clock;
    long maxid = 1;
    int correct = 0;
    int incorrect = 0;
    int noRespond = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marking);

        Button btnCorrect = (Button) findViewById(R.id.btnCorrect);
        Button btnIncorrect = (Button) findViewById(R.id.btnIncorrect);
        Button btnNoRespond = (Button) findViewById(R.id.btnNoRespond);

        reffSession = FirebaseDatabase.getInstance().getReference().child("Session");
        reffReports = FirebaseDatabase.getInstance().getReference().child("reports");

        statusSpeak = Integer.toString(getIntent().getExtras().getInt("com.example.quqicklauncher.statusSpeak"));
        clock = getIntent().getExtras().getString("com.example.quqicklauncher.clock");
        maxid = getIntent().getExtras().getLong("com.example.quqicklauncher.maxid");


        reffReports.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if ((dataSnapshot.child(String.valueOf(maxid)).child("correct").exists()))
                    correct = Integer.parseInt(dataSnapshot.child(String.valueOf(maxid)).child("correct").getValue().toString());
                else
                    correct = 0;
                if ((dataSnapshot.child(String.valueOf(maxid)).child("incorrect").exists()))
                    incorrect = Integer.parseInt(dataSnapshot.child(String.valueOf(maxid)).child("incorrect").getValue().toString());
                else
                    incorrect = 0;
                if ((dataSnapshot.child(String.valueOf(maxid)).child("noRespond").exists()))
                    noRespond = Integer.parseInt(dataSnapshot.child(String.valueOf(maxid)).child("noRespond").getValue().toString());
                else
                    noRespond = 0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnCorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reffSession.child(String.valueOf(maxid)).child(statusSpeak).child("clockNum").setValue(clock);
                reffSession.child(String.valueOf(maxid)).child(statusSpeak).child("answerValue").setValue("Correct");

                correct++;

                reffReports.child(String.valueOf(maxid)).child("correct").setValue(correct);
                reffReports.child(String.valueOf(maxid)).child("incorrect").setValue(incorrect);
                reffReports.child(String.valueOf(maxid)).child("noRespond").setValue(noRespond);

                finish();
            }
        });


        btnIncorrect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reffSession.child(String.valueOf(maxid)).child(statusSpeak).child("clockNum").setValue(clock);
                reffSession.child(String.valueOf(maxid)).child(statusSpeak).child("answerValue").setValue("Incorrect");

                incorrect++;

                reffReports.child(String.valueOf(maxid)).child("correct").setValue(correct);
                reffReports.child(String.valueOf(maxid)).child("incorrect").setValue(incorrect);
                reffReports.child(String.valueOf(maxid)).child("noRespond").setValue(noRespond);

                finish();
            }
        });

        btnNoRespond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reffSession.child(String.valueOf(maxid)).child(statusSpeak).child("clockNum").setValue(clock);
                reffSession.child(String.valueOf(maxid)).child(statusSpeak).child("answerValue").setValue("No Respond");

                noRespond++;

                reffReports.child(String.valueOf(maxid)).child("correct").setValue(correct);
                reffReports.child(String.valueOf(maxid)).child("incorrect").setValue(incorrect);
                reffReports.child(String.valueOf(maxid)).child("noRespond").setValue(noRespond);

                finish();
                /*
                Intent startIntent = new Intent(getApplicationContext(), SessionActivity.class);
                startIntent.putExtra("com.example.quqicklauncher.statusSpeak", statusSpeak);
                startIntent.putExtra("com.example.quqicklauncher.numCurrent", clock);
                startIntent.putExtra("com.example.quqicklauncher.maxid", maxid);
                startActivity(startIntent);
                 */
            }
        });
    }
}
