package zajecia8;

import java.awt.*;

public abstract class Figure {
    public abstract void draw(Graphics g);

    public abstract void accept(Measurer m);
}
