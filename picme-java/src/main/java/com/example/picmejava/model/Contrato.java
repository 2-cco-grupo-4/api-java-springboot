package com.example.picmejava.model;

import java.util.Date;

public class Contrato {

    private String dataContrato;
    private Cliente cliente;
    private Fotografo fotografo;

    public Contrato(String dataContrato, Cliente cliente, Fotografo fotografo) {
        this.dataContrato = dataContrato;
        this.cliente = cliente;
        this.fotografo = fotografo;
    }

    public String criarContrato(Contrato contrato){
        return String.format("Data do contrato: " + dataContrato + "Cliente: " + cliente + "Fotografo: " + fotografo);
    }

    public String getDataContrato() {
        return dataContrato;
    }

    public void setDataContrato(String dataContrato) {
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
