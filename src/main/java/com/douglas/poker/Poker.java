package com.douglas.poker;

import com.douglas.poker.io.PokerIO;
import com.douglas.poker.io.SeletorIO;
import com.douglas.poker.mao.Mao;

public class Poker {
    private final Mao primeiraMao;
    private final Mao segundaMao;
    private final PokerIO io;

    public Poker(String ioArg) {
        this.io = SeletorIO.criaInterface(ioArg);
        this.primeiraMao = new Mao();
        this.segundaMao = new Mao();
    }

    public void iniciar() {
        io.iniciar(primeiraMao, segundaMao);
    }
}