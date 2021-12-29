package com.example.databasetest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText word_input, main_word_input, translate_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        word_input = findViewById(R.id.word);
        main_word_input = findViewById(R.id.mainWord);
        translate_input = findViewById(R.id.translate);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper = new DBHelper(AddActivity.this);
                dbHelper.addWord(word_input.getText().toString().trim(),
                        main_word_input.getText().toString().trim(),
                        translate_input.getText().toString().trim());
            }
        });
    }
}