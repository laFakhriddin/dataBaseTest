package com.example.databasetest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private final Context context;
    Activity activity;
    private final ArrayList word_id;
    private final ArrayList word_name;
    private final ArrayList word_main_name;
    private final ArrayList word_translation;

    Animation translate_anim;

    CustomAdapter(Activity activity, Context context,
                  ArrayList word_id,
                  ArrayList word_name,
                  ArrayList word_main_name,
                  ArrayList word_translation) {
        this.activity = activity;
        this.context = context;
        this.word_id = word_id;
        this.word_name = word_name;
        this.word_main_name = word_main_name;
        this.word_translation = word_translation;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.word_id_txt.setText(String.valueOf(word_id.get(position)));
        holder.word_name_txt.setText(String.valueOf(word_name.get(position)));
        holder.word_main_name_txt.setText(String.valueOf(word_main_name.get(position)));
        holder.word_translation_txt.setText(String.valueOf(word_translation.get(position)));
        holder.mainLayout.setOnClickListener((view) -> {

            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(word_id.get(position)));
            intent.putExtra("word", String.valueOf(word_name.get(position)));
            intent.putExtra("main_word", String.valueOf(word_main_name.get(position)));
            intent.putExtra("translate", String.valueOf(word_translation.get(position)));
            activity.startActivityForResult(intent, 1);

        });
    }

    @Override
    public int getItemCount() {
        return word_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView word_id_txt, word_name_txt, word_main_name_txt, word_translation_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word_id_txt = itemView.findViewById(R.id.word_id);
            word_name_txt = itemView.findViewById(R.id.word_title);
            word_main_name_txt = itemView.findViewById(R.id.word_main);
            word_translation_txt = itemView.findViewById(R.id.word_translation);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
