package com.pranav.quizoku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TrueFalseQuizInstructionsActivity extends AppCompatActivity {

    // declaring the elements
    private SharedPreferences preferences;
    private CheckBox dontRemindCheckbox;
    private Button startQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("quiz_settings", MODE_PRIVATE);

        // Check if instructions should be skipped
        if (!preferences.getBoolean("show_instructions", true)) {
            startActivity(new Intent(this, TrueFalseQuizActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_instructions);

        TextView instructionText = findViewById(R.id.instruction_text);
        instructionText.setText("                       INSTRUCTIONS\n\n• You will be asked a total of 10 statements.\n• Choose 'True' or 'False' for each one." +
                "\n• Each correct answer rewards you 10 points. \n•Each incorrect answer deducts 5 points from your score \n•You cannot go back to the previous question." +
                "\n• You can restart or quit from the quiz anytime." +
                "\n• Your score will be displayed at the end. \n\n Happy Learning!");

        dontRemindCheckbox = findViewById(R.id.dont_remind_checkbox);
        startQuizButton = findViewById(R.id.start_quiz_button);

        startQuizButton.setOnClickListener(v -> {
            if (dontRemindCheckbox.isChecked()) {
                preferences.edit().putBoolean("show_instructions", false).apply();
            }
            startActivity(new Intent(this, TrueFalseQuizActivity.class));
            finish();
        });
    }
}