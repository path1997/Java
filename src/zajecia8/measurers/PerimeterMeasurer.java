package zajecia8.measurers;

import zajecia8.Figures.Circle;
import zajecia8.Figures.Rectangle;
import zajecia8.Figures.Triangle;
import zajecia8.Measurer;

public class PerimeterMeasurer extends Measurer {
    @Override
    public void visit(Circle circle) {
        double o= 2*Math.PI*circle.r;
        total+=o;
    }

    @Override
    public void visit(Rectangle rectangle) {
        double o=2*(rectangle.w+rectangle.h);
        total+=o;
    }

    @Override
    public void visit(Triangle triangle) {
        double d=Math.pow(triangle.x1-triangle.x2,2);
        d+=Math.pow(triangle.y1-triangle.y2,2);
        total+=Math.sqrt(d);

        d=Math.pow(triangle.x2-triangle.x3,2);
        d+=Math.pow(triangle.y2-triangle.y3,2);
        total+=Math.sqrt(d);

        d=Math.pow(triangle.x3-triangle.x1,2);
        d+=Math.pow(triangle.y3-triangle.y1,2);
        total+=Math.sqrt(d);

    }
}
