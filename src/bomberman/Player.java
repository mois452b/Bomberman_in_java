package src.bomberman;

import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    private int x = 50;
    private int y = 50;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50);
    }

    // Aquí puedes agregar métodos para mover al jugador
}
