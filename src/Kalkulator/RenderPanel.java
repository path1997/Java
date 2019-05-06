package Kalkulator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RenderPanel extends Kalkulator implements ActionListener{

    public  JFrame okno;

    public static JTextField text;

    public static JButton[] bKey;

    public Font font;

    public Object cel;

    public static String sEkran = "0";

    public static String[] sKey = {"1","2","3","C","4","5","6","+","7","8","9","-","0","*","/","="};

    public static Color[] cKey = {Color.BLACK, Color.BLACK, Color.BLACK, Color.RED, Color.BLACK,Color.BLACK,Color.BLACK,Color.BLUE
            ,Color.BLACK,Color.BLACK,Color.BLACK,Color.BLUE,Color.BLACK,Color.BLUE,Color.BLUE,Color.GREEN};

    public RenderPanel(){
        okno = new JFrame("Kalkulator");
        text = new JTextField(sEkran);
        bKey = new JButton[16];
        font = new Font("System", Font.BOLD, 15);

                for (byte i=0; i<16; i++)
        {
            bKey[i] = new JButton(sKey[i]);
            okno.add(bKey[i]);bKey[i].addActionListener(this);
        }
        byte index = 0;
        for (byte y=0; y<4; y++)
            for (byte x=0; x<4; x++)
            {
                bKey[index].setBounds(10+(x*50), 55+(y*50), 45, 45);
                bKey[index].setFont(font);
                bKey[index].setForeground(cKey[index]);
                index++;
            }

        text.setBounds(10, 10, 195, 35);text.setFont(new Font("System",Font.BOLD,20));
        text.setEditable(false);text.setHorizontalAlignment(JTextField.RIGHT);

        okno.add(text);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(220, 285);
        okno.setLocationRelativeTo(null);
        okno.setResizable(false);
        okno.setLayout(null);
        okno.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        cel = e.getSource();

        for (byte i=0; i<16; i++){
            if (cel==bKey[i]){
                oblicz(i);
            }
        }
    }
}
