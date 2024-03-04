package src.bomberman.entity;

import src.bomberman.Game;

import java.awt.*;

public class Block {

    public int x;
    public int y;
    public int w;
    public int h;

    private Game game;

    public Block( Game game, int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.game = game;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, w, h);
    }

    public void destroy() {
        game.blocks.remove(this);
    }
    
}
