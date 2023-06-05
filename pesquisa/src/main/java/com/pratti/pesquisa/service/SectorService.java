/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.service;

import com.pratti.pesquisa.model.SectorModel;
import com.pratti.pesquisa.model.UserModel;
import com.pratti.pesquisa.repository.SectorRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roger
 */
@Service
public class SectorService {
    final SectorRepository sectorRepository;
    
    public SectorService(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }
    
    public List<SectorModel> findAll(){
        return sectorRepository.findAll();
    }
    
    public Optional<SectorModel> findById(UUID id){
        return sectorRepository.findById(id);
    }
    
    public boolean existsByNome(String nome){
        return sectorRepository.existsByNome(nome);
    }
    
    public SectorModel save(SectorModel sectorModel){
        
        return sectorRepository.save(sectorModel);
    }
}
