package de.hsos.prog3.swingolfapp.activities;

import android.os.Bundle;
import android.widget.Button;

import de.hsos.prog3.swingolfapp.R;

public class MainActivity extends GolfActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
    }

    private void initButtons() {
        Button addNewPlayerBtn = findViewById(R.id.addNewPlayerBtn);
        Button createGameBtn = findViewById(R.id.createGameBtn);
        Button showAllGamesBtn = findViewById(R.id.showAllGamesBtn);

        addNewPlayerBtn.setOnClickListener(v ->
                startActivity(
                        MainActivity.this, CreatePlayerActivity.class
                )
        );
        createGameBtn.setOnClickListener(v ->
                startActivity(
                        MainActivity.this, CreateGameActivity.class
                )
        );
        showAllGamesBtn.setOnClickListener(v ->
                startActivity(
                        MainActivity.this, GameHistoryActivity.class
                )
        );
    }
}