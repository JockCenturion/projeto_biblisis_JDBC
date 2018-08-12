/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;



public class Periodico extends Obra {
    private String issn;
    
    public Periodico(int codObra, String titulo, String categoria, String autor, Data dataPublicacao, Editora editora, String issn){
        super(codObra, titulo, categoria, autor, dataPublicacao, editora);
        this.issn = issn;
    }
    
    public String getIssn() {
        return this.issn;
    }
    
    public void setIsbn(String issn) {
        this.issn = issn;
    }
    
         @Override
    public String toString() {
        return "Periódico{" + "codObra=" + super.getCodigoObra() + ", titulo=" + super.getTitulo() + ", categoria=" + super.getCategoria() + ", autor=" + super.getAutor() + ", dataPublicacao=" + super.getDataPublicacao() + ", ISSN=" + this.issn + '}';
    }
}
