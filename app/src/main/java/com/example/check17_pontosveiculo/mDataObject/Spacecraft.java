package com.example.check17_pontosveiculo.mDataObject;

public class Spacecraft {
    int id;
    String nome,data,placa,numero,lacre;

    public Spacecraft(String nome, String data, String placa,String numero, String lacre) {
        this.nome = nome;
        this.data = data;
        this.placa = placa;
        this.numero = numero;
        this.lacre = lacre;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getData() {
        return data;
    }

    public String getPlaca() {
        return placa;
    }

    public String getNumero() {
        return numero;
    }

    public String getLacre() {
        return lacre;
    }

}
