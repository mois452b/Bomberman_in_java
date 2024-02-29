package src.bomberman;

import java.awt.Graphics;

import javax.swing.*;

public class Game {
    private JFrame frame;
    private Timer timer;
    private GamePanel gamePanel; // Nuevo panel para el juego

    private Player player;
    private Bomb[] bombs;
    private Block[] blocks;
    private Wall[] walls;

    public Keyboard keyboard;

    public Game() {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        keyboard = new Keyboard();
        frame.addKeyListener(keyboard);

        gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.setVisible(true);
    }

    public void start() {
        player = new Player(this);

        timer = new Timer(1000 / 10, e -> {
            player.update();

            gamePanel.repaint();
        });
        timer.start();
    }

    // Clase interna para el panel de juego
    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dibujar elementos del juego aquí, por ejemplo:
            player.draw(g);
            // for (Bomb bomb : bombs) {
            //     bomb.draw(g);
            // }
            // etc.
        }
    }
}
