package de.hsos.prog3.swingolfapp.model;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import de.hsos.prog3.swingolfapp.model.gson.PlayerGson;

public class Player {
    private final ArrayList<EditText> editTextList;
    private TextView resultText;

    private final String name;
    private final int courseCount;

    private int totalShoots, min, max;

    public Player(String name, int courseCount) {
        this.name = name;
        this.courseCount = courseCount;

        editTextList = new ArrayList<>(courseCount);
        max = 0;
        min = 100;
    }

    public void addEditText(EditText editText) {
        editTextList.add(editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                changeResultText();
            }
        });
    }

    public void setResultText(TextView resultText) {
        this.resultText = resultText;
    }

    public boolean hasEmptyFields() {
        for(EditText editText : editTextList) {
            String value = editText.getText().toString();
            try {
                if(value.isEmpty() || Integer.parseInt(value) < 1) {
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    private void changeResultText() {
        if(resultText != null) {
            int count = 0;
            for(EditText editText : editTextList) {
                try {
                    /*
                    * Extract the value from the edit text and convert it to
                    * an integer, may produce NumberFormatException
                     * */
                    int value = Integer.parseInt(editText.getText().toString());

                    if(value < min) min = value;
                    if(value > max) max = value;

                    count += value;
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            totalShoots = count;
            resultText.setText(String.valueOf(count));
        }
    }

    /**
    * Convert to GSON
    * */

    public PlayerGson toGson() {
        return new PlayerGson(name, getAvgShoots(), min, max);
    }

    public float getAvgShoots() {
        return (float) totalShoots / courseCount;
    }
}
