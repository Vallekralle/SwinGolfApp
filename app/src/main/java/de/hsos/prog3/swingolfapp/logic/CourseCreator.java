package de.hsos.prog3.swingolfapp.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.activities.CreateCourseActivity;
import de.hsos.prog3.swingolfapp.adapter.CourseInfoHolder;
import de.hsos.prog3.swingolfapp.adapter.PlayerInfoHolder;

public class CourseCreator {
    private CreateCourseActivity activity;

    private String gameName;
    private Integer holeCount;
    private ArrayList<String> players;

    public CourseCreator(CreateCourseActivity activity) {
        this.activity = activity;
    }

    public CourseInfoHolder create() {
        if(invalidValues()) {
            return null;
        }
        return new CourseInfoHolder(gameName, holeCount, players);
    }

    private boolean invalidValues() {
        if ((gameName = retrieveGameName()) == null) {
            Toast.makeText(activity, activity.getString(R.string.missing_game_name), Toast.LENGTH_SHORT).show();
            return true;
        }
        if((holeCount = retrieveHoleCount()) == null) {
            Toast.makeText(activity, activity.getString(R.string.missing_hole_count), Toast.LENGTH_SHORT).show();
            return true;
        }
        if((players = retrieveSelectedPlayers()).isEmpty()) {
            Toast.makeText(activity, activity.getString(R.string.missing_player_selection), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private String retrieveGameName() {
        String value = ((EditText) activity.findViewById(R.id.gameNameEditText)).getText().toString().trim();
        if(value.isEmpty()) {
            return null;
        }
        return value;
    }

    private Integer retrieveHoleCount() {
        int holeCount = 0;

        try {
            holeCount = Integer.parseInt(((EditText) activity.findViewById(R.id.holeCountEditText)).getText().toString());
        } catch (NumberFormatException e) {
            return null;
        }

        if (holeCount < 1 || holeCount > 18) {
            Toast.makeText(activity, activity.getString(R.string.hole_count_span_error), Toast.LENGTH_SHORT).show();
            return null;
        }
        return holeCount;
    }

    private ArrayList<String> retrieveSelectedPlayers() {
        ArrayList<String> selectedPlayers = new ArrayList<>();

        for(int i = 0; i < activity.getAdapter().getCount(); i++) {
            PlayerInfoHolder infoHolder = activity.getAdapter().getItem(i);
            if(infoHolder != null && infoHolder.isChecked()) {
                selectedPlayers.add(infoHolder.getName());
            }
        }

        return selectedPlayers;
    }

    public void displayPlayersFromStorage() {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                activity.getString(R.string.preferences), Context.MODE_PRIVATE
        );

        int playerCount = sharedPref.getInt(activity.getString(R.string.player_count), 0);

        for(int count = 0; count < playerCount; count++) {
            String playerName = sharedPref.getString(
                    activity.getString(R.string.player_name) + count, "Error"
            );
            activity.getPlayerInfoHolderList().add(new PlayerInfoHolder(playerName));
        }
    }
}
