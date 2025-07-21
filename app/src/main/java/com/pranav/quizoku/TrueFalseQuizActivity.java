package com.pranav.quizoku;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class TrueFalseQuizActivity extends AppCompatActivity {

    private TextView questionText, questionCount;
    private Button trueButton, falseButton;
    private ProgressBar progressBar;

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
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);

        progressBar = findViewById(R.id.quiz_progress);

        soundPool = new SoundPool.Builder().setMaxStreams(2).build();
        soundCorrect = soundPool.load(this, R.raw.correct_answer_sound, 1);
        soundWrong = soundPool.load(this, R.raw.wrong_answer_sound, 1);

        updateQuestion();

        trueButton.setOnClickListener(v -> checkAnswer(true));
        falseButton.setOnClickListener(v -> checkAnswer(false));
    }

    private void updateQuestion() {
        if (currentQuestion < questions.length) {
            questionText.setText(questions[currentQuestion]);
            questionCount.setText("Question " + (currentQuestion + 1) + " of " + questions.length);
            progressBar.setProgress(currentQuestion + 1);
        } else {
            showResultDialog();
        }
    }

    private void checkAnswer(boolean userAnswer) {
        if (userAnswer == answers[currentQuestion]) {
            score++;
            soundPool.play(soundCorrect, 1, 1, 0, 0, 1);
        } else {
            soundPool.play(soundWrong, 1, 1, 0, 0, 1);
        }
        currentQuestion++;
        updateQuestion();
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
                })
                .setNegativeButton("Go to the home screen", (dialog, which) -> {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                })
                .setNeutralButton("Exit", (dialog, which) -> finishAffinity())
                .show();
    }
}