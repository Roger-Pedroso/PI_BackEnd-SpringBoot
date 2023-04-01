/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.service;

import com.pratti.pesquisa.dtos.LoginDto;
import com.pratti.pesquisa.model.UserModel;
import com.pratti.pesquisa.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Roger
 */
@Service
public class UserService {
    
    /*@Autowired
    UserRepository userRepository;
    */
    
    final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    
    public UserModel save(UserModel userModel){
        
        userModel.setSenha(passwordEncoder().encode(userModel.getSenha()));
        
        return userRepository.save(userModel);
    }
    
    public List<UserModel> findAll(){
        return userRepository.findAll();
    }
            
    public boolean existsByCracha(String cracha){
        return userRepository.existsByCracha(cracha);
    }
    
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    public Optional<UserModel> findByCracha(String cracha){
        return userRepository.findByCracha(cracha);
    }
    
    public Optional<UserModel> findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    
    public Optional<UserModel> findById(UUID id){
        return userRepository.findById(id);
    }

    public Optional<UserModel> findOneByCrachaAndSenha(String cracha, String senha){
        return userRepository.findOneByCrachaAndSenha(cracha, senha);
    }
    
    public Optional<UserModel> findOneByEmailAndSenha(String email, String senha){
        return userRepository.findOneByEmailAndSenha(email, senha);
    }
    
    public LoginMessage loginMessage(LoginDto loginDto){
        String msg = null;

        Optional<UserModel> userModel1 = userRepository.findByEmail(loginDto.getEmail());
        if(userModel1.isPresent()) {
            String senha = loginDto.getSenha();
            String senhaEncriptada = userModel1.get().getSenha();

            Boolean senhaCorreta = passwordEncoder().matches(senha, senhaEncriptada);

            if(senhaCorreta){
                Optional<UserModel> userModel2 = userRepository.findOneByEmailAndSenha(loginDto.getEmail(), userModel1.get().getSenha());
                if(userModel2.isPresent()){
                    return new LoginMessage("Logado com Sucesso", true);
                }else {
                    return new LoginMessage("Falha ao Logar", false);
                }
            } else {
                return new LoginMessage("Senha incorreta", false);
            }
        }else {
            return new LoginMessage("Email  incorreto", false);
        }
    }
}
