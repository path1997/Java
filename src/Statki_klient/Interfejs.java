package Statki_klient;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Interfejs extends Silnik{
    public BufferedReader in;
    public PrintWriter out;
    private JPanel mainPanel;
    private JTextField graWStatkiTextField;
    private JRadioButton BtC;
    private JRadioButton btZ;
    private JRadioButton btN;
    public  JTextField textC;
    private JTextField textZ;
    private JTextField textN;
    private JTextField komunikaty;
    private JPanel statki_gracza;
    private JPanel statki_przeciwnika;
    private JButton startButton;
    private JPanel linia;
    public Socket socket;
    private JTextField statkiGraczaTextField;
    private JTextField statkiPrzeciwnikaTextField;
    private JButton nowaGraButton;
    public int kolor=1;
    public JFrame frame = new JFrame("Statki");
    public String nazwa_gracza;
    public String adres_serwera;
    int dlugosc_nazwy_gracza;
    int strzal;
    public int licznik3;
    String id;
    //JTextField textField = new JTextField(40);
    //JTextArea messageArea = new JTextArea(8, 40);

    public void odtworz_dzwiek(String nazwa) {
        Thread dzwiek=new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                            Sudoku.Interfejs.class.getResource("/Statki_klient/"+nazwa+".wav"));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.err.println("zla sciezka");
                }
            }
        });
        dzwiek.start();
    }

    public void polacz_z_serwerem() throws IOException {
        socket = new Socket(adres_serwera, 9001);
        in = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        //System.out.println("test");
    }
    public Interfejs() {

        generuj_plansze();
        //textField.setEditable(false);
        // messageArea.setEditable(false);
        //frame.getContentPane().add(textField, "North");
        // frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        // frame.pack();

        /*textField.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });*/
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sprawdz_czy_dobrze()) {
                    for (JButton l : przyciski_gracza) {
                        l.setEnabled(false);
                    }
                    out.println(nazwa_gracza);
                    for(int i=0;i<rozmiar;i++) {
                        for (int j = 0; j < rozmiar; j++) {
                            out.println(pola_gracza[i][j]);
                        }
                    }
                    startButton.setEnabled(false);
                    komunikaty.setText("Oczekiwanie na przeciwnika");

                } else {
                    komunikaty.setText("Zle ustawione statki. Można ustawiać pionowo lub poziomo");
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

        nowaGraButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out.println("nowa gra");
                nowaGraButton.setEnabled(false);
            }
        });
    }
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
                frame,
                "Wprowadź adres IP:",
                "Statki",
                JOptionPane.QUESTION_MESSAGE);
    }
    private String getName() {
        return JOptionPane.showInputDialog(
                frame,
                "Wprowadź swoją nazwę:",
                "Statki",
                JOptionPane.PLAIN_MESSAGE);
    }


    private void run() throws IOException {

        while (true) {
            int index1,wartosc;
            String line = in.readLine();
            //System.out.println(line);
            if (line.startsWith("name?")) {
                out.println(nazwa_gracza);
            } else if (line.startsWith("accepted")) {
                komunikaty.setText("Połaczono z serwerem! Ustaw statki, a następnie kliknij Start");
            } else if(line.startsWith(nazwa_gracza)){
                if(line.equals(nazwa_gracza+"1")){
                    for (JButton m : przyciski_przeciwnika) {
                        m.setEnabled(true);
                        komunikaty.setText("Twoja kolej");
                    }
                } else if(line.startsWith(nazwa_gracza+" w ")){
                    /*for (JButton m : przyciski_przeciwnika) {
                        m.setEnabled(true);
                    }
                    System.out.println(line);

                    line = in.readLine();
                    wartosc=Integer.parseInt(line.substring(dlugosc_nazwy_gracza+1));*/
                    wartosc=Integer.parseInt(line.substring(dlugosc_nazwy_gracza+3));
                    if(wartosc==1){
                        przyciski_przeciwnika.get(strzal).setBackground(new Color(255,0,0));
                    } else if(wartosc==2){
                        przyciski_przeciwnika.get(strzal).setBackground(new Color(0,255,0));
                    } else if(wartosc==3){
                        przyciski_przeciwnika.get(strzal).setBackground(new Color(0,0,255));
                    }
                    //odtworz_dzwiek("kill");

                } else if(line.startsWith(nazwa_gracza+" b ")){
                    przyciski_gracza.get(Integer.parseInt(line.substring(dlugosc_nazwy_gracza+3))).setBackground(new Color(0,0,0));
                    for (JButton m : przyciski_przeciwnika) {
                        komunikaty.setText("Twoja kolej");
                        m.setEnabled(true);
                    }
                    for (JButton n : przyciski_uzyte) {
                        n.setEnabled(false);
                    }
                    //odtworz_dzwiek("kill");
                }
                else if(line.startsWith(nazwa_gracza+" i ")){
                    przyciski_gracza.get(Integer.parseInt(line.substring(dlugosc_nazwy_gracza+3))).setBackground(new Color(75, 75, 75));
                    for (JButton m : przyciski_przeciwnika) {
                        m.setEnabled(true);
                    }
                    for (JButton n : przyciski_uzyte) {
                        n.setEnabled(false);
                    }
                }
//Integer.parseInt(line.substring(5))==null
            }
            else if(line.startsWith("wygral")){
                if(line.substring(7).equals(nazwa_gracza)){
                    JOptionPane.showMessageDialog(frame, "Brawo! Wygrałeś :)");
                    komunikaty.setText("Brawo! Wygrałeś :)");
                } else {
                    JOptionPane.showMessageDialog(frame, "Niestety przegrales :/");
                    komunikaty.setText("Niestety przegrales :/");
                }
                nowaGraButton.setEnabled(true);
            } else if(line.startsWith("nowa gra")){
                wyczysc_tablice();
                generuj_plansze();
                out.println("nowagraok");

            } else{
                if(licznik3==0) {
                    statkiPrzeciwnikaTextField.setText(line);
                    licznik3++;
                }
            }
        }
    }


    public void generuj_plansze(){
        licznik3=0;
        startButton.setEnabled(true);
        statki_gracza.removeAll();
        statki_przeciwnika.removeAll();
        przyciski_przeciwnika.clear();
        przyciski_uzyte.clear();
        przyciski_gracza.clear();
        statki_gracza.setLayout(new GridLayout(rozmiar, rozmiar));
        il_c=3*ilosc_statkow_czerwonych;
        il_z=2*ilosc_statkow_zielonych;
        il_n=1*ilosc_statkow_niebieskich;
        il_c2=il_c;
        il_z2=il_z;
        il_n2=il_n;
        textC.setText("Dlugosc statku:3   Ilosc wolnych statków: "+il_c/3);
        textZ.setText("Dlugosc statku:2   Ilosc wolnych statków: "+il_z/2);
        textN.setText("Dlugosc statku:1   Ilosc wolnych statków: "+il_n);
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
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton b = (JButton) e.getSource();
                        int index;
                        for (index = 0; index<przyciski_przeciwnika.size(); index++){
                            if(przyciski_przeciwnika.get(index)==b) break;
                        }
                        przyciski_uzyte.add(b);
                        for (JButton m : przyciski_przeciwnika) {
                            m.setEnabled(false);
                        }
                        out.println(nazwa_gracza+" "+index);
                        //System.out.println(nazwa_gracza+" "+index);
                        strzal=index;
                        komunikaty.setText("Oczekiwanie na przeciwnika");
                    }
                });
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
            textC.setText("Dlugosc statku:3   Ilosc wolnych statków: "+(il_c+2)/3);
            textZ.setText("Dlugosc statku:2   Ilosc wolnych statków: "+(il_z+1)/2);
            textN.setText("Dlugosc statku:1   Ilosc wolnych statków: "+il_n);
        }
    }


    public static void main(String[] args) throws IOException {
        Interfejs interfejs = new Interfejs();
        interfejs.adres_serwera=interfejs.getServerAddress();
        interfejs.nazwa_gracza=interfejs.getName();
        interfejs.statkiGraczaTextField.setText(interfejs.nazwa_gracza);
        interfejs.dlugosc_nazwy_gracza=interfejs.nazwa_gracza.length();
        interfejs.polacz_z_serwerem();
        interfejs.frame.setContentPane(interfejs.mainPanel);
        interfejs.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        interfejs.frame.setVisible(true);
        interfejs.frame.pack();
        interfejs.run();

    }
}
