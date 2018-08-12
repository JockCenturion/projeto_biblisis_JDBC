/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;


public class Usuario {
    private String nome, login, senha;
    
    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome  = nome;
    }
    
    public Usuario(String login) {
        this.login = login;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String novoNome) {
        this.nome = novoNome;
    }
 
    public boolean loginAutenticacao(Usuario u) {
        return u.login.equals(this.login) && u.senha.equals(this.senha);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof Usuario)) {
            return false;
        }
        
        Usuario other = (Usuario) obj;
        return (other.nome.equals(this.nome) && other.login.equals(this.login));
    }
    
    public String toString() {
        return this.nome + " - " + this.login + " - " + this.senha;
    }
}
