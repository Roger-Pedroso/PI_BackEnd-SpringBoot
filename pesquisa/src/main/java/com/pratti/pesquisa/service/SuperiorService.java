/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.service;

import com.pratti.pesquisa.model.SuperiorModel;
import com.pratti.pesquisa.repository.SuperiorRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roger
 */
@Service
public class SuperiorService {
     final SuperiorRepository superiorRepository;
    
    public SuperiorService(SuperiorRepository superiorRepository) {
        this.superiorRepository = superiorRepository;
    }
    
    public List<SuperiorModel> findAll(){
        return superiorRepository.findAll();
    }
    
    public Optional<SuperiorModel> findById(UUID id){
        return superiorRepository.findById(id);
    }
    
    public boolean existsByCracha(String cracha){
        return superiorRepository.existsByCracha(cracha);
    }
    
    public SuperiorModel save(SuperiorModel superiorModel){
        
        return superiorRepository.save(superiorModel);
    }


}
