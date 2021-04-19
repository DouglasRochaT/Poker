package com.douglas.poker.mao;

import com.douglas.poker.carta.Carta;
import com.douglas.poker.io.PokerIO;
import com.douglas.poker.jogadas.EnumJogadas;
import com.douglas.poker.jogadas.Jogadas;

import java.util.ArrayList;
import java.util.List;

public class Mao implements Comparable<Mao>{
    private final List<Carta> cartas;
    private final PokerIO io;
    private EnumJogadas jogada;
    private int cartaAlta;

    public Mao(PokerIO io){
        this.cartas = new ArrayList<>();
        this.io = io;
        this.jogada = EnumJogadas.Indefinido;
        this.cartaAlta = 0;
    }

    public Mao solicitaCartas(){
        for (int i = 0; i < 5; i++) {
            this.cartas.add(io.selecionaCarta());
        }
        return this;
    }

    private void ordenaMao(){
        this.cartas.sort(Carta::compareTo);
    }

    private void defineCartaAlta(){
        this.cartaAlta = cartas.get(4).getValor();
    }

    private void defineJogada(){
        if (Jogadas.royalFlush(this.cartas)) {
            this.jogada = EnumJogadas.Royal_Flush;
        } else if(Jogadas.straightFlush(this.cartas)){
            this.jogada = EnumJogadas.Straight_Flush;
        } else if(Jogadas.quadra(this.cartas)) {
            this.jogada = EnumJogadas.Quadra;
        } else if(Jogadas.fullHouse(this.cartas)) {
            this.jogada = EnumJogadas.Full_House;
        } else if(Jogadas.flush(this.cartas)) {
            this.jogada = EnumJogadas.Flush;
        } else if(Jogadas.straight(this.cartas)){
            this.jogada = EnumJogadas.Straight;
        } else if(Jogadas.trinca(this.cartas)){
            this.jogada = EnumJogadas.Trinca;
        } else if(Jogadas.doisPares(this.cartas)){
            this.jogada = EnumJogadas.Dois_Pares;
        } else if(Jogadas.umPar(this.cartas)){
            this.jogada = EnumJogadas.Um_Par;
        } else {
            this.jogada = EnumJogadas.Carta_Alta;
        }
    }

    public Mao verificaMao(){
        this.ordenaMao();
        this.defineJogada();
        this.defineCartaAlta();

        System.out.println("Jogada: " + this.jogada + ", carta alta: " + this.cartaAlta + ".");
        return this;
    }

    @Override
    public int compareTo(Mao mao) {
        int resultado = Integer.compare(this.jogada.peso, mao.jogada.peso);
        if(resultado > 0 ){
            return 1;
        } else if (resultado < 0){
            return -1;
        }
        return 0;
    }

    public int getCartaAlta() {
        return cartaAlta;
    }

    public EnumJogadas getJogada() {
        return jogada;
    }
}
