package src.bomberman.entity;

import javax.swing.*;

import src.bomberman.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player {
    private Game game;
    private int x;
    private int y;
    private int w;
    private int h;
    private int speed = 5;

    public Player( Game game, int x, int y, int w, int h ) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }


    public void update() {
        if (game.keyboard.isDown("LEFT_ARROW")) {
            move(-speed, 0);
        }
        else if (game.keyboard.isDown("RIGHT_ARROW")) {
            move(speed, 0);
        }
        else if (game.keyboard.isDown("UP_ARROW")) {
            move(0, -speed);
        }
        else if (game.keyboard.isDown("DOWN_ARROW")) {
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

    public void draw(Graphics g) { 
        g.setColor(Color.RED);
        g.fillRect(x, y, w, h);
    }


}
