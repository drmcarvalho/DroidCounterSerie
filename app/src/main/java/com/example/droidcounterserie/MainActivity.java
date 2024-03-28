package com.example.droidcounterserie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

import com.example.droidcounterserie.databinding.ActivityMainBinding;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        init();
    }

    @SuppressLint("SetTextI18n")
    private void init() {
        SwitchCompat switchCompatNightMode = findViewById(R.id.switchDarkMode);
        switchCompatNightMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        });
        Button buttonCounter = findViewById(R.id.buttonContar);
        buttonCounter.setOnClickListener(v -> {
            TextView textViewCounter = findViewById(R.id.textViewCounter);
            int counter = Integer.parseInt(textViewCounter.getText().toString());
            if (counter >= 50) {
                Toast toast = Toast.makeText(getBaseContext(), getString(R.string.mensagem_limite_series), Toast.LENGTH_SHORT);
                toast.show();
                return;
            }
            counter += 1;
            textViewCounter.setText(String.format(Integer.toString(counter)));
        });
        Button buttonZerar = findViewById(R.id.buttonZerarSerie);
        buttonZerar.setOnClickListener(v -> {
            TextView textViewCounter = findViewById(R.id.textViewCounter);
            textViewCounter.setText("0");
        });
        Button buttonTimer = findViewById(R.id.buttonDescansar);
        TextView txtTimer = findViewById(R.id.textTimer);
        CountDownTimer contador = new CountDownTimer(45000,1000) {
            public void onTick(long millisUntilFinished) {
                txtTimer.setText(String.format(Integer.toString(Math.round((float) (millisUntilFinished / 1000)))));
            }

            public void onFinish() {
                txtTimer.setText("45");
                buttonTimer.setText(getString(R.string.descansar));
            }
        };
        buttonTimer.setOnClickListener(v -> {
            if (buttonTimer.getText().equals(getString(R.string.parar_timer))) {
                contador.cancel();
                txtTimer.setText("45");
                buttonTimer.setText(getString(R.string.descansar));
            }
            else {
                buttonTimer.setText(getText(R.string.parar_timer));
                txtTimer.setText("");
                contador.start();
            }
        });
    }
}