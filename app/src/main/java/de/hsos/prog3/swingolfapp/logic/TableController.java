package de.hsos.prog3.swingolfapp.logic;

import android.app.Activity;
import android.graphics.Color;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import java.util.Objects;

import de.hsos.prog3.swingolfapp.R;
import de.hsos.prog3.swingolfapp.model.Player;
import de.hsos.prog3.swingolfapp.model.TableInfo;

public class TableController {
    public static final int CELL_WIDTH = 500;
    public static final int CELL_HEIGHT = 200;
    public static final int TEXT_SIZE = 22;
    public static final int TEXT_COLOR = Color.BLACK;

    private Activity activity;
    private TableLayout playerTableLayout;
    private TableRow playerNameRow, resultRow;

    private TableInfo tableInfo;
    private Player[] players;

    public TableController(Activity activity, TableInfo tableInfo) {
        Objects.requireNonNull(activity);
        Objects.requireNonNull(tableInfo);
        this.activity = activity;
        this.tableInfo = tableInfo;

        init();
    }

    private void init() {
        players = new Player[tableInfo.playerNames().length];
        playerTableLayout = activity.findViewById(R.id.playerTableLayout);
        playerNameRow = activity.findViewById(R.id.playerNameRow);
        resultRow = activity.findViewById(R.id.resultRow);
    }

    /**
    * Start function
    * */

    public void displayGameTable() {
        displayNameRow(tableInfo.courseName(), tableInfo.playerNames());
        displayInputRows(tableInfo.courseCount(), tableInfo.playerNames());
        displayResultRow(tableInfo.playerNames());
    }

    /**
    * Row display
    * */

    private void displayNameRow(String courseName, String[] playerNames) {
        // Append the course name at the beginning
        playerNameRow.addView(createTextView(courseName));

        // Iterate through all players and add them to the row
        for(int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(tableInfo.courseCount());
            playerNameRow.addView(createTextView(playerNames[i]));
        }
    }

    private void displayInputRows(int courseCount, String[] playerNames) {
        for(int i = 1; i < courseCount + 1; i++) {
            TableRow row = createTableRow();
            row.addView(createTextView(activity.getString(R.string.course) + " " + i));

            for(int j = 0; j < playerNames.length; j++) {
                EditText editText = createNumberEditText();
                row.addView(editText);
                players[j].addEditText(editText);
            }
            playerTableLayout.addView(row);
        }
    }

    private void displayResultRow(String[] playerNames) {
        resultRow.addView(createTextView(activity.getString(R.string.total)));

        for(int i = 0; i < playerNames.length; i++) {
            TextView resultText = createTextView("0");
            resultRow.addView(resultText);
            players[i].setResultText(resultText);
        }
    }

    public void saveGame() {
        if(isSaveable()) {

        } else {
            Toast.makeText(activity, activity.getString(R.string.empty_fields), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isSaveable() {
        for(Player player : players) {
            if(player.hasEmptyFields()) {
                return false;
            }
        }
        return true;
    }

    /**
    * Views creation
    * */

    private TableRow createTableRow() {
        return new TableRow(activity);
    }

    private TextView createTextView(String textValue) {
        TextView text = new TextView(activity);

        // TextView config
        text.setText(textValue);
        text.setBackground(ContextCompat.getDrawable(activity, R.drawable.border));
        text.setGravity(Gravity.CENTER);
        text.setWidth(CELL_WIDTH);
        text.setHeight(CELL_HEIGHT);
        text.setTextSize(TEXT_SIZE);
        text.setTextColor(TEXT_COLOR);

        return text;
    }

    private EditText createNumberEditText() {
        EditText editText = new EditText(activity);

        // EditText config
        editText.setHint("0");
        editText.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        editText.setWidth(CELL_WIDTH);
        editText.setHeight(CELL_HEIGHT);
        editText.setGravity(Gravity.CENTER);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        return editText;
    }
}
