package com.pranav.quizoku;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class TrueFalseQuizActivity extends AppCompatActivity {

    private TextView questionText, questionCount, feedbackText;
    private Button trueButton, falseButton, restartButton, endButton;
    private ProgressBar progressBar;
    private SharedPreferences preferences;
    private Boolean soundOn;

    private String[] questions = {
            "The sun rises in the east.",
            "Water boils at 90°C.",
            "The Earth is flat.",
            "Python is a snake and a programming language.",
            "Light travels faster than sound.",
            "Mount Everest is the tallest mountain on Earth.",
            "Fish can breathe out of water.",
            "The Great Wall of China is visible from space.",
            "Humans have 4 senses.",
            "The capital of France is Paris."
    };

    private boolean[] answers = {
            true, false, false, true, true,
            true, false, false, false, true
    };

    private int currentQuestion = 0;
    private int score = 0;

    private SoundPool soundPool;
    private int soundCorrect, soundWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false_quiz);

        questionText = findViewById(R.id.question_text);
        questionCount = findViewById(R.id.question_count);
        feedbackText = findViewById(R.id.feedback_text);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        restartButton = findViewById(R.id.restart_button);
        endButton = findViewById(R.id.end_button);

        progressBar = findViewById(R.id.quiz_progress);
        preferences = getSharedPreferences("quiz_settings", MODE_PRIVATE);

        soundOn = preferences.getBoolean("sound_enabled", true);
        soundPool = new SoundPool.Builder().setMaxStreams(2).build();
        soundCorrect = soundPool.load(this, R.raw.correct_answer_sound, 1);
        soundWrong = soundPool.load(this, R.raw.wrong_answer_sound, 1);

        updateQuestion();

        trueButton.setOnClickListener(v -> checkAnswer(true,v));
        falseButton.setOnClickListener(v -> checkAnswer(false,v));

        restartButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("End Quiz")
                    .setMessage("Are you sure you want to restart? You would lose your progress.")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        currentQuestion = 0;
                        score = 0;
                        updateQuestion();
                        enableButtons();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        endButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("End Quiz")
                    .setMessage("Are you sure you want to end the quiz early? You would lose your progress.")
                    .setPositiveButton("Yes", (dialog, which) -> showPostQuizOptions())
                    .setNegativeButton("No", null)
                    .show();
        });

    }

    private void updateQuestion() {
        if (currentQuestion < questions.length) {
            questionText.setText(questions[currentQuestion]);
            questionCount.setText("Question " + (currentQuestion + 1) + " of " + questions.length);
            progressBar.setProgress(currentQuestion + 1);
            enableButtons();
        } else {
            showResultDialog();
        }
    }

    private void checkAnswer(boolean userAnswer, View v) {
        Button clickedButton = (Button) v;
        if (userAnswer == answers[currentQuestion]) {
            score++;
            if (soundOn){
                soundPool.play(soundCorrect, 1, 1, 0, 0, 1);
            }
            clickedButton.setBackgroundTintList(ContextCompat.getColorStateList(this,android.R.color.holo_green_dark));
            feedbackText.setText("Correct!");
        } else {
            if (soundOn){
                soundPool.play(soundWrong, 1, 1, 0, 0, 1);
            }
            clickedButton.setBackgroundTintList(ContextCompat.getColorStateList(this,android.R.color.holo_red_dark));
            feedbackText.setText("Wrong!");
        }
        disableButtons();
        // Delay before loading next question
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            currentQuestion++;
            updateQuestion();
        }, 1500);
    }

    private void showResultDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_result_rating, null);
        RatingBar ratingBar = dialogView.findViewById(R.id.result_rating);

        new AlertDialog.Builder(this)
                .setTitle("Quiz Completed!")
                .setMessage("Your score: " + score + " / " + questions.length)
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton("Submit", (dialog, which) -> {
                    showPostQuizOptions();
                })
                .show();
    }

    private void showPostQuizOptions() {
        new AlertDialog.Builder(this)
                .setTitle("We appreciate your participation!")
                .setMessage("What would you like to do next?")
                .setCancelable(false)
                .setPositiveButton("Retry the quiz", (dialog, which) -> {
                    currentQuestion = 0;
                    score = 0;
                    updateQuestion();
                    enableButtons();
                })
                .setNegativeButton("Go to the home screen", (dialog, which) -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNeutralButton("Exit", (dialog, which) -> finishAffinity())
                .show();
    }

    private void enableButtons(){
        trueButton.setEnabled(true);
        falseButton.setEnabled(true);
        feedbackText.setText("");
        trueButton.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.teal_200));
        falseButton.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.teal_200));
    }

    private void disableButtons(){
        trueButton.setEnabled(false);
        falseButton.setEnabled(false);
    }
}