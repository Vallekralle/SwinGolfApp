package de.hsos.prog3.swingolfapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.logic.PlayerNameStore;

public class CreatePlayerActivity extends MainActivity {
    private PlayerNameStore playerNameStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        init();
    }

    private void init() {
        playerNameStore = new PlayerNameStore(this);
        initButtons();
    }

    private void initButtons() {
        Button createPlayerBackBtn = findViewById(R.id.createPlayerBackBtn);
        Button savePlayerBtn = findViewById(R.id.savePlayerBtn);

        createPlayerBackBtn.setOnClickListener(v ->
                startActivity(CreatePlayerActivity.this, HomeActivity.class)
        );

        savePlayerBtn.setOnClickListener(v -> {
            savePlayerBtn.setEnabled(false);
            savePlayerName();
            savePlayerBtn.setEnabled(true);
        });
    }

    private void savePlayerName() {
        TextView playerNameEditText = findViewById(R.id.playerNameEditText);
        String name = playerNameEditText.getText().toString().trim();

        playerNameStore.save(name);

        playerNameEditText.setText("");
    }
}