package com.pratti.pesquisa.service;

public class LoginMessage {
    String message;
    Boolean status;
    String userId;
  
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
     public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public LoginMessage(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public LoginMessage(String message, Boolean status, String userId) {
        this.message = message;
        this.status = status;
        this.userId = userId;
    }
}
