package com.douglas.poker;

import com.douglas.poker.carta.Carta;
import com.douglas.poker.console.PokerConsole;
import com.douglas.poker.mao.Mao;

public class Poker {
    public static void main(String[] args) {
        Mao temp = new Mao(new PokerConsole());
        temp.solicitaCartas();
        for (Carta carta: temp.getCartas()) {
            System.out.println(carta.getValor());
            System.out.println(carta.getNaipe());
        }
    }
}
