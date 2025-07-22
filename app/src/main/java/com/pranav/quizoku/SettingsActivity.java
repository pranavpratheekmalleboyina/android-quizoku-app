package com.pranav.quizoku;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    // declaring all the elements
    private SwitchCompat soundSwitch, instructionSwitch;
    private SharedPreferences preferences;
    private ImageButton backButton;
    private Button resetButton, saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("quiz_settings", MODE_PRIVATE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //initialising all the elements
        soundSwitch = findViewById(R.id.sound_switch);
        instructionSwitch = findViewById(R.id.instruction_switch);

        backButton = findViewById(R.id.back_button);
        resetButton = findViewById(R.id.resetButton);
        saveButton = findViewById(R.id.saveButton);

        // Setting the toggles to true by default
        soundSwitch.setChecked(preferences.getBoolean("sound_enabled", true));
        instructionSwitch.setChecked(preferences.getBoolean("show_instructions", true));

        soundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("sound_enabled", isChecked).apply();
        });


        instructionSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            preferences.edit().putBoolean("show_instructions", isChecked).apply();
        });

        backButton.setOnClickListener(v -> finish()); // Go back\

        // in order to reset the preferences back to default
        resetButton.setOnClickListener(v ->  resetToDefaults());

        // in order to save the new preferences of the user
        saveButton.setOnClickListener(v -> saveSettings());
    }

    private void resetToDefaults() {
        new AlertDialog.Builder(SettingsActivity.this)
                .setTitle("Reset to Default")
                .setMessage("Are you sure you want to reset all settings to default?")
                .setPositiveButton("Reset", (dialog, which) -> {
                    // Clear or overwrite with default values
                    preferences.edit()
                            .putBoolean("sound_enabled", true)
                            .putBoolean("show_instructions", true)
                            .apply();

                    // Reflect in UI
                    soundSwitch.setChecked(true);
                    instructionSwitch.setChecked(true);

                    Toast.makeText(this, "Settings reset to default", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void saveSettings(){
        new AlertDialog.Builder(SettingsActivity.this)
                .setTitle("Save Settings")
                .setMessage("Are you sure you want to save these settings?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    preferences.edit()
                            .putBoolean("sound_enabled", soundSwitch.isChecked())
                            .putBoolean("show_instructions", instructionSwitch.isChecked())
                            .apply();
                    Toast.makeText(this, "Settings saved", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
