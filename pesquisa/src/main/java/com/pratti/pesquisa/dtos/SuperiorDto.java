/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pratti.pesquisa.dtos;

import com.pratti.pesquisa.model.SectorModel;
import java.util.UUID;

/**
 *
 * @author Roger
 */
public class SuperiorDto {
    private String nome;
    private String cargo;
    private String cracha;
    private String email;
    private UUID idSector;

    public UUID getIdSector() {
        return idSector;
    }

    public void setIdSector(UUID idSector) {
        this.idSector = idSector;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
