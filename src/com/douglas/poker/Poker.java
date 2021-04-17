package com.douglas.poker;

import com.douglas.poker.console.PokerConsole;
import com.douglas.poker.io.PokerIO;
import com.douglas.poker.mao.Mao;

public class Poker {
    private final Mao primeiraMao;
    private final Mao segundaMao;

    public Poker(){
        PokerIO io = new PokerConsole();
        this.primeiraMao = new Mao(io);
        this.segundaMao = new Mao(io);
    }

    public void iniciar(){
        primeiraMao.solicitaCartas().verificaMao();
        segundaMao.solicitaCartas().verificaMao();
    }

}
