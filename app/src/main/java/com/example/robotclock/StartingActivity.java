package com.example.robotclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StartingActivity extends AppCompatActivity {
    static StartingActivity activityA;
    EditText textName;
    Button btnSubmit;
    int num = 0;
    int numCurrent = 12;
    DatabaseReference reff, reffSession;
    long maxid = 0;
    int count = 1;

    public static StartingActivity getInstance() {
        return activityA;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        activityA = this;

        getSupportActionBar().setTitle("Before we begin...");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //maxid = getIntent().getExtras().getLong("com.example.quqicklauncher.maxid");

        reff = FirebaseDatabase.getInstance().getReference().child("clockHand");
        reffSession = FirebaseDatabase.getInstance().getReference().child("Session");

        Button btnPlus = (Button)findViewById(R.id.btnPlus);
        Button btnMinus = (Button) findViewById(R.id.btnMinus);
        Button btnPlusDec = (Button)findViewById(R.id.btnPlusDec);
        Button btnMinusDec = (Button) findViewById(R.id.btnMinusDec);
        btnSubmit = (Button)findViewById(R.id.save);

        textName = (EditText)findViewById(R.id.name);

        textName.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) imm.showSoftInput(textName, InputMethodManager.SHOW_IMPLICIT);

        /*
        if (getIntent().hasExtra("com.example.quqicklauncher.apa2")){
            TextView tv = (TextView) findViewById(R.id.textView);
            String text = getIntent().getExtras().getString("com.example.quqicklauncher.apa2");
            tv.setText(text);
        }
         */

        reffSession.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid = (dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String valueString = dataSnapshot.child("statusCalibrate").getValue().toString();
                count = Integer.parseInt(valueString) + 1;

                if (count > 9)
                    count = 1;

                numCurrent = Integer.parseInt(dataSnapshot.child("clockSpeakNumber").getValue().toString());

                numCurrent = Integer.parseInt(dataSnapshot.child("clockSpeakNumber").getValue().toString());

                TextView textPreciseCurrentNumber = (TextView) findViewById(R.id.textPreciseCurrentNumber);
                num = 0;
                if(numCurrent < 10)
                    textPreciseCurrentNumber.setText("  " + numCurrent + " . " + num);
                else
                    textPreciseCurrentNumber.setText(numCurrent + " . " + num);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnPlusDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textPreciseCurrentNumber = (TextView) findViewById(R.id.textPreciseCurrentNumber);
                if (numCurrent == 12)
                    numCurrent = 1;
                else
                    numCurrent++;

                if(numCurrent < 10)
                    textPreciseCurrentNumber.setText("  " + numCurrent + " . " + num);
                else
                    textPreciseCurrentNumber.setText(numCurrent + " . " + num);
            }
        });

        btnMinusDec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textPreciseCurrentNumber = (TextView) findViewById(R.id.textPreciseCurrentNumber);
                if (numCurrent == 1)
                    numCurrent = 12;
                else numCurrent--;

                if(numCurrent < 10)
                    textPreciseCurrentNumber.setText("  " + numCurrent + " . " + num);
                else
                    textPreciseCurrentNumber.setText(numCurrent + " . " + num);
            }
        });

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textPreciseCurrentNumber = (TextView) findViewById(R.id.textPreciseCurrentNumber);
                if (num == 0){
                    num = 9;
                    if (numCurrent <= 1)
                        numCurrent = 12;
                    else
                        numCurrent--;
                }
                else
                    num--;

                if(numCurrent < 10)
                    textPreciseCurrentNumber.setText("  " + numCurrent + " . " + num);
                else
                    textPreciseCurrentNumber.setText(numCurrent + " . " + num);
            }
        });


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textPreciseCurrentNumber = (TextView) findViewById(R.id.textPreciseCurrentNumber);
                if (num == 9){
                    num = 0;
                    if (numCurrent >= 12)
                        numCurrent = 1;
                    else
                        numCurrent++;
                }
                else
                    num++;

                if(numCurrent < 10)
                    textPreciseCurrentNumber.setText("  " + numCurrent + " . " + num);
                else
                    textPreciseCurrentNumber.setText(numCurrent + " . " + num);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                //String selectedValue = mySpinner.getSelectedItem().toString();
                //int numCurrent = Integer.parseInt(selectedValue);

                reff.child("clockCurrentNumber").setValue(numCurrent);
                reff.child("clockCurrentPrecise").setValue(num);
                reff.child("statusCalibrate").setValue(count++);

                String name = textName.getText().toString().trim();

                /*
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh.mm dd/MM/yyyy");
                String format = simpleDateFormat.format(new Date());

                reffSession.child(String.valueOf(maxid)).child("0").child("Time").setValue(format);
                reffSession.child(String.valueOf(maxid)).child("0").child("Name").setValue(name);
                 */

                Intent startIntent = new Intent(getApplicationContext(), SessionActivity.class);
                startIntent.putExtra("com.example.quqicklauncher.name", name);
                startIntent.putExtra("com.example.quqicklauncher.maxid", maxid);
                startActivity(startIntent);
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
