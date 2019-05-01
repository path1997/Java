package Sudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Interfejs extends Silnik {
    private JPanel mainPanel;
    private JTextField Tytul;
    private JTextField ilośćPustychMiejscTextField;
    private JSpinner il_miejsc;
    private JButton losujButton;
    private JTextField T1;
    private JTextField T2;
    private JTextField T3;
    private JTextField T4;
    private JTextField T5;
    private JTextField T6;
    private JTextField T7;
    private JTextField T8;
    private JTextField T9;
    private JTextField komunikat;
    private JButton sprawdzButton;
    private JTextField T10;
    private JTextField T11;
    private JTextField T12;
    private JTextField T13;
    private JTextField T14;
    private JTextField T15;
    private JTextField T16;
    private JTextField T17;
    private JTextField T18;
    private JTextField T19;
    private JTextField T20;
    private JTextField T21;
    private JTextField T22;
    private JTextField T23;
    private JTextField T24;
    private JTextField T25;
    private JTextField T26;
    private JTextField T27;
    private JTextField T28;
    private JTextField T29;
    private JTextField T30;
    private JTextField T31;
    private JTextField T32;
    private JTextField T33;
    private JTextField T34;
    private JTextField T35;
    private JTextField T36;
    private JTextField T37;
    private JTextField T38;
    private JTextField T39;
    private JTextField T40;
    private JTextField T41;
    private JTextField T42;
    private JTextField T43;
    private JTextField T44;
    private JTextField T45;
    private JTextField T46;
    private JTextField T47;
    private JTextField T48;
    private JTextField T49;
    private JTextField T50;
    private JTextField T51;
    private JTextField T52;
    private JTextField T53;
    private JTextField T54;
    private JTextField T55;
    private JTextField T56;
    private JTextField T57;
    private JTextField T58;
    private JTextField T59;
    private JTextField T60;
    private JTextField T61;
    private JTextField T62;
    private JTextField T63;
    private JTextField T64;
    private JTextField T65;
    private JTextField T66;
    private JTextField T67;
    private JTextField T68;
    private JTextField T69;
    private JTextField T70;
    private JTextField T71;
    private JTextField T72;
    private JTextField T73;
    private JTextField T74;
    private JTextField T75;
    private JTextField T76;
    private JTextField T77;
    private JTextField T78;
    private JTextField T79;
    private JTextField T80;
    private JTextField T81;
    private JPanel linia1;
    private JPanel linia3;
    private JButton przykładowaPoprawnaOdpowiedźButton;
    private int il_miejsc_wol;
    private boolean czy_losowano=false;
    private boolean puste_pola;

   List<JTextField> pola = new ArrayList<>();

    public Interfejs() {
        Color defaultColor = T1.getBackground();
        ilośćPustychMiejscTextField.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        Tytul.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        wczytaj_pola();
        losujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miejsca_puste.clear();
                czy_losowano=true;
                komunikat.setText("");
                uzupelnij_tablice();
                il_miejsc_wol= (int) il_miejsc.getValue();
                wstaw_puste(il_miejsc_wol);
                int i=0,j=0;
                for(JTextField l : pola){
                    if(tablica[i][j]==-1){
                        l.setText("");
                        l.setEditable(true);
                        l.setBackground(Color.white);
                    }else {
                        l.setText(Integer.toString(tablica[i][j]));
                        l.setEditable(false);
                        l.setBackground(defaultColor);
                    }
                    j++;
                    if(j==9){
                        j=0;
                        i++;
                    }
                }
            }
        });
        sprawdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miejsca_zle.clear();
                if(czy_losowano==true) {
                    int i = 0, j = 0,licznik=0;
                    String liczba_string;
                    int liczba_int;
                    for (JTextField l : pola) {
                        liczba_string = l.getText();
                        if (liczba_string.isEmpty()) {
                            komunikat.setText("Uzupełnij wszystkie pola");
                            puste_pola=true;
                            break;
                        } else {
                            try{
                                liczba_int=Integer.parseInt(liczba_string);
                                if(liczba_int>9 ||liczba_int<1){
                                    komunikat.setText("Wprowadzadź tylko liczby w przedziale 1-9");
                                    l.setEditable(true);
                                    puste_pola=true;
                                    tablica[i][j]=-1;
                                    l.setText("");
                                    break;
                                }
                                tablica[i][j] = liczba_int;
                                l.setEditable(false);
                                j++;
                                if (j == 9) {
                                    j = 0;
                                    i++;
                                }
                                puste_pola=false;
                            } catch (NumberFormatException r){
                                komunikat.setText("Wprowadzadź tylko liczby w puste pola");
                                l.setEditable(true);
                                puste_pola=true;
                                tablica[i][j]=-1;
                                l.setText("");
                                break;
                            }
                        }
                    }

                    if(puste_pola==false) {
                        if (sprawdz_wynik() == true) {
                            komunikat.setText("Brawo! Wygrałeś :)");
                            for(Integer p: miejsca_puste){
                                pola.get(p).setBackground(new Color(0, 171,0));
                            }
                        } else {
                            komunikat.setText("Niestety przegrałeś :(");
                            for(Integer p: miejsca_puste){
                                for(Integer z: miejsca_zle) {
                                    if(z==p){
                                        pola.get(z).setBackground(new Color(255,0,0));
                                        break;
                                    }
                                    else{
                                        pola.get(p).setBackground(new Color(0,171,0));
                                    }
                                }
                            }

                        }
                    }
                }
            }
        });
        przykładowaPoprawnaOdpowiedźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(czy_losowano==true) {
                    int i=0,j=0;
                    for (JTextField l : pola) {
                        l.setText(Integer.toString(odpowiedz[i][j]));
                        l.setEditable(false);
                        j++;
                        if(j==9) {
                            j = 0;
                            i++;
                        }
                    }
                }
            }
        });
    }

    private void wczytaj_pola(){
        pola.add(T1);pola.add(T2);pola.add(T3);pola.add(T4);pola.add(T5);pola.add(T6);pola.add(T7);pola.add(T8);
        pola.add(T9);pola.add(T10);pola.add(T11);pola.add(T12);pola.add(T13);pola.add(T14);pola.add(T15);pola.add(T16);
        pola.add(T17);pola.add(T18);pola.add(T19);pola.add(T20);pola.add(T21);pola.add(T22);pola.add(T23);pola.add(T24);
        pola.add(T25);pola.add(T26);pola.add(T27);pola.add(T28);pola.add(T29);pola.add(T30);pola.add(T31);pola.add(T32);
        pola.add(T33);pola.add(T34);pola.add(T35);pola.add(T36);pola.add(T37);pola.add(T38);pola.add(T39);pola.add(T40);
        pola.add(T41);pola.add(T42);pola.add(T43);pola.add(T44);pola.add(T45);pola.add(T46);pola.add(T47);pola.add(T48);
        pola.add(T49);pola.add(T50);pola.add(T51);pola.add(T52);pola.add(T53);pola.add(T54);pola.add(T55);pola.add(T56);
        pola.add(T57);pola.add(T58);pola.add(T59);pola.add(T60);pola.add(T61);pola.add(T62);pola.add(T63);pola.add(T64);
        pola.add(T65);pola.add(T66);pola.add(T67);pola.add(T68);pola.add(T69);pola.add(T70);pola.add(T71);pola.add(T72);
        pola.add(T73);pola.add(T74);pola.add(T75);pola.add(T76);pola.add(T77);pola.add(T78);pola.add(T79);pola.add(T80);
        pola.add(T81);

    }
    public void wynik_koncowy(){

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku");
        frame.setMaximumSize(new Dimension(530,640));
        frame.setMinimumSize(new Dimension(500, 615));
        frame.setContentPane(new Interfejs().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
