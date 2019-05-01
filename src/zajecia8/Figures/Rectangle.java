package zajecia8.Figures;

import zajecia8.Figure;
import zajecia8.Measurer;

import java.awt.*;

public class Rectangle extends Figure {
    public double x,y,w,h;

    public Rectangle(double x, double y, double w, double h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void accept(Measurer m) {
        m.visit(this);
    }
}
