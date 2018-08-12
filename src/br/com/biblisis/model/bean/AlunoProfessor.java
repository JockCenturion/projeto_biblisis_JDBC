/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;


public class AlunoProfessor extends Usuario {
    private boolean status;
    
    public AlunoProfessor(String login, String senha, String nome, boolean status) {
        super(login, senha, nome);
        this.status = status;
    }
    
    public AlunoProfessor(String login) {
        super(login);
    }

    public void setStaus(boolean status) {
        this.status = status;
    }
    
    public boolean getStatus() {
        return this.status;
    }
    
    public String toString() {
        return super.toString() + " - " + status;
    }
    
    /*
    public Emprestimo fazEmprestimo (Usuario usuario, Exemplar exemplar){
        Date dataAtual = new Date ();
        Date dataLimite = geraDataLimite();
        Emprestimo novoEmprestimo = new Emprestimo (this, usuario, exemplar, exemplar.getObra(), dataAtual, dataLimite );
        
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
    */
    
}
