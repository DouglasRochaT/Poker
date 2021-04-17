package com.douglas.poker.carta;

public enum EnumValor {
    T(10),
    J(11),
    Q(12),
    K(13),
    A(14);

    public int valor;

    EnumValor(int valor) {
        this.valor = valor;
    }
}
