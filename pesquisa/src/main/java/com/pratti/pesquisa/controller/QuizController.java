/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.controller;

import com.pratti.pesquisa.dtos.QuestionDto;
import com.pratti.pesquisa.dtos.QuizDto;
import com.pratti.pesquisa.model.QuestionModel;
import com.pratti.pesquisa.model.QuizModel;
import com.pratti.pesquisa.service.QuizService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roger
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class QuizController { 
    final QuizService quizService;
    
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("/quiz")
    public ResponseEntity<List<QuizModel>> getAllQuizes(){
        return ResponseEntity.status(HttpStatus.OK).body(quizService.findAll());
    }
    
    @GetMapping("/quiz/{id}")
    public ResponseEntity<Object> getOneQuiz(@PathVariable(value= "id") UUID id){
        Optional<QuizModel> quizModelOptional = quizService.findById(id);
        if(!quizModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(quizModelOptional.get());
    }
    
    @PostMapping("/quiz")
    public ResponseEntity<Object> saveQuiz(@RequestBody @Validated QuizDto quizDto){
        if(quizService.existsByDescricao(quizDto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Quiz is already in use");
        }
        
        var quizModel = new QuizModel();
        BeanUtils.copyProperties(quizDto, quizModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.save(quizModel));
    }
    
    @PutMapping("/quiz/{id}")
    public ResponseEntity<Object> updateQuiz(@PathVariable(value ="id") UUID id, @RequestBody @Validated QuizDto quizDto){
        Optional<QuizModel> quizModelOptional = quizService.findById(id);
        if(!quizModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Quiz not found");
        }
        
        var quizModel = quizModelOptional.get();
        
        if(quizDto.getDescricao()!= null){
            quizModel.setDescricao(quizDto.getDescricao());
        }
        
        if (quizDto.getNome()!= null) {
            quizModel.setNome(quizDto.getNome());
        }
        
        if (quizDto.getQuestion()!= null) {
            quizModel.setQuestion(quizDto.getQuestion());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(quizService.save(quizModel));
    }

}
