package zajecia3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DrawingApp {
    private JButton DOWNButton;
    private JButton UPButton;
    private JPanel drawingPanel;
    private JPanel mainPanel;
    private JButton kolorButton;
    private JButton czyscButton;
    private JButton koloButton;
    private JButton kwadratButton;
    private JButton rozmiar_button;
    private JButton rozmiarplus_button;


    public DrawingApp() {
        /*DOWNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.moveBall(0,5); //przesun w dol o 5px
                mp.repaint();
            }
        });
        UPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.moveBall(0,-5); //przesun w gore o 5px
                mp.repaint();
            }
        });
        LEFTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.moveBall(-5,0); //przesun w lewo o 5px
                mp.repaint();
            }
        });
        RIGHTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.moveBall(5,0); //przesun w prawo o 5px
                mp.repaint();
            }
        });*/

        kolorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.setColor();
                mp.repaint();
            }
        });
        drawingPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.moveBallTo(e.getX(),e.getY());
                mp.repaint();
            }
        });
        czyscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.czysc();
            }
        });
        koloButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.setFigura("kolo");
                mp.repaint();
            }
        });

        kwadratButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.setFigura("kwadrat");
                mp.repaint();
            }
        });
        rozmiar_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.setRozmiar(-5);
                mp.repaint();
            }
        });
        rozmiarplus_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.setRozmiar(5);
                mp.repaint();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DrawingApp");
        frame.setContentPane(new DrawingApp().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        drawingPanel=new MyDrawingPanel();
    }
}
