/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pratti.pesquisa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pratti.pesquisa.model.User;

/**
 *
 * @author Roger
 */
public interface UserRepository extends JpaRepository<User, Long>{
    
}
