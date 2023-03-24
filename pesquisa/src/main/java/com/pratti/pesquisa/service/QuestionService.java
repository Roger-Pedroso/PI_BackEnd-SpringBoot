/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.service;

import com.pratti.pesquisa.model.QuestionModel;
import com.pratti.pesquisa.model.SectorModel;
import com.pratti.pesquisa.repository.QuestionRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roger
 */
@Service
public class QuestionService {
    final QuestionRepository questionRepository;
    
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }
    
    public List<QuestionModel> findAll(){
        return questionRepository.findAll();
    }
    
    public Optional<QuestionModel> findById(UUID id){
        return questionRepository.findById(id);
    }
    
    public boolean existsByDescricao(String nome){
        return questionRepository.existsByDescricao(nome);
    }
    
    public QuestionModel save(QuestionModel questionModel){
        
        return questionRepository.save(questionModel);
    }
}
