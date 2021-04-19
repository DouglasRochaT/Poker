package com.douglas.poker.jogadas;

import com.douglas.poker.carta.Carta;

import java.util.List;

public class Jogadas {
    public static boolean flush(List<Carta> cartas){
        String naipe = cartas.get(0).getNaipe();
        for (Carta carta: cartas) {
            if(!carta.getNaipe().equals(naipe)){
                return false;
            }
        }
        return true;
    }

    public static boolean straight(List<Carta> cartas){
        int valor = cartas.get(0).getValor();
        for (Carta carta: cartas) {
            if(carta.getValor() != valor++){
                return false;
            }
        }
        return true;
    }

    public static boolean royalFlush(List<Carta> cartas){
        int incremento = 0;
        for (Carta carta: cartas) {
            if(carta.getValor() != 10 + incremento){
                return false;
            };
            incremento++;
        }
        return true;
    }

    public static boolean straightFlush(List<Carta> cartas){
        return Jogadas.straight(cartas) && Jogadas.flush(cartas);
    }

    public static boolean umPar(List<Carta> cartas){
        int valor = cartas.get(0).getValor();
        int pares = -1;
        for (Carta carta: cartas) {
            if(carta.getValor() == valor){
                pares++;
            }
            valor = carta.getValor();
        }
        return pares == 1;
    }

    public static boolean doisPares(List<Carta> cartas){
        int valor = cartas.get(0).getValor();
        int pares = -1;
        for (Carta carta: cartas) {
            if(carta.getValor() == valor){
                pares++;
            }
            valor = carta.getValor();
        }
        return pares == 2;
    }

    public static boolean fullHouse(List<Carta> cartas){
        int valor = cartas.get(0).getValor();
        int pares = -1;
        for (Carta carta: cartas) {
            if(carta.getValor() == valor){
                pares++;
            }
            valor = carta.getValor();
        }
        return pares == 3;
    }

    public static boolean quadra(List<Carta> cartas){
        if(cartas.get(0).getValor() == cartas.get(3).getValor()){
            return true;
        } else if(cartas.get(1).getValor() == cartas.get(4).getValor()){
            return true;
        } else {
            return false;
        }
    }

    public static boolean trinca(List<Carta> cartas){
        if(cartas.get(0).getValor() == cartas.get(2).getValor()){
            return true;
        } else if(cartas.get(2).getValor() == cartas.get(4).getValor()){
            return true;
        } else {
            return false;
        }
    }
}
