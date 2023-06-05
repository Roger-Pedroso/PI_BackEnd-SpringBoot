/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.controller;

import com.pratti.pesquisa.dtos.LoginDto;
import com.pratti.pesquisa.dtos.UserDto;
import com.pratti.pesquisa.model.UserModel;
import com.pratti.pesquisa.service.LoginMessage;
import com.pratti.pesquisa.service.UserService;


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
public class UserController {
    final UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @PostMapping("/user")
    public ResponseEntity<Object> saveUser(@RequestBody @Validated UserDto userDto){
        if(userService.existsByCracha(userDto.getCracha())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Cracha is already in use");
        }
        
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userModel));
    }
    
    
    @GetMapping("/user")
    public ResponseEntity<List<UserModel>> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }
    
    @GetMapping("/user/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value= "id") UUID id){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value ="id") UUID id, @RequestBody @Validated UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        
        var userModel = userModelOptional.get();
        
        if(userDto.getCracha() != null){
            userModel.setCracha(userDto.getCracha());
        }
        
        if (userDto.getEmail() != null) {
            userModel.setEmail(userDto.getEmail());
        }
        
        if (userDto.getNascimento() != null) {
            userModel.setNascimento(userDto.getNascimento());
        }
        
        if (userDto.getNome() != null) {
            userModel.setNome(userDto.getNome());
        }
       
        if (userDto.getRamal() != null) {
            userModel.setRamal(userDto.getRamal());
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(userModel));
    }

    @PostMapping("/login/adm")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
        LoginMessage loginMessage = userService.loginMessage(loginDto);

        return ResponseEntity.ok(loginMessage);
    }
    
    @PutMapping("/user/change-password/{id}")
    public ResponseEntity<Object> loginUser(@PathVariable(value ="id") UUID id, @RequestBody @Validated UserDto userDto){
      Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        
        var userModel = userModelOptional.get();
        
        userModel.setSenha(userDto.getSenha());
        
        return ResponseEntity.status(HttpStatus.OK).body(userService.updatePassword(userModel));
    }
    
    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@PathVariable(value ="id") UUID id, @RequestBody @Validated UserDto userDto){
        Optional<UserModel> userModelOptional = userService.findById(id);
        if(!userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        
        var userModel = userModelOptional.get();
        
        userModel.setSenha(userDto.getSenha());
        
        return ResponseEntity.status(HttpStatus.OK).body(userService.updatePassword(userModel));
        userModel.setCracha(userDto.getCracha());
        userModel.setEmail(userDto.getEmail());
        userModel.setNascimento(userDto.getNascimento());
        userModel.setNome(userDto.getNome());
        userModel.setRamal(userDto.getRamal());
        userModel.setSenha(userDto.getSenha());
        
        return ResponseEntity.status(HttpStatus.OK).body(userService.save(userModel));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
        LoginMessage loginMessage = userService.loginMessage(loginDto);

        return ResponseEntity.ok(loginMessage);
    }
}
