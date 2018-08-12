/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;

/**
 *
 * @author romeu
 */
public class Editora {
    private int codEdit;
    private String nomeEdit;
    
    public Editora(int codEdit, String nomeEdit){
        this.codEdit    = codEdit;
        this.nomeEdit   = nomeEdit;
    }
    
    public Editora(String nomeEdit) {
        this.nomeEdit = nomeEdit;
    }
    
    public Editora(int codEdit) {
        this.codEdit    = codEdit;
        this.nomeEdit   = null;
    }

    public void setNomeEdit(String nomeEdit) {
        this.nomeEdit = nomeEdit;
    }

    public int getCodEdit() {
        return codEdit;
    }

    public String getNomeEdit() {
        return nomeEdit;
    }

    @Override
    public String toString() {
        return "Editora{" + "codEdit=" + codEdit + ", nomeEdit=" + nomeEdit + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof Editora)) {
            return false;
        }
        
        Editora other  = (Editora) obj;
        return other.codEdit == this.codEdit;
    }
}