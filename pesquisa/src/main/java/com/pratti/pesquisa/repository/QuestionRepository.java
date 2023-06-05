/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.pratti.pesquisa.repository;

import com.pratti.pesquisa.model.QuestionModel;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Roger
 */
public interface QuestionRepository extends JpaRepository<QuestionModel, UUID>{
    boolean existsByDescricao(String descricao);
}
