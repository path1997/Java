package zajecia8.Figures;

import zajecia8.Figure;
import zajecia8.Measurer;

import java.awt.*;

public class Triangle extends Figure {
    public double x1,y1,x2,y2,x3,y3;

    public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void accept(Measurer m) {
        m.visit(this);
    }
}
