package com.covid.info.controller;


import com.covid.info.domain.Question;

import com.covid.info.domain.QuestionsModel;
import com.covid.info.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    QuestionService questionService;

   @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterPage(){
       return new ModelAndView("addQuestion", "question", new Question());
    }
    @RequestMapping(value="/register", method= RequestMethod.POST)
    public String handleRegisterForm(Model model, @Valid @ModelAttribute("question") Question question, BindingResult bindingResult) throws SQLException {



        questionService.addQuestion(question);

        return "redirect:/";
    }

    @RequestMapping("/questions")
    public ModelAndView getUsersPage(){
        return new ModelAndView("questions","questions", questionService.getAllQuestions());
    }

    @RequestMapping("/answer")
    public String getAnswerPage(Model model){

        List<Question> questions = questionService.getRandomQuestionList(questionService.getAllQuestions(),10);

        QuestionsModel qModel = new QuestionsModel(10);
        qModel.setRandomQuestions(questions);
        qModel.getRandomQuestions();
        model.addAttribute("qModel", qModel);
        return "answerQuestions";
    }


    public ModelAndView getResultPage(Model model, @ModelAttribute("qModel") QuestionsModel qModel){
       Boolean[] results = new Boolean[10];
       Arrays.fill(results, Boolean.FALSE);

       System.out.println(Arrays.toString(results));
        for (int i = 0; i < 10 ; i++){

            if (qModel.getRandomQuestions().get(i).getAnswer().equals(qModel.getAnswers()[i])){
                results[i]=true;
                System.out.println(results);
            }
            else{
                results[i]=false;
                System.out.println(results);
            }

        }
        qModel.setResults(results);
        model.addAttribute("qModel", qModel);
        return new ModelAndView("result","qModel", qModel);
    }


    @PostMapping(path = "/checkAnswers")
    public ModelAndView questionnairePost(Model model, @ModelAttribute("qModel") QuestionsModel qModel)
    {
        System.out.println(qModel.getRandomQuestions().get(0).getAnswer());
        System.out.println(qModel.getAnswers());


        return getResultPage(model,qModel);
    }

   /* @RequestMapping(value="/updateCovidInfo/{id}", method= RequestMethod.POST)
    public String updateStatus(@PathVariable("id") int id){
        animalService.updatePersonCovidInfo(id);
        return "redirect:/patients";
    }*/

    @RequestMapping(value = "/deleteQuestion/{id}", method = RequestMethod.POST)
    public String handleItemDelete(@PathVariable("id") int id) {
        questionService.deleteQuestionById(id);
        return "redirect:/questions";
    }

    @RequestMapping(value = "/updateQuestion/{id}", method = RequestMethod.POST)
    public ModelAndView ModelAndView(@PathVariable("id") int id) {
        return new ModelAndView("update","question", questionService.getQuestionById(id));
    }

    @RequestMapping(value="/update/{id}", method= RequestMethod.POST)
    public String updateQuestion(Model model, @Valid @ModelAttribute("question") Question question, BindingResult bindingResult) throws SQLException {

        questionService.updateQuestion(question);
        return "redirect:/";
    }


}
