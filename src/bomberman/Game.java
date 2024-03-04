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

    public ArrayList<Player> players;
    // public ArrayList<Monster> monsters;
    public ArrayList<Bomb> bombs;
    public ArrayList<Block> blocks;
    public ArrayList<Wall> walls;

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
                final int xPos = x;
                final int yPos = y;
                boolean cellIsFree = walls.stream().noneMatch(wall -> xPos == wall.x / tileSize && yPos == wall.y / tileSize);

                boolean canPlaceBlock = getRandomInt( 0, 100 ) < 30;
                if( cellIsFree && canPlaceBlock ) {
                    blocks.add( new Block( this, x*tileSize, y*tileSize, tileSize, tileSize ));
                }
            };
        }

        players.add( new Player( this, 50, 50, 50, 50 ) );
        //destruimos los bloques que estan a al menos 2 bloques de distancia del player
        for (Block block : blocks) {
            for (Player player : players) {
                if (Math.abs(block.x - player.x) <= 2 * tileSize && Math.abs(block.y - player.y) <= 2 * tileSize) {
                    block.destroy();
                }
            }
        }
        
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
