package de.hsos.prog3.swingolfapp.activities;

import android.os.Bundle;
import android.widget.Button;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.logic.TableController;
import de.hsos.prog3.swingolfapp.adapter.GameInfoHolder;
import de.hsos.prog3.swingolfapp.model.TableInfo;

public class GameActivity extends MainActivity {
    private TableController tableController;

    private String[] playerNames;
    private String courseName;
    private int courseCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
        createTable();
    }

    private void init() {
        try {
            retrieveExtras();
        } catch (RuntimeException e) {
            // Return to the Home screen if no extras were passed
            startActivity(GameActivity.this, HomeActivity.class);
        }
        initButtons();
    }

    private void initButtons() {
        Button gameBackBtn = findViewById(R.id.gameBackBtn);
        Button saveGameBtn = findViewById(R.id.saveGameBtn);

        gameBackBtn.setOnClickListener(v -> {
            startActivity(GameActivity.this, HomeActivity.class);
        });

        saveGameBtn.setOnClickListener(v -> {
            if(tableController.saveGame()) {
                saveGameBtn.setEnabled(false);
            }
        });
    }

    private void retrieveExtras() throws RuntimeException {
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            throw new RuntimeException("asdf");
        }

        GameInfoHolder gameInfoHolder = (GameInfoHolder) extras.getSerializable("extra");
        playerNames = gameInfoHolder.players().toArray(new String[0]);
        courseName = gameInfoHolder.gameName();
        courseCount = gameInfoHolder.holeCount();
    }

    private void createTable() {
        TableInfo tableInfo = new TableInfo(playerNames, courseName, courseCount);
        tableController = new TableController(this, tableInfo);
        tableController.displayGameTable();
    }
}