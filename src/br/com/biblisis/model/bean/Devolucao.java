/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;
import java.util.Date;


public class Devolucao {
    private int codDevolucao;
    private Funcionario funcionario;
    private Exemplar exemplar;
    private Obra obra;
    private Date dataDevolucao;

    public Devolucao(int codDevolucao, Funcionario funcionario, Exemplar exemplar, Obra obra, Date dataDevolucao ) {
        this.codDevolucao = codDevolucao;
        this.funcionario = funcionario;
        this.exemplar = exemplar;
        this.obra = obra;
        this.dataDevolucao = dataDevolucao;  
    }

    public int getCodDevolucao() {
        return codDevolucao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setCodDevolucao(int codDevolucao) {
        this.codDevolucao = codDevolucao;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    @Override
    public String toString() {
        return "Devolucao{" + "codDevolucao=" + codDevolucao + ", funcionario=" + funcionario.getNome() + ", exemplar=" + exemplar.getCodExemplar() + ", obra=" + obra.getTitulo() + ", dataDevolucao=" + dataDevolucao + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Devolucao)) {
            return false;
        }
        
        Devolucao other = (Devolucao) obj;
        return other.getCodDevolucao() == this.codDevolucao;
    }
    
    
    
}
