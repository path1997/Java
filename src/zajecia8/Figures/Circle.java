package zajecia8.Figures;

import zajecia8.Figure;
import zajecia8.Measurer;

import java.awt.*;

public class Circle extends Figure {
    public double x,y,r;

    public Circle(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void accept(Measurer m) {
        m.visit(this);
    }
}
