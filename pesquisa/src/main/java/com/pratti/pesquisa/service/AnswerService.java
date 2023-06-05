/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.service;

import com.pratti.pesquisa.model.AnswerModel;
import com.pratti.pesquisa.repository.AnswerRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 *
 * @author Pichau
 */
@Service
public class AnswerService {
     final AnswerRepository answerRepository;
    
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
    
    public List<AnswerModel> findAll(){
        return answerRepository.findAll();
    }
    
    public List<AnswerModel> findByQuestion(String question){
        return answerRepository.findByQuestion(question);
    }
    
    public Optional<AnswerModel> findById(UUID id){
        return answerRepository.findById(id);
    }
    
    public AnswerModel save(AnswerModel answerModel){
        return answerRepository.save(answerModel);
    }

}
