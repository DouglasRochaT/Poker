package com.douglas.poker;

import com.douglas.poker.console.PokerConsole;
import com.douglas.poker.io.PokerIO;
import com.douglas.poker.mao.Mao;

public class Poker {
    private final Mao primeiraMao;
    private final Mao segundaMao;
    private final PokerIO io;

    public Poker() {
        this.io = new PokerConsole();
        this.primeiraMao = new Mao();
        this.segundaMao = new Mao();
    }

    public void iniciar() {
        io.iniciar(primeiraMao, segundaMao);
    }
}