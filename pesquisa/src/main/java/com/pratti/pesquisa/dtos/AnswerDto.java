/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.dtos;

import java.util.UUID;

/**
 *
 * @author Pichau
 */
public class AnswerDto {
    private String resposta;
    private UUID idQuestion;    
    private UUID idQuiz;
    
    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public UUID getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(UUID idQuestion) {
        this.idQuestion = idQuestion;
    }

    public UUID getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(UUID idQuiz) {
        this.idQuiz = idQuiz;
    }
}
