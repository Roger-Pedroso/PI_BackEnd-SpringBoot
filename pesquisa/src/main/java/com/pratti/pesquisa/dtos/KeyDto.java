/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.dtos;

import java.util.UUID;

/**
 *
 * @author Roger
 */
public class KeyDto {
    private UUID id;
    private UUID idSuperior;
    private UUID idQuiz;

    public KeyDto(UUID id, UUID idSuperior, UUID idQuiz) {
        this.id = id;
        this.idSuperior = idSuperior;
        this.idQuiz = idQuiz;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getIdSuperior() {
        return idSuperior;
    }

    public void setIdSuperior(UUID idSuperior) {
        this.idSuperior = idSuperior;
    }

    public UUID getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(UUID idQuiz) {
        this.idQuiz = idQuiz;
    }
    
    
}
