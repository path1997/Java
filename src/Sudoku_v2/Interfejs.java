package Sudoku_v2;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Interfejs extends Silnik {
    JTextField tytul,tf2,tf3;
    JButton b1,b2;
    public static JTextField f;
    private int il_miejsc_wol;
    private boolean czy_losowano=false;
    private boolean puste_pola;
    private int stary_stan_puste;

   List<JTextField> pola = new ArrayList<>();

    public Interfejs() {

        JFrame f= new JFrame();
        tytul=new JTextField();
        //tytul.setBounds(250,50,100,50);
        //tytul.setLayout(new GridLayout(1,11));
        tytul.setEditable(false);
        tytul.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        //tytul.setAlignmentX(Component.CENTER_ALIGNMENT);
        //tytul.setAlignmentY(Component.TOP_ALIGNMENT);
        /*tf2=new JTextField();
        tf2.setBounds(50,100,150,20);
        tf3=new JTextField();
        tf3.setBounds(50,150,150,20);
        tf3.setEditable(false);
        b1=new JButton("+");
        b1.setBounds(50,200,50,50);
        b2=new JButton("-");
        b2.setBounds(120,200,50,50);*/
        f.add(tytul);//f.add(tf2);f.add(tf3);f.add(b1);f.add(b2);
        //f.setSize(300,300);
        f.setLayout(null);
        f.setSize(500,700);
        f.setVisible(true);




        /*Color defaultColor = T1.getBackground();
        ilośćPustychMiejscTextField.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        Tytul.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        wczytaj_pola();

        losujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                komunikat.setText("");
                il_miejsc_wol= (int) il_miejsc.getValue();

                if(il_miejsc_wol<1 || il_miejsc_wol>81){
                    komunikat.setText("Liczba miejsc musi być w przedziale 1-81");
                    il_miejsc_wol=stary_stan_puste;
                    il_miejsc.setValue(stary_stan_puste);
                    return;
                }

                miejsca_puste.clear();
                uzupelnij_tablice();
                wstaw_puste(il_miejsc_wol);
                stary_stan_puste=il_miejsc_wol;
                czy_losowano=true;
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
                                    komunikat.setText("Wprowadaj tylko liczby w przedziale 1-9");
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
                                komunikat.setText("Wprowadzaj tylko liczby w puste pola");
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
                            odtworz_dzwiek("success");
                            komunikat.setText("Brawo! Wygrałeś :)");
                            for(Integer p: miejsca_puste){
                                pola.get(p).setBackground(new Color(0, 171,0));
                            }
                        } else {
                            odtworz_dzwiek("fail");
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

    public void odtworz_dzwiek(String nazwa) {
        Thread dzwiek=new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Interfejs.class.getResource("/Sudoku/"+nazwa+".wav"));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println("zla sciezka");
                }
            }
        });
        dzwiek.start();
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
*/
    }

    public static void main(String[] args) {

        new Interfejs();
    }
}
