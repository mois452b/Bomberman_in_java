package src;

import javax.imageio.ImageIO;
import javax.swing.*;

import src.ultils.Types;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class UI {
    private GamePanel gamePanel;
    private JFrame frame;
    private Scene scene;

    public UI() {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.setVisible(true);
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
                    drawImage( g, "bmpSolido.png", x * 50, y * 50 );
                }
                else if( cellType == Types.CellType.BREAKABLE ) {
                    drawImage( g, "bmpDestruible.png", x * 50, y * 50 );
                }
                else if( cellType == Types.CellType.EMPTY ) {
                    drawImage( g, "bmpSuelo.png", x * 50, y * 50 );
                }
            }
        }
    }

    private void drawImage(Graphics g, String filename, int x, int y) {
        try {
            Image image = ImageIO.read(new File("resource/" + filename));
            g.drawImage(image, x, y, 50, 50, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            drawBase( g, scene.map );
        }
    }
}
