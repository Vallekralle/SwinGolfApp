package de.hsos.prog3.swingolfapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.adapter.PlayerItemAdapter;
import de.hsos.prog3.swingolfapp.model.NameHolder;

public class CreateGameActivity extends GolfActivity {
    private ArrayList<NameHolder> nameHolderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        nameHolderList = new ArrayList<>();

        initButtons();
        retrievePlayers();
        showPlayerList();
    }

    private void initButtons() {
        Button createGameBackBtn = findViewById(R.id.createGameBackBtn);
        Button startGameBtn = findViewById(R.id.startGameBtn);

        createGameBackBtn.setOnClickListener(v -> {
            startActivity(CreateGameActivity.this, MainActivity.class);
        });

        startGameBtn.setOnClickListener(v -> {
            startActivity(CreateGameActivity.this, GameActivity.class);
        });
    }

    private void retrievePlayers() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preferences), Context.MODE_PRIVATE
        );

        int playerCount = sharedPref.getInt(getString(R.string.player_count), 0);

        for(int count = 0; count < playerCount; count++) {
            String playerName = sharedPref.getString(
                    getString(R.string.player_name) + count, "Error"
            );
            nameHolderList.add(new NameHolder(playerName));
        }
    }

    private void showPlayerList() {
        ListView playerListView = findViewById(R.id.playerListView);

        PlayerItemAdapter adapter = new PlayerItemAdapter(
                getApplicationContext(), 0, nameHolderList
        );
        playerListView.setAdapter(adapter);
    }
}