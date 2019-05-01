package zajecia7;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Ball {
    private Image image;
    private int x,y,r,vx=2,vy=-1;

    public Ball(BufferedImage img, int x, int y, int r) {
        this.image=img;
        this.x=x;
        this.y=y;
        this.r=r;

    }

    public static Ball create(String path, int x, int y, int r) throws IOException {
        BufferedImage img= ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
        return new Ball(img,x,y,r);
    }


    public void draw(Graphics g) {
        g.drawImage(image,x-r,y-r,2*r,2*r,Color.white,null);
    }

    public void move() {
        x+=vx;
        y+=vy;
    }

    public void checkColisionWithBorder(int width, int height) {
        if (y-r<=0 || y+r>=height){
            vy=-vy;
        }
        if(x-r<=0 || x+r>=width){
            vx=-vx;
        }
    }

    public void checkColisionWithBall(Ball ball2) {
        if ((x-r<=ball2.x-ball2.r || x+r<=ball2.x+ball2.r) && (y-r<ball2.y-ball2.r || y+r<ball2.y+ball2.r)){
            vx=-vx;
            ball2.vx=-ball2.vx;
            vy=-vy;
            ball2.vy=-ball2.vy;
        }
        /*if(x-r<=0 || x+r>=width){
            vx=-vx;
        }*/
    }
}
