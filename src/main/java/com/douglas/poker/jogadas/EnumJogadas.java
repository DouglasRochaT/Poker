package com.douglas.poker.jogadas;

public enum EnumJogadas {
    Indefinido(0),
    Carta_Alta(1),
    Um_Par(2),
    Dois_Pares(3),
    Trinca(4),
    Straight(5),
    Flush(6),
    Full_House(7),
    Quadra(8),
    Straight_Flush(9),
    Royal_Flush(10);

    public int peso;

    EnumJogadas(int peso){
        this.peso = peso;
    }
}
