package src.bomberman.entity;

import java.awt.Color;
import java.awt.Graphics;

public class Wall {

    public int x;
    public int y;
    public int w;
    public int h;
    
    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, w, h);
    }
    
}
