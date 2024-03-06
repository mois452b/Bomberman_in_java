package src;

import javax.swing.*;

import src.bomberman.entity.Wall;
import src.ultils.random;
import src.ultils.Types;



public class Scene {

    public int rows;
    public int cols;
    public Types.CellType[][] map;
    public Player player;


    public Scene(  ) {
        this.rows = 13;
        this.cols = 23;
    }

    public void update() {
        // System.out.println("updated");
        player.update( );
    }

    public void addNewPlayer( ) {
        player = new Player( 50, 50, 100/3, 100/3, 5, 1, 1 );
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
