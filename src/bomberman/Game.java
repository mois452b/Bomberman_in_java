package src.bomberman;

import java.awt.Graphics;
import java.util.Random;
import java.util.ArrayList;

import javax.swing.*;

import src.bomberman.components.Keyboard;
import src.bomberman.entity.Block;
import src.bomberman.entity.Bomb;
import src.bomberman.entity.Player;
import src.bomberman.entity.Wall;

public class Game {
    private JFrame frame;
    private Timer timer;
    private GamePanel gamePanel; // Nuevo panel para el juego

    private ArrayList<Player> players;
    // private ArrayList<Monster> monsters;
    private ArrayList<Bomb> bombs;
    private ArrayList<Block> blocks;
    private ArrayList<Wall> walls;

    public Keyboard keyboard;

    public Game() {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        keyboard = new Keyboard();
        frame.addKeyListener(keyboard);

        gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.setVisible(true);
    }

    public void update( ) {
        for( Player player : players ) {
            player.update();
        }
    }

    public void start() {
        generateMap( );

        timer = new Timer(1000 / 10, e -> {
            update();

            gamePanel.repaint();
        });
        timer.start();
    }

    private void generateMap() {
        int tileSize = 50;
        int rows = 13;
        int cols = 23;

        walls = new ArrayList<>();
        blocks = new ArrayList<>();
        players = new ArrayList<>();

        // Colocar muros
        for( int y = 0; y < rows; y++ ) {
            for( int x = 0; x < cols; x++ ) {
                if( y == 0 || y == rows-1 || x == 0 || x == cols-1 || y % 2 == 0 && x % 2 == 0 ) {
                    walls.add( new Wall( x * tileSize, y * tileSize, tileSize, tileSize ) );
                }
            }
        }

        // Generar posiciones aleatorias para los bloques
        for( int x = 1; x < cols-1; x++ ) {
            for( int y = 1; y < rows-1; y++ ) {
                boolean cellIsFree = true;
                for( Wall wall : walls ) {
                    if( x == wall.x / tileSize && y == wall.y / tileSize ) {
                        cellIsFree = false;
                        break;
                    }
                }

                boolean canPlaceBlock = getRandomInt( 0, 100 ) < 30;
                if( cellIsFree && canPlaceBlock ) {
                    blocks.add( new Block( x*tileSize, y*tileSize, tileSize, tileSize ));
                }
            };
        }

        players.add( new Player( this, 50, 50, 50, 50 ) );
        
    }
    

    private int getRandomInt( int min, int max ) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    // Clase interna para el panel de juego
    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar los elementos del juego
            for (Wall wall : walls) {
                if (wall != null) {
                    wall.draw(g);
                }
            }
            for (Block block : blocks) {
                if (block != null) {
                    block.draw(g);
                }
            }
            // for (Monster monster : monsters) {
            //     monster.draw(g);
            // }

            for (Player player : players) {
                player.draw(g);
            }
        }
    }
}
