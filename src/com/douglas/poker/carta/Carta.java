package com.douglas.poker.carta;

public class Carta {
    private int valor = 0;
    private String naipe = "";

    public Carta setValor(int valor) {
        this.valor = valor;
        return this;
    }

    public Carta setNaipe(String naipe) {
        this.naipe = naipe;
        return this;
    }

    public int getValor() {
        return valor;
    }

    public String getNaipe() {
        return naipe;
    }
}
