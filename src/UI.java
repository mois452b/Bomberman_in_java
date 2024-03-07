package src;

import javax.imageio.ImageIO;
import javax.swing.*;

import src.bomberman.components.Keyboard;
import src.ultils.Types;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UI {
    private GamePanel gamePanel;
    private Scene scene;

    public JFrame frame;

    public Image wall;
    public Image block;
    public Image floor;
    public Image playerSpriteSheet;
    public Image bombSpriteSheet;

    public UI() {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.setVisible(true);

        try {
            wall = ImageIO.read(new File("resource/bmpSolido.png"));
            block = ImageIO.read(new File("resource/bmpDestruible.png"));
            floor = ImageIO.read(new File("resource/bmpSuelo.png"));
            playerSpriteSheet = ImageIO.read( new File("resource/Jugador.png") );
            bombSpriteSheet = ImageIO.read( new File("resource/bomba.png") );
            
        } catch( IOException e ) {
            e.printStackTrace();
        }
    }

    public void repaint( ) {
        this.gamePanel.repaint();
    }

    public void setScene( Scene scene ) {
        this.scene = scene;
    }

    public void drawBase( Graphics g, Types.CellType[][] baseMap ) {
        for( int x = 0; x < scene.cols; x++ ) {
            for( int y = 0; y < scene.rows; y++ ) {
                Types.CellType cellType = baseMap[x][y];
                if( cellType == Types.CellType.WALL ) {
                    drawImage( g, wall, x * 50, y * 50 );
                }
                else if( cellType == Types.CellType.BREAKABLE ) {
                    drawImage( g, block, x * 50, y * 50 );
                }
                else {
                    drawImage( g, floor, x * 50, y * 50 );
                }
            }
        }
    }

    public void drawImage(Graphics g, Image image, int x, int y) {
        g.drawImage(image, x, y, 50, 50, null);
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if( scene == null ) return;
            drawBase( g, scene.map );
            scene.player.draw( g, playerSpriteSheet );

            for( Bomb bomb : scene.bombs ) {
                // drawImage(g, bombSpriteSheet, bomb.x*50, bomb.y*50);
                bomb.draw( g, bombSpriteSheet );
            }
                
        }
    }
}
