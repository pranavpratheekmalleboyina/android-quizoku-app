package com.pranav.quizoku;

// model for the true false quiz questions
public class QuestionTrueFalse {
    private final String question;
    private final boolean answer;

    public QuestionTrueFalse(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() { return question; }
    public boolean getAnswer() { return answer; }
}
