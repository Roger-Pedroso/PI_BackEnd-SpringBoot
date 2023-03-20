package com.pratti.pesquisa.dtos;

import java.util.UUID;

public class LoginDto {
    
    private UUID id;
    private String cracha;
    private String senha;

    public UUID getId() {
        return id;
    }
    public String getCracha() {
        return cracha;
    }
    public void setCracha(String cracha) {
        this.cracha = cracha;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public LoginDto(String cracha, String senha) {
        this.cracha = cracha;
        this.senha = senha;
    }

    
}
