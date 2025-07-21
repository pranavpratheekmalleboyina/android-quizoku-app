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

public class MultiChoiceQuizActivity extends AppCompatActivity {

    private TextView questionText, questionNumber;
    private Button optionA, optionB, optionC, optionD, restartButton, endButton;
    private ProgressBar progressBar;

    private String[] questions = {
            "What is the capital of India?",
            "Which planet is known as the Red Planet?",
            "What language is used for Android development?",
            "What is the result of 3 + 5?",
            "Which is the largest ocean?",
            "Who discovered gravity?",
            "What is the boiling point of water?",
            "What does HTML stand for?",
            "Which continent is Australia in?",
            "What is the currency of Japan?"
    };

    private String[][] options = {
            {"New Delhi", "Mumbai", "Kolkata", "Chennai"},
            {"Venus", "Mars", "Jupiter", "Saturn"},
            {"Java", "Python", "Swift", "C++"},
            {"6", "8", "9", "10"},
            {"Indian Ocean", "Arctic Ocean", "Atlantic Ocean", "Pacific Ocean"},
            {"Einstein", "Newton", "Galileo", "Tesla"},
            {"90°C", "50°C", "100°C", "120°C"},
            {"Hyper Trainer Markup Language", "High Text Markup Language", "HyperText Markup Language", "None of the above"},
            {"Asia", "Europe", "Australia", "Africa"},
            {"Yen", "Won", "Ringgit", "Baht"}
    };

    private int[] correctOptionIndexes = {0, 1, 0, 1, 3, 1, 2, 2, 2, 0};

    private int currentQuestion = 0;
    private int score = 0;

    private SoundPool soundPool;
    private int soundCorrect, soundWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choice_quiz);

        questionText = findViewById(R.id.mc_question);
        questionNumber = findViewById(R.id.question_number);
        optionA = findViewById(R.id.option_a);
        optionB = findViewById(R.id.option_b);
        optionC = findViewById(R.id.option_c);
        optionD = findViewById(R.id.option_d);

        restartButton = findViewById(R.id.restart_button);
        endButton = findViewById(R.id.end_button);

        progressBar = findViewById(R.id.quiz_progress);

        soundPool = new SoundPool.Builder().setMaxStreams(2).build();
        soundCorrect = soundPool.load(this, R.raw.correct_answer_sound, 1);
        soundWrong = soundPool.load(this, R.raw.wrong_answer_sound, 1);

        updateQuestion();

        optionA.setOnClickListener(v -> checkAnswer(0));
        optionB.setOnClickListener(v -> checkAnswer(1));
        optionC.setOnClickListener(v -> checkAnswer(2));
        optionD.setOnClickListener(v -> checkAnswer(3));

        restartButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("End Quiz")
                    .setMessage("Are you sure you want to end the quiz early? You would lose your progress.")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        currentQuestion = 0;
                        score = 0;
                        updateQuestion();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        endButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("End Quiz")
                    .setMessage("Are you sure you want to end the quiz early? You would lose your progress.")
                    .setPositiveButton("Yes", (dialog, which) -> showResultDialog())
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    private void updateQuestion() {
        if (currentQuestion < questions.length) {
            questionText.setText(questions[currentQuestion]);
            questionNumber.setText("Question " + (currentQuestion + 1) + " of " + questions.length);
            optionA.setText(options[currentQuestion][0]);
            optionB.setText(options[currentQuestion][1]);
            optionC.setText(options[currentQuestion][2]);
            optionD.setText(options[currentQuestion][3]);
            progressBar.setProgress(currentQuestion + 1);
        } else {
            showResultDialog();
        }
    }

    private void checkAnswer(int selectedIndex) {
        if (selectedIndex == correctOptionIndexes[currentQuestion]) {
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
                .setPositiveButton("Submit", (dialog, which) -> showPostQuizOptions())
                .show();
    }

    private void showPostQuizOptions() {
        new AlertDialog.Builder(this)
                .setTitle("We appreciate your participation!")
                .setMessage("What would you like to do next?")
                .setCancelable(false)
                .setPositiveButton("Retry the multichoice quiz", (dialog, which) -> {
                    currentQuestion = 0;
                    score = 0;
                    updateQuestion();
                })
                .setNegativeButton("Go to the home screen", (dialog, which) -> {
                    startActivity(new Intent(this, TrueFalseQuizActivity.class));
                    finish();
                })
                .setNeutralButton("Exit", (dialog, which) -> finishAffinity())
                .show();
    }
}