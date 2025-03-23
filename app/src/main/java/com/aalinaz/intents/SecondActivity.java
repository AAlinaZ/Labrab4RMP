package com.aalinaz.intents;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.graphics.Color;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;
import java.util.Random;

public class SecondActivity extends AppCompatActivity {
    private final String[] motivationalPhrases = {
            "Никогда не сдавайтесь!",
            "Вы можете больше, чем думаете!",
            "Каждый день — это новый шанс!",
            "Мечты сбываются, если верить!",
            "Вы сильнее, чем кажетесь!",
            "Успех начинается с первого шага!",
            "Вы уникальны, и это ваша сила!",
            "Верьте в себя, и всё получится!",
            "Вы ближе к цели, чем вчера!",
            "Не бойтесь ошибаться, бойтесь бездействовать!",
            "Вы созданы для великих дел!",
            "Ваш потенциал безграничен!",
            "Сделайте сегодня то, что другие не хотят, завтра будете жить так, как другие не могут!",
            "Вы — автор своей жизни!",
            "Каждая проблема — это возможность!",
            "Вы достойны успеха!",
            "Идите вперед, несмотря ни на что!",
            "Вы можете изменить мир!",
            "Вы — это то, во что вы верите!",
            "Вы уже на пути к успеху!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView motivationalPhrase = findViewById(R.id.motivationalPhrase);
        Button selectDateButton = findViewById(R.id.selectDateButton);
        Button okButton = findViewById(R.id.okButton);

        selectDateButton.setBackgroundColor(Color.parseColor("#608be2"));
        okButton.setBackgroundColor(Color.parseColor("#608be2"));

        String name = getIntent().getStringExtra("NAME");
        String randomPhrase = motivationalPhrases[new Random().nextInt(motivationalPhrases.length)];
        motivationalPhrase.setText(getString(R.string.motivational_phrase_template, name, randomPhrase));

        selectDateButton.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(
                    this,
                    (view, year, month, day) -> okButton.setTag(day + "-" + (month + 1) + "-" + year),
                    c.get(Calendar.YEAR),
                    c.get(Calendar.MONTH),
                    c.get(Calendar.DAY_OF_MONTH)
            ).show();
        });

        okButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("DATE", okButton.getTag().toString());
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}