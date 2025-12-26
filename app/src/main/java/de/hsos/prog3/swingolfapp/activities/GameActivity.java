package de.hsos.prog3.swingolfapp.activities;

import android.os.Bundle;
import android.widget.Button;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.logic.TableController;
import de.hsos.prog3.swingolfapp.model.TableInfo;

public class GameActivity extends GolfActivity {
    private TableController tableController;

    /// ONLY TEMPORARILY
    private String[] playerNames = {"Dalina", "Valentin", "Peter", "Joel"};
    private String courseName = "Norderney";
    private int courseCount = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initButton();

        TableInfo tableInfo = new TableInfo(playerNames, courseName, courseCount);
        tableController = new TableController(this, tableInfo);
        tableController.displayGameTable();
    }

    private void initButton() {
        Button gameBackBtn = findViewById(R.id.gameBackBtn);
        gameBackBtn.setOnClickListener(v -> {
            startActivity(GameActivity.this, MainActivity.class);
        });
    }
}