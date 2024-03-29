package com.pratti.pesquisa.dtos;

import java.util.UUID;

public class LoginDto {
    
    private UUID id;
    private String email;
    private String cracha;
    private String senha;

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public LoginDto(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    
}
