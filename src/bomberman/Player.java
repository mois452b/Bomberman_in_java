package src.bomberman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private Game game;
    private int x = 50;
    private int y = 50;
    private int speed = 5;

    public Player( Game game ) {
        // Constructor del jugador

        this.game = game;
    }


    public void update() {
        if (game.keyboard.isDown("LEFT_ARROW")) {
            move(-speed, 0);
        }
        if (game.keyboard.isDown("RIGHT_ARROW")) {
            move(speed, 0);
        }
        if (game.keyboard.isDown("UP_ARROW")) {
            move(0, -speed);
        }
        if (game.keyboard.isDown("DOWN_ARROW")) {
            move(0, speed);
        }
    }

    public void move(int dx, int dy) {
        if (!canMove(dx, dy)) { return; }
        x += dx;
        y += dy;
    }

    public void placeBomb() {
        // Método para colocar una bomba
    }

    private boolean canMove(int dx, int dy) {
        // Verifica si el jugador puede moverse en la dirección especificada
        // sin colisionar con bloques u otros objetos del juego
        return true; // Implementa la lógica de colisión aquí
    }

    protected void draw(Graphics g) { 
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 50);
    }


}
