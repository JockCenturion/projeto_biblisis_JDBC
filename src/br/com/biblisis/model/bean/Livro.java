/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;



public class Livro extends Obra {
    private String isbn;
    
    public Livro (int codObra, String titulo, String categoria, String autor, Data dataPublicacao, Editora editora, String isbn){
        super(codObra, titulo, categoria, autor, dataPublicacao, editora);
        this.isbn = isbn;
    }
    
    public String getIsbn() {
        return this.isbn;
    }
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
        @Override
    public String toString() {
        return "Livro{" + "codObra=" + super.getCodigoObra() + ", titulo=" + super.getTitulo() + ", categoria=" + super.getCategoria() + ", autor=" + super.getAutor() + ", dataPublicacao=" + super.getDataPublicacao() + ", ISBN=" + this.isbn + '}';
    }

    
    
}



