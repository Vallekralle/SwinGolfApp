package de.hsos.prog3.swingolfapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import de.hsos.prog3.swingolfapp.R;

public class CreatePlayerActivity extends GolfActivity {
    private EditText playerNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        playerNameEditText = findViewById(R.id.playerNameEditText);
        initButtons();
    }

    private void initButtons() {
        Button createPlayerBackBtn = findViewById(R.id.createPlayerBackBtn);
        Button savePlayerBtn = findViewById(R.id.savePlayerBtn);

        createPlayerBackBtn.setOnClickListener(v -> {
            startActivity(CreatePlayerActivity.this, MainActivity.class);
        });

        savePlayerBtn.setOnClickListener(v -> {
            savePlayerBtn.setEnabled(false);
            String name = playerNameEditText.getText().toString().trim();
            if(isValid(name)) {
                writeToStorage(name);
                Toast.makeText(
                        this,
                        String.format("%s %s!", getString(R.string.player_name_success), name),
                        Toast.LENGTH_SHORT
                ).show();
            }
            playerNameEditText.setText("");
            savePlayerBtn.setEnabled(true);
        });
    }

    private boolean isValid(String name) {
        if(name.isEmpty()) {
            Toast.makeText(this, R.string.player_name_error, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void writeToStorage(String name) {
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preferences), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        int playerCount = retrievePlayerCount(sharedPref);

        editor.putString(getString(R.string.player_name) + playerCount, name);
        editor.putInt(getString(R.string.player_count), ++playerCount);
        editor.apply();
    }

    private int retrievePlayerCount(SharedPreferences sharedPref) {
        return sharedPref.getInt(getString(R.string.player_count), 0);
    }
}