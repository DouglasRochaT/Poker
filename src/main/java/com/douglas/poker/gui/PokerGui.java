package com.douglas.poker.gui;

import com.douglas.poker.carta.Carta;
import com.douglas.poker.carta.EnumNaipe;
import com.douglas.poker.carta.EnumValor;
import com.douglas.poker.io.PokerIO;
import com.douglas.poker.mao.Mao;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.text.MaskFormatter;
import javax.swing.text.StyleContext;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PokerGui extends JFrame implements PokerIO {
    private JLabel titulo;
    private JPanel j;
    private JLabel labelPrimeiraMao;
    private JLabel labelSegundaMao;

    private MaskFormatter maskCarta;
    private JFormattedTextField textCarta1;
    private JFormattedTextField textCarta2;
    private JFormattedTextField textCarta3;
    private JFormattedTextField textCarta4;
    private JFormattedTextField textCarta5;
    private JFormattedTextField textCarta6;
    private JFormattedTextField textCarta7;
    private JFormattedTextField textCarta8;
    private JFormattedTextField textCarta9;
    private JFormattedTextField textCarta10;
    private JButton botaoComparar;

    private boolean invalidInput;

    public PokerGui() {
        this.invalidInput = false;
        try {
            this.maskCarta = new MaskFormatter("AU");
        } catch (ParseException e) {
            System.out.println("String inválida no formatter.");
        }
        $$$setupUI$$$();
    }

    @Override
    public void iniciar(Mao primeiraMao, Mao segundaMao) {
        add(j);
        setSize(400, 300);
        setVisible(true);

        botaoComparar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (selecionaCartas(primeiraMao, segundaMao)) {
                    primeiraMao.verificaMao();
                    segundaMao.verificaMao();
                    exibeGanhador(primeiraMao, segundaMao);
                }
            }
        });
    }

    private boolean selecionaCartas(Mao primeiraMao, Mao segundaMao) {
        List<Carta> cartasPrimeiraMao = new ArrayList<>();
        cartasPrimeiraMao.add(this.retornaCarta(textCarta1));
        cartasPrimeiraMao.add(this.retornaCarta(textCarta2));
        cartasPrimeiraMao.add(this.retornaCarta(textCarta3));
        cartasPrimeiraMao.add(this.retornaCarta(textCarta4));
        cartasPrimeiraMao.add(this.retornaCarta(textCarta5));

        List<Carta> cartasSegundaMao = new ArrayList<>();
        cartasSegundaMao.add(this.retornaCarta(textCarta6));
        cartasSegundaMao.add(this.retornaCarta(textCarta7));
        cartasSegundaMao.add(this.retornaCarta(textCarta8));
        cartasSegundaMao.add(this.retornaCarta(textCarta9));
        cartasSegundaMao.add(this.retornaCarta(textCarta10));

        if (this.invalidInput) {
            invalidInput = false;
            return false;
        } else {
            primeiraMao.setCartas(cartasPrimeiraMao);
            segundaMao.setCartas(cartasSegundaMao);
            return true;
        }
    }

    private Carta retornaCarta(JFormattedTextField textField) {
        Carta carta = new Carta();
        String input = textField.getText().toUpperCase(Locale.ROOT);
        String inputValor = input.replaceAll("[^0-9TJQKA]", "");
        String inputNaipe = input.replaceAll("[^DHSC]", "");

        int valor;
        try {
            valor = Integer.parseInt(inputValor);
        } catch (NumberFormatException parseException) {
            try {
                valor = EnumValor.valueOf(inputValor).valor;
            } catch (IllegalArgumentException valueException) {
                valor = 0;
            }
        }

        String naipe;
        try {
            naipe = EnumNaipe.valueOf(inputNaipe).naipe;
        } catch (IllegalArgumentException valueException) {
            naipe = "";
        }

        if ((valor < 2 || valor > 14) && naipe.equals("")) {
            textField.setText("");
            this.invalidInput = true;
        } else {
            carta.setValor(valor).setNaipe(naipe);
        }
        return carta;
    }

    private void exibeGanhador(Mao primeiraMao, Mao segundaMao) {
        JFrame g = new JFrame();
        g.setLayout(new GridLayout(3, 1));
        g.setSize(200, 200);

        String stringGanhador, stringJogada;
        int compareTo = primeiraMao.compareTo(segundaMao);
        if (compareTo > 0) {
            stringGanhador = " Primeira Mão ganhou!";
            stringJogada = " Jogada: " + primeiraMao.getJogada().toString();
        } else if (compareTo < 0) {
            stringGanhador = " Segunda Mão ganhou!";
            stringJogada = " Jogada: " + segundaMao.getJogada().toString();
        } else {
            stringGanhador = "Não foi possível definir o vencerdor ainda.";
            stringJogada = "";
        }

        JTextArea textGanhador = new JTextArea();
        textGanhador.setEditable(false);
        textGanhador.setText(stringGanhador);
        textGanhador.setFont(new Font(null, Font.PLAIN, 16));

        JTextArea textJogada = new JTextArea();
        textJogada.setEditable(false);
        textJogada.setText(stringJogada);
        textJogada.setFont(new Font(null, Font.PLAIN, 16));

        JTextArea textResultado = new JTextArea();
        textResultado.setEditable(false);
        textResultado.setText(" Resultado:");
        textResultado.setFont(new Font(null, Font.BOLD, 18));

        g.add(textResultado);
        g.add(textGanhador);
        g.add(textJogada);
        g.setVisible(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        j = new JPanel();
        j.setLayout(new GridBagLayout());
        j.setMinimumSize(new Dimension(814, 213));
        j.setOpaque(false);
        j.setPreferredSize(new Dimension(814, 213));
        titulo = new JLabel();
        titulo.setFocusable(false);
        Font tituloFont = this.$$$getFont$$$(null, Font.BOLD, 28, titulo.getFont());
        if (tituloFont != null) titulo.setFont(tituloFont);
        titulo.setMinimumSize(new Dimension(100, 34));
        titulo.setPreferredSize(new Dimension(100, 34));
        titulo.setText("Poker");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        j.add(titulo, gbc);
        labelPrimeiraMao = new JLabel();
        labelPrimeiraMao.setHorizontalAlignment(10);
        labelPrimeiraMao.setMinimumSize(new Dimension(200, 18));
        labelPrimeiraMao.setPreferredSize(new Dimension(200, 18));
        labelPrimeiraMao.setText("Cartas da Primeira Mão:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        j.add(labelPrimeiraMao, gbc);
        labelSegundaMao = new JLabel();
        labelSegundaMao.setMinimumSize(new Dimension(200, 18));
        labelSegundaMao.setPreferredSize(new Dimension(200, 18));
        labelSegundaMao.setText("Cartas da Segunda Mão:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        j.add(labelSegundaMao, gbc);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.BOTH;
        j.add(panel1, gbc);
        textCarta1.setToolTipText("Carta 1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel1.add(textCarta1, gbc);
        textCarta2.setToolTipText("Carta 2");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel1.add(textCarta2, gbc);
        textCarta3.setToolTipText("Carta 3");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel1.add(textCarta3, gbc);
        textCarta4.setToolTipText("Carta 4");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel1.add(textCarta4, gbc);
        textCarta5.setToolTipText("Carta 5");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel1.add(textCarta5, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.BOTH;
        j.add(panel2, gbc);
        textCarta10.setToolTipText("Carta 5");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel2.add(textCarta10, gbc);
        textCarta6.setToolTipText("Carta 1");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel2.add(textCarta6, gbc);
        textCarta8.setToolTipText("Carta 3");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel2.add(textCarta8, gbc);
        textCarta9.setToolTipText("Carta 4");
        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel2.add(textCarta9, gbc);
        textCarta7.setToolTipText("Carta 2");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.ipadx = 20;
        panel2.add(textCarta7, gbc);
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.BOTH;
        j.add(panel3, gbc);
        botaoComparar = new JButton();
        botaoComparar.setText("Comparar");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        panel3.add(botaoComparar, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel3.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        j.add(spacer3, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        Font font = new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
        boolean isMac = System.getProperty("os.name", "").toLowerCase(Locale.ENGLISH).startsWith("mac");
        Font fontWithFallback = isMac ? new Font(font.getFamily(), font.getStyle(), font.getSize()) : new StyleContext().getFont(font.getFamily(), font.getStyle(), font.getSize());
        return fontWithFallback instanceof FontUIResource ? fontWithFallback : new FontUIResource(fontWithFallback);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return j;
    }

    private void createUIComponents() {
        textCarta1 = new JFormattedTextField(this.maskCarta);
        textCarta2 = new JFormattedTextField(this.maskCarta);
        textCarta3 = new JFormattedTextField(this.maskCarta);
        textCarta4 = new JFormattedTextField(this.maskCarta);
        textCarta5 = new JFormattedTextField(this.maskCarta);
        textCarta6 = new JFormattedTextField(this.maskCarta);
        textCarta7 = new JFormattedTextField(this.maskCarta);
        textCarta8 = new JFormattedTextField(this.maskCarta);
        textCarta9 = new JFormattedTextField(this.maskCarta);
        textCarta10 = new JFormattedTextField(this.maskCarta);
    }
}
