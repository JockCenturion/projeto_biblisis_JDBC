/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;






public class Obra {
    private int codObra;
    private String tituloObra;
    private String categoriaObra;
    private String autorObra;
    private Data dataPublicacao;
    private Editora editora;
    
    
    public Obra(int codObra, String titulo, String categoria, String autor, Data dataPublicacao, Editora editora) {
        this.codObra                = codObra;
        this.tituloObra             = titulo;
        this.categoriaObra          = categoria;
        this.autorObra              = autor;
        this.dataPublicacao         = dataPublicacao;
        this.editora                = editora;
    }
    public Obra(String titulo, String categoria, String autor, Data dataPublicacao, Editora editora) {
        this.codObra                = 0;
        this.codObra                = codObra;
        this.tituloObra             = titulo;
        this.categoriaObra          = categoria;
        this.autorObra              = autor;
        this.dataPublicacao         = dataPublicacao;
        this.editora                = editora;
    }
    
   public Obra(int codObra) {
        this.codObra = codObra;
    }
    
    public int getCodigoObra() {
        return this.codObra;
    }

    public String getTitulo() {
        return tituloObra;
    }
    
    public void setTitulo(String titulo) {
        this.tituloObra = titulo;
    }
   
    public String getAutor() {
        return autorObra;
    }
    
    public void setAutor(String autor) {
        this.autorObra = autor;
    }

    public String getCategoria() {
        return categoriaObra;
    }
    
    public void setCategoria(String categoria) {
        this.categoriaObra = categoria;
    }

    public Data getDataPublicacao() {
        return dataPublicacao;
    }
    
    public void setDataPublicacao(Data dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    @Override
    public String toString() {
        return "Obra{" + "codObra=" + codObra + ", titulo=" + tituloObra + ", categoria=" + categoriaObra + ", autor=" + autorObra + ", dataPublicacao=" + dataPublicacao + ", editora=" + this.editora.getNomeEdit() + '}';
    }
    
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof Obra)) {
            return false;
        }
        
        Obra other = (Obra) obj;
        return other.codObra == this.codObra;
    }


    
    
}

