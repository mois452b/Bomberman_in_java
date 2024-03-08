package src;

import java.util.ArrayList;

import javax.swing.*;

import src.bomberman.entity.Wall;
import src.ultils.random;
import src.ultils.Types;



public class Scene {

    public int rows;
    public int cols;
    public Types.CellType[][] map;
    public Player player;
    public ArrayList<Bomb> bombs;
    public ArrayList<Bomb> bombsToKill = new ArrayList<Bomb>();

    public ArrayList<PowerUps> powerUps = new ArrayList<PowerUps>();
    public ArrayList<PowerUps> powerUpsToKill = new ArrayList<PowerUps>();

    public static Scene scene;

    public Scene(  ) {
        this.rows = 13;
        this.cols = 23;

        this.bombs = new ArrayList<Bomb>();
    }

    public static Scene getScene( ) {
        if( scene == null ) scene = new Scene( );
        return scene;
    }

    public void addBomb( Bomb bomb ) {
        bombs.add( bomb );
        map[bomb.x][bomb.y] = Types.CellType.BOMB;
    }

    public void removeBomb( Bomb bomb ) {
        bombs.remove( bomb );
        map[bomb.x][bomb.y] = Types.CellType.EMPTY;
    }

    public void destroyBlock( int x, int y ) {
        map[x][y] = Types.CellType.EMPTY;
        //hay una posibilidad del 50% de que suelte un powerup
        if( random.getRandomInt( 0, 100 ) <= 33 ) {
            powerUps.add( new PowerUps( x, y ) );
        }
    }

    public void update( long deltaTime ) {
        // System.out.println("updated");
        player.validateMovement( map );
        player.update( deltaTime );

        for( Bomb bomb : bombs ) {
            bomb.update( deltaTime );
        }
        for( Bomb bomb : bombsToKill ) {
            removeBomb( bomb );
        }
        bombsToKill = new ArrayList<Bomb>();        

        for( PowerUps powerUp : powerUps ) {
            powerUp.update( deltaTime );
        }
        for( PowerUps powerUp : powerUpsToKill ) {
            powerUps.remove( powerUp );
        }
        powerUpsToKill = new ArrayList<PowerUps>();
    }

    public void addNewPlayer( ) {
        player = new Player( 60, 60, 100/3, 100/3, 5, map );
    }

    public Types.CellType[][] generateMap() {
        Types.CellType[][] map = new Types.CellType[cols][rows];

        for( int y = 0; y < rows; y++ ) {
            for( int x = 0; x < cols; x++ ) {
                if( y == 0 || y == rows-1 || x == 0 || x == cols-1 || y % 2 == 0 && x % 2 == 0 ) {
                    map[x][y] = Types.CellType.WALL;
                }
                else if( random.getRandomInt( 0, 100 ) < 50 ) {
                    map[x][y] = Types.CellType.BREAKABLE;
                }
                else {
                    map[x][y] = Types.CellType.EMPTY;
                }
            }
        }

        //liberamos las posiciones donde el player puede aparecer
        map[1][1] = Types.CellType.EMPTY;
        map[1][2] = Types.CellType.EMPTY;
        map[2][1] = Types.CellType.EMPTY;
        map[cols-2][rows-2] = Types.CellType.EMPTY;
        map[cols-3][rows-2] = Types.CellType.EMPTY;
        map[cols-2][rows-3] = Types.CellType.EMPTY;
        map[1][rows-2] = Types.CellType.EMPTY;
        map[1][rows-3] = Types.CellType.EMPTY;
        map[2][rows-2] = Types.CellType.EMPTY;
        map[cols-2][1] = Types.CellType.EMPTY;
        map[cols-3][1] = Types.CellType.EMPTY;
        map[cols-2][2] = Types.CellType.EMPTY;

        this.map = map;

        return map;
    }

}
