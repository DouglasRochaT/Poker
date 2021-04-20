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

    private int desempate(Mao outra){
        if(this.jogada == EnumJogadas.Straight_Flush){
            return this.cartas.get(4).compareTo(outra.cartas.get(4));
        } else if(this.jogada == EnumJogadas.Quadra){
            return this.cartas.get(2).compareTo(outra.cartas.get(2));
        } else if(this.jogada == EnumJogadas.Full_House){
            return this.cartas.get(2).compareTo(outra.cartas.get(2));
        } else if(this.jogada == EnumJogadas.Flush){
            for (int carta = 4; carta > 0; carta--) {
                int compareTo = this.cartas.get(carta).compareTo(outra.cartas.get(carta));
                if(compareTo != 0){
                    return compareTo;
                }
            }
            return 0;
        } else if(this.jogada == EnumJogadas.Straight){
            int cartaPrimeiraMao = this.cartas.get(4).getValor();
            int cartaSegundaMao = outra.cartas.get(4).getValor();
            if(cartaPrimeiraMao == 14 && this.cartas.get(3).getValor() == 5){
                cartaPrimeiraMao = 5;
            }
            if(cartaSegundaMao == 14 && outra.cartas.get(3).getValor() == 5){
                cartaSegundaMao = 5;
            }
            return Integer.compare(cartaPrimeiraMao, cartaSegundaMao);
        } else if(this.jogada == EnumJogadas.Trinca){
            return this.cartas.get(2).compareTo(outra.cartas.get(2));
        } else if(this.jogada == EnumJogadas.Dois_Pares){
            int primeiraMaoParBaixo, primeiraMaoParAlto, primeiraMaoKicker;
            int segundaMaoParBaixo, segundaMaoParAlto, segundaMaoKicker;
            if(this.cartas.get(2).getValor() == this.cartas.get(3).getValor()){
                primeiraMaoParBaixo = this.cartas.get(0).getValor();
                primeiraMaoParAlto = this.cartas.get(2).getValor();
                primeiraMaoKicker = this.cartas.get(4).getValor();
            } else {
                primeiraMaoParBaixo = this.cartas.get(1).getValor();
                primeiraMaoParAlto = this.cartas.get(3).getValor();
                if(this.cartas.get(2).getValor() == this.cartas.get(1).getValor()){
                    primeiraMaoKicker = this.cartas.get(0).getValor();
                } else {
                    primeiraMaoKicker = this.cartas.get(2).getValor();
                }
            }
            if(outra.cartas.get(2).getValor() == outra.cartas.get(3).getValor()){
                segundaMaoParBaixo = outra.cartas.get(0).getValor();
                segundaMaoParAlto = outra.cartas.get(2).getValor();
                segundaMaoKicker = this.cartas.get(4).getValor();
            } else {
                segundaMaoParBaixo = outra.cartas.get(1).getValor();
                segundaMaoParAlto = outra.cartas.get(3).getValor();
                if(this.cartas.get(2).getValor() == this.cartas.get(1).getValor()){
                    segundaMaoKicker = this.cartas.get(0).getValor();
                } else {
                    segundaMaoKicker = this.cartas.get(2).getValor();
                }
            }
            int compareTo = Integer.compare(primeiraMaoParAlto, segundaMaoParAlto);
            if(compareTo != 0){
                return compareTo;
            } else {
                compareTo = Integer.compare(primeiraMaoParBaixo, segundaMaoParBaixo);
                if(compareTo != 0){
                    return compareTo;
                } else {
                    return Integer.compare(primeiraMaoKicker, segundaMaoKicker);
                }
            }
        } else if(this.jogada == EnumJogadas.Um_Par){
            int primeiraMaoValor;
            int segundaMaoValor;
            if(this.cartas.get(0).getValor() == this.cartas.get(1).getValor()){
                primeiraMaoValor = this.cartas.get(0).getValor();
            } else if(this.cartas.get(3).getValor() == this.cartas.get(4).getValor()){
                primeiraMaoValor = this.cartas.get(3).getValor();
            } else {
                primeiraMaoValor = this.cartas.get(2).getValor();
            }
            if(outra.cartas.get(0).getValor() == outra.cartas.get(1).getValor()){
                segundaMaoValor = outra.cartas.get(0).getValor();
            } else if(outra.cartas.get(3).getValor() == outra.cartas.get(4).getValor()){
                segundaMaoValor = outra.cartas.get(3).getValor();
            } else {
                segundaMaoValor = outra.cartas.get(2).getValor();
            }
            int compareTo = Integer.compare(primeiraMaoValor, segundaMaoValor);
            if(compareTo != 0){
                return compareTo;
            } else {
                List<Carta> primeiraMao = new ArrayList<Carta>(this.cartas);
                primeiraMao.removeIf(carta -> carta.getValor() == primeiraMaoValor);
                List<Carta> segundaMao = new ArrayList<Carta>(outra.cartas);
                segundaMao.removeIf(carta -> carta.getValor() == segundaMaoValor);

                for (int carta = 2; carta > 0; carta--) {
                    compareTo = primeiraMao.get(carta).compareTo(segundaMao.get(carta));
                    if(compareTo != 0){
                        return compareTo;
                    }
                }
                return 0;
            }
        } else if(this.jogada == EnumJogadas.Carta_Alta){
            for (int carta = 4; carta > 0; carta--) {
                int compareTo = this.cartas.get(carta).compareTo(outra.cartas.get(carta));
                if(compareTo != 0){
                    return compareTo;
                }
            }
        }
        return  0;
    }

    @Override
    public int compareTo(Mao outra) {
        int resultado = Integer.compare(this.jogada.peso, outra.jogada.peso);
        if(resultado > 0 ){
            return 1;
        } else if (resultado < 0) {
            return -1;
        } else {
            return this.desempate(outra);
        }
    }

    public int getCartaAlta() {
        return cartaAlta;
    }

    public EnumJogadas getJogada() {
        return jogada;
    }
}
