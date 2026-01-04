package de.hsos.prog3.swingolfapp.activities;

import android.os.Bundle;
import android.widget.Button;

import de.hsos.prog3.swingolfapp.R;

public class HomeActivity extends MainActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();
    }

    private void initButtons() {
        Button addNewPlayerBtn = findViewById(R.id.addNewPlayerBtn);
        Button createGameBtn = findViewById(R.id.createGameBtn);
        Button showAllGamesBtn = findViewById(R.id.showAllGamesBtn);

        addNewPlayerBtn.setOnClickListener(v ->
                startActivity(
                        HomeActivity.this, CreatePlayerActivity.class
                )
        );
        createGameBtn.setOnClickListener(v ->
                startActivity(
                        HomeActivity.this, CreateCourseActivity.class
                )
        );
        showAllGamesBtn.setOnClickListener(v ->
                startActivity(
                        HomeActivity.this, CourseHistoryActivity.class
                )
        );
    }
}