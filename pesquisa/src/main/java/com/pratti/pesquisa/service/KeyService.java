/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.service;

import com.pratti.pesquisa.model.KeyModel;
import com.pratti.pesquisa.repository.KeysRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roger
 */
@Service
public class KeyService {
     final KeysRepository keyRepository;
    
    public KeyService(KeysRepository keyRepository) {
        this.keyRepository = keyRepository;
    }
    
    public List<KeyModel> findAll(){
        return keyRepository.findAll();
    }
    
    public Optional<KeyModel> findById(UUID id){
        return keyRepository.findById(id);
    }
    
    public KeyModel save(KeyModel keyModel){
        
        return keyRepository.save(keyModel);
    }
}
