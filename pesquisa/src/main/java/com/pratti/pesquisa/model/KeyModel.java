/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 *
 * @author Roger
 */
@Entity
@Table(name = "access_keys")
public class KeyModel {
    private static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(nullable = false)
    private String keyAccess;
    
    @Column(nullable = false)
    private boolean status;
    
    @ManyToOne
    @JoinColumn(name = "id_superior")
    private SuperiorModel superior;
     
    @ManyToOne
    @JoinColumn(name = "id_quiz")
    private QuizModel quiz;

    public KeyModel() {
    }

    public KeyModel(UUID id, String keyAccess, boolean status, SuperiorModel superior, QuizModel quiz) {
        this.id = id;
        this.keyAccess = keyAccess;
        this.status = status;
        this.superior = superior;
        this.quiz = quiz;
    }

    public String getKeyAccess() {
        return keyAccess;
    }

    public void setKeyAccess(String keyAccess) {
        this.keyAccess = keyAccess;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SuperiorModel getSuperior() {
        return superior;
    }

    public void setSuperior(SuperiorModel superior) {
        this.superior = superior;
    }

    public QuizModel getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizModel quiz) {
        this.quiz = quiz;
    }
    
    
    
}
