package de.hsos.prog3.swingolfapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.adapter.GameItemAdapter;
import de.hsos.prog3.swingolfapp.model.gson.GameGson;

public class GameHistoryActivity extends MainActivity {
    private Gson gson;
    private ArrayList<GameGson> gameInfoHolderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        init();
        displaySavedGames();
    }

    private void init() {
        gson = new Gson();
        gameInfoHolderList = new ArrayList<>();

        initButtons();
        initAdapter();
    }

    private void initButtons() {
        Button gameHistoryBackBtn = findViewById(R.id.gameHistoryBackBtn);

        gameHistoryBackBtn.setOnClickListener(v ->
                startActivity(GameHistoryActivity.this, HomeActivity.class)
        );
    }

    private void initAdapter() {
        ListView gameHistoryListView = findViewById(R.id.gameHistoryListView);

        GameItemAdapter adapter = new GameItemAdapter(
                this, 0, gameInfoHolderList
        );
        gameHistoryListView.setAdapter(adapter);
    }

    private void displaySavedGames() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preferences), Context.MODE_PRIVATE
        );

        int gameCount = sharedPref.getInt(getString(R.string.game_count), 0);

        for(int count = 0; count < gameCount; count++) {
            String gameJSON = sharedPref.getString(
                    getString(R.string.game_name) + count, "Error"
            );
            GameGson gameGson = gson.fromJson(gameJSON, GameGson.class);
            gameInfoHolderList.add(gameGson);
        }
    }
}