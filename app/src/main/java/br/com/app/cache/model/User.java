package br.com.app.cache.model;

import java.io.Serializable;

public class User implements Serializable {

    public User() {
        this.name = null;
        this.cpf = null;
        this.pass = null;
        this.accessToken = null;
    }

    private String name = "";
    private String cpf = "";
    private String pass = "";
    private String accessToken = "";

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }
}