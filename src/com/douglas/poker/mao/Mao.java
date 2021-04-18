package com.douglas.poker.mao;

import com.douglas.poker.carta.Carta;
import com.douglas.poker.io.PokerIO;

import java.util.ArrayList;
import java.util.List;

public class Mao {
    private final List<Carta> cartas;
    private final PokerIO io;
    private EnumJogadas jogada;

    public Mao(PokerIO io){
        this.cartas = new ArrayList<>();
        this.io = io;
        this.jogada = EnumJogadas.Indefinido;
    }

    public Mao solicitaCartas(){
        for (int i = 0; i < 5; i++) {
            this.cartas.add(io.selecionaCarta());
        }
        return this;
    }

    public void ordenaMao(){
        this.cartas.sort(Carta::compareTo);
    }

    public boolean flush(){
        String naipe = this.cartas.get(0).getNaipe();
        for (Carta carta: this.cartas) {
            if(!carta.getNaipe().equals(naipe)){
                return false;
            }
        }
        return true;
    }

    public boolean straight(){
        int valor = this.cartas.get(0).getValor();
        for (Carta carta: this.cartas) {
            if(carta.getValor() != valor++){
                return false;
            }
        }
        return true;
    }

    public boolean royalFlush(){
        int incremento = 0;
        for (Carta carta: this.cartas) {
            if(carta.getValor() != 10 + incremento){
                return false;
            };
            incremento++;
        }
        return true;
    }

    public int qtdValoresIguais(){
        int contador = 0, quantidade = 0;
        int valorComparado = cartas.get(0).getValor();
        for (Carta carta: cartas) {
            if(carta.getValor() == valorComparado){
                contador++;
            } else {
                if(contador > quantidade){
                    quantidade = contador;
                }
                valorComparado = carta.getValor();
                contador = 1;
            }
        }
        if(contador > quantidade){
            quantidade = contador;
        }
        return quantidade;
    }

    public Mao verificaMao(){
        this.ordenaMao();

        if(this.straight()){
            if(this.flush()){
                if(this.royalFlush()){
                    this.jogada = EnumJogadas.Royal_Flush;
                } else {
                    this.jogada = EnumJogadas.Straight_Flush;
                }
            } else {
                this.jogada = EnumJogadas.Straight;
            }
        } else {
            if(this.flush()){
                this.jogada = EnumJogadas.Flush;
            }
        }

        switch (this.qtdValoresIguais()){
            case 4:
                this.jogada = EnumJogadas.Quadra;
                break;
            case 3:
                this.jogada = EnumJogadas.Trinca;
                break;
            case 2:
                this.jogada = EnumJogadas.Um_Par;
                break;
        }

        System.out.println("Jogada: " + this.jogada + ", com " + this.qtdValoresIguais() + " valores iguais:");
        return this;
    }

}
