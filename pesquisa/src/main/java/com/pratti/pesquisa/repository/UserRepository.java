/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pratti.pesquisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pratti.pesquisa.model.UserModel;

import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author Roger
 */
public interface UserRepository extends JpaRepository<UserModel, UUID>{
    boolean existsByCracha(String cracha);
    boolean existsByEmail(String email);
    Optional<UserModel> findByCracha(String cracha);
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findOneByCrachaAndSenha(String cracha, String senha);
    Optional<UserModel> findOneByEmailAndSenha(String email, String senha);
}
