package Sudoku;

import javax.sound.sampled.*;
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

    private JTextField komunikat;
    private JButton sprawdzButton;
    private JButton przykładowaPoprawnaOdpowiedźButton;
    private JPanel pole_gry;
    private JTextField ileWWierszuITextField;
    private JSpinner wielkosc;
    private int il_miejsc_wol;
    private boolean czy_losowano=false;
    private boolean puste_pola;
    private int stary_stan_puste;

   List<JTextField> pola = new ArrayList<>();

    public Interfejs() {
        ileWWierszuITextField.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        wielkosc.setValue(3);
        generuj_plansze();

        ilośćPustychMiejscTextField.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        Tytul.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));


        losujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ZABEZPIECZENIA---------
                komunikat.setText("");
                il_miejsc_wol= (int) il_miejsc.getValue();
                rozmiar= (int) wielkosc.getValue();
                rozmiar_petla=rozmiar;
                if(rozmiar<2 || rozmiar>3){
                    komunikat.setText("Liczba okienek musi być w przedziale 2-3");
                    il_miejsc.setValue(3);
                    return;
                }
                rozmiar=rozmiar*rozmiar;
                if(il_miejsc_wol<1 || il_miejsc_wol>rozmiar*rozmiar){
                    komunikat.setText("Liczba miejsc musi być w przedziale 1-"+rozmiar*rozmiar);
                    il_miejsc_wol=stary_stan_puste;
                    il_miejsc.setValue(stary_stan_puste);
                    return;
                }
                //--------------------------
                generuj_plansze();
                generuj_puste_miejsca();

            }
        });

        sprawdzButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                miejsca_zle.clear();
                //ZABEZPIECZENIA
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
                                if(liczba_int>rozmiar ||liczba_int<1){
                                    komunikat.setText("Wprowadaj tylko liczby w przedziale 1-"+rozmiar);
                                    l.setEditable(true);
                                    puste_pola=true;
                                    tablica[i][j]=-1;
                                    l.setText("");
                                    break;
                                }
                //----------------------------------------
                                //WPISYWANIE DO TABLICY
                                tablica[i][j] = liczba_int;
                                l.setEditable(false);
                                j++;
                                if (j == rozmiar) {
                                    j = 0;
                                    i++;
                                }
                                puste_pola=false;
                                //----------------------
                                //ZABEZPIECZENIE PRZED LITERAMI
                            } catch (NumberFormatException r){
                                komunikat.setText("Wprowadzaj tylko liczby w puste pola");
                                l.setEditable(true);
                                puste_pola=true;
                                tablica[i][j]=-1;
                                l.setText("");
                                break;
                            }
                            //--------------------------------------
                        }
                    }
                    sprawdz_wygrana();

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
                        if(j==rozmiar) {
                            j = 0;
                            i++;
                        }
                    }
                }
            }
        });
    }

    public void generuj_plansze(){
        pola.clear();
        pole_gry.removeAll();
        pole_gry.setLayout(new GridLayout((rozmiar+(rozmiar_petla-1)), (rozmiar+(rozmiar_petla-1))));
        for(int i=1;i<=(rozmiar+(rozmiar_petla-1));i++){
            for(int j=1;j<=(rozmiar+(rozmiar_petla-1));j++) {
                if(j%(rozmiar_petla+1)==0 && j!=0 && j!=(rozmiar+(rozmiar_petla-1))){
                    JPanel jPanel=new JPanel();
                    jPanel.setBackground(new Color(0,0,0));
                    jPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
                    jPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    jPanel.setSize(new Dimension(2, -1));
                    jPanel.setMaximumSize(new Dimension(2, -1));
                    jPanel.setMinimumSize(new Dimension(2, -1));
                    pole_gry.add(jPanel);

                } else if (i%(rozmiar_petla+1)==0) {
                    JPanel jPanel=new JPanel(new GridLayout(1,1));
                    jPanel.setBackground(new Color(0,0,0));
                    jPanel.setMaximumSize(new Dimension(5,5));
                    pole_gry.add(jPanel);
                }else {
                    JTextField textField = new JTextField();
                    textField.setHorizontalAlignment(SwingConstants.CENTER);
                    textField.setAlignmentY(Component.CENTER_ALIGNMENT);
                    textField.setAlignmentX(Component.CENTER_ALIGNMENT);
                    textField.setSize(new Dimension(30, 30));
                    textField.setMaximumSize(new Dimension(30, 30));
                    textField.setMinimumSize(new Dimension(30, 30));
                    textField.setEditable(false);
                    pola.add(textField);
                    pole_gry.add(textField);
                }
            }
        }
        pole_gry.revalidate();
        pole_gry.repaint();
    }

    public void generuj_puste_miejsca(){
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
                l.setBackground(Tytul.getBackground());
            }
            j++;
            if(j==rozmiar){
                j=0;
                i++;
            }
        }
    }

    public void sprawdz_wygrana(){
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


    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku");
        frame.setIconImage(new ImageIcon(Interfejs.class.getResource("/Sudoku/ikona.png")).getImage());
        frame.setMinimumSize(new Dimension(500, 680));
        frame.setContentPane(new Interfejs().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
