/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.biblisis.model.bean;
import java.util.Date;
import java.util.Objects;


public class Reserva {
    private Usuario usuario;
    private Obra obra;
    private Exemplar exemplar;
    private Data dataReserva;
    private Data dataRetirada;
    
    public Reserva (Usuario usuario, Obra obra, Exemplar exemplar, Data dataReserva){
        this.usuario = usuario;
        this.obra = obra;
        this.exemplar = exemplar;
        this.dataReserva = dataReserva;
    }
    
    public Reserva (Usuario usuario, Obra obra, Exemplar exemplar){
        this.usuario = usuario;
        this.obra = obra;
        this.exemplar = exemplar;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public Exemplar getExemplar() {
        return this.exemplar;
    }

    public Data getDataReserva() {
        return this.dataReserva;
    }

    public Data getDataRetirada() {
        return this.dataRetirada;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    public void setDataReserva(Data dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void setDataRetirada(Data dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    @Override
    public String toString() {
        return "Reserva{" + "usuario=" + usuario.getNome() + ", obra=" + obra.getTitulo() + ", exemplar=" + exemplar.getCodExemplar() + ", dataReserva=" + dataReserva + ", dataRetirada=" + dataRetirada + '}';
    }
   
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (!(obj instanceof Reserva)) {
            return false;
        }
        
        Reserva other = (Reserva) obj;
        
        return other.getExemplar().getCodExemplar()== this.getExemplar().getCodExemplar();
    }
    
    
}
