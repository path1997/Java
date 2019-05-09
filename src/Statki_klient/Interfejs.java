package Statki_klient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Interfejs extends Silnik{
    private JPanel mainPanel;
    private JTextField STATKITextField;
    private JRadioButton BtC;
    private JRadioButton btZ;
    private JRadioButton btN;
    private JTextField textC;
    private JTextField textZ;
    private JTextField textN;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPanel statki_gracza;
    private JPanel statki_przeciwnika;
    private JButton startButton;
    private JPanel linia;
    private JTextField statkiGraczaTextField;
    private JTextField statkiPrzeciwnikaTextField;
    public int kolor=1;
    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("Statki");
    public String nazwa_gracza;


    public Interfejs() throws IOException {
        generuj_plansze();




        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sprawdz_czy_dobrze()) {
                    for (JButton l : przyciski_gracza) {
                        l.setEnabled(false);
                    }
                    for (JButton m : przyciski_przeciwnika) {
                        m.setEnabled(true);
                    }
                }
            }
        });
        BtC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kolor=1;
            }
        });
        btZ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kolor=2;
            }
        });
        btN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                kolor=3;
            }
        });
    }
    private String pobierz_nazwe_gracza() {
        return JOptionPane.showInputDialog(
                frame,
                "Wprowadź nazwę gracza",
                "Gra Statki",
                JOptionPane.QUESTION_MESSAGE);
    }

    private String pobierz_adres_serwera() {
        return JOptionPane.showInputDialog(
                frame,
                "Wpisz adres IP serwera",
                "Gra Statki",
                JOptionPane.QUESTION_MESSAGE);
    }

    private void run() throws IOException {
        String serverAddress = pobierz_adres_serwera();
        Socket socket = new Socket(serverAddress, 9001);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        nazwa_gracza=pobierz_nazwe_gracza();
        int dlugosc_nazwy_gracza=nazwa_gracza.length();
        statkiGraczaTextField.setText(nazwa_gracza);
        out.println(nazwa_gracza);

        while (true) {
            String line = in.readLine();
            System.out.println(line);
            if (line.startsWith("name?")) {
                out.println(nazwa_gracza);
            } else if (line.startsWith("accepted")) {
                System.out.println("sukces");
            } else if(line.startsWith("go")){
                System.out.println("jazda");
            } else if (line.startsWith(nazwa_gracza)) {
                /*int i=dlugosc_nazwy_gracza;
                String liczba="";
                char litera=line.charAt(i);
                liczba+=litera;
                i++;
                while(true){
                   litera= line.charAt(i);
                   if(litera=='\n'){
                       break;
                   } else {
                       liczba+=litera;
                       i++;
                   }
                }*/
                System.out.println(line.substring(dlugosc_nazwy_gracza));
            }
        }
    }

    public void generuj_plansze(){
        statki_gracza.removeAll();
        statki_gracza.setLayout(new GridLayout(rozmiar, rozmiar));
        for(int i=0;i<rozmiar;i++) {
            for (int j = 0; j < rozmiar; j++) {
                JButton button = new JButton();
                //button.setText(i+","+j);
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setAlignmentY(Component.CENTER_ALIGNMENT);
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.setSize(new Dimension(30, 30));
                button.setMaximumSize(new Dimension(30, 30));
                button.setMinimumSize(new Dimension(30, 30));
                przyciski_gracza.add(button);
                statki_gracza.add(button);
                button.addActionListener(new Gracz_listener());
            }
        }
        statki_przeciwnika.removeAll();
        statki_przeciwnika.setLayout(new GridLayout(rozmiar, rozmiar));
        for(int i=0;i<rozmiar;i++) {
            for (int j = 0; j < rozmiar; j++) {
                JButton button = new JButton();
                //button.setText(i+","+j);
                button.setHorizontalAlignment(SwingConstants.CENTER);
                button.setAlignmentY(Component.CENTER_ALIGNMENT);
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
                button.setSize(new Dimension(30, 30));
                button.setMaximumSize(new Dimension(30, 30));
                button.setMinimumSize(new Dimension(30, 30));
                button.setEnabled(false);
                przyciski_przeciwnika.add(button);
                statki_przeciwnika.add(button);
                button.addActionListener(new Przeciwnik_listener());
            }
        }
        linia.setBackground(new Color(0,0,0));
        statki_gracza.revalidate();
        statki_gracza.repaint();
        statki_przeciwnika.revalidate();
        statki_przeciwnika.repaint();
    }

    public int getIndex(JButton b){
        int index;
        for (index = 0; index<przyciski_gracza.size(); index++){
            if(przyciski_gracza.get(index)==b) break;
        }
        return index;
    }
    private class Gracz_listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            if(b.getBackground().getGreen()==255){
                b.setBackground(null);
                int index=getIndex(b);
                int row = index/rozmiar;
                int col = index%rozmiar;
                pola_gracza[row][col]=0;
                il_z++;
            } else if(b.getBackground().getRed()==255){
                b.setBackground(null);
                int index=getIndex(b);
                int row = index/rozmiar;
                int col = index%rozmiar;
                pola_gracza[row][col]=0;
                il_c++;
            } else if(b.getBackground().getBlue()==255){
                b.setBackground(null);
                int index=getIndex(b);
                int row = index/rozmiar;
                int col = index%rozmiar;
                pola_gracza[row][col]=0;
                il_n++;
            } else if(kolor==1 && il_c>0){
                b.setBackground(Color.red);
                int index=getIndex(b);
                int row = index/rozmiar;
                int col = index%rozmiar;
                pola_gracza[row][col]=kolor;
                il_c--;
            } else if(kolor==2 && il_z>0){
                b.setBackground(Color.green);
                int index=getIndex(b);
                int row = index/rozmiar;
                int col = index%rozmiar;
                pola_gracza[row][col]=kolor;
                il_z--;
            } else if (kolor==3 && il_n>0){
                b.setBackground(Color.blue);
                int index=getIndex(b);
                int row = index/rozmiar;
                int col = index%rozmiar;
                pola_gracza[row][col]=kolor;
                il_n--;
            }
        }
    }

    private class Przeciwnik_listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton) e.getSource();
            int index;
            for (index = 0; index<przyciski_przeciwnika.size(); index++){
                if(przyciski_przeciwnika.get(index)==b) break;
            }
            //Integer.toString(index)
            out.println(Integer.toString(index));
            //System.out.println(index);
        }
    }

    public static void main(String[] args) throws IOException {
        Interfejs i = new Interfejs();
        i.frame.setContentPane(new Interfejs().mainPanel);
        i.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        i.frame.pack();
        i.frame.setVisible(true);
        i.run();

    }
}
