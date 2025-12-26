package de.hsos.prog3.swingolfapp.model;

import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class Player {
    private ArrayList<EditText> editTextList;
    private TextView resultText;

    public Player(int courseCount) {
        editTextList = new ArrayList<>(courseCount);
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

    private void changeResultText() {
        if(resultText != null) {
            int count = 0;
            for(EditText editText : editTextList) {
                try {
                    int value = Integer.parseInt(editText.getText().toString());
                    count += value;
                } catch (NumberFormatException e) {
                    System.out.println(e.getMessage());
                }
            }
            resultText.setText(String.valueOf(count));
        }
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
}
