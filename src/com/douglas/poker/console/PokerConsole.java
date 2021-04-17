package com.douglas.poker.console;

import com.douglas.poker.carta.Carta;
import com.douglas.poker.carta.EnumNaipe;
import com.douglas.poker.carta.EnumValor;
import com.douglas.poker.io.PokerIO;

import java.util.Locale;
import java.util.Scanner;

public class PokerConsole implements PokerIO {
    Scanner leitor;

    public PokerConsole(){
        this.leitor = new Scanner(System.in);
    }

    @Override
    public Carta selecionaCarta(){
        Carta carta = new Carta();
        System.out.println("Escolha o valor da carta e o naipe da carta:");

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
            } catch (IllegalArgumentException naipeException){
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
            }
        }
        return carta;
    }
}
