/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.controller;

import com.pratti.pesquisa.dtos.SuperiorDto;
import com.pratti.pesquisa.model.SuperiorModel;
import com.pratti.pesquisa.service.SuperiorService;
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
public class SuperiorController {
    final SuperiorService superiorService;
    
    public SuperiorController(SuperiorService superiorService) {
        this.superiorService = superiorService;
    }
    
    @GetMapping("/superior")
    public ResponseEntity<List<SuperiorModel>> getAllSuperiors(){
        return ResponseEntity.status(HttpStatus.OK).body(superiorService.findAll());
    }
    
    @GetMapping("/superior/{id}")
    public ResponseEntity<Object> getOneSuperior(@PathVariable(value= "id") UUID id){
        Optional<SuperiorModel> sectorModelOptional = superiorService.findById(id);
        if(!sectorModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sector not found");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(sectorModelOptional.get());
    }
    
    @PostMapping("/superior")
    public ResponseEntity<Object> saveSuperior(@RequestBody @Validated SuperiorDto superiorDto){
        if(superiorService.existsByCracha(superiorDto.getCracha())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Superior is already in use");
        }
        
        var superiorModel = new SuperiorModel();
        BeanUtils.copyProperties(superiorDto, superiorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(superiorService.save(superiorModel));
    }
    
    @PutMapping("/superior/{id}")
    public ResponseEntity<Object> updateSector(@PathVariable(value ="id") UUID id, @RequestBody @Validated SuperiorDto superiorDto){
        Optional<SuperiorModel> superiorModelOptional = superiorService.findById(id);
        if(!superiorModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Superior not found");
        }
        
        var superiorModel = superiorModelOptional.get();
        
        if(superiorDto.getNome()!= null){
            superiorModel.setNome(superiorDto.getNome());
        }
        
        if (superiorDto.getCargo()!= null) {
            superiorModel.setCargo(superiorDto.getCargo());
        }
        
        if (superiorDto.getEmail()!= null) {
            superiorModel.setEmail(superiorDto.getEmail());
        }
        
        if (superiorDto.getCracha()!= null) {
            superiorModel.setCracha(superiorDto.getCracha());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(superiorService.save(superiorModel));
    }
}
