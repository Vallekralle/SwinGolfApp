package de.hsos.prog3.swingolfapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class GolfActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public static void startActivity(Activity currentActivity, Class<?> nextActivity) {
        Intent intent = new Intent(currentActivity, nextActivity);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }

    public static void startActivity(Activity currentActivity, Class<?> nextActivity, Serializable extra) {
        Intent intent = new Intent(currentActivity, nextActivity);
        // Add extras to the intent
        intent.putExtra("extra", extra);
        currentActivity.startActivity(intent);
        currentActivity.finish();
    }
}
