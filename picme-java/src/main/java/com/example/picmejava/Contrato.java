package com.example.picmejava;

import java.util.Date;

public class Contrato {

    private Date dataContrato;
    private Cliente cliente;
    private Fotografo fotografo;

    public Contrato(Date dataContrato, Cliente cliente, Fotografo fotografo) {
        this.dataContrato = dataContrato;
        this.cliente = cliente;
        this.fotografo = fotografo;
    }

    //METODO

    //CRIAR CONTRATO
    public String criarContrato(Contrato contrato){
        return String.format("Data do contrato: " + dataContrato + "Cliente: " + cliente + "Fotografo: " + fotografo);
    }

    //GETTERS AND SETTERS

    public Date getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(Date dataContrato) {
        this.dataContrato = dataContrato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Fotografo getFotografo() {
        return fotografo;
    }

    public void setFotografo(Fotografo fotografo) {
        this.fotografo = fotografo;
    }
}
