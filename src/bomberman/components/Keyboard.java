package src.bomberman.components;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class Keyboard implements KeyListener {
    private Map<Integer, Boolean> down;
    private Map<String, Integer> keys;

    public Keyboard() {
        down = new HashMap<>();
        keys = new HashMap<>();

        // Definir los códigos de tecla
        keys.put("ESCAPE", KeyEvent.VK_ESCAPE);
        keys.put("ENTER", KeyEvent.VK_ENTER);
        keys.put("SHIFT", KeyEvent.VK_SHIFT);
        keys.put("CTRL", KeyEvent.VK_CONTROL);
        keys.put("SPACE", KeyEvent.VK_SPACE);
        keys.put("LEFT_ARROW", KeyEvent.VK_LEFT);
        keys.put("UP_ARROW", KeyEvent.VK_UP);
        keys.put("RIGHT_ARROW", KeyEvent.VK_RIGHT);
        keys.put("DOWN_ARROW", KeyEvent.VK_DOWN);
        keys.put("A", KeyEvent.VK_A);
        keys.put("B", KeyEvent.VK_B);
        keys.put("C", KeyEvent.VK_C);
        keys.put("D", KeyEvent.VK_D);
        keys.put("E", KeyEvent.VK_E);
        keys.put("F", KeyEvent.VK_F);
        keys.put("G", KeyEvent.VK_G);
        keys.put("H", KeyEvent.VK_H);
        keys.put("I", KeyEvent.VK_I);
        keys.put("J", KeyEvent.VK_J);
        keys.put("K", KeyEvent.VK_K);
        keys.put("L", KeyEvent.VK_L);
        keys.put("M", KeyEvent.VK_M);
        keys.put("N", KeyEvent.VK_N);
        keys.put("O", KeyEvent.VK_O);
        keys.put("P", KeyEvent.VK_P);
        keys.put("Q", KeyEvent.VK_Q);
        keys.put("R", KeyEvent.VK_R);
        keys.put("S", KeyEvent.VK_S);
        keys.put("T", KeyEvent.VK_T);
        keys.put("U", KeyEvent.VK_U);
        keys.put("V", KeyEvent.VK_V);
        keys.put("W", KeyEvent.VK_W);
        keys.put("X", KeyEvent.VK_X);
        keys.put("Y", KeyEvent.VK_Y);
        keys.put("Z", KeyEvent.VK_Z);

        // Inicializar el mapa de teclas presionadas
        for (int key : keys.values()) {
            down.put(key, false);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No se usa en este ejemplo
    }

    @Override
    public void keyPressed(KeyEvent e) {
        down.put(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        down.put(e.getKeyCode(), false);
    }

    public boolean isDown(String keyName) {
        if (!keys.containsKey(keyName)) {
            throw new IllegalArgumentException("La tecla '" + keyName + "' no está definida");
        }
        return down.get(keys.get(keyName));
    }
}

