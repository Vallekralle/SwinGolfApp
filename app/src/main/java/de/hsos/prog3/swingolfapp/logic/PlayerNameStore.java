package de.hsos.prog3.swingolfapp.logic;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import de.hsos.prog3.swingolfapp.R;

public class PlayerNameStore {
    private Activity activity;

    public PlayerNameStore(Activity activity) {
        this.activity = activity;
    }

    public void save(String name) {
        if(isValid(name)) {
            writeToStorage(name);
            Toast.makeText(
                    activity,
                    String.format("%s %s!", activity.getString(R.string.player_name_success), name),
                    Toast.LENGTH_SHORT
            ).show();
        }
        Toast.makeText(activity, activity.getString(R.string.player_name_error), Toast.LENGTH_SHORT).show();
    }

    private boolean isValid(String name) {
        return !name.isEmpty();
    }

    private void writeToStorage(String name) {
        SharedPreferences sharedPref = activity.getSharedPreferences(
                activity.getString(R.string.preferences),
                Context.MODE_PRIVATE
        );
        SharedPreferences.Editor editor = sharedPref.edit();

        int playerCount = retrievePlayerCount(sharedPref);

        // <"player_0", "Dalina">
        // <"player_1", "Valentin">
        editor.putString(activity.getString(R.string.player_name) + playerCount, name);
        editor.putInt(activity.getString(R.string.player_count), ++playerCount);
        editor.apply();
    }

    private int retrievePlayerCount(SharedPreferences sharedPref) {
        return sharedPref.getInt(activity.getString(R.string.player_count), 0);
    }
}
