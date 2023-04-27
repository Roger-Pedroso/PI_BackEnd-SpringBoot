/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.service;

import com.pratti.pesquisa.model.QuizModel;
import com.pratti.pesquisa.repository.QuizRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roger
 */
@Service
public class QuizService {
    final QuizRepository quizRepository;
    
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    
    public List<QuizModel> findAll(){
        return quizRepository.findAll();
    }
    
    public Optional<QuizModel> findById(UUID id){
        return quizRepository.findById(id);
    }
    
    public boolean existsByDescricao(String nome){
        return quizRepository.existsByDescricao(nome);
    }
    
    public QuizModel save(QuizModel quizModel){
        
        return quizRepository.save(quizModel);
    }
}
