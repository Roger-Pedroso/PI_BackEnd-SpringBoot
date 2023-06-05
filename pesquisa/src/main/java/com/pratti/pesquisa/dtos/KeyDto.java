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
    private UUID idSuperior;
    private UUID idQuiz;
    private int numberOfKeys;


    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    public void setNumberOfKeys(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
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
