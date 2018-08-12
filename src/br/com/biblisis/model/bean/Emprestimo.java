/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*Trocar o tipo DATE por Data*/

package br.com.biblisis.model.bean;
import java.util.Date;


public class Emprestimo {
    private Funcionario funcionario;
    private Usuario usuario;
    private Exemplar exemplar;
    private Obra obra;
    private Data dataEmprestimo;
    private Data dataDevolucao;

    public Emprestimo(Funcionario funcionario, Usuario usuario, Exemplar exemplar, Obra obra, Data dataEmprestimo, Data dataDevolucao) {
        this.funcionario = funcionario;
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.obra = obra;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Exemplar getExemplar() {
        return exemplar;
    }

    public Data getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Data getDataDevolucao() {
        return dataDevolucao;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public void setDataEmprestimo(Data dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setDataDevolucao(Data dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }


    public boolean isAtraso (){
        return !dataDevolucao.depoisDaDataAtual();
    }
    

    public void calculaDataDevolucao(int dias) {
        this.dataDevolucao.adicionarDias(dias);
    }
    
    @Override
    public String toString() {
        return "Emprestimo{" + "funcionario=" + funcionario.getLogin() + ", usuario=" + usuario.getLogin() + ", exemplar=" + exemplar.getCodExemplar() + ", obra=" + obra.getTitulo() + ", dataEmprestimo=" + dataEmprestimo + ", dataDevolucao=" + dataDevolucao + '}';
    }
    
    
}
