/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 *
 * @author Roger
 */
@Entity
@Table(name = "questionario")
public class QuizModel {
    private static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String descricao;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "QuestaoQuestionario",
        joinColumns = @JoinColumn(name = "id_questionario"),
        inverseJoinColumns = @JoinColumn(name = "id_questao"))
    private Set<QuestionModel> question = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "id_superior")
    private SuperiorModel superior;
    
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;

    public SuperiorModel getSuperior() {
        return superior;
    }

    public void setSuperior(SuperiorModel superior) {
        this.superior = superior;
    }

    public SectorModel getSector() {
        return sector;
    }

    public void setSector(SectorModel sector) {
        this.sector = sector;
    }
    
    private SectorModel sector;
    public Set<QuestionModel> getQuestion() {
        return question;
    }

    public void setQuestion(Set<QuestionModel> question) {
        this.question = question;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    
}
