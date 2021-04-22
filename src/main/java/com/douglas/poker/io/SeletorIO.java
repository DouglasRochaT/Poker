package com.douglas.poker.io;

import com.douglas.poker.console.PokerConsole;
import com.douglas.poker.gui.PokerGui;

public class SeletorIO {
    public static PokerIO criaInterface(String arg){
        PokerIO io;
        switch (arg) {
            case "gui":
                io = new PokerGui();
                break;
            case "terminal":
                io = new PokerConsole();
                break;
            case "":
                System.out.println("Interface não especificada, use \"gui\" ou \"terminal\" para definir a interface.");
                System.out.println("Assumindo a interface gráfica como padrão.");
                System.out.println("");
                io = new PokerGui();
                break;
            default:
                System.out.println("Interface  \"" + arg + "\" não encontrada.");
                System.out.println("Assumindo a interface gráfica como padrão.");
                io = new PokerGui();
                break;
        }
        return io;
    }
}
