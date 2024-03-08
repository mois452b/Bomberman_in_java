package src;

import java.awt.*;
import src.ultils.Types;
import src.ultils.random;

public class PowerUps {
    public int x;
    public int y;

    public int indexX;
    public int indexY;
    public int spriteW;
    public int spriteH;

    public Types.PowerUpType type;
    
    public PowerUps( int x, int y ) {
        this.x = x;
        this.y = y;
        indexX = 0;
        indexY = 0;
        spriteW = 128 / 8;
        spriteH = 96 / 6;

        type = Types.PowerUpType.values()[ random.getRandomInt( 0, Types.PowerUpType.values().length - 1 ) ];
    }

    public void draw( Graphics g, Image image, Types.CellType[][] map ) {
        g.drawImage( image, x*50, y*50, x*50+50, y*50+50, indexX*spriteW, indexY*spriteH, indexX*spriteW + spriteW, indexY*spriteH + spriteH, null);
    }

    public void update( long deltaTime ) {
        animate( );
    }

    public void animate( ) {
        switch( type ) {
            case Types.PowerUpType.BOMB:
                indexX = 0;
                indexY++;
                if( indexY > 1 )
                    indexY = 0; 

                break;
            
            case Types.PowerUpType.PUSH_BOMB:
                indexX = 4;
                indexY++;
                if( indexY > 1 )
                    indexY = 0; 
            
                break;

            case Types.PowerUpType.CALAVERA:
                indexX = 5;
                indexY++;
                if( indexY > 1 )
                    indexY = 0; 
            
                break;

            case Types.PowerUpType.SPEED:
                indexX = 8;
                indexY++;
                if( indexY > 1 )
                    indexY = 0; 
            
                break;

            case Types.PowerUpType.LIFE:
                indexX = 9;
                indexY++;
                if( indexY > 1 )
                    indexY = 0; 
            
                break;
        }
    }
}
