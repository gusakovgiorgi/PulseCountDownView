package com.gusakov.pulsecountdownview;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gusakov.library.PulseCountDown;

public class JavaActivity extends AppCompatActivity {

    private Button startBtn;
    private PulseCountDown pulseCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        startBtn = findViewById(R.id.startBtn);
        pulseCountDown = findViewById(R.id.pulseCountDown);

        startBtn.setOnClickListener(v -> pulseCountDown.start(() -> {
            Toast.makeText(JavaActivity.this, "Bammmm!!!", Toast.LENGTH_LONG).show();
        }));
    }
}
