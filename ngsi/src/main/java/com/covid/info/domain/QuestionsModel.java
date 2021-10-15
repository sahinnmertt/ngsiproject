package com.covid.info.domain;

import java.util.ArrayList;
import java.util.List;

public class QuestionsModel {

    private String[] answers;
    private Boolean[] results;
    private List<Question> randomQuestions;

    public Boolean[] getResults() {
        return results;
    }

    public void setResults(Boolean[] results) {
        this.results = results;
    }

    public List<Question> getRandomQuestions() {
        return randomQuestions;
    }

    public void setRandomQuestions(List<Question> randomQuestions) {
        this.randomQuestions = randomQuestions;
    }

    public QuestionsModel(){}
    public QuestionsModel(int size){
        answers = new String[size];
        results = new Boolean[size];
        randomQuestions = new ArrayList<Question>();
    }
    public String[] getAnswers() {
        return answers;
    }
    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

}
