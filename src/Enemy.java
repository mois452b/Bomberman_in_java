package src;

import java.awt.*;

import src.ultils.Types;
import src.ultils.random;

class Enemy {

    public int x;
    public int y;

    public int w = 40;
    public int h = 40;

    public int dx;
    public int dy;
    public int speed = random.getRandomInt( 1, 3 );

    public int indexX = 0;
    public int indexY = 0;
    public int spriteW = 96/6;
    public int spriteH = 16;

    public Types.CellType[][] map;

    public Enemy( int x, int y, int dx, int dy, Types.CellType[][] map ) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.map = map;
    }

    public int getLeft( ) {
        return x;
    }

    public int getRight( ) {
        return x + w;
    }

    public int getTop( ) {
        return y;
    }

    public int getBottom( ) {
        return y + h;
    }

    public void draw( Graphics g, Image spritesImage ) {
        g.drawImage( spritesImage, x, y, x+w, y+h, indexX*spriteW, 0, indexX*spriteW + spriteW, spriteH, null);
    }

    public void update( long deltaTime ) {
        animate();
        move( dx, dy );
    }

    public void animate( ) {
        if( indexX < 5 )
            indexX++;
        else 
            indexX = 0;
    }
    
    public boolean validateMovement( Types.CellType[][] map ) {
        for( int x = 0; x < map.length; x++ ) {
            for( int y = 0; y < map[x].length; y++ ) {
                if( map[x][y] == Types.CellType.BREAKABLE || map[x][y] == Types.CellType.WALL || map[x][y] == Types.CellType.BOMB ) { 
                    if( this.getRight() > x*50 && this.getLeft( ) < (x+1)*50 && this.getBottom( ) > y*50 && this.getTop( ) < (y+1)*50 ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void move( int dx, int dy ) {
        this.x += dx*speed;
        this.y += dy*speed;

        if( !validateMovement( map ) ) {
            this.x -= dx*speed;
            this.y -= dy*speed;

            this.dx *= -1;
            this.dy *= -1;
        }
    }
}