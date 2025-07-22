package com.pranav.quizoku;

// model for the multichoice quiz questions
public class QuestionMultiChoice {
    private final String question;
    private final String[] options;
    private final int correctIndex;

    public QuestionMultiChoice(String question, String[] options, int correctIndex) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestion() { return question; }
    public String[] getOptions() { return options; }
    public int getCorrectIndex() { return correctIndex; }
}
