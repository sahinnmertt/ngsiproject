package com.covid.info.service;


import com.covid.info.domain.Question;

import java.util.List;

public interface QuestionService {
    void addQuestion(Question question);
    void updateQuestion(Question question);
    List<Question> getAllQuestions();
    Question getQuestionById(int id);
    //Animal findByEarring(int earring);
    //void updatePersonCovidInfo(int id);
    void deleteQuestionById(int id);
    List<Question> getRandomQuestionList(List<Question> list, int totalItems);
}
