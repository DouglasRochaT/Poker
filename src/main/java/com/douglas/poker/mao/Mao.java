package com.douglas.poker.mao;

import com.douglas.poker.carta.Carta;
import com.douglas.poker.io.PokerIO;
import com.douglas.poker.jogadas.EnumJogadas;
import com.douglas.poker.jogadas.Jogadas;

import java.util.ArrayList;
import java.util.List;

public class Mao implements Comparable<Mao>{
    private List<Carta> cartas;
    private EnumJogadas jogada;

    public Mao(){
        this.cartas = new ArrayList<>();
        this.jogada = EnumJogadas.Indefinido;
    }

    public void setCartas(List<Carta> cartas) {
        this.cartas = cartas;
    }

    private void ordenaMao(){
        this.cartas.sort(Carta::compareTo);
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

    public int getValorCarta(int carta){
        return this.cartas.get(carta).getValor();
    }

    public Mao verificaMao(){
        this.ordenaMao();
        this.defineJogada();
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
            int cartaPrimeiraMao = this.getValorCarta(4);
            int cartaSegundaMao = outra.getValorCarta(4);
            if(cartaPrimeiraMao == 14 && this.getValorCarta(3) == 5){
                cartaPrimeiraMao = 5;
            }
            if(cartaSegundaMao == 14 && outra.getValorCarta(3) == 5){
                cartaSegundaMao = 5;
            }
            return Integer.compare(cartaPrimeiraMao, cartaSegundaMao);
        } else if(this.jogada == EnumJogadas.Trinca){
            return this.cartas.get(2).compareTo(outra.cartas.get(2));
        } else if(this.jogada == EnumJogadas.Dois_Pares){
            int primeiraMaoParBaixo, primeiraMaoParAlto, primeiraMaoKicker;
            int segundaMaoParBaixo, segundaMaoParAlto, segundaMaoKicker;
            if(this.getValorCarta(2) == this.getValorCarta(3)){
                primeiraMaoParBaixo = this.getValorCarta(0);
                primeiraMaoParAlto = this.getValorCarta(2);
                primeiraMaoKicker = this.getValorCarta(4);
            } else {
                primeiraMaoParBaixo = this.getValorCarta(1);
                primeiraMaoParAlto = this.getValorCarta(3);
                if(this.cartas.get(2).getValor() == this.getValorCarta(1)){
                    primeiraMaoKicker = this.getValorCarta(0);
                } else {
                    primeiraMaoKicker = this.getValorCarta(2);
                }
            }
            if(outra.getValorCarta(2) == outra.getValorCarta(3)){
                segundaMaoParBaixo = outra.getValorCarta(0);
                segundaMaoParAlto = outra.getValorCarta(2);
                segundaMaoKicker = outra.getValorCarta(4);
            } else {
                segundaMaoParBaixo = outra.getValorCarta(1);
                segundaMaoParAlto = outra.getValorCarta(3);
                if(outra.getValorCarta(2) == outra.getValorCarta(1)){
                    segundaMaoKicker = outra.getValorCarta(0);
                } else {
                    segundaMaoKicker = outra.getValorCarta(2);
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
            if(this.getValorCarta(0) == this.getValorCarta(1)){
                primeiraMaoValor = this.getValorCarta(0);
            } else if(this.getValorCarta(3) == this.getValorCarta(4)){
                primeiraMaoValor = this.getValorCarta(3);
            } else {
                primeiraMaoValor = this.getValorCarta(2);
            }
            if(outra.getValorCarta(0) == outra.getValorCarta(1)){
                segundaMaoValor = outra.getValorCarta(0);
            } else if(outra.getValorCarta(3) == outra.getValorCarta(4)){
                segundaMaoValor = outra.getValorCarta(3);
            } else {
                segundaMaoValor = outra.getValorCarta(2);
            }
            int compareTo = Integer.compare(primeiraMaoValor, segundaMaoValor);
            if(compareTo != 0){
                return compareTo;
            } else {
                List<Carta> primeiraMao = new ArrayList<>(this.cartas);
                primeiraMao.removeIf(carta -> carta.getValor() == primeiraMaoValor);
                List<Carta> segundaMao = new ArrayList<>(outra.cartas);
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

    public EnumJogadas getJogada() {
        return jogada;
    }
}
