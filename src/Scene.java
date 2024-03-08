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

    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public ArrayList<Enemy> enemiesToKill = new ArrayList<Enemy>();

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

    public void addPowerUp( PowerUps powerUp ) {
        powerUps.add( powerUp );
        map[powerUp.x][powerUp.y] = Types.CellType.POWERUP;
    }

    public void removePowerUp( PowerUps powerUp ) {
        powerUps.remove( powerUp );
        map[powerUp.x][powerUp.y] = Types.CellType.EMPTY;
    }

    public void addEnemy( Enemy enemy ) {
        enemies.add( enemy );
    }

    public void removeEnemy( Enemy enemy ) {
        enemies.remove( enemy );
    }

    public void destroyBlock( int x, int y ) {
        map[x][y] = Types.CellType.EMPTY;
        //hay una posibilidad del 50% de que suelte un powerup
        if( random.getRandomInt( 0, 100 ) <= 25 ) {
            addPowerUp( new PowerUps( x, y ) );
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
            removePowerUp( powerUp );
        }
        powerUpsToKill = new ArrayList<PowerUps>();

        for( Enemy enemy : enemies ) {
            enemy.update( deltaTime );
        }
        for( Enemy enemy : enemiesToKill ) {
            removeEnemy( enemy );
        }
        enemiesToKill = new ArrayList<Enemy>();
    }

    public void addNewPlayer( ) {
        player = new Player( 60, 60, 100/3, 100/3, 5, map );
    }

    public void createEnemies( ) {
        for( int y = 1; y < rows-1; y++ ) {
            System.out.println(y%2);
            for( int x = 1; x < cols-1; x++ ) {
                if( map[x][y] == Types.CellType.EMPTY ) {
                    int dx = 0;
                    int dy = 0;
                    if( y % 2 == 1 )
                        dx = 1;
                    else 
                        dy = 1;

                    if( random.getRandomInt( 0, 100 ) < 10 && random.distance( x*50+5, y*50+5, player.x, player.y ) > 100 ) {
                        Enemy enemy = new Enemy( x*50+5, y*50+5, dx, dy, map );
                        addEnemy( enemy );
                    }
                }
            }
        }
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
