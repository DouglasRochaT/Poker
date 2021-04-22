package com.douglas;

import com.douglas.poker.Poker;

public class Principal {
    public static void main(String[] args) {
        Poker jogo;
        if(args.length == 0){
            jogo = new Poker("");
        } else {
            jogo = new Poker(args[0]);
        }
        jogo.iniciar();
    }
}
