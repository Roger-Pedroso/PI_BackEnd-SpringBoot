/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.controller;

import com.pratti.pesquisa.model.Administrador;
import com.pratti.pesquisa.repository.AdministradorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Roger
 */
@RestController
@CrossOrigin("http://localhost:3000")
public class AdministradorController {
    @Autowired
    private AdministradorRepository userRepository;
    
    @PostMapping("/adm")
    Administrador newAdm(@RequestBody Administrador newAdm){
        return userRepository.save(newAdm);
    }
    
    @GetMapping("/adm")
    List<Administrador> getAllAdms(){
        return userRepository.findAll();
    }
}
