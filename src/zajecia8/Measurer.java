package zajecia8;

import zajecia8.Figures.Circle;
import zajecia8.Figures.Rectangle;
import zajecia8.Figures.Triangle;
import zajecia8.measurers.FieldMeasurer;
import zajecia8.measurers.PerimeterMeasurer;

public abstract class Measurer {
    public double total;

    public static Measurer createInstance(MeasurerType type) {
        Measurer m=null;
        switch(type){
            case FieldMeasurer : m=new FieldMeasurer(); break;
            case PerimeterMeasurer:m=new PerimeterMeasurer();break;
        }
        return m;

    }

    public abstract void visit(Circle circle);

    public abstract void visit(Rectangle rectangle);

    public abstract void visit(Triangle triangle);
}
enum MeasurerType{
    FieldMeasurer,
    PerimeterMeasurer
}