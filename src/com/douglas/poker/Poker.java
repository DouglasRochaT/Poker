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

        if(primeiraMao.compareTo(segundaMao) > 0){
            System.out.println("Primeira Mão ganhou!");
            System.out.println("Jogada: " + primeiraMao.getJogada());
            System.out.println("Carta alta: " + primeiraMao.getCartaAlta());
        } else if(primeiraMao.compareTo(segundaMao) < 0){
            System.out.println("Segunda Mão ganhou!");
            System.out.println("Jogada: " + segundaMao.getJogada());
            System.out.println("Carta alta: " + segundaMao.getCartaAlta());
        } else {
            System.out.println("Não foi possível definir o vencerdor ainda.");
        }
    }
}
