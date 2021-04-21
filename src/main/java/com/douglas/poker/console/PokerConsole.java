package com.douglas.poker.console;

import com.douglas.poker.carta.Carta;
import com.douglas.poker.carta.EnumNaipe;
import com.douglas.poker.carta.EnumValor;
import com.douglas.poker.io.PokerIO;
import com.douglas.poker.mao.Mao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class PokerConsole implements PokerIO {
    Scanner leitor;

    public PokerConsole(){
        this.leitor = new Scanner(System.in);
    }

    @Override
    public void iniciar(Mao primeiraMao, Mao segundaMao) {
        System.out.println("Poker");
        System.out.println("");
        System.out.println("Exemplos de entrada:");
        System.out.println("5H 5C 6S 7S KD 5D 8C 9S JS AC");
        System.out.println("");
        System.out.println("Insira as cartas para a primeira mão individualmente:");
        primeiraMao.setCartas(this.selecionaCartas());
        System.out.println("Insira as cartas para a segunda mão individualmente:");
        segundaMao.setCartas(this.selecionaCartas());
        primeiraMao.verificaMao();
        segundaMao.verificaMao();
        this.exibeGanhador(primeiraMao, segundaMao);
    }

    private List<Carta> selecionaCartas(){
        List<Carta> cartas = new ArrayList<>();

        while(cartas.size() < 5){
            Carta carta = new Carta();
            System.out.println("Escolha o valor e o naipe da carta:");
            while(carta.getValor() == 0 || carta.getNaipe().equals("")){
                String input = leitor.next().toUpperCase(Locale.ROOT);
                String inputValor = input.replaceAll("[^0-9TJQKA]", "");
                String inputNaipe = input.replaceAll("[^DHSC]","");

                int valor;
                try{
                    valor = Integer.parseInt(inputValor);
                } catch (NumberFormatException parseException){
                    try {
                        valor = EnumValor.valueOf(inputValor).valor;
                    } catch (IllegalArgumentException valueException) {
                        valor = 0;
                    }
                }

                String naipe;
                try{
                    naipe = EnumNaipe.valueOf(inputNaipe).naipe;
                } catch (IllegalArgumentException valueException){
                    naipe = "";
                }

                if((valor < 2 || valor > 14) && naipe.equals("")){
                    System.out.println("Valor e naipe de carta inválidos, verifique os dados inseridos.");
                } else if(valor < 2 || valor > 14){
                    System.out.println("Valor de carta inválido, insira outro valor.");
                } else if(naipe.equals("")){
                    System.out.println("Naipe de carta inválido, insira outro naipe.");
                } else {
                    carta.setValor(valor).setNaipe(naipe);
                    cartas.add(carta);
                }
            }
        }
        return cartas;
    }

    public void exibeGanhador(Mao primeiraMao, Mao segundaMao){
        int compareTo = primeiraMao.compareTo(segundaMao);
        if(compareTo > 0){
            System.out.println("Primeira Mão ganhou!");
            System.out.println("Jogada: " + primeiraMao.getJogada());
        } else if(compareTo < 0){
            System.out.println("Segunda Mão ganhou!");
            System.out.println("Jogada: " + segundaMao.getJogada());
        } else {
            System.out.println("Não foi possível definir o vencerdor ainda.");
        }
    }
}
