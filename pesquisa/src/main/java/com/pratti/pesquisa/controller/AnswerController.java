/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.controller;

import com.pratti.pesquisa.dtos.AnswerDto;
import com.pratti.pesquisa.model.AnswerModel;
import com.pratti.pesquisa.model.QuestionModel;
import com.pratti.pesquisa.model.QuizModel;
import com.pratti.pesquisa.service.AnswerService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Pichau
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AnswerController {
    final AnswerService answerService;
    
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }
    
    @GetMapping("/answer")
    public ResponseEntity<List<AnswerModel>> getAllAnswers(){       
        return ResponseEntity.status(HttpStatus.OK).body(answerService.findAll());
    }
    
    @GetMapping("/answer/{id}")
    public ResponseEntity<Object> getOneAnswer(@PathVariable(value= "id") UUID id){
        Optional<AnswerModel> answerModelOptional = answerService.findById(id);
        if(!answerModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Answer not found");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(answerModelOptional.get());
    }
    
    @PostMapping("/answer")
    public ResponseEntity<AnswerModel> createAnswer(@RequestBody @Validated AnswerDto answerDto) {
        var answerModel = new AnswerModel();     
        var questionModel = new QuestionModel();
        var quizModel = new QuizModel();
        
        questionModel.setId(answerDto.getIdQuestion());
        quizModel.setId(answerDto.getIdQuiz());
        
        answerModel.setQuestion(questionModel);
        answerModel.setQuiz(quizModel);
        
        BeanUtils.copyProperties(answerDto, answerModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(answerService.save(answerModel));
    }
}
