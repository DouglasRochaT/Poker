package com.douglas.poker.carta;

public class Carta implements Comparable<Carta> {
    private int valor;
    private String naipe;

    public Carta(){
        this.valor = 0;
        this.naipe = "";
    }

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

    @Override
    public int compareTo(Carta carta) {
        return Integer.compare(this.valor, carta.valor);
    }
}
