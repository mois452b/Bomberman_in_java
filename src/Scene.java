package src;

import src.bomberman.entity.Wall;
import src.ultils.random;

enum CellType {
    EMPTY,
    WALL,
    BREAKABLE
}

public class Scene {
    public int rows;
    public int cols;

    public Scene() {
        this.rows = 15;
        this.cols = 17;



        generateMap();
    }

    private void generateMap() {
        CellType[][] map = new CellType[cols][rows];

        for( int y = 0; y < rows; y++ ) {
            for( int x = 0; x < cols; x++ ) {
                if( y == 0 || y == rows-1 || x == 0 || x == cols-1 || y % 2 == 0 && x % 2 == 0 ) {
                    map[x][y] = CellType.WALL;
                }
                else if( random.getRandomInt( 0, 100 ) < 50 ) {
                    map[x][y] = CellType.BREAKABLE;
                }
                else {
                    map[x][y] = CellType.EMPTY;
                }
            }
        }

        //liberamos las posiciones donde el player puede aparecer
        map[1][1] = CellType.EMPTY;
        map[1][2] = CellType.EMPTY;
        map[2][1] = CellType.EMPTY;
        map[cols-2][rows-2] = CellType.EMPTY;
        map[cols-3][rows-2] = CellType.EMPTY;
        map[cols-2][rows-3] = CellType.EMPTY;
        map[1][rows-2] = CellType.EMPTY;
        map[1][rows-3] = CellType.EMPTY;
        map[2][rows-2] = CellType.EMPTY;
        map[cols-2][1] = CellType.EMPTY;
        map[cols-3][1] = CellType.EMPTY;
        map[cols-2][2] = CellType.EMPTY;
    }

    

}
