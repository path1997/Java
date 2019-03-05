package zajecia3;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class MyDrawingPanel extends JPanel {
    private int x=200;
    private int y=156;
    private Color kolorek;
    private int czysc1=0;

    @Override
    public void paint(Graphics g) {
        if(czysc1==1) {
            super.paint(g);
            czysc1=0;
        }
        g.setColor(kolorek);
        g.fillOval(x-12,y-9,24,18);
    }

    public void moveBall(int dx, int dy) {
        x+=dx;
        y+=dy;
    }
    public void setColor(){
        kolorek=randColor();
    }
    private Color randColor(){
        Random rand=new Random();
        float r=rand.nextFloat();
        float g=rand.nextFloat();
        float b=rand.nextFloat();
        return (new Color(r,g,b));
    }

    public void moveBallTo(int x, int y) {
        this.x=x;
        this.y=y;
    }
    public void czysc(){
        czysc1=1;
        x=200;
        y=156;
        repaint();
    }
}
