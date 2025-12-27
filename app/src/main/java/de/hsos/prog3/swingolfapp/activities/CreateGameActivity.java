package de.hsos.prog3.swingolfapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.adapter.PlayerItemAdapter;
import de.hsos.prog3.swingolfapp.model.GameInfo;
import de.hsos.prog3.swingolfapp.model.PlayerInfoHolder;

public class CreateGameActivity extends GolfActivity {
    private ArrayList<PlayerInfoHolder> playerInfoHolderList;
    private PlayerItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        playerInfoHolderList = new ArrayList<>();

        initButtons();
        retrievePlayersFromSharedPreferences();
        showPlayerList();
    }

    private void initButtons() {
        Button createGameBackBtn = findViewById(R.id.createGameBackBtn);
        Button startGameBtn = findViewById(R.id.startGameBtn);

        createGameBackBtn.setOnClickListener(v -> {
            startActivity(CreateGameActivity.this, MainActivity.class);
        });

        startGameBtn.setOnClickListener(v -> {
            startGame();
        });
    }

    private void startGame() {
        String gameName;
        Integer holeCount;
        ArrayList<String> players;

        if ((gameName = retrieveGameName()) == null) {
            Toast.makeText(this, getString(R.string.missing_game_name), Toast.LENGTH_SHORT).show();
            return;
        }
        if((holeCount = retrieveHoleCount()) == null) {
            Toast.makeText(this, getString(R.string.missing_hole_count), Toast.LENGTH_SHORT).show();
            return;
        }
        if((players = retrieveSelectedPlayers()).isEmpty()) {
            Toast.makeText(this, getString(R.string.missing_player_selection), Toast.LENGTH_SHORT).show();
            return;
        }

        GameInfo gameInfo = new GameInfo(gameName, holeCount, players);

        startActivity(CreateGameActivity.this, GameActivity.class, gameInfo);
    }

    private String retrieveGameName() {
        String value = ((EditText) findViewById(R.id.gameNameEditText)).getText().toString().trim();
        if(value.isEmpty()) {
            return null;
        }
        return value;
    }

    private Integer retrieveHoleCount() {
        int holeCount = 0;

        try {
            holeCount = Integer.parseInt(((EditText) findViewById(R.id.holeCountEditText)).getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }

        if (holeCount < 1 || holeCount > 18) {
            Toast.makeText(this, getString(R.string.hole_count_span_error), Toast.LENGTH_SHORT).show();
            return null;
        }
        return holeCount;
    }

    private ArrayList<String> retrieveSelectedPlayers() {
        ArrayList<String> selectedPlayers = new ArrayList<>();

        for(int i = 0; i < adapter.getCount(); i++) {
            PlayerInfoHolder infoHolder = adapter.getItem(i);
            if(infoHolder != null && infoHolder.isChecked()) {
                Log.i("SELECTED_PLAYERS", adapter.getItem(i).getName());
                selectedPlayers.add(infoHolder.getName());
            }
        }

        return selectedPlayers;
    }

    private void retrievePlayersFromSharedPreferences() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preferences), Context.MODE_PRIVATE
        );

        int playerCount = sharedPref.getInt(getString(R.string.player_count), 0);

        for(int count = 0; count < playerCount; count++) {
            String playerName = sharedPref.getString(
                    getString(R.string.player_name) + count, "Error"
            );
            playerInfoHolderList.add(new PlayerInfoHolder(playerName));
        }
    }

    private void showPlayerList() {
        ListView playerListView = findViewById(R.id.playerListView);

        adapter = new PlayerItemAdapter(
                getApplicationContext(), 0, playerInfoHolderList
        );
        playerListView.setAdapter(adapter);
    }
}