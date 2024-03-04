package src.controller;

import src.Scene;
import src.UI;

import javax.swing.Timer;

public class Controller {
    Scene scene;
    UI ui;

    Timer timer;

    public Controller( ) {
        ui = new UI( );
        scene = new Scene( ui );
        start( );
    }

    public void start() {
        scene.generateMap( );

        ui.setScene( scene );


        this.timer = new Timer( 1000 / 10, e -> {
            scene.update( );
            ui.repaint( );
        });
        this.timer.start( );
    }
}
