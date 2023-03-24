/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.pratti.pesquisa.dtos.SectorDto;
import com.pratti.pesquisa.model.SectorModel;
import com.pratti.pesquisa.service.SectorService;
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
public class SectorController {
    final SectorService sectorService;
    
    public SectorController(SectorService sectorService) {
        this.sectorService = sectorService;
    }
    
    @GetMapping("/sector")
    public ResponseEntity<List<SectorModel>> getAllSectors(){
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.findAll());
    }
    
    @GetMapping("/sector/{id}")
    public ResponseEntity<Object> getOneSector(@PathVariable(value= "id") UUID id){
        Optional<SectorModel> sectorModelOptional = sectorService.findById(id);
        if(!sectorModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sector not found");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(sectorModelOptional.get());
    }
    
    @PostMapping("/sector")
    public ResponseEntity<Object> saveUser(@RequestBody @Validated SectorDto sectorDto){
        if(sectorService.existsByNome(sectorDto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Nome is already in use");
        }
        
        var sectorModel = new SectorModel();
        BeanUtils.copyProperties(sectorDto, sectorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(sectorService.save(sectorModel));
    }
    
    @PutMapping("/sector/{id}")
    public ResponseEntity<Object> updateSector(@PathVariable(value ="id") UUID id, @RequestBody @Validated SectorDto sectorDto){
        Optional<SectorModel> sectorModelOptional = sectorService.findById(id);
        if(!sectorModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sector not found");
        }
        
        var sectorModel = sectorModelOptional.get();
        
        if(sectorDto.getNome()!= null){
            sectorModel.setNome(sectorDto.getNome());
        }
        
        if (sectorDto.getStatus()!= null) {
            sectorModel.setStatus(sectorDto.getStatus());
        }
        
        if (sectorDto.getTipo()!= null) {
            sectorModel.setTipo(sectorDto.getTipo());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(sectorService.save(sectorModel));
    }
}
