package com.pranav.quizoku;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    private SwitchCompat themeSwitch, instructionSwitch;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("quiz_settings", MODE_PRIVATE);
        applyTheme(preferences.getBoolean("dark_mode", false));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        themeSwitch = findViewById(R.id.theme_switch);
        instructionSwitch = findViewById(R.id.instruction_switch);

        // Set saved state
        themeSwitch.setChecked(preferences.getBoolean("dark_mode", false));
        instructionSwitch.setChecked(preferences.getBoolean("show_instructions", true));

        themeSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("dark_mode", isChecked).apply();
            recreate();  // Recreate to apply theme immediately
        });

        instructionSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("show_instructions", isChecked).apply();
        });
    }

    private void applyTheme(boolean darkMode) {
        AppCompatDelegate.setDefaultNightMode(
                darkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO
        );
    }
}
