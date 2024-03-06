package src.controller;

import src.Scene;
import src.UI;
import src.bomberman.components.Keyboard;
import src.ultils.Types;

import javax.swing.Timer;

public class Controller {
    Scene scene;
    UI ui;

    Timer timer;

    Keyboard keyboard;
    public int tStart = 0;
    public int tEnd = 0;

    public Controller( ) {
        ui = new UI( );
        scene = new Scene( );

        keyboard = new Keyboard();
        ui.frame.addKeyListener(keyboard);
    }

    public void start() {
        scene.generateMap( );
        scene.addNewPlayer( );

        ui.setScene( scene );

        this.timer = new Timer( 1000/10 , e -> {
            if( tStart != tEnd ) return;
            tStart++;
            System.out.println(tStart);
            if (keyboard.isDown("LEFT_ARROW")) {
                scene.player.setDirection( Types.Directions.LEFT );
            } else if (keyboard.isDown("RIGHT_ARROW")) {
                scene.player.setDirection( Types.Directions.RIGHT );
            } else if (keyboard.isDown("UP_ARROW")) {
                scene.player.setDirection( Types.Directions.UP );
            } else if (keyboard.isDown("DOWN_ARROW")) {
                scene.player.setDirection( Types.Directions.DOWN );
            } else {
                scene.player.setDirection( Types.Directions.NONE );
            }

            scene.update( );
            ui.repaint( );
            tEnd = tStart;
        });
        this.timer.start( );
    }
}
