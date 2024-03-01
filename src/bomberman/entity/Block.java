package src.bomberman.entity;

import java.awt.*;

public class Block {

    public int x;
    public int y;
    public int w;
    public int h;

    public Block(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, w, h);
    }
    
}
