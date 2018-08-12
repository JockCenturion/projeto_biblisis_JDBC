/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;


public class Exemplar {
    private int codEx;
    private Obra obra;
    private boolean isEmprestado;
    
    public Exemplar(int codExemplar, Obra obra, boolean isEmprestado) {
        this.codEx          = codExemplar;
        this.obra           = obra;
        this.isEmprestado   = isEmprestado;
    }
    
    public Exemplar(int codExemplar, Obra obra) {
        this.codEx          = codExemplar;
        this.obra           = obra;
        this.isEmprestado   = false;
    }
    public boolean getIsEmprestado() {
        return this.isEmprestado;
    }
    
    public void setIsEmprestado(boolean status) {
        this.isEmprestado = status;
    }
    
    public int getCodExemplar() {
        return this.codEx;
    }
    
    public Obra getObra() {
        return this.obra;
    }
    
    public void setObra(Obra obra) {
        this.obra = obra;
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof Exemplar)) {
            return false;
        }
        
        Exemplar other  = (Exemplar) obj;
        return other.codEx == this.codEx && other.obra.equals(this.obra);
    }

    @Override
    public String toString() {
        return "Exemplar{" + "codEx=" + codEx + ", obra=" + obra.getTitulo() + ", isEmprestado=" + isEmprestado + '}';
    }
}
