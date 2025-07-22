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

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class TrueFalseQuizActivity extends AppCompatActivity {

    // declaring the elements
    private TextView questionText, questionCount, feedbackText;
    private Button trueButton, falseButton, restartButton, endButton,nextButton;
    private ProgressBar progressBar;
    private SharedPreferences preferences;
    private Boolean soundOn;

    private int currentQuestionIndex = 0;
    private List<QuestionTrueFalse> questionList = new ArrayList<>();
    private int totalScore = 0;

    private SoundPool soundPool;
    private int soundCorrect, soundWrong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false_quiz);

        // initialising the elements
        questionList = TrueFalseQuestionBank.getShuffledQuestions(10);

        questionText = findViewById(R.id.question_text);
        questionCount = findViewById(R.id.question_count);
        feedbackText = findViewById(R.id.feedback_text);
        trueButton = findViewById(R.id.true_button);
        falseButton = findViewById(R.id.false_button);
        restartButton = findViewById(R.id.restart_button);
        endButton = findViewById(R.id.end_button);
        nextButton = findViewById(R.id.nextButton);

        progressBar = findViewById(R.id.quiz_progress);
        preferences = getSharedPreferences("quiz_settings", MODE_PRIVATE);

        soundOn = preferences.getBoolean("sound_enabled", true);
        soundPool = new SoundPool.Builder().setMaxStreams(2).build();
        soundCorrect = soundPool.load(this, R.raw.correct_answer_sound, 1);
        soundWrong = soundPool.load(this, R.raw.wrong_answer_sound, 1);

        updateQuestion(currentQuestionIndex);

        trueButton.setOnClickListener(v -> checkAnswer(true,v));
        falseButton.setOnClickListener(v -> checkAnswer(false,v));

        // in order to restart the quiz in between
        restartButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("End Quiz")
                    .setMessage("Are you sure you want to restart? You would lose your progress.")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        currentQuestionIndex = 0;
                        totalScore = 0;
                        updateQuestion(currentQuestionIndex);
                        enableButtons();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        // in order to end the quiz session
        endButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("End Quiz")
                    .setMessage("Are you sure you want to end the quiz early? You would lose your progress.")
                    .setPositiveButton("Yes", (dialog, which) -> showPostQuizOptions())
                    .setNegativeButton("No", null)
                    .show();
        });

        // in order to go to the next question
        nextButton.setOnClickListener(v -> loadQuestion());

        // in order to prevent the user from going back to the previos screen on back button press
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Option 1: Do nothing to completely block back press
                // (No code here)
            }
        });

    }

    private void loadQuestion(){
        if(currentQuestionIndex == questionList.size() - 1 ){
            showResultDialog();
        }else{
            currentQuestionIndex++;
            updateQuestion(currentQuestionIndex);
        }
    }
    private void updateQuestion(int index) {
            nextButton.setEnabled(false);
            questionText.setText(questionList.get(index).getQuestion());
            questionCount.setText("Question " + (index + 1) + " of " + questionList.size());
            progressBar.setProgress(index + 1);
            enableButtons();
            feedbackText.setText("");
            if(index == questionList.size() - 1){
                nextButton.setText("View Result");
            }
    }

    private void checkAnswer(boolean userAnswer, View v) {
        Button clickedButton = (Button) v;
        nextButton.setEnabled(true);
        if (userAnswer == questionList.get(currentQuestionIndex).getAnswer()) {
            totalScore+= 10;
            if (soundOn){
                soundPool.play(soundCorrect, 1, 1, 0, 0, 1);
            }
            clickedButton.setBackgroundTintList(ContextCompat.getColorStateList(this,android.R.color.holo_green_dark));
            feedbackText.setText("Correct!");
        } else {
            totalScore -= 5;
            if (soundOn){
                soundPool.play(soundWrong, 1, 1, 0, 0, 1);
            }
            clickedButton.setBackgroundTintList(ContextCompat.getColorStateList(this,android.R.color.holo_red_dark));
            feedbackText.setText("Wrong!");
        }
        disableButtons();
    }

    private void showResultDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_result_rating, null);
        RatingBar ratingBar = dialogView.findViewById(R.id.result_rating);

        new AlertDialog.Builder(this)
                .setTitle("Quiz Completed!")
                .setMessage("Your totalScore: " + totalScore + " / " + (questionList.size()*10))
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
                    currentQuestionIndex = 0;
                    totalScore = 0;
                    updateQuestion(currentQuestionIndex);
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