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

import java.util.List;

public class MultiChoiceQuizActivity extends AppCompatActivity {

    //declaring all the components
    private TextView questionText, questionNumber, feedbackText;
    private Button optionA, optionB, optionC, optionD, restartButton, endButton,nextButton;
    private ProgressBar progressBar;
    private SharedPreferences preferences;
    private Boolean soundOn;
    private List<QuestionMultiChoice> questionList;
    private int currentQuestionIndex = 0;
    private int totalScore = 0;

    private SoundPool soundPool;
    private int soundCorrect, soundWrong;  //sounds for the correct and wrong answers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_choice_quiz);

        //initialising all the components
        questionList = MultiChoiceQuestionBank.getShuffledQuestions(10);

        questionText = findViewById(R.id.mc_question);
        questionNumber = findViewById(R.id.question_number);
        feedbackText = findViewById(R.id.feedback_text);
        optionA = findViewById(R.id.option_a);
        optionB = findViewById(R.id.option_b);
        optionC = findViewById(R.id.option_c);
        optionD = findViewById(R.id.option_d);

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

        //on selecting the options for the question
        optionA.setOnClickListener(v -> checkAnswer(0));
        optionB.setOnClickListener(v -> checkAnswer(1));
        optionC.setOnClickListener(v -> checkAnswer(2));
        optionD.setOnClickListener(v -> checkAnswer(3));

        // in order for the user to restart the quiz anywhere in between
        restartButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("End Quiz")
                    .setMessage("Are you sure you want to restart? You would lose your progress.")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        currentQuestionIndex = 0;
                        totalScore = 0;
                        updateQuestion(currentQuestionIndex);
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        //in order for the user to end the quiz in between
        endButton.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("End Quiz")
                    .setMessage("Are you sure you want to end the quiz early? You would lose your progress.")
                    .setPositiveButton("Yes", (dialog, which) -> showPostQuizOptions())
                    .setNegativeButton("No", null)
                    .show();
        });

        // this is to move to the next question
        nextButton.setOnClickListener(v -> loadQuestion());

        // in order to prevent the user from going back to the previous screen when back button is pressed
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Option 1: Do nothing to completely block back press
                // (No code here)
            }
        });

    }

    private void loadQuestion(){
        if (currentQuestionIndex == questionList.size() - 1){
            showResultDialog();
        }else{
            currentQuestionIndex++;
            resetButtonStyles();
            updateQuestion(currentQuestionIndex);
        }
    }
    private void updateQuestion(int index) {
            nextButton.setEnabled(false);  //the next button would be disabled on the loading of every new question
            questionText.setText(questionList.get(index).getQuestion());
            questionNumber.setText("Question " + (index + 1) + " of " + questionList.size());
            optionA.setText(questionList.get(index).getOptions()[0]);
            optionB.setText(questionList.get(index).getOptions()[1]);
            optionC.setText(questionList.get(index).getOptions()[2]);
            optionD.setText(questionList.get(index).getOptions()[3]);
            progressBar.setProgress(index + 1);
            feedbackText.setText("");
            if(index == questionList.size() - 1){
                nextButton.setText("View Result"); // this gets displayed when the last question gets loaded on the screen
            }
    }

    private void checkAnswer(int selectedIndex) {
        Button[] optionButtons = {optionA, optionB, optionC, optionD};

        if (selectedIndex == questionList.get(currentQuestionIndex).getCorrectIndex()) {
            // when the Correct answer gets selected
            totalScore+= 10;
            if (soundOn){  //, making sure to play the sounds only if the preferences selected is true
                soundPool.play(soundCorrect, 1, 1, 0, 0, 1);
            }

            // Highlight green and disable all
            optionButtons[selectedIndex].setBackgroundTintList(ContextCompat.getColorStateList(this,android.R.color.holo_green_dark));
            disableAllButtons();
            feedbackText.setText("Correct!"); // in order to motivate and engage the user on the output of the answer
            nextButton.setEnabled(true);  // the next button gets enabled only when the correct answer gets selected

        } else {
            // Wrong answer selected
            totalScore-= 3;
            if (soundOn){
                soundPool.play(soundWrong, 1, 1, 0, 0, 1);
            }

            // Mark red and disable only that button
            optionButtons[selectedIndex].setEnabled(false);
            optionButtons[selectedIndex].setBackgroundTintList(ContextCompat.getColorStateList(this,android.R.color.holo_red_dark));
            feedbackText.setText("Wrong!");
        }
    }

    // the dialog box that shows the result of the quiz
    private void showResultDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_result_rating, null);
        RatingBar ratingBar = dialogView.findViewById(R.id.result_rating);

        new AlertDialog.Builder(this)
                .setTitle("Quiz Completed!")
                .setMessage("Your totalScore: " + totalScore + " / " + (questionList.size() * 10))
                .setView(dialogView)
                .setCancelable(false)
                .setPositiveButton("Submit", (dialog, which) -> showPostQuizOptions())
                .show();
    }

    // the dialog box that shows the options after the quiz is completed
    private void showPostQuizOptions() {
        new AlertDialog.Builder(this)
                .setTitle("We appreciate your participation!")
                .setMessage("What would you like to do next?")
                .setCancelable(false)
                .setPositiveButton("Retry the multichoice quiz", (dialog, which) -> {
                    currentQuestionIndex = 0;
                    totalScore = 0;
                    updateQuestion(currentQuestionIndex);
                })
                .setNegativeButton("Go to the home screen", (dialog, which) -> {
                    startActivity(new Intent(this, MainActivity.class));
                    finish();
                })
                .setNeutralButton("Exit", (dialog, which) -> finishAffinity())
                .show();
    }

    private void disableAllButtons() {
        optionA.setEnabled(false);
        optionB.setEnabled(false);
        optionC.setEnabled(false);
        optionD.setEnabled(false);
    }

    private void enableAllButtons() {
        optionA.setEnabled(true);
        optionB.setEnabled(true);
        optionC.setEnabled(true);
        optionD.setEnabled(true);
    }

    //resets all the option buttons after a new question is loaded
    private void resetButtonStyles() {
        optionA.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.teal_200));
        optionB.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.teal_200));
        optionC.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.teal_200));
        optionD.setBackgroundTintList(ContextCompat.getColorStateList(this,R.color.teal_200));

        enableAllButtons();
    }

}