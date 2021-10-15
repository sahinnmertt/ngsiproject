package com.covid.info.service;


import com.covid.info.domain.Question;
import com.covid.info.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public void addQuestion(Question question) {
        question.setId(getRandomNumber(1,999999999));
        questionRepository.save(question);
    }

    @Override
    public void updateQuestion(Question question) {

        questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {

        List<Question> questionList = questionRepository.findAll();

        return questionList;
    }

    @Override
    public List<Question> getRandomQuestionList(List<Question> list, int totalItems) {

        Random rand = new Random();

        // create a temporary list for storing
        // selected element
        List<Question> newList = new ArrayList<>();
        for (int i = 0; i < totalItems; i++) {

            // take a random index between 0 to size
            // of given List
            int randomIndex = rand.nextInt(list.size());

            // add element in temporary list
            newList.add(list.get(randomIndex));

            // Remove selected element from original list
            list.remove(randomIndex);
        }
        return newList;
    }

    @Override
    public Question getQuestionById(int id) {
        return questionRepository.getOne(id);
    }



  /*  @Override
    public void updatePersonCovidInfo(int id) {
        animalRepository.setCovidInfo("Negative",id);

    }*/

    @Override
    public void deleteQuestionById(int id) {
        questionRepository.deleteById(id);
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
