package de.hsos.prog3.swingolfapp.activities;

import android.os.Bundle;
import android.widget.Button;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.logic.TableController;
import de.hsos.prog3.swingolfapp.model.GameInfo;
import de.hsos.prog3.swingolfapp.model.TableInfo;

public class GameActivity extends GolfActivity {
    private TableController tableController;

    private String[] playerNames;
    private String courseName;
    private int courseCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            GameInfo gameInfo = (GameInfo) extras.getSerializable("extra");
            playerNames = gameInfo.players().toArray(new String[0]);
            courseName = gameInfo.gameName();
            courseCount = gameInfo.holeCount();
        }

        initButton();

        TableInfo tableInfo = new TableInfo(playerNames, courseName, courseCount);
        tableController = new TableController(this, tableInfo);
        tableController.displayGameTable();
    }

    private void initButton() {
        Button gameBackBtn = findViewById(R.id.gameBackBtn);
        Button saveGameBtn = findViewById(R.id.saveGameBtn);

        gameBackBtn.setOnClickListener(v -> {
            startActivity(GameActivity.this, MainActivity.class);
        });

        saveGameBtn.setOnClickListener(v -> {
            saveGameBtn.setEnabled(false);
            tableController.saveGame();
        });
    }
}