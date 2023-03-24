/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.controller;

import com.pratti.pesquisa.dtos.QuestionDto;
import com.pratti.pesquisa.model.QuestionModel;
import com.pratti.pesquisa.service.QuestionService;
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
public class QuestionController {
    final QuestionService questionService;
    
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    
    @GetMapping("/question")
    public ResponseEntity<List<QuestionModel>> getAllQuestions(){
        return ResponseEntity.status(HttpStatus.OK).body(questionService.findAll());
    }
    
    @GetMapping("/question/{id}")
    public ResponseEntity<Object> getOneQuestion(@PathVariable(value= "id") UUID id){
        Optional<QuestionModel> questionModelOptional = questionService.findById(id);
        if(!questionModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(questionModelOptional.get());
    }
    
    @PostMapping("/question")
    public ResponseEntity<Object> saveSuperior(@RequestBody @Validated QuestionDto questionDto){
        if(questionService.existsByDescricao(questionDto.getDescricao())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Question is already in use");
        }
        
        var questionModel = new QuestionModel();
        BeanUtils.copyProperties(questionDto, questionModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.save(questionModel));
    }
    
    @PutMapping("/question/{id}")
    public ResponseEntity<Object> updateSector(@PathVariable(value ="id") UUID id, @RequestBody @Validated QuestionDto questionDto){
        Optional<QuestionModel> questionModelOptional = questionService.findById(id);
        if(!questionModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Question not found");
        }
        
        var questionModel = questionModelOptional.get();
        
        if(questionDto.getDescricao()!= null){
            questionModel.setDescricao(questionDto.getDescricao());
        }
        
        if (questionDto.getTipo()!= null) {
            questionModel.setTipo(questionDto.getTipo());
        }

        
        return ResponseEntity.status(HttpStatus.OK).body(questionService.save(questionModel));
    }
}
