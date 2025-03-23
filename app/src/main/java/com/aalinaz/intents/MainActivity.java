package com.aalinaz.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.graphics.Color;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    String date = result.getData().getStringExtra("DATE");
                    TextView dateInfo = findViewById(R.id.dateInfo);
                    dateInfo.setText(getString(R.string.date_template, date));
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameInput = findViewById(R.id.nameInput);
        Button goToSecondActivity = findViewById(R.id.goToSecondActivity);
        Button playMarketButton = findViewById(R.id.playMarketButton);

        goToSecondActivity.setBackgroundColor(Color.parseColor("#608be2"));

        goToSecondActivity.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("NAME", nameInput.getText().toString());
            launcher.launch(intent);
        });

        playMarketButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("https://play.google.com/store"));
            startActivity(intent);
        });
    }
}