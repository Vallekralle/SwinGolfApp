package de.hsos.prog3.swingolfapp.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.adapter.CourseItemAdapter;
import de.hsos.prog3.swingolfapp.model.gson.CourseGson;

public class CourseHistoryActivity extends MainActivity {
    private Gson gson;
    private ArrayList<CourseGson> gameInfoHolderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        init();
        displaySavedGamesFromStorage();
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
                startActivity(CourseHistoryActivity.this, HomeActivity.class)
        );
    }

    private void initAdapter() {
        ListView gameHistoryListView = findViewById(R.id.gameHistoryListView);

        CourseItemAdapter adapter = new CourseItemAdapter(
                this, 0, gameInfoHolderList
        );
        gameHistoryListView.setAdapter(adapter);
    }

    private void displaySavedGamesFromStorage() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preferences), Context.MODE_PRIVATE
        );

        int gameCount = sharedPref.getInt(getString(R.string.game_count), 0);

        for(int count = 0; count < gameCount; count++) {
            String gameJSON = sharedPref.getString(
                    getString(R.string.game_name) + count, "Error"
            );
            CourseGson courseGson = gson.fromJson(gameJSON, CourseGson.class);
            gameInfoHolderList.add(courseGson);
        }
    }
}