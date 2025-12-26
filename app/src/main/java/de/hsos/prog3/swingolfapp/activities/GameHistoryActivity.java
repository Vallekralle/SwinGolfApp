package de.hsos.prog3.swingolfapp.activities;

import android.os.Bundle;
import android.widget.Button;

import de.hsos.prog3.swingolfapp.R;

public class GameHistoryActivity extends GolfActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        initButtons();
    }

    private void initButtons() {
        Button gameHistoryBackBtn = findViewById(R.id.gameHistoryBackBtn);

        gameHistoryBackBtn.setOnClickListener(v -> {
            startActivity(GameHistoryActivity.this, MainActivity.class);
        });
    }
}