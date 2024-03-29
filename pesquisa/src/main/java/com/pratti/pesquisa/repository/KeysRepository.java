/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pratti.pesquisa.repository;

import com.pratti.pesquisa.model.KeyModel;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Roger
 */
public interface KeysRepository extends JpaRepository<KeyModel, UUID>{
    Optional<KeyModel> findBykeyAccess(String keyAccess);
    List<KeyModel> findByQuizId(UUID idQuiz);
}
