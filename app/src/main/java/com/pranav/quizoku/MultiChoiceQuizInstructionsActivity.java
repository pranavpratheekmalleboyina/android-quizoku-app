package com.pranav.quizoku;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MultiChoiceQuizInstructionsActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private CheckBox dontRemindCheckbox;
    private Button startQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("quiz_settings", MODE_PRIVATE);

        // Skip if user disabled instructions
        if (!preferences.getBoolean("show_instructions", true)) {
            startActivity(new Intent(this, MultiChoiceQuizActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_instructions);

        TextView instructionText = findViewById(R.id.instruction_text);
        instructionText.setText("                       INSTRUCTIONS\n\n• You will get a total of 10 questions.\n• Choose the correct option from 4 choices." +
                "\n• You must select the correct answer to move forward.\n• You cannot go back to the previous question.\n• Your can restart or quit from the quiz anytime." +
                "\n• Your score will be displayed at the end.\n\n Happy Learning!");

        dontRemindCheckbox = findViewById(R.id.dont_remind_checkbox);
        startQuizButton = findViewById(R.id.start_quiz_button);

        startQuizButton.setOnClickListener(v -> {
            if (dontRemindCheckbox.isChecked()) {
                preferences.edit().putBoolean("show_instructions", false).apply();
            }
            startActivity(new Intent(this, MultiChoiceQuizActivity.class));
            finish();
        });
    }
}
