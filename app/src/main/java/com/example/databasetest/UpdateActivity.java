package com.example.databasetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText word_input, main_word_input, translate_input;
    Button update_button;

    String id, word, mainWord, translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        word_input = findViewById(R.id.word1);
        main_word_input = findViewById(R.id.mainWord1);
        translate_input = findViewById(R.id.translate1);
        update_button = findViewById(R.id.update_button);

        getAndSetIntentDate();

        update_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DBHelper myDB = new DBHelper(UpdateActivity.this);
                myDB.updateData(id, word, mainWord, translate);
            }
        });

    }

    void getAndSetIntentDate() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("word") &&
                getIntent().hasExtra("main_word") && getIntent().hasExtra("translate")) {
            id = getIntent().getStringExtra("id");
            word = getIntent().getStringExtra("word");
            mainWord = getIntent().getStringExtra("main_word");
            translate = getIntent().getStringExtra("translate");

            word_input.setText(word);
            main_word_input.setText(mainWord);
            translate_input.setText(translate);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}