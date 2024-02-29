package src.bomberman;

import javax.swing.*;

public class Game {
    private JFrame frame;
    private Player player;

    public Game() {
        frame = new JFrame("Bomberman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        player = new Player();
        frame.add(player);

        frame.setVisible(true);
    }

    public void start() {
        // Aquí irá el bucle principal del juego
    }
}
