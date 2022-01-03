package com.example.databasetest;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    EditText word_input, main_word_input, translate_input;
    Button update_button, delete_button;

    String id, word, mainWord, translate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        word_input = findViewById(R.id.word1);
        main_word_input = findViewById(R.id.mainWord1);
        translate_input = findViewById(R.id.translate1);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentDate();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(word);
        }

        update_button.setOnClickListener(v -> {
            DBHelper myDB = new DBHelper(UpdateActivity.this);
            word = word_input.getText().toString().trim();
            mainWord = main_word_input.getText().toString().trim();
            translate = translate_input.getText().toString().trim();
            myDB.updateData(id, word, mainWord, translate);
        });
        delete_button.setOnClickListener(v -> confirmDialog());
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

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + word + " ?");
        builder.setMessage("Are you sure you want to delete " + word + " ?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            DBHelper myDB = new DBHelper(UpdateActivity.this);
            myDB.deleteOneRow(id);
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {

        });
        builder.create().show();
    }
}