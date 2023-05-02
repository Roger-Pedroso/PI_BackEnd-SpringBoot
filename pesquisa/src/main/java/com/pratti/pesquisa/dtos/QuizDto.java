/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.dtos;

import com.pratti.pesquisa.model.QuestionModel;
import com.pratti.pesquisa.model.QuizModel;
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
    private Set<UUID> questions;

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

    public Set<UUID> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<UUID> questions) {
        this.questions = questions;
    }

    public QuizDto(String nome, String descricao, Set<UUID> questions) {
        this.nome = nome;
        this.descricao = descricao;
        this.questions = questions;
    }
    
    // Método para converter de QuestionarioDTO para Questionario
    public QuizModel toQuiz() {
        QuizModel quiz = new QuizModel();
        quiz.setNome(this.getNome());
        quiz.setDescricao(this.getDescricao());
        Set<QuestionModel> questions = new HashSet<>();
        for (UUID questionId : this.getQuestions()) {
            QuestionModel questionModel = new QuestionModel();
            questionModel.setId(questionId);
            questions.add(questionModel);
        }
        quiz.setQuestions(questions);
        return quiz;
    }
}
