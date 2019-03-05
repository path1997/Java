package zajecia3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingApp {
    private JButton LEFTButton;
    private JButton RIGHTButton;
    private JButton DOWNButton;
    private JButton UPButton;
    private JPanel drawingPanel;
    private JPanel mainPanel;

    public DrawingApp() {
        DOWNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyDrawingPanel mp=(MyDrawingPanel) drawingPanel;
                mp.moveBall(0,5); //przesun w dol o 5px
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
