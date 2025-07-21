package com.pranav.quizoku;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button trueFalseButton;
    private Button multiChoiceButton;
    private Button exitButton;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        trueFalseButton = findViewById(R.id.true_false_button);
        multiChoiceButton = findViewById(R.id.multi_choice_button);
        exitButton = findViewById(R.id.exit_button);

        trueFalseButton.setOnClickListener(v -> showConfirmationDialog(
                "Are you sure that you want to start the True/False Quiz?",
                () -> {
                    Intent intent = new Intent(MainActivity.this, TrueFalseQuizActivity.class);
                    startActivity(intent);
                }
        ));

        multiChoiceButton.setOnClickListener(v -> showConfirmationDialog(
                "Are you sure that you want to start the Multi Choice Quiz?",
                () -> {
                    Intent intent = new Intent(MainActivity.this, MultiChoiceQuizActivity.class);
                    startActivity(intent);
                }
        ));

        exitButton.setOnClickListener(v -> showConfirmationDialog(
                "Are you sure that you want to exit the app?",
                () -> {
                    finishAffinity();
                }
        )); // Exit the app
    }

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
