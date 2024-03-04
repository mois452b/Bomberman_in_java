package src;

import src.controller.Controller;

public class Main {
    public static void main(String[] args) {
        System.out.println( "Hello World!" );
        Controller controller = new Controller();
        controller.start();
    }
}
