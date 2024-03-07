package src;

import java.awt.*;

import src.ultils.Types;

public class Bomb {
    public int x;
    public int y;
    public int w;
    public int h;

    
    private int spriteW = 66/3;
    private int spriteH = 24;
    
    private int indexX;
    private int timeBeforeExplode;
    private long createdAt;

    private Types.BombStates state;

    //Datos de la explosion
    private int indexEX;
    private int indexEY;

    private int spriteEW;
    private int spriteEY;
    private Player player;


    public Bomb( int x, int y, Player player ) {
        this.x = x;
        this.y = y;
        this.w = 50;
        this.h = 50;

        this.player = player;

        this.createdAt = System.currentTimeMillis();

        this.state = Types.BombStates.NORMAL;
        this.indexX = 0;
        this.timeBeforeExplode = 5000;
    }

    public void draw( Graphics g, Image image ) {
        g.drawImage( image, x*50, y*50, x*50+50, y*50+50, indexX*spriteW, 0, indexX*spriteW + spriteW, spriteH, null);
       
    }

    public boolean validarPosition( int x, int y, Types.CellType[][] map ) {
        return map[x][y] == Types.CellType.EMPTY;
    }

    public void update( long deltaTime ) {
        animate( );
        timeBeforeExplode -= deltaTime;
        if( timeBeforeExplode <= 0 ) {
            // state = Types.BombStates.EXPLODING;
            explode( );
        }
    }

    public void explode( ) {
        Scene scene = Scene.getScene( );
        player.bombs.remove( this );
        scene.bombsToKill.add( this );
    }

    public void animate( ) {
        if( indexX < 2 )
            indexX++;
        else {
            timeBeforeExplode++;
            indexX = 0;
        }

    }
}
