package com.example.robotclock;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().setTitle("Report");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        new FirebaseDatabaseHelper().readSessions(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Sessions> sessions, List<String> keys) {
                findViewById(R.id.progressBar).setVisibility(View.GONE);
                findViewById(R.id.textViewDisplay1).setVisibility(View.VISIBLE);
                findViewById(R.id.textViewDisplay2).setVisibility(View.VISIBLE);
                findViewById(R.id.textViewDisplay3).setVisibility(View.VISIBLE);
                findViewById(R.id.textViewDisplay4).setVisibility(View.VISIBLE);
                //findViewById(R.id.textViewDisplay5).setVisibility(View.VISIBLE);
                //findViewById(R.id.textViewDisplay6).setVisibility(View.VISIBLE);

                new listViewConfig().setConfig(mRecyclerView, ReportActivity.this, sessions, keys);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataIsDeleted() {

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
