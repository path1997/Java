package zajecia8.measurers;

import zajecia8.Figures.Circle;
import zajecia8.Figures.Rectangle;
import zajecia8.Figures.Triangle;
import zajecia8.Measurer;

public class FieldMeasurer extends Measurer {
    @Override
    public void visit(Circle circle) {
        double f= Math.PI*circle.r*circle.r;
        total+=f;
    }

    @Override
    public void visit(Rectangle rectangle) {
        double f = rectangle.w*rectangle.h;
        total+=f;
    }

    @Override
    public void visit(Triangle triangle) {
        double f =0; //TODO better
        total+=f;
    }
}
