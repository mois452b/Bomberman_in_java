package src;

import javax.swing.JFrame;
import javax.swing.JPanel;
import src.ultils.Types;

import java.awt.Color;
import java.awt.Graphics;

public class UI {
    private GamePanel gamePanel;
    private JFrame frame;
    Scene scene;

    
    public UI( ) {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setLocationRelativeTo(null);

        // keyboard = new Keyboard();
        // frame.addKeyListener(keyboard);

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
        for( int x = 0; x<scene.cols; x++ ) {
            for( int y = 0; y<scene.rows; y++ ) {
                Types.CellType cellType = baseMap[x][y];
                if( cellType == Types.CellType.WALL ) {
                    g.setColor(Color.BLACK);
                    g.fillRect(x*50, y*50, 50, 50);
                }
                else if( cellType == Types.CellType.BREAKABLE ) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x*50, y*50, 50, 50);
                }
            }
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
