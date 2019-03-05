package zajecia3;

import javax.swing.*;
import java.awt.*;

public class MyDrawingPanel extends JPanel {
    private int x=200;
    private int y=156;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.orange);
        g.fillOval(x,y,24,18);
    }

    public void moveBall(int dx, int dy) {
        x+=dx;
        y+=dy;
    }
}
