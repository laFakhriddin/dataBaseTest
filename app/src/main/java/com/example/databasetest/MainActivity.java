package com.example.databasetest;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add;

    DBHelper dbHelper;
    ArrayList<String> word_id, word_name, word_main_name, word_translation;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DBHelper(MainActivity.this);
        word_id = new ArrayList<>();
        word_name = new ArrayList<>();
        word_main_name = new ArrayList<>();
        word_translation = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(MainActivity.this, word_id, word_name, word_main_name, word_translation);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArrays() {
        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_LONG).show();
        } else {
            while (cursor.moveToNext()) {
                word_id.add(cursor.getString(0));
                word_name.add(cursor.getString(1));
                word_main_name.add(cursor.getString(2));
                word_translation.add(cursor.getString(3));
            }
        }
    }
}