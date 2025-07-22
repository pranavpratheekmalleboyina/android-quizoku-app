package com.pranav.quizoku;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // declare all the components
    private Button trueFalseButton,multiChoiceButton,exitButton;
    private ImageButton settingsButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialising all the comments
        trueFalseButton = findViewById(R.id.true_false_button);
        multiChoiceButton = findViewById(R.id.multi_choice_button);
        exitButton = findViewById(R.id.exit_button);
        settingsButton = findViewById(R.id.settings_button);

        // navigates to the instructions page by default as set in the shared preferences screen
        trueFalseButton.setOnClickListener(v -> showConfirmationDialog(
                "Are you sure that you want to start the True/False Quiz?",
                () -> {
                    Intent intent = new Intent(MainActivity.this, TrueFalseQuizInstructionsActivity.class);
                    startActivity(intent);
                }
        ));

        // navigates to the instructions page by default as set in the shared preferences screen
        multiChoiceButton.setOnClickListener(v -> showConfirmationDialog(
                "Are you sure that you want to start the Multi Choice Quiz?",
                () -> {
                    Intent intent = new Intent(MainActivity.this, MultiChoiceQuizInstructionsActivity.class);
                    startActivity(intent);
                }
        ));

        // to exit from the app
        exitButton.setOnClickListener(v -> showConfirmationDialog(
                "Are you sure that you want to exit the app?",
                () -> {
                    finishAffinity();
                }
        ));

        // to go to the settings page
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });
    }

    // method for customizing the alert dialog box
    private void showConfirmationDialog(String message, Runnable onConfirmAction) {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage(message)
                .setCancelable(true)
                .setPositiveButton("Yes", (dialog, which) -> onConfirmAction.run())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
