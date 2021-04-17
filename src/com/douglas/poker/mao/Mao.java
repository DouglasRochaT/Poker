package com.douglas.poker.mao;

import com.douglas.poker.carta.Carta;
import com.douglas.poker.io.PokerIO;

import java.util.ArrayList;
import java.util.List;

public class Mao {
    private final List<Carta> cartas;
    private final PokerIO io;

    public Mao(PokerIO io){
        this.cartas = new ArrayList<>();
        this.io = io;
    }

    public void solicitaCartas(){
        for (int i = 0; i < 5; i++) {
            this.cartas.add(io.selecionaCarta());
        }
    }

    public List<Carta> getCartas() {
        return cartas;
    }
}
