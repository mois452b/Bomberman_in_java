package src;

import java.awt.*;

import src.ultils.Types;

public class Player {
    public int x;
    public int y;
    public int w;
    public int h;
    public int cx;
    public int cy;
    public int dx;
    public int dy;
    public int speed;
    public int indexX;
    public int indexY;
    public Types.Directions direction;
    public Types.Directions lastDirections;

    private int spriteW = 18;
    private int spriteH = 26;

    public Player(int x, int y, int w, int h, int speed, int indexX, int indexY) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.speed = speed;
        this.indexX = indexX;
        this.indexY = indexY;

        //coordenadas centrales del player
        this.cx = x + w/2;
        this.cy = y + h/2;

        this.direction = Types.Directions.NONE;
        this.lastDirections = Types.Directions.DOWN;
    }

    public void draw( Graphics g, Image spriteSheet ) {
        this.spriteW = this.lastDirections == Types.Directions.UP ? 17 : 18;
        int spriteX = indexX * spriteW;
        int spriteY = indexY * spriteH;
        int x =  this.cx - spriteW*3/2;
        int y =  this.cy - spriteH*3/2;

        g.drawImage(spriteSheet, x, y, x + spriteW*3, y + spriteH*3, spriteX, spriteY, spriteX + spriteW, spriteY + spriteH, null);

        g.setColor(Color.ORANGE);
        g.fillRect(x, y, w, h);

    }

    public void setDirection( Types.Directions direction ) {
        this.direction = direction;
    }

    public void move( int dx, int dy ) {
        this.x += dx;
        this.y += dy;
    }

    public void update( ) {
        this.cx = x + w/2;
        this.cy = y + h/2;
        switch( this.direction ) {
            case Types.Directions.UP:
                this.indexY = 0;
                if( indexX >= 1 && indexX < 3 ) {
                    this.indexX++;
                } else {
                    this.indexX = 1;
                }
                move( 0, -speed );
                this.lastDirections = Types.Directions.UP;
                break;
        
            case Types.Directions.DOWN:
                this.indexX = 0;
                if( indexY >= 1 && indexY < 3 ) {
                    this.indexY++;
                } else {
                    this.indexY = 1;
                }
                move( 0, speed );
                this.lastDirections = Types.Directions.DOWN;
                break;

            case Types.Directions.LEFT:
                this.indexY = 3;
                if( indexX >= 1 && indexX < 3 ) {
                    this.indexX++;
                } else {
                    this.indexX = 1;
                }
                move( -speed, 0 );
                this.lastDirections = Types.Directions.LEFT;
                break;
                
            case Types.Directions.RIGHT:
                this.indexY = 1;
                if( indexX >= 1 && indexX < 3 ) {
                    this.indexX++;
                } else {
                    this.indexX = 1;
                }
                move( speed, 0 );
                this.lastDirections = Types.Directions.RIGHT;
                break;

            case Types.Directions.NONE:
                this.dx = 0;
                this.dy = 0;
                if( lastDirections == Types.Directions.DOWN ) {
                    this.indexX = 0;
                    this.indexY = 2;
                } else if( lastDirections == Types.Directions.UP ) {
                    this.indexX = 0;
                    this.indexY = 0;
                } else if( lastDirections == Types.Directions.LEFT ) {
                    this.indexX = 1;
                    this.indexY = 3;
                } else if( lastDirections == Types.Directions.RIGHT ) {
                    this.indexX = 1;
                    this.indexY = 1;
                }
                break;
        }
    }
}
