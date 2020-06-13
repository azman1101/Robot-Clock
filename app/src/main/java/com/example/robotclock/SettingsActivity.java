package com.example.robotclock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsActivity extends AppCompatActivity {
    DatabaseReference reff;
    int num = 0;
    int numCurrent = 0;
    int status = 0;
    String voiceState = "Female";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportActionBar().setTitle("Settings");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reff = FirebaseDatabase.getInstance().getReference().child("clockHand");

        Button btnPlus = (Button)findViewById(R.id.btnPlus);
        Button btnMinus = (Button) findViewById(R.id.btnMinus);
        Button btnPlusDec = (Button)findViewById(R.id.btnPlusDec);
        Button btnMinusDec = (Button) findViewById(R.id.btnMinusDec);
        Button btnSubmit = (Button)findViewById(R.id.btnSubmitSetting);

        // Toggle button for Female/Male
        final ToggleButton tglBtnVoice = (ToggleButton) findViewById(R.id.tglBtnVoice);

        /*
        // Spinner = dropdown menu
        final Spinner mySpinner = (Spinner) findViewById(R.id.clockValue);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(SettingsActivity.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.clockValueNum));

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);

        mySpinner.setDropDownWidth(100);
        mySpinner.setSelection(11);
        // Spinner = dropdown menu
         */

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                voiceState = dataSnapshot.child("espeakState").getValue().toString();

                if (voiceState.equals("Female"))
                    tglBtnVoice.setChecked(true);
                else
                    tglBtnVoice.setChecked(false);

                String valueString = dataSnapshot.child("statusCalibrate").getValue().toString();
                status = Integer.parseInt(valueString) + 1;

                if(status > 9)
                    status = 1;

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

        tglBtnVoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // The toggle is enabled
                    //Toast.makeText(SettingupActivity.this, "Male", Toast.LENGTH_SHORT).show();
                    voiceState = "Female";

                } else {
                    // The toggle is disabled
                    //Toast.makeText(SettingupActivity.this, "Female", Toast.LENGTH_SHORT).show();
                    voiceState = "Male";
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                EditText editTextCurrentNumber = (EditText) findViewById(R.id.editTextCurrentNumber);

                int numCurrent = 0;
                if (editTextCurrentNumber.equals("")){
                    numCurrent = 0;
                }
                else {
                    numCurrent = Integer.parseInt(editTextCurrentNumber.getText().toString());
                }
                 */

                //String selectedValue = mySpinner.getSelectedItem().toString();
                //int numCurrent = Integer.parseInt(selectedValue);

                reff.child("clockCurrentNumber").setValue(numCurrent);
                reff.child("clockCurrentPrecise").setValue(num);
                reff.child("espeakState").setValue(voiceState);
                reff.child("statusCalibrate").setValue(status++);
                reff.child("clockSpeakNumber").setValue(12);

                Toast.makeText(SettingsActivity.this, "Settings Saved", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selectedValue = mySpinner.getSelectedItem().toString();
                numCurrent = Integer.parseInt(selectedValue);

                TextView textPreciseCurrentNumber = (TextView) findViewById(R.id.textPreciseCurrentNumber);
                textPreciseCurrentNumber.setText(numCurrent + " . " + num);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
         */

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
