/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.dtos;

import com.pratti.pesquisa.model.QuestionModel;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Roger
 */
public class QuizDto {
    private String nome;
    private String descricao;
    private Set<QuestionModel> question = new HashSet<>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<QuestionModel> getQuestion() {
        return question;
    }

    public void setQuestion(Set<QuestionModel> question) {
        this.question = question;
    }
    
    
}
