package src;

import java.awt.*;
import java.util.ArrayList;

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
    private Types.CellType[][] map;

    private ArrayList<Bomb> bombs;
    private int maxBombs = 1;

    public Player(int x, int y, int w, int h, int speed, Types.CellType[][] map ) {
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

        this.map = map;
        this.bombs = new ArrayList<Bomb>();
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

    public boolean validateMovement( Types.CellType[][] map ) {
        for( int x = 0; x < map.length; x++ ) {
            for( int y = 0; y < map[x].length; y++ ) {
                if( map[x][y] == Types.CellType.BREAKABLE || map[x][y] == Types.CellType.WALL ) {
                    if( this.direction == Types.Directions.UP )
                        dy = -speed;
                    else if( this.direction == Types.Directions.DOWN )
                        dy = speed;
                    else if( this.direction == Types.Directions.LEFT )
                        dx = -speed;
                    else if( this.direction == Types.Directions.RIGHT )
                        dx = speed;
                    
                    if( this.getRight() > x*50 && this.getLeft( ) < (x+1)*50 && this.getBottom( ) > y*50 && this.getTop( ) < (y+1)*50 ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void draw( Graphics g, Image spriteSheet ) {
        this.spriteW = this.lastDirections == Types.Directions.UP ? 17 : 18;
        int spriteX = indexX * spriteW;
        int spriteY = indexY * spriteH;
        int x =  this.cx - spriteW*3/2;
        int y =  this.cy - spriteH*2;

        g.drawImage(spriteSheet, x, y, x + spriteW*3, y + spriteH*3, spriteX, spriteY, spriteX + spriteW, spriteY + spriteH, null);

        g.setColor(Color.ORANGE);
        g.fillRect(cx-w/2, cy-h/2, w, h);

    }

    public void setDirection( Types.Directions direction ) {
        this.direction = direction;
    }

    public void placeBomb( ) {
        Scene scene = Scene.getScene();
        int x = (int)Math.floor( this.cx / 50.0 );
        int y = (int)Math.floor( this.cy / 50.0 );

        if( this.bombs.size() < maxBombs && scene.map[x][y] == Types.CellType.EMPTY ) {
            System.out.println("added bomb");
            Bomb bomb = new Bomb( x, y );
            this.bombs.add( bomb );
            scene.addBomb( bomb );
        }
    }

    public void move( int dx, int dy ) {
        this.x += dx;
        this.y += dy;

        if( !validateMovement( map ) ) {
            this.x -= dx;
            this.y -= dy;
        }
    }

    public void update( long deltaTime ) {
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
        // this.x += dx;
        // this.y += dy;
    }
}
