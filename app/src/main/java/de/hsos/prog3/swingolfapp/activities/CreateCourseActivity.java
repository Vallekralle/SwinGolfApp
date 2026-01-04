package de.hsos.prog3.swingolfapp.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.adapter.PlayerItemAdapter;
import de.hsos.prog3.swingolfapp.adapter.CourseInfoHolder;
import de.hsos.prog3.swingolfapp.adapter.PlayerInfoHolder;
import de.hsos.prog3.swingolfapp.logic.CourseCreator;

public class CreateCourseActivity extends MainActivity {
    private PlayerItemAdapter adapter;
    private CourseCreator courseCreator;

    private ArrayList<PlayerInfoHolder> playerInfoHolderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        playerInfoHolderList = new ArrayList<>();

        init();
        courseCreator.displayPlayersFromStorage();
    }

    private void init() {
        initButtons();
        initAdapter();

        courseCreator = new CourseCreator(this);
    }

    private void initButtons() {
        Button createGameBackBtn = findViewById(R.id.createGameBackBtn);
        Button startGameBtn = findViewById(R.id.startGameBtn);

        createGameBackBtn.setOnClickListener(v ->
            startActivity(CreateCourseActivity.this, HomeActivity.class)
        );

        startGameBtn.setOnClickListener(v -> startGame());
    }

    private void initAdapter() {
        ListView playerListView = findViewById(R.id.playerListView);

        adapter = new PlayerItemAdapter(
                this, 0, playerInfoHolderList
        );
        playerListView.setAdapter(adapter);
    }

    private void startGame() {
        CourseInfoHolder courseInfoHolder = courseCreator.create();
        while(courseInfoHolder == null) {
            courseInfoHolder = courseCreator.create();
        }
        startActivity(CreateCourseActivity.this, CourseActivity.class, courseInfoHolder);
    }

    public ArrayList<PlayerInfoHolder> getPlayerInfoHolderList() {
        return playerInfoHolderList;
    }

    public PlayerItemAdapter getAdapter() {
        return adapter;
    }
}