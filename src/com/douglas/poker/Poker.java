package com.douglas.poker;

import com.douglas.poker.carta.Carta;
import com.douglas.poker.console.PokerConsole;

public class Poker {

    public static void main(String[] args) {
        PokerConsole poker = new PokerConsole();
        Carta temp = poker.selicionaCarta();
        System.out.println(temp.getValor() + " " + temp.getNaipe());
    }
}
