/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;
import java.util.Date;

public class Renovacao {
    private int codRenovacao;
    private Funcionario funcionario;
    private Exemplar exemplar;
    private Obra obra;
    private Date dataRenovacao;

    public Renovacao(int codRenovacao, Funcionario funcionario, Exemplar exemplar, Obra obra, Date dataRenovacao) {
        this.codRenovacao = codRenovacao;
        this.funcionario = funcionario;
        this.exemplar = exemplar;
        this.obra = obra;
        this.dataRenovacao = dataRenovacao;
    }

    public int getCodRenovacao() {
        return codRenovacao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Date getDataRenovacao() {
        return dataRenovacao;
    }

    public void setCodRenovacao(int codRenovacao) {
        this.codRenovacao = codRenovacao;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public void setDataRenovacao(Date dataRenovacao) {
        this.dataRenovacao = dataRenovacao;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }
    
    @Override
    public String toString() {
        return "Renovacao{" + "codRenovacao=" + codRenovacao + ", funcionario=" + funcionario.getNome() + ", exemplar=" + exemplar.getCodExemplar() + ", obra=" + obra.getTitulo() + ", dataRenovacao=" + dataRenovacao + '}';
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof Renovacao)) {
            return false;
        }
        
        Renovacao other = (Renovacao) obj;
        return other.getCodRenovacao() == this.getCodRenovacao();
        
    }
    
}
