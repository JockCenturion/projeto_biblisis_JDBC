/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;
import java.util.Calendar;
import java.util.Date;


public class Funcionario extends Usuario {
    private int codCargo;
    
    public Funcionario(String login, String senha, String nome, int codCargo) {
        super(login, senha, nome);
        this.codCargo = codCargo;
    }
    
    public Funcionario(String login) {
        super(login);
    }
    
    public int getCargo() {
        return this.codCargo;
    }
    
    public void setCargo(int codCargo) {
        this.codCargo = codCargo;
    }
    
    @Override
    public String toString() {
        return "Funcionario{" + " funcionario = " + super.getNome() + " login = " + super.getLogin() + " senha = " + super.getSenha() + " codigo = " + this.codCargo + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Funcionario)) {
            return false;
        }
        
        Funcionario other = (Funcionario) obj;
        return other.getLogin().equals(this.getLogin());
    }
    
    /*
    public Emprestimo fazEmprestimo (Usuario usuario, Exemplar exemplar, Obra obra){
        Data dtEmprestimo, dtDevolucao; 
        dtEmprestimo = dtDevolucao = new Data("yyyy-MM-dd HH:mm:ss");
        dtDevolucao.adicionarAnos(7);
        return  (new Emprestimo(this, usuario, exemplar, obra, dtEmprestimo, dtDevolucao));
    }*/
    
    /*
    public Emprestimo fazEmprestimo (Usuario usuario, Exemplar exemplar){
        Date dataAtual = new Date ();
        Date dataLimite = geraDataLimite();
        Emprestimo novoEmprestimo = new Emprestimo (this, usuario, exemplar, dataAtual, dataLimite );
        
        return novoEmprestimo;
    }
    
    private Date geraDataLimite (){
        Date dataLimite;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, +3); 
        dataLimite = calendar.getTime(); 
        
        return dataLimite;
    }
    
    public boolean verificaAtrasoDeEmprestimo (Emprestimo emprestimo){
        return emprestimo.isAtraso();
    }
    
    public Devolucao devolveEmprestimo (Emprestimo emprestimo, int codigoDaDevolucao){//EXPLICAR
        Date dataAtual = new Date ();
        Devolucao novaDevolucao = new Devolucao (codigoDaDevolucao, this, emprestimo.getExemplar(),dataAtual);
        return novaDevolucao;
    }
    */
    
            
 
}
