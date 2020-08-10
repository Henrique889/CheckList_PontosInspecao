package com.example.check17_pontosveiculo.mDataObject;

public class SpaceCraft_Containers {
    int id;
    String nome,data,placa_carreta,numero,lacre;

    public SpaceCraft_Containers(String nome, String data, String placa_carreta, String numero, String lacre) {
        this.nome = nome;
        this.data = data;
        this.placa_carreta = placa_carreta;
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

    public String getPlaca_Carreta() {
        return placa_carreta;
    }

    public String getNumero00() {
        return numero;
    }

    public String getLacre() {
        return lacre;
    }

}
